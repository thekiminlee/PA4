package servlets;

import models.RecentItems;
import models.RecentProduct;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author kimin
 */
public class Item extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String baseURL = "http://centaurus-3.ics.uci.edu:2069/PA4";
    private Scanner sc;
    
    private String product;
    HttpSession session;
    RecentItems recent;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        product = request.getParameter("product");   
        
        session = request.getSession(false);
        recent = (RecentItems) session.getAttribute("recent");        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("        <title>Product " + product +"</title>");
            out.println("        <script type='text/javascript' src=\"static/js/menu_bar.js\"></script>");
            out.println("        <script type='text/javascript' src=\"static/js/item.js\"></script>");
            out.println("        <link rel='stylesheet' type='text/css' href='static/css/menu.css'>");
            out.println("        <link rel='stylesheet' type='text/css' href='static/css/items.css'");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <script>indexMenu();</script>");
            out.println("       <div class='main_body'>");
            displayDetail(out, product);             
            out.println("       </div>");
            out.println("   </body>");
            out.println("</html>");
        }
    }
    
    private void displayDetail(PrintWriter out, String id){
        try{
                    URL url = new URL(baseURL + "/api/products/" + id); 
                    String strResponse = "";
                    HttpURLConnection conn =  (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    if(conn.getResponseCode() != 200)
                        throw new RuntimeException("HttpResponseCode: " + conn.getResponseCode());
                    else {
                        sc = new Scanner(url.openStream());
                        while(sc.hasNext()){
                            strResponse += sc.nextLine();
                        }
                    }
                    JSONParser parser = new JSONParser();
                    JSONObject obj = (JSONObject)parser.parse(strResponse);
                    
                    out.println("       <table><tr><td id='mainImageCell'>");
                    out.println("           <img id='mainImage' src='" + obj.get("imagePath") + "1.jpg' alt='Product 1'></td>");
                    out.println("           <td id='description'>");
                    out.println("               <b>" + obj.get("name") + "</b>");
                    out.println("               <p>" + obj.get("description") + "</p><br><br>$" + obj.get("price") + "<br><br>");
                    out.println("               <form method='post' action='cart?item=" + obj.get("name") + "&price=" + obj.get("price") + "'>");
                    out.println("                   <input type='submit' value='Add to Cart'></form></td>");
                    out.println("       </tr></table>"); 
                    
                    out.println("       <table><tr><td>");
                    int count = Integer.parseInt(obj.get("imageCount").toString());
                    for(int i = 0; i < count; i++){
                        out.println("<img class='thumbnails' id='thumbnail" + (i+1) + "' onClick='switchImage(\"thumbnail" + (i+1) + "\")' src='" + obj.get("imagePath") + (i+1) + ".jpg' alt='" + obj.get("name") + (i+1) + "'>");
                    }
                    out.println("       </td></tr></table>");
                    
                    if(recent == null){
                        recent = new RecentItems();
                        session.setAttribute("recent", recent);
                    } else {
                        recent.addItem(new RecentProduct((String)obj.get("name"), obj.get("imagePath") + "1.jpg", id));
                    }
                    
                    //conn.disconnect();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    sc.close();
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
        processRequest(request, response);
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
