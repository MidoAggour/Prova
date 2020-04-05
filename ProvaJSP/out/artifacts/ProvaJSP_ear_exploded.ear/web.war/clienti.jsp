<%--
  Created by IntelliJ IDEA.
  User: Mido
  Date: 02/04/2020
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@page import="psw.models.Cliente" %>
        <jsp:useBean id="clienti" scope="request" class="java.util.LinkedList<Cliente>" />

        <html>
        <head>
            <title>Title</title>
        </head>
<body>
<h1>I clienti del DB sono i seguenti:</h1>
<table border="2" style="background-color: aquamarine">
    <tr>
        <th>id</th>
        <th>nome</th>
    </tr>
    <% for(Cliente c: clienti){ %>
    <tr>
        <td><%=c.getId()%></td>
        <td><%=c.getNome()%></td>
    </tr>
    <%}%>
</table>

</body>
</html>
</title>
</head>
<body>

</body>
</html>
