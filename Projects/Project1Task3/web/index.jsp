<%-- 
    Document   : index
    Created on : Sep 23, 2015, 5:07:52 PM
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
        <h1>Palindromes</h1>
        <form action="Palin" method="GET">
            Please input a string, and I will tell you if it is a palindrome <br>
            <br>
            <input type="text" name="input" value=""/><br>
            <br>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
