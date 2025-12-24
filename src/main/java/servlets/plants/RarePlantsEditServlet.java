package servlets.plants;


import entities.RarePlant;
import exceptions.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repository.RarePlantsRepository;
import service.RarePlantsService;
import servlets.URLUtils;
import validators.NameValidator;
import validators.RarityScoreValidator;

import java.io.IOException;

@WebServlet("/plants/edit")
public class RarePlantsEditServlet extends HttpServlet {
    private final RarePlantsService rarePlantsService = new RarePlantsService(new RarePlantsRepository());
    NameValidator nameValidator = new NameValidator();
    RarityScoreValidator rarityScoreValidator = new RarityScoreValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            resp.sendRedirect(URLUtils.getFullURLForRedirect(req, "/plants"));
            return;
        }
        int id = Integer.parseInt(idParam);

        RarePlant rarePlant = rarePlantsService.getRarePlantById(id);
            if (rarePlant == null) {
                req.setAttribute("message", "404 Not found");
                req.getRequestDispatcher("/WEB-INF/plants/error.jsp").forward(req, resp);
            }
            req.setAttribute("plant", rarePlant);
            req.getSession().setAttribute("plant", rarePlant);
            req.getRequestDispatcher("/WEB-INF/plants/form.jsp").forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String name = req.getParameter("name");
        String scoreParam = req.getParameter("score");
        HttpSession session = req.getSession();
        try {
            nameValidator.validateNameLength(name);
            rarityScoreValidator.validateScoreNumber(scoreParam);
            rarityScoreValidator.validateScorePositive(scoreParam);
        } catch (ValidationException e) {
            req.setAttribute("message", e.getMessage());
            req.setAttribute("plant", session.getAttribute("plant"));
            req.getRequestDispatcher("/WEB-INF/plants/form.jsp").forward(req, resp);
            return;
        }
        Integer userId = (Integer) (session.getAttribute("userId"));
        RarePlant rarePlant = new RarePlant(
                Integer.parseInt(idParam),
                name,
                Integer.parseInt(scoreParam),
                userId);
        rarePlantsService.updateRarePlants(rarePlant);
        session.removeAttribute("plant");
        resp.sendRedirect(URLUtils.getFullURLForRedirect(req, "/plants"));
    }
}
