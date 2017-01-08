package web;

import dao.EmployeeDAO;
import model.Employee;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EmployeeList", urlPatterns = {"/EmployeeList"})
public class EmployeeList extends HttpServlet {

    EmployeeDAO employeeDAO = null;

    @Override
    public void init() {
        employeeDAO = (EmployeeDAO) this.getServletContext().getAttribute("employeeDAO");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Employee> listEmployee = employeeDAO.list();
        request.setAttribute("listEmployee", listEmployee);
        this.getServletContext().getRequestDispatcher("/employee.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
