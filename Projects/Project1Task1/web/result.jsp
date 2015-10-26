<%-- 
    Document   : result
    Created on : Sep 23, 2015, 1:07:10 PM
    Author     : hanxi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Project1 Task1</h1>
        <p>Here is the Hashes of String <%=request.getAttribute("inputText")%> :</p>
        <form action="getHashes" method="GET">
            <ul>
            <li><%=request.getAttribute("encodingType")%> Hexadecimal Encoding : <%=request.getAttribute("hex")%></li>
            <li><%=request.getAttribute("encodingType")%> Base64 Encoding : <%=request.getAttribute("base64")%></li>
            </ul>
        </form>
    </body>
</html>
