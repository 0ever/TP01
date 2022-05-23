package mif.vu.lt.TP01.rest;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.TP01.entities.University;
import mif.vu.lt.TP01.rest.dto.UniversityDto;
import mif.vu.lt.TP01.services.UniversityServiceImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/universities")
public class UniversityRestController {

    @Inject
    @Getter @Setter
    private UniversityServiceImpl universityService;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUniversity(@PathParam("id") long id) {

        University university = universityService.getUniversityById(id);

        if (university == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        UniversityDto universityDto = new UniversityDto();
        universityDto.setCity(university.getCity());
        universityDto.setCountry(university.getCountry());
        universityDto.setName(university.getName());
        universityDto.setRank(university.getRank());

        return Response.ok(universityDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addUniversity(UniversityDto universityDto) {
        try {

            University university = new University();
            university.setCity(universityDto.getCity());
            university.setCountry(universityDto.getCountry());
            university.setName(universityDto.getName());
            university.setRank(universityDto.getRank());
            universityService.saveUniversity(university);

            return Response.status(Response.Status.CREATED).build();

        } catch (OptimisticLockException ole) {

            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateUniversity(@PathParam("id") long id, UniversityDto universityDto) throws InterruptedException{
        try {
            University university = universityService.getUniversityById(id);

            if (university == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            Thread.sleep(5000);
            university.setCity(universityDto.getCity());
            university.setCountry(universityDto.getCountry());
            university.setName(universityDto.getName());
            university.setRank(universityDto.getRank());
            universityService.saveUniversity(university);

            return Response.ok().build();
        } catch (OptimisticLockException ole) {

            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public Response deleteUniversity(@PathParam("id") long id) {

        University university = universityService.getUniversityById(id);

        if (university == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        universityService.deleteUniversityById(id);

        return Response.status(Response.Status.OK).build();
    }
}
