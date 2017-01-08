package web;

import dao.IncidentDAO;
import model.Incident;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "IncidentServlet", urlPatterns = {"/Incident"})
public class IncidentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IncidentDAO incidentDAO = (IncidentDAO) this.getServletContext().getAttribute("incidentDAO");

        List<Incident> incident = incidentDAO.list();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<html><head><title>Список инцидентов</title></head>");
        out.print("<body>");
        out.print("<table>");

        for (Incident inc : incident) {
            out.print("<tr>");
            out.print("<td>");
            out.print(inc.getDescription());
            out.print("<td>");
            out.print("<tr>");
        }

        out.print("</table>");
        out.print("</body>");
        out.print("</html>");
    }
}
