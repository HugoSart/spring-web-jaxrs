package com.hsartori.spring.web.testing;

import com.hsartori.spring.web.jaxrs.JaxrsController;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@JaxrsController
@Path("/jaxrs")
public class DummyJaxrsController {

    private String value = "0";

    @GET
    @Path("/value")
    public String get() {
        return value;
    }

    @POST
    @Path("/value")
    public String post(final String value) {
        return this.value = value;
    }


}
