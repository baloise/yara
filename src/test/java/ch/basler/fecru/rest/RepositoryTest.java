package ch.basler.fecru.rest;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.atlassian.fisheye.spi.admin.services.RepositoryAdminService;
import com.atlassian.fisheye.spi.admin.services.RepositoryConfigException;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {
	@Mock
	private RepositoryAdminService repositoryAdminService;
	@InjectMocks
	private Repository repository = new Repository();

	@Test
	@Ignore
	public void createErsetzungderAusrufungszeichen() throws RepositoryConfigException {
		Response create = repository.create("a!b", "c!d");
		assertEquals("Repository a_b created at c/d",create.getEntity());
	}
	
	@Test
	@Ignore
	public void create() throws RepositoryConfigException {
		Response create = repository.create("ab", "cd");
		assertEquals("Repository ab created at cd",create.getEntity());
	}

}
