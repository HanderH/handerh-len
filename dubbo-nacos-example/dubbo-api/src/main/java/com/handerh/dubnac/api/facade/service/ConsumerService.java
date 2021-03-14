package com.handerh.dubnac.api.facade.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * @author handerh
 * @date 2021/03/13
 */
@Path("consumer")
public interface ConsumerService {

    @POST
    @Path("print")
    @Consumes({MediaType.APPLICATION_JSON})
    public void printRes();
}
