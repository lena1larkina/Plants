package servlets.plants;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RarePlantsService;
import repository.RarePlantsRepository;
import servlets.URLUtils;

import java.io.IOException;

@WebServlet("/plants/delete")
public class RarePlantsDeleteServlet extends HttpServlet {
    private final RarePlantsService rarePlantsService = new RarePlantsService(new RarePlantsRepository());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        rarePlantsService.deleteRarePlant(Integer.parseInt(idParam));
        resp.sendRedirect(URLUtils.getFullURLForRedirect(req,"/plants"));
    }
}
