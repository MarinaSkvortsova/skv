package web;

import dao.IncidentDAO;
import model.Incident;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "IncidentList", urlPatterns = {"/IncidentList"})
public class IncidentList extends HttpServlet {

    IncidentDAO incidentDAO = null;

    @Override
    public void init() {
        incidentDAO = (IncidentDAO) this.getServletContext().getAttribute("incidentDAO");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Incident> listIncident = incidentDAO.list();
        request.setAttribute("listIncident", listIncident);
        this.getServletContext().getRequestDispatcher("/incident.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
