<%-- 
    Document   : products
    Created on : Jun 3, 2018, 7:27:08 PM
    Author     : kimin
--%>

<%@page import="java.io.IOException"%>
<%@page import="models.RecentItems"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.io.StringReader"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
        <script type='text/javascript' src="static/js/menu_bar.js"></script>
        <link rel='stylesheet' type='text/css' href='static/css/menu.css'>
        <link rel='stylesheet' type='text/css' href='static/css/page_layout.css'>
    </head>
    <body>
        <script>indexMenu();</script>
        <%! private final String baseURL = "http://centaurus-3.ics.uci.edu:2069/PA4"; %>
        <%! private Scanner sc; %>
        <%
            RecentItems recent = (RecentItems) session.getAttribute("recent");
            if(recent == null){
                recent = new RecentItems();
                session.setAttribute("recent", recent);
            }
        %>
        
        <div class="main_body">
            <table style='width: 70%' align='center'> 
                <tr>
                    <th>Product Image</th>
                    <th>Product Name</th>
                    <th>Price</th></tr>
            <% 
                try{
                    URL url = new URL(baseURL + "/api/products"); 
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
                    JSONArray jsonResponse = (JSONArray)parser.parse(strResponse);
                    for(int i = 0; i < jsonResponse.size(); i++){
                        JSONObject obj = (JSONObject)jsonResponse.get(i);
                        out.print("<tr><td><a href='item?product=" + (i+1) + "'><img src='" + obj.get("imagePath") + "1.jpg' alt='Product 1'></td></a>");
                        out.println("<td>" + obj.get("name") + "<br>Type: " + obj.get("type") + "</td>");
                        out.println("<td>$" + obj.get("price") + "</td></tr>");
                    }
                    sc.close();
                    
                } catch(IOException e) {
                    System.out.println(e.getMessage());
                } 
            %>
            <jsp:include page="/tracking" flush="false"/>
            </table>
        </div>
        
    </body>
</html>
