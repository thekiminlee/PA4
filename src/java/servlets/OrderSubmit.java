/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author kimin
 */
public class OrderSubmit extends HttpServlet {
    private final String baseURL = "http://centaurus-3.ics.uci.edu:2069/PA4";

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
        
        
        
            // Item Information
            
            int itemCount = Integer.parseInt(request.getParameter("productCount"));
            for(int i = 0; i < itemCount; i++){
                String info = request.getParameter("product" + (i+1));
                String[] productInfo = info.split(",");
                String productName = productInfo[0];
                String productCount = productInfo[1];
                // Customer Information
                String firstName = request.getParameter("first_name");
                String lastName = request.getParameter("last_name");
                String phoneNum = request.getParameter("phone_number");
                String email = request.getParameter("e-mail");
                String address = request.getParameter("address") + " " + request.getParameter("city") + ", " + request.getParameter("state") + " " + request.getParameter("zip_code");
                String cardNum = request.getParameter("card_number");
                String shipping = request.getParameter("shipping_method");

                // Creating JSON with customer information
                JSONObject order = new JSONObject();
                order.put("firstName", firstName);
                order.put("lastName", lastName);
                order.put("phoneNum", phoneNum);
                order.put("email", email);
                order.put("product", productName);
                order.put("quantity", productCount);
                order.put("address", address);
                order.put("cardNum", cardNum);
                order.put("shipping", shipping);
                
                // Invoking RESTful API for POST. Database update
                URL url = new URL(baseURL + "/api/products");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.write(order.toString().getBytes());
                connection.connect();
                Integer responseCode = connection.getResponseCode();
                
                if(responseCode != 200){
                    System.out.println("Connection failed");
                } else {
                    System.out.println("Query successful");
                }
                
                
            }

        try (PrintWriter out = response.getWriter()) {                       
            RequestDispatcher rd = request.getRequestDispatcher("order_detail");
            rd.forward(request, response);
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
