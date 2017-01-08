package web;

import dao.StationsDAO;
import model.Stations;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "StationsList", urlPatterns = {"/StationsList"})
public class StationsList extends HttpServlet {

    StationsDAO stationsDAO = null;

    @Override
    public void init() {
        stationsDAO = (StationsDAO) this.getServletContext().getAttribute("stationsDAO");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Stations> listStations = stationsDAO.list();
        request.setAttribute("listStations", listStations);
        this.getServletContext().getRequestDispatcher("/stations.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
