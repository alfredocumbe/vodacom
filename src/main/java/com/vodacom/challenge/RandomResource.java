package com.vodacom.challenge;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vodacom.challenge.models.Random;
import com.vodacom.challenge.models.SystemUsageStatistic;
import com.vodacom.challenge.services.RandomService;

@RequestScoped
@Path("/")
public class RandomResource {

    @Inject
    private RandomService randomService; 
    @Context 
    private ServletContext context;

    @POST
    @Path("/random")  
    @Produces(MediaType.APPLICATION_JSON)
    public Response  random(@HeaderParam("X-Max-Wait") long x_max_wait){
        System.out.println("X-Max-Wait: " + x_max_wait);
        long startTime = System.currentTimeMillis();   
        long generationMinTime = Long.parseLong(context.getInitParameter("GenerationMinTime"));
        System.out.println("generationMinTime" + generationMinTime);
        randomService.random(x_max_wait, generationMinTime);
        long elapsedTime = System.currentTimeMillis() - startTime;
        return Response
              .status(Response.Status.OK)
              .header("X-Request-Duration", elapsedTime)
              .build();
    }
   

    @GET
    @Path("/history")  
    @Produces(MediaType.APPLICATION_JSON)
    public Response history(){
        long startTime = System.currentTimeMillis(); 
        List<Random> list = randomService.history();
        long elapsedTime = System.currentTimeMillis() - startTime;
        return Response
           .status(Response.Status.OK)
           .entity(list)
           .header("X-Request-Duration", elapsedTime)
           .build();       
    }

    @PUT
    @Path("{requestid}/cancel")  
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancel(@PathParam("requestid") String requestid){
        
        long startTime = System.currentTimeMillis(); 
        randomService.cancel(requestid);
        long elapsedTime = System.currentTimeMillis() - startTime;

        return Response
           .status(Response.Status.OK)
           .header("X-Request-Duration", elapsedTime)
           .build();  
    }   
    
    @GET
    @Path("/stats")  
    @Produces(MediaType.APPLICATION_JSON)
    public Response stats(){

        long startTime = System.currentTimeMillis();
        SystemUsageStatistic stat = randomService.stats();
        long elapsedTime = System.currentTimeMillis() - startTime;

        return Response
           .status(Response.Status.OK)
           .entity(stat)
           .header("X-Request-Duration", elapsedTime)
           .build(); 
    }    
           
    @GET
    @Path("/pending")  
    @Produces(MediaType.APPLICATION_JSON)
    public Response pending(){
        long startTime = System.currentTimeMillis();
        List<Random> list = randomService.pending();
        
        long elapsedTime = System.currentTimeMillis() - startTime;
        return Response
           .status(Response.Status.OK)
           .entity(list)
           .header("X-Request-Duration", elapsedTime)
           .build(); 
    }
    
    @PUT
    @Path("{poolSize}/threads") 
    @Produces(MediaType.APPLICATION_JSON) 
    @Consumes(MediaType.APPLICATION_JSON)
    public Response threads(@PathParam("poolSize") int poolSize){    
        long startTime = System.currentTimeMillis();
        randomService.threads(poolSize);
        long elapsedTime = System.currentTimeMillis() - startTime;
        return Response
          .status(Response.Status.OK)
          .header("X-Request-Duration", elapsedTime)
          .build(); 
    }    
    
}