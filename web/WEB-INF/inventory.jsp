<%-- 
    Document   : inventory
    Created on : Jan 31, 2020, 11:26:17 AM
    Author     : 787900
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>
    </head>
    <body>
    <h1>Home Inventory for ${mess}</h1>
          <h2>Add Item</h2>
     
        <form method="POST" action="/Assignment1/InventoryServlet">
            
            <p>
             <label>Category: </label>
             <select id = "category" name="category"> 
               <option value = "bedroom">bedroom</option>
               <option value = "garage">garage</option>
               <option value = "kitchen">kitchen</option>
               <option value = "living room">living room</option>
             </select>
          </p>
          <div>Item name: <input type="text"  name="item"><br/></div>
          <div>Price: <input type="text" name="price"></div> <br/>
          <div><input type="submit" value="Add"> ${add}</div>
        </form>
        <br/>
        ${success}
        </br>
        Total value in inventory: $${totalL} </br>
        </br>
         <a href="/Assignment1/login?submit">Logout</a>
    
        
    </body>
</html>