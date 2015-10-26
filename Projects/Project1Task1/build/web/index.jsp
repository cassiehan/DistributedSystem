<%-- 
    Document   : index
    Created on : Sep 23, 2015, 1:06:47 PM
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
        <p>Please enter a string and make a choice of two hash functions.</p>
        <form action="getHashes" method="GET">
            <input type="text" name="inputText" value=""/><br>
            <input type="radio" name="encodingType" value="MD5"/>MD5<br>
            <input type="radio" name="encodingType" value="SHA-1"/>SHA-1<br>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
