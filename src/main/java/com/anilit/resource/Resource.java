package com.anilit.resource;

import com.anilit.entity.CitizenNew;
import com.anilit.repository.CitigenRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Set;
import java.util.stream.Collectors;

@Path("/")
public class Resource {

    @Inject
    CitigenRepository citigenRepository;
    @Inject
    Validator validator;

    @POST
    @Path("save")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response saveCitizen(CitizenNew citizen){
        Set<ConstraintViolation<CitizenNew>> validate = validator.validate(citizen);
        if (validate.isEmpty()) {
            citigenRepository.persist(citizen);
            if (citigenRepository.isPersistent(citizen)) {
                return Response.ok(citizen).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }else {
            String errorMsg = validate.stream().map(citizenNewConstraintViolation -> citizenNewConstraintViolation.getMessage())
                    .collect(Collectors.joining(", "));

            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("saveValid")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response saveCitizenValid(@Valid CitizenNew citizen){
        citigenRepository.persist(citizen);
            if (citigenRepository.isPersistent(citizen)) {
                return Response.ok(citizen).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
