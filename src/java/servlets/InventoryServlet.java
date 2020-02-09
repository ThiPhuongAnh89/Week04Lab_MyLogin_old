/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 787900
 */
public class InventoryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InventoryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InventoryServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          HttpSession session = request.getSession();
     //   processRequest(request, response);
         String priceI = request.getParameter("price");
         String    itemI = request.getParameter("item");
         int price  =0;
         if(priceI.equals("") || itemI .equals(""))
         {
            request.setAttribute("add", "Invalid Item. Please re-enter");
             request.setAttribute("totalL", session.getAttribute("totalWrite"));
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
         }
         else
         {
             try{
                 price = Integer.parseInt(priceI);
             }
             catch(NumberFormatException e)
             {
               request.setAttribute("add", "Invalid Item. Please re-enter");
                request.setAttribute("totalL", session.getAttribute("totalWrite"));
               getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);  
             }
             if(price <=0 || price >10000)
             {
            request.setAttribute("add", "Invalid Item. Please re-enter");
            request.setAttribute("totalL", session.getAttribute("totalWrite"));
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
             }
             else
             {
                 
             
// WRITE THE NEW ITEM TO THE homeitems file             
             
            
             session.setAttribute("category", request.getParameter("category"));
             session.setAttribute("item", request.getParameter("item"));
             session.setAttribute("price", request.getParameter("price"));
             
             String category = (String) session.getAttribute("category");
             String item = (String) session.getAttribute("item");
             String priceL = (String) session.getAttribute("price");
             int priceS = Integer.parseInt(priceL);
             //String userName = (String) session.getAttribute("userName");
             int totalAd = (int) session.getAttribute("totalWrite");
             String userNameS =   (String)session.getAttribute("useName");
                request.setAttribute("mess", userNameS);  
            // System.out.println((String)session.getAttribute("userName"));
            
            String success = " The item was successfully added to your inventory";
             request.setAttribute("success", success);  
         
             
             String path = getServletContext().getRealPath("/WEB-INF/homeitems.txt");
             
            // String categoryAdd = request.getParameter("category");
//             String itemAdd = request.getParameter("item");
//             String priceAdd = request.getParameter("price");
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, true))); 
             pw.print(userNameS);
             pw.print(",");
             pw.print(category);
             pw.print(",");
             pw.print(item);
             pw.print(",");
             pw.println(priceS);
            int totalPrint = totalAd +priceS;
            session.setAttribute("totalL", totalPrint);
            request.setAttribute("totalL", session.getAttribute("totalL"));
            pw.close();
             
      getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
             }
    }
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
