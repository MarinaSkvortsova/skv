package web;

import dao.IncidentDAO;
import dao.CexDAO;
import dao.EmployeeDAO;
import dao.StationsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cex;
import model.Incident;
import model.Employee;
import model.Stations;

@WebServlet(name = "updateOnePost", urlPatterns = {"/updateOnePost"})
public class updateOnePost extends HttpServlet {

    IncidentDAO incidentDAO;
    CexDAO cexDAO;
    EmployeeDAO employeeDAO;
    StationsDAO stationsDAO;
    
    @Override
    public void init() throws ServletException {
        incidentDAO = (IncidentDAO) this.getServletContext().getAttribute("incidentDAO");
        cexDAO = (CexDAO) this.getServletContext().getAttribute("cexDAO");
        employeeDAO = (EmployeeDAO) this.getServletContext().getAttribute("employeeDAO");
        stationsDAO = (StationsDAO) this.getServletContext().getAttribute("stationsDAO");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            switch (request.getParameter("nameTable")) {
                case "incident":
                    Incident incidentObj = incidentDAO.get(Integer.parseInt(request.getParameter("codeIncident")));
                    request.setAttribute("incidentObj", incidentObj);
                    request.setAttribute("nameTable", request.getParameter("nameTable"));
                    this.getServletContext().getRequestDispatcher("/updateOnePost.jsp").forward(request, response);
                    break;
                case "cex":
                    Cex cexObj = cexDAO.get(Integer.parseInt(request.getParameter("codeCex")));
                    request.setAttribute("cexObj", cexObj);
                    request.setAttribute("nameTable", request.getParameter("nameTable"));
                    this.getServletContext().getRequestDispatcher("/updateOnePost1.jsp").forward(request, response);
                    break;
                    
                case "employee":
                    Employee employeeObj = employeeDAO.get(Integer.parseInt(request.getParameter("codeEmployee")));
                    request.setAttribute("employeeObj", employeeObj);
                    request.setAttribute("nameTable", request.getParameter("nameTable"));
                    this.getServletContext().getRequestDispatcher("/updateOnePost2.jsp").forward(request, response);
                    break;
                    
                case "stations":
                    Stations stationsObj = stationsDAO.get(Integer.parseInt(request.getParameter("codeStation")));
                    request.setAttribute("stationsObj", stationsObj);
                    request.setAttribute("nameTable", request.getParameter("nameTable"));
                    this.getServletContext().getRequestDispatcher("/updateOnePost3.jsp").forward(request, response);
                    break;
                default:
                    out.println("Вы пытаетесь отредактировать не существующую запись");
                    break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
