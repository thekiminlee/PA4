/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;



/**
 *
 * @author kimin
 */
public class RecentItems {
    private ArrayList<RecentProduct> productNames;
    private int counter = 0;
    
    public RecentItems(){
        productNames = new ArrayList<>();
    }
    
    public void addItem(RecentProduct product){
        if(productNames.size() == 5){
            RecentProduct del = null;
            for(RecentProduct items : productNames){
                if(items.getPriority() == 1)
                    del = items;
                else                  
                    items.decPriority();                
            }
            productNames.remove(del);
        } else 
            counter++;
        product.setPriority(counter);
        productNames.add(product);
    }
    
    public void removeItem(RecentProduct product){
        productNames.remove(product);
    }
    
    public ArrayList<RecentProduct> getNames(){
        return productNames;
    }
    
    public boolean isEmpty(){
        return productNames.isEmpty();
    }
    @Override
    public String toString(){
        String result = "  ";
        for(RecentProduct items : productNames){
            result += "<a href='item?product=" + items.getProductNum() + "'><img src='" + items.getPath() + "' alt='Recent Product'>" + "</a>";
        }
        return result;
    }
}
