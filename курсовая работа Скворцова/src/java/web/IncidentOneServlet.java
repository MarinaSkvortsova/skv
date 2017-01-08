package web;

import dao.IncidentDAO;
import model.Incident;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "IncidentOneServlet", urlPatterns = {"/IncidentOneServlet"})
public class IncidentOneServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IncidentDAO incidentDAO = (IncidentDAO) this.getServletContext().getAttribute("incidentDAO");
        if (request.getParameter("codeIncident") == null) {
            response.sendError(404);
            return;
        }
        int codeIncident = Integer.parseInt(request.getParameter("codeIncident"));
        Incident i = incidentDAO.get(codeIncident);
        if (i == null) {
            response.sendError(404);
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(String.format("<html><head><title>Информация о инциденте %s</title></head>", i.getCodeIncident()));
        out.print("<body>");
        out.print("<h1>" + i.getStation().getNameStation() + "</h1><h4>"
                + i.getDescription() + "</h1><h4>" + i.getCodeStation() + "</h1><h4>"
                + i.getDetectionDate() + "</h4>" + "</h1><h4>"
                + i.getClosingPeriod() + "</h1><h4>"
                + i.getClosingDate() + "</h1><h4>"
                + i.getEmployeeFound().getFio() + "</h1><h4>"
                + i.getEmployeeClose().getFio());

        out.print("</body>");
        out.print("</html");
    }
}
