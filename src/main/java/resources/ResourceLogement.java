package resources;

import Busniess.LogementBusiness;
import entities.Logement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Secured
@Path("/logements")
public class ResourceLogement {

    public static LogementBusiness lb = new LogementBusiness();

    @POST

    @Consumes(MediaType.APPLICATION_XML)
    public Response addLogement(Logement logement){
        if(lb.addLogement(logement)){
            return Response.status(Response.Status.CREATED).build();
        }else return Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogements(){
         List<Logement> list = lb.getLogements();
         if(list.size()==0) return Response.status(Response.Status.NOT_FOUND).build();
         else return Response.status(Response.Status.OK).entity(list).build();
    }

    @Path("/search-logement")
    @GET()
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByQueryParam(@QueryParam(value = "reference") int reference){
        if (lb.getLogementsByReference(reference)!= null){
            return Response.status(Response.Status.FOUND).entity(lb.getLogementsByReference(reference)).build();

        }else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("{reference}")
    @DELETE
    @Secured
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLogement(@PathParam("reference") int reference){
        if(lb.deleteLogement(reference)) return Response.status(Response.Status.OK).entity("logement supprimé").build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }


    @Path("{reference}")
    @PUT
    @Secured
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement){
        if(lb.updateLogement(reference, logement)) return Response.status(Response.Status.OK).entity("logement modifié").build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }




}
