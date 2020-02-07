<%-- 
    Document   : admin
    Created on : Jan 31, 2020, 11:24:12 AM
    Author     : 787900
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin Page</title>
    </head>
    <body>
       <h1>Home Inventory</h1>
       <h2>Admin Summary</h2>
       Total value for all users: $${sum}. Most expensive item is ${maxItem} at $${max} owned by ${name}.
       </br>
       </br>
          <a href="/Assignment1/login?submit">Logout</a>
    </body>
</html>
