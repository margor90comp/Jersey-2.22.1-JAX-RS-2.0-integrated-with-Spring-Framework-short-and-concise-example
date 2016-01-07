package pl.margor.rest.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.margor.db.PersonDB;
import pl.margor.dto.InputPerson;
import pl.margor.db.domain.Person;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * Created by margor on 29.11.15.
 */
@Component("personRest")
@Path("person")
@Produces({"application/json"})
@Consumes({"application/json"})
public class PersonService
{
    @Inject
    private PersonDB memDb;
    @Context
    private ServletContext servletContext;

    @Path("/persist")
    @POST
    public Response persist(InputPerson person) {
        UUID uuid = memDb.create(person);
        URI uri = UriBuilder.fromUri(servletContext.getContextPath() + "/rest/person/" + uuid).build();
        return Response.created(uri).build();
    }

    @Path("/findAllBySurname")
    @GET
    public List<Person> findAll(@QueryParam("surname") String surname) {
        return memDb.findAllBySurname(StringUtils.defaultString(surname));
    }

    @Path("/findAll")
    @GET
    public List<Person> findAll() {
        return memDb.findAll();
    }
}
