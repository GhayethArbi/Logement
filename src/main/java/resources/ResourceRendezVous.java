package resources;

import Busniess.RendezVousBusiness;
import entities.RendezVous;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rendezvous")
public class ResourceRendezVous {

    public static RendezVousBusiness rvb = new RendezVousBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response addRendezVous(RendezVous rendezVous){
        if(rvb.addRendezVous(rendezVous)){
            return Response.status(Response.Status.CREATED).build();
        }else return Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVous(){
        System.out.println("-------------");
        List<RendezVous> list = rvb.getListeRendezVous();
        if(list.size()==0) return Response.status(Response.Status.NOT_FOUND).build();
        else return Response.status(Response.Status.OK).entity(list).build();
    }

    @Path("/search-rendezVous")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousByQueryParam(@QueryParam(value = "id") int id){
        if (rvb.getRendezVousById(id)!= null){
            return Response.status(Response.Status.FOUND).entity(rvb.getRendezVousById(id)).build();

        }else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRendezVous(@PathParam("id") int id){
        if(rvb.deleteRendezVous(id)) return Response.status(Response.Status.OK).entity("RendezVous supprimé").build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }


    @Path("{id}")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous rendezVous){
        if(rvb.updateRendezVous(id, rendezVous)) return Response.status(Response.Status.OK).entity("RendezVous modifié").build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }


}
