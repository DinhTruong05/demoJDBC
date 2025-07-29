<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<h1>List Student</h1>
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Address</th>
                <th>Phone</th>
            </tr>
            <c:forEach var="student" items="${requestScope.list}">

                <tr>
                    <td><c:out value="${student.id}"/></td>
                    <td><c:out value="${student.name}"/></td>
                    <td><c:out value="${student.address}"/></td>
                    <td><c:out value="${student.phone}"/></td>
                    <td><a href="/student/delete?id=<c:out value="${student.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>
