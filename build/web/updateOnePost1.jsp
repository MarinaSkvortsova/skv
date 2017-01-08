<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактирование таблицы Cex</title>
    </head>
    <body>
  
        <div class="container">
            <div class="row">
                <div class="span12">
                    <br><br><br><br><br><br>
                    <center><h3>Обновление записи для таблицы Cex</h3></center>
                        <c:set var="elem" value="${cexObj}"></c:set>
                        <form action="upOnePost" method="GET">
                            <table border="0">
                                <tr><th>Код цеха</th><td><label>${elem.codeCex}</label><input type="hidden" name="codeCex" value="${elem.codeCex}"></td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>Название цеха</th><td><input type="text" class="form-control" name="nameCex" value="${elem.nameCex}"></td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>Количество работников</th><td><input type="text" class="form-control" name="countWorker" value="${elem.countWorker}"></td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th><input type="hidden" name="nameTable" value="${nameTable}"></th><td><input type="submit" class="btn btn-primary" name="update" value="Сохранить"></td></tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
