<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.CexDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактирование таблицы Employee</title>
    </head>
    <body>
    <center><h3>Обновление записи для таблицы Employee</h3></center>
        <c:set var="elem" value="${employeeObj}"></c:set>
        <form action="upOnePost" method="GET">
            <table border="0">
                  <tr><th>Код сотрудника</th><td><label>${elem.codeEmployee}</label><input type="hidden" name="codeEmployee" value="${elem.codeEmployee}"></td></tr>
            
            <tr><th>ФИО</th><td><input type="text" name="fio" value="${elem.fio}"></td></tr>
             <tr><th>Цех</th><td>
                                    <select name="codeCex" class="form-control">
                                        <c:set var="codeCex" value="${elem.codeCex}"/>
                                        <%
                                            CexDAO cexDAO = (CexDAO) this.getServletContext().getAttribute("cexDAO");
                                            String strCodeCex = (pageContext.getAttribute("codeCex", PageContext.PAGE_SCOPE)).toString();
                                            int codeCex = Integer.parseInt(strCodeCex);
                                            List<String> massCodeCex = new ArrayList<String>();
                                            massCodeCex = cexDAO.getNameCexUpdate(codeCex);
                                            for (int i = 0; i < massCodeCex.size(); ++i) {
                                                pageContext.getOut().println("<option value=" + massCodeCex.get(i) + ">" + massCodeCex.get(++i) + "</option>");
                                            }
                                        %>
                                    </select>
                                </td></tr>
            <tr><th>Должность</th><td><input type="text" name="position" value="${elem.position}"></td></tr>
            <tr><th>Дата приема на работу</th><td><input type="text" name="employmentDate" value="${elem.employmentDate}"></td></tr>
            <tr><th><input type="hidden" name="nameTable" value="${nameTable}"></th><td><input type="submit" name="update" value="Сохранить"></td></tr>
        </table>
    </form>
</body>
</html>
