
package web;

import dao.CexDAO;
import model.Cex;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CexList", urlPatterns = {"/CexList"})
public class CexList extends HttpServlet {

    CexDAO cexDAO = null;

    @Override
    public void init() {
         cexDAO = (CexDAO) this.getServletContext().getAttribute("cexDAO");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Cex> listCex = cexDAO.list();
        request.setAttribute("listCex", listCex);
        this.getServletContext().getRequestDispatcher("/cex.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
