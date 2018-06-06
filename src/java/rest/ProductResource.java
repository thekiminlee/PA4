/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author kimin
 */
@Path("products")
public class ProductResource {
    
    @GET
    @Produces( {MediaType.APPLICATION_JSON} )
    public Response getAllProducts(){
        ArrayList<Products> products = ProductService.getAllProducts();
         
        if(products == null) {
            Response.ok().entity("Error here").build();
            //return Response.status(Response.Status.NOT_FOUND).build();
        }
        GenericEntity<ArrayList<Products>> list = new GenericEntity<ArrayList<Products>>(products) {};
         
        return Response.ok(list).build();
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON} )
    public Response getProduct(@PathParam("id") int id){
        Products product = ProductService.getProduct(id);
        
        if(product == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(product).build();
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addOrder(Orders order){
        if(ProductService.addOrder(order)){
            return Response.ok().entity("Order successfully added").build();
        } 
        
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    
    @PUT
    @Path("update/order_id={id}")
    @Consumes({MediaType.APPLICATION_JSON})
    //@Produces({MediaType.APPLICATION_JSON})
    public Response updateOrder(@PathParam("id") int id, Orders order){
        
        if(ProductService.updateOrder(order, id)) {
            return Response.ok().entity("Order successfully updated").build();
        }
        
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @DELETE
    @Path("del/order_id={id}")
    public Response deleteOrder(@PathParam("id") int id){
        if(ProductService.deleteOrder(id)){
            return Response.ok().entity("Order successfully deleted").build();
        } 
        
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    
}
