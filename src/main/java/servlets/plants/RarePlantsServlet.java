package servlets.plants;

import entities.RarePlant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import repository.RarePlantsRepository;
import service.RarePlantsService;

import java.io.IOException;
import java.util.List;

@WebServlet("/plants")
public class RarePlantsServlet extends HttpServlet {
    private final RarePlantsService rarePlantsService = new RarePlantsService(new RarePlantsRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Integer userId = (Integer) req.getSession().getAttribute("userId");
        List<RarePlant> rarePlants;
        if (name == null || name.isBlank()) {
            rarePlants = rarePlantsService.getRarePlantsByProfileId(userId);
        }
        else {
            rarePlants = rarePlantsService.getPlantsByProfileIdAndName(userId, name);
        }
        req.setAttribute("plants", rarePlants);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/plants/mainpage.jsp");
        requestDispatcher.forward(req, resp);
    }
}
