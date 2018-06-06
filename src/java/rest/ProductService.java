/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import db.Connect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kimin
 */
public class ProductService {
    
    public static ArrayList<Products> getAllProducts(){
        Connect connection = null;
        try {
            connection = new Connect();
            ArrayList<Products> products = new ArrayList<>();            
            ResultSet result = connection.executeQuery("SELECT * FROM products");
            while(result.next()){
                Products product = new Products();
                product.setId(result.getInt("id"));
                product.setName(result.getString("name"));
                product.setType(result.getString("type"));
                product.setPrice(result.getString("price"));
                product.setImagePath(result.getString("picture"), result.getString("image_name"));
                products.add(product);
            }
            return products;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Product Service Error. Connection not established");
        } finally {
            try{
                connection.closeConn();
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    
    public static Products getProduct(int id){
        Connect connection = null;
        try{
            Products product = new Products();
            connection = new Connect();
            ResultSet result = connection.executeQuery("SELECT * FROM products WHERE id = " + id);
            while(result.next()){
                product.setId(result.getInt("id"));
                product.setName(result.getString("name"));
                product.setType(result.getString("type"));
                product.setPrice(result.getString("price"));
                product.setImagePath(result.getString("picture"), result.getString("image_name"));
                product.setImageCount(result.getInt("image_count"));
                product.setDescription(result.getString("description"));
            }
            return product;
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("Product Service Error. Connection not established");
        } finally {
            try{
                connection.closeConn();
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    
    public static boolean updateOrder(Orders order, int id){
        String firstName = order.getFirstName();
        String lastName = order.getLastName();
        String phoneNum = order.getPhoneNum();
        String email = order.getEmail();
        String product = order.getProduct();
        int quantity = order.getQuantity();
        String address = order.getAddress();
        String cardNum = order.getCardNum();
        String shipping = order.getShipping();
        
        String query = "UPDATE purchased SET first_name='" + firstName + "', last_name='" + lastName + "', phone_num='" + phoneNum + "', email='" + email + "', product='" +
                product + "', quantity='" + quantity + "', address='" + address + "', card_num='" + cardNum + "', shipping='" + shipping + "' WHERE id=" + id + ";";
        
        Connect connection = null;
        try{
            connection = new Connect();
            return connection.updateQuery(query);
        } catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        } finally {
            try{
                connection.closeConn();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        return false;
    }
    
    public static boolean deleteOrder(int id){
        String query = "DELETE FROM purchased WHERE id=" + id + ";";
        
        Connect connection = null;
        try{
            connection = new Connect();
            return connection.updateQuery(query);
        } catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        } finally {
            try{
                connection.closeConn();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    
    public static boolean addOrder(Orders order){
        String firstName = order.getFirstName();
        String lastName = order.getLastName();
        String phoneNum = order.getPhoneNum();
        String email = order.getEmail();
        String product = order.getProduct();
        int quantity = order.getQuantity();
        String address = order.getAddress();
        String cardNum = order.getCardNum();
        String shipping = order.getShipping();
        
        String query = "INSERT INTO purchased (first_name, last_name, phone_num, email, product, quantity, address, card_num, shipping) VALUES ('" 
                                    + firstName + "','" + lastName + "','" + phoneNum + "','" + email + "','" + product + "','" + quantity + "','" + address + "','"
                                    + cardNum + "','" + shipping + "')";
        
        Connect connection = null;
        try{
            connection = new Connect();
            return connection.updateQuery(query);
        } catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        } finally {
            try{
                connection.closeConn();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        return false;
    }
}
