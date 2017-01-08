<%@page import="dao.CexDAO"%>
<%@page import="dao.IncidentDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main</title>
    </head>
    <body>
        
        <div class="container">
            <div class="row">
                <div class="span12">
                    <br><br><br><br><br><br>
                    <form method="get" action="createEmployee">
                        <input type="hidden" name="lastID" value="<% pageContext.getOut().print(request.getAttribute("lastId"));%>">
                        <table>
                            <tr><th>ФИО:</th><td><input type="text" class="form-control" name="fio" required="required"></td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>Код станции:</th>
                                <td>
                                    <select name="codecex" class="form-control">
                                        <c:forEach var="elem" items="${listCex}">
                                            <option value="${elem.codeCex}">${elem.nameCex}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>Должность:</th><td><input type="text" class="form-control" name="position" required="required"></td></tr>
                            <tr><td><br></td><td><br></td></tr>
                             <tr><th>Дата приема на работу:</th><td><input type="text" class="form-control" name="employmentdate" required="required"></td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            
          
                            <tr><th>&nbsp;</th><td><input type="submit" name="createEmployee" class="btn btn-primary"></td></tr>
                        </table>
                 
                    </form>
  <a href="IncidentList">Инциденты</a>
 <a href="StationsList">Станции</a>
  <a href="EmployeeList">Сотрудники</a>
   <a href="CexList">Цеха</a>
                </div>
            </div>
        </div>       
    </body>    
</html>
