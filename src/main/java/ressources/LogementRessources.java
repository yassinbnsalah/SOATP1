package ressources;

import entities.Logement;
import filtres.Secured;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("logements")
public class LogementRessources {
    public static LogementBusiness logementMetier = new LogementBusiness();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("add")
    public Response addLogement(Logement l) {
        if(logementMetier.addLogement(l))
            return  Response.status(Status.CREATED).build();
        return  Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findbygouvernat(@QueryParam(value ="reference") Integer d)
    {
        if(d != null)
        {
            if(logementMetier.getLogementsByReference(d)!=null) {
                Logement l=new Logement();
                l=logementMetier.getLogementsByReference(d);
                return Response.status(Status.OK).entity(l).build();
            }
            return Response.status(Status.NOT_FOUND).entity("impossible de trouver").build();
        }
        return Response.status(Status.OK).entity(logementMetier.getLogements()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{reference}")
    public Response findbygouvernatpa(@PathParam(value ="reference") Integer d)
    {
        if(d != null)
        {
            if(logementMetier.getLogementsByReference(d)!=null) {
                Logement l=new Logement();
                l=logementMetier.getLogementsByReference(d);
                return Response.status(Status.OK).entity(l).build();
            }
            return Response.status(Status.NOT_FOUND).entity("impossible de trouver").build();
        }
        return Response.status(Status.OK).entity(logementMetier.getLogements()).build();
    }

    //@Secured
@GET
@Path("all")
@Produces(MediaType.APPLICATION_JSON)
    public Response getLogements() {
        List<Logement> liste=logementMetier.getLogements();
    if(liste.size()==0)
        return  Response.status(Status.NOT_FOUND).build();
    return  Response.status(Status.OK).entity(liste).build();

    }
    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  GetAll()
    {

        if(logementMetier.getLogements()!=null)
            return Response.status(Status.OK).entity(logementMetier.getLogements()).build();

        else

            return Response.status(Status.NOT_FOUND).entity("liste vide" ).build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{reference}")
    public Response updateLogement(Logement updatedLogement, @PathParam(value ="reference") int reference) {


        if (logementMetier.updateLogement(reference,updatedLogement)) {
            return Response.status(Status.OK).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{reference}")
        public  Response deleteLogement( @PathParam(value ="reference")int reference){
           if(logementMetier.deleteLogement(reference))
                    return Response.status(Status.OK).build();


            return Response.status(Status.NOT_FOUND).build();

        }

}
