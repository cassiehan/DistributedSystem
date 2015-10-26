<%-- 
    Document   : index
    Created on : Sep 23, 2015, 3:36:11 PM
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
        <h1>Calculator</h1>
        <form action="BigCalc" method="GET">
            Input x : 
            <input type="text" name="x" value=""/><br>
            <br>
            Input y : 
            <input type="text" name="y" value=""/><br>
            <br>
            Operator : 
            <select name="operator">
                <option value="add">Addition</option>
                <option value="multiply">Multiplication</option>
                <option value="prime">Relatively Prime</option>
                <option value="mod">Modulo</option>
                <option value="modInverse">Mod Inverse</option>
                <option value="power">Power</option>
            </select>
            <br>
            <br>
            <input type="submit" name="submit" value="Submit"/>
        </form>
    </body>
</html>
