package web;

import dao.IncidentDAO;
import dao.CexDAO;
import dao.EmployeeDAO;
import dao.StationsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteToId", urlPatterns = {"/DeleteToId"})
public class DeleteToId extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        switch (request.getParameter("nameTable")) {
            case "incident":
                IncidentDAO incidentDAO = (IncidentDAO) this.getServletContext().getAttribute("incidentDAO");
               incidentDAO.delete(Integer.parseInt(request.getParameter("deletCode")));
                out.println("Запись из таблицы incident удалена");
                break;
            case "cex":
                CexDAO cexDAO = (CexDAO) this.getServletContext().getAttribute("cexDAO");
                cexDAO.delete(Integer.parseInt(request.getParameter("deletCode")));
                out.println("Запись из таблицы cex удалена");
                break;
                case "employee":
                EmployeeDAO employeeDAO = (EmployeeDAO) this.getServletContext().getAttribute("employeeDAO");
                employeeDAO.delete(Integer.parseInt(request.getParameter("deletCode")));
                out.println("Запись из таблицы employee удалена");
                break;
                
                case "stations":
                StationsDAO stationsDAO = (StationsDAO) this.getServletContext().getAttribute("stationsDAO");
                stationsDAO.delete(Integer.parseInt(request.getParameter("deletCode")));
                out.println("Запись из таблицы stations удалена");
                break;
                
            default:
                out.println("Вы пытаетесь удалить не существующую запись");
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteToId.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
