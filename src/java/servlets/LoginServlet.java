/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.jni.Directory;

/**
 *
 * @author 787900
 */
public class LoginServlet extends HttpServlet {
      private List<String>   user = new ArrayList<>();
      private List<String>   pass = new ArrayList<>();
      private List<String>   homeitems = new ArrayList<>(); 
       String item[];
      String userL;
      String passL;
      int total=0;
      int sum=0;

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
       
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        String submit = request.getParameter("submit");
        
        if(submit==null) {  
          //  getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
           String path;
            path = getServletContext().getRealPath("/WEB-INF/users.txt");
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String line ;
            
            while((line = br.readLine())!= null)       
        {
            String userMember[] = new String[2];
             userMember = line.split(",");
            user.add(userMember[0]);
            pass.add(userMember[1]);
            System.out.println(user.get(0)); 
            System.out.println(pass.get(0)); 
             }
            
             userL = request.getParameter("user");
             passL = request.getParameter("pass");
            System.out.println(userL); 
            System.out.println(passL);
            
            
            
           
        }
        else
        {
          
        request.setAttribute("message", "You have successfully logged out");
            
            
    }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
            int total=0;
            int counter=0;
            userL = request.getParameter("user");
            passL = request.getParameter("pass");
            HttpSession session = request.getSession();
            
            String path;
            path = getServletContext().getRealPath("/WEB-INF/homeitems.txt");
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String line = br.readLine();
           item = new String[4];
            while((line!= null))     
        {
             item = line.split(",");
            
             sum +=Integer.parseInt(item[3]);
              
            System.out.println(sum);
             
             if(userL.equals(item[0]))
                {
                    
                  total +=Integer.parseInt(item[3]);
                }
              line=br.readLine();
        }      

   // CHECK LOGIN USER AND PASS         
            if(userL.equals("") || passL.equals(""))
            {
                request.setAttribute("message", "Invalid login");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
            if(userL.equals("admin") &&  passL.equals("password" )){
                for(int i=0; i<item.length; i++){
                  
                }
                request.setAttribute("sum", sum);
                request.setAttribute("max", "Invalid login");
                request.setAttribute("name", "Invalid login");
                 getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
            }
            else
            {    
            for(int i=0; i<user.size();i++){
               
            if(userL.equals(user.get(i)) &&  passL.equals(pass.get(i) )) {
             session.setAttribute("useName", userL);
               String userNameS =   (String)session.getAttribute("useName");
               request.setAttribute("mess", userNameS);  
                 
            
           
             
              session.setAttribute("totalWrite", total);
              request.setAttribute("totalL", session.getAttribute("totalWrite"));
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            break;
            }
            else
            {
                counter++;
            }
            
    }
            if(counter==user.size())
            {
                request.setAttribute("message", "Invalid login");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
