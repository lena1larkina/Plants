package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repository.ProfileRepository;
import service.PasswordService;
import service.ProfileService;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final ProfileService profileService;

    public LoginServlet() {
        this.profileService = new ProfileService(new ProfileRepository(), new PasswordService());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Integer userId = profileService.login(login, password);
        if (userId != null) {
            HttpSession session = req.getSession();
            session.setAttribute("userId", userId);
            String redirect = servlets.URLUtils.getFullURLForRedirect(req, "/plants");
            resp.sendRedirect(redirect);
            return;
        }
        req.setAttribute("error", "Неверный логин или пароль. Зарегистрируйтесь!");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
        dispatcher.forward(req, resp);
    }
}
