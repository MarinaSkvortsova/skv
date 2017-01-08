package web;

import dao.CexDAO;
import dao.EmployeeDAO;
import dao.IncidentDAO;
import dao.StationsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cex;
import model.Employee;
import model.Incident;
import model.Stations;

@WebServlet(name = "globalPage", urlPatterns = {"/globalPage"})
public class globalPage extends HttpServlet {

    StationsDAO stationsDAO = null;
    EmployeeDAO employeeDAO = null;
    CexDAO cexDAO = null;

    @Override
    public void init() {
        cexDAO = (CexDAO) this.getServletContext().getAttribute("cexDAO");
        stationsDAO = (StationsDAO) this.getServletContext().getAttribute("stationsDAO");
        employeeDAO = (EmployeeDAO) this.getServletContext().getAttribute("employeeDAO");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Stations> listStations = stationsDAO.list();
        request.setAttribute("listStations", listStations);

        List<Employee> listEmployee = employeeDAO.list();
        request.setAttribute("listEmployee", listEmployee);
        
        
        List<Cex> listCex = cexDAO.list();
        request.setAttribute("listCex", listCex);
        
        EmployeeDAO empDAO = (EmployeeDAO) this.getServletContext().getAttribute("employeeDAO");
        int lastId = empDAO.getLastId();
        request.setAttribute("lastId", lastId);
        

        
        this.getServletContext().getRequestDispatcher("/globalPage.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
