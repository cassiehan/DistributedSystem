<%-- 
    Document   : prompt
    Created on : Sep 24, 2015, 12:27:29 PM
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
            <h1 style="text-align: center">The 
            <a href="http://digital.library.illinoisstate.edu/cdm/search/collection/icca/">International Collection of Child Art</a>
            does not have a picture of a <%= request.getAttribute("pictureTag")%>
            </h1>
            <br>
            <br>
            <p style="text-align: center">Try searching for another subject? : <input type="text" name="input" value=""/> </p>
            
            <br>
            <p style="text-align: center"><input type="submit" value="Click Here"/></p>
        </form>
    </body>
</html>
