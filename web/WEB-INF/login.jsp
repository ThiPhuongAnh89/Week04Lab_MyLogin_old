<%-- 
    Document   : login
    Created on : Jan 31, 2020, 11:26:40 AM
    Author     : 787900
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
          <h2>Login</h2>
     
        <form method="POST" action="/Assignment1/login">
            User name: <input type="text" name="user"><br/>  <br/>
        Password: <input type="text" name="pass"><br/>
        <input type="submit" value="submit"      /> <br/>
        ${message}
        <br/>
       
        </form>
    </body>
</html>
