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
public class Orders {
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String email;
    private String product;
    private int quantity;
    private String address;
    private String cardNum;
    private String shipping;
    
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNum() { return phoneNum; }
    public String getEmail() { return email; }
    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public String getAddress() { return address; }
    public String getCardNum() { return cardNum; }
    public String getShipping() { return shipping;}
    
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPhoneNum(String phone) { phoneNum = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setProduct(String product) { this.product = product; }
    public void setQuantity(int count) { quantity = count; }
    public void setAddress(String addr) { address = addr; }
    public void setCardNum(String cardNum) { this.cardNum = cardNum; }
    public void setShipping(String shipping) { this.shipping = shipping; }
    
    
}
