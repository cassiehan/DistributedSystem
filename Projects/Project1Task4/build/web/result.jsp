<%-- 
    Document   : result
    Created on : Sep 23, 2015, 9:15:32 PM
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
            <h1 style="text-align: center">Here is a child's picture of a <%= request.getAttribute("pictureTag")%></h1><br>
            <p style="text-align: center"><img src="<%= request.getAttribute("pictureURL")%>"></p><br><br>
            <p style="text-align: center">Type in a subject to search for : </p>
            <p style="text-align: center"><input type="text" name="pictureTag" value=""/></p><br>
            <p style="text-align: center"><input type="submit" value="Click Here"/></p>
        </form>
    </body>
</html>
