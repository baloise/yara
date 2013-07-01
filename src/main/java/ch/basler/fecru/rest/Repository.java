package ch.basler.fecru.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.crucible.spi.PluginId;
import com.atlassian.crucible.spi.services.ImpersonationService;
import com.atlassian.crucible.spi.services.Operation;
import com.atlassian.fisheye.spi.admin.data.GitRepositoryData;
import com.atlassian.fisheye.spi.admin.data.RepositoryData;
import com.atlassian.fisheye.spi.admin.services.RepositoryAdminService;
import com.atlassian.fisheye.spi.admin.services.RepositoryConfigException;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

@Path("/repository")
public class Repository {

	private RepositoryAdminService repositoryAdminService;
	private ImpersonationService impersonationService;

	@GET
	@AnonymousAllowed
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("create/{name}/{location}")
	public Response create(@PathParam("name") final String pName, @PathParam("location") final String pLocation) throws RepositoryConfigException {

		Operation<Response, RepositoryConfigException> op = new Operation<Response, RepositoryConfigException>() {
			@Override
			public Response perform() throws RepositoryConfigException {
				String location = pLocation.replace('!', '/');
				String name = pName.replace('!', '_');
				GitRepositoryData repositoryData = new GitRepositoryData(name, location);
				repositoryData.setManaged(false);
				repositoryData.setStoreDiff(true);
				repositoryAdminService.create(repositoryData);
				repositoryAdminService.disablePolling(name);
				repositoryAdminService.enable(name);
				repositoryAdminService.start(name);
				return Response.ok("Repository " + name + " created at " + location).build();
			}
		};
		return doAsAdmin(op);

	}

	private Response doAsAdmin(Operation<Response, RepositoryConfigException> op) throws RepositoryConfigException {
		return impersonationService.doAsUser(new PluginId() {
		}, "admin", op);
	}

	@GET
	@AnonymousAllowed
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("delete/{name}")
	public Response delete(@PathParam("name") final String pName) throws RepositoryConfigException {
		
		Operation<Response, RepositoryConfigException> op = new Operation<Response, RepositoryConfigException>() {
			@Override
			public Response perform() throws RepositoryConfigException {
				String name = pName.replace('!', '/');
				try {
					repositoryAdminService.stopAndWait(name, 30 * 1000);					
				} catch (IllegalStateException e) {
					e.printStackTrace(System.out);
				}
				repositoryAdminService.disable(name);
				repositoryAdminService.delete(name);
				return Response.ok("Repository " + name + " deleted").build();
			}
		};
		return doAsAdmin(op);

	}

	public void setRepositoryAdminService(RepositoryAdminService repositoryAdminService) {
		this.repositoryAdminService = repositoryAdminService;
	}

	public void setImpersonationService(ImpersonationService impersonationService) {
		this.impersonationService = impersonationService;
	}

}
