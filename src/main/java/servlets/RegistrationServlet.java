package servlets;

import entities.Profile;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ProfileService;
import service.PasswordService;
import repository.ProfileRepository;
import exceptions.ValidationException;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final ProfileService profileService = new ProfileService(new ProfileRepository(), new PasswordService());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/registration.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            Profile newProfile = profileService.createProfile(login, password);
            HttpSession session = req.getSession();
            session.setAttribute("userId", newProfile.getIdProfile());
            resp.sendRedirect(servlets.URLUtils.getFullURLForRedirect(req, "/plants"));
        } catch (ValidationException e) {
            req.setAttribute("message", "Пользователь с таким логином уже существует!");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/registration.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
