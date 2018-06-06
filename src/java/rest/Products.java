/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;


/**
 *
 * @author kimin
 */
public class Products {
    private int id;
    private String name;
    private String type;
    private String price;
    private String image_path;
    private int image_count;
    private String description;
    
    public Products () {}
    
    public int getId() { return id; }   
    public String getName() { return name; }    
    public String getType() { return type; }    
    public String getPrice() { return price; }
    public String getImagePath() { return image_path; }
    public int getImageCount() { return image_count; }
    public String getDescription() { return description; }
    
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setPrice(String price) { this.price = price; }
    public void setImagePath(String addr, String name) { image_path = addr + name; }
    public void setImageCount(int image_count) { this.image_count = image_count; }
    public void setDescription(String description) { this.description = description; }
}
