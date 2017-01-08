<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
    <h3>Таблица цехов</h3>
</head>
<body>
    <table border="1">
            <tr>
                    <th>Код цеха</th>
                    <th>Название цеха</th>
                    <th>Количество сотрудников</th>
                    <th>Операция</th>                    
                    <th>Операция</th>
            </tr>
        <c:forEach var="elem" items="${listCex}">
            <tr>
                                <td>${elem.codeCex}</td>
                                <td>${elem.nameCex}</td>
                                <td>${elem.countWorker}</td>
                                <td><a href="/DbLab4/updateOnePost?codeCex=${elem.codeCex}&nameTable=cex" target="_blank">Редактировать</a></td>
                                <td><a href="/DbLab4/DeleteToId?deletCode=${elem.codeCex}&nameTable=cex" target="_blank">Удалить</a></td>
                            </tr>   
            </c:forEach>    
    </table>
</body>
</html>
