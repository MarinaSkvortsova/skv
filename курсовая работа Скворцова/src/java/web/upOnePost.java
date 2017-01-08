package web;

import dao.IncidentDAO;
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
import model.Incident;
import model.Cex;
import dao.CexDAO;
import model.Employee;
import dao.EmployeeDAO;
import dao.StationsDAO;
import model.Stations;

@WebServlet(name = "upOnePost", urlPatterns = {"/upOnePost"})
public class upOnePost extends HttpServlet {

    IncidentDAO incidentDAO;
    Incident incidentObj;

    CexDAO cexDAO;
    Cex cexObj;

    EmployeeDAO employeeDAO;
    Employee employeeObj;

    StationsDAO stationsDAO;
    Stations stationsObj;

    @Override
    public void init() throws ServletException {
        incidentDAO = (IncidentDAO) this.getServletContext().getAttribute("incidentDAO");
        incidentObj = new Incident();

        cexDAO = (CexDAO) this.getServletContext().getAttribute("cexDAO");
        cexObj = new Cex();

        employeeDAO = (EmployeeDAO) this.getServletContext().getAttribute("employeeDAO");
        employeeObj = new Employee();

        stationsDAO = (StationsDAO) this.getServletContext().getAttribute("stationsDAO");
        stationsObj = new Stations();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            switch (request.getParameter("nameTable")) {
                case "incident":
                    incidentObj.setCodeIncident(Integer.parseInt(request.getParameter("codeIncident")));
                    incidentObj.setDescription((request.getParameter("description")));
                    incidentObj.setCodeStation(Integer.parseInt(request.getParameter("codeStation")));
                    incidentObj.setDetectionDate(request.getParameter("detectionDate"));
                    incidentObj.setClosingPeriod(request.getParameter("closingPeriod"));
                    incidentObj.setClosingDate(request.getParameter("closingDate"));
                    incidentObj.setFoundEmployee(Integer.parseInt(request.getParameter("foundEmployee")));
                    incidentObj.setCloseEmployee(Integer.parseInt(request.getParameter("closeEmployee")));
                    if (incidentDAO.update(incidentObj) == 1) {
                        out.println("Запись обновлена");
                    } else {
                        out.println("Ошибка при обновлении");
                    }
                    break;

                case "cex":
                    cexObj.setCodeCex(Integer.parseInt(request.getParameter("codeCex")));
                    cexObj.setNameCex(request.getParameter("nameCex"));
                    cexObj.setCountWorker(Integer.parseInt(request.getParameter("countWorker")));
                    if (cexDAO.update(cexObj) == 1) {
                        out.println("Запись обновлена");
                    } else {
                        out.println("Ошибка при обновлении");
                    }
                    break;

                case "employee":
                    employeeObj.setCodeEmployee(Integer.parseInt(request.getParameter("codeEmployee")));
                    employeeObj.setFio(request.getParameter("fio"));
                    employeeObj.setCodeCex(Integer.parseInt(request.getParameter("codeCex")));
                    employeeObj.setPosition(request.getParameter("position"));
                    employeeObj.setEmploymentDate(request.getParameter("employmentDate"));
                    if (employeeDAO.update(employeeObj) == 1) {
                        out.println("Запись обновлена");
                    } else {
                        out.println("Ошибка при обновлении");
                    }
                    break;

                case "stations":
                    stationsObj.setCodeStation(Integer.parseInt(request.getParameter("codeStation")));
                    stationsObj.setNameStation(request.getParameter("nameStation"));
                    stationsObj.setCodeCex(Integer.parseInt(request.getParameter("codeCex")));
                    stationsObj.setOlder(request.getParameter("older"));

                    if (stationsDAO.update(stationsObj) == 1) {
                        out.println("Запись обновлена");
                    } else {
                        out.println("Ошибка при обновлении");
                    }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(upOnePost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
