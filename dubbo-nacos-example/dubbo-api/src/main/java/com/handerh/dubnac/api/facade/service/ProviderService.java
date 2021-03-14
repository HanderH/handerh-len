package com.handerh.dubnac.api.facade.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * @author handerh
 * @date 2021/03/13
 */
@Path("provider")
public interface ProviderService {

    @POST
    @Path("test")
    @Produces({MediaType.APPLICATION_JSON})
    public String providerTest1();

    @POST
    @Path("getUserInfo")
    @Produces({MediaType.APPLICATION_JSON})
    public Map<String,Object> getUserInfo(Map<String,Object> map);
}
