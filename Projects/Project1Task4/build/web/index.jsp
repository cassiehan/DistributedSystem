<%-- 
    Document   : index
    Created on : Sep 23, 2015, 9:15:12 PM
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
        <form action="searchArtwork" method="GET">
            <h1 style="text-align: center">Search the 
            <a href="http://digital.library.illinoisstate.edu/cdm/search/collection/icca/">International Collection of Child Art</a>
            for pictures for any subject
            </h1>
            <br>
            <br>
            <p style="text-align: center">Type in a subject to search for : <input type="text" name="input" value=""/> </p>
            
            <br>
            <p style="text-align: center"><input type="submit" value="Click Here"/></p>
        </form>
    </body>
</html>
