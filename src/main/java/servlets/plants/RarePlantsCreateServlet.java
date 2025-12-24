package servlets.plants;

import entities.RarePlant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.RarePlantsRepository;
import service.RarePlantsService;
import exceptions.ValidationException;
import servlets.URLUtils;
import validators.NameValidator;
import validators.RarityScoreValidator;

import java.io.IOException;

@WebServlet("/plants/create")
public class RarePlantsCreateServlet extends HttpServlet {
    final private RarePlantsService rarePlantsService = new RarePlantsService(new RarePlantsRepository());
    NameValidator nameValidator = new NameValidator();
    RarityScoreValidator rarityScoreValidator = new RarityScoreValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/plants/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String scoreParam = req.getParameter("score");
        try {
            nameValidator.validateNameLength(name);
            rarityScoreValidator.validateScoreNumber(scoreParam);
            rarityScoreValidator.validateScorePositive(scoreParam);
        } catch (ValidationException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/plants/form.jsp").forward(req, resp);
            return;
        }
        Integer userId = (Integer) (req.getSession().getAttribute("userId"));
        RarePlant rarePlant = new RarePlant(
                name,
                Integer.parseInt(scoreParam),
                userId);
        rarePlantsService.createRarePlants(rarePlant);
        resp.sendRedirect(URLUtils.getFullURLForRedirect(req, "/plants"));
    }
}