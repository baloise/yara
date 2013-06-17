package ch.basler.fecru.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.fisheye.spi.admin.data.GitRepositoryData;
import com.atlassian.fisheye.spi.admin.services.RepositoryAdminService;
import com.atlassian.fisheye.spi.admin.services.RepositoryConfigException;
import com.atlassian.fisheye.spi.services.RepositoryService;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;


@Path("/")
public class Root {

	@GET
    @AnonymousAllowed
    @Produces({MediaType.TEXT_PLAIN})
    public Response welcome() throws RepositoryConfigException
    {
		return Response.ok("YARA\nYet Another Rest API").build();
    }
	
}