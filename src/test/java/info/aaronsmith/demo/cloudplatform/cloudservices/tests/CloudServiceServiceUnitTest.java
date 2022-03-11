package info.aaronsmith.demo.cloudplatform.cloudservices.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import info.aaronsmith.demo.cloudplatform.cloudservices.CloudService;
import info.aaronsmith.demo.cloudplatform.cloudservices.CloudServiceNotFoundException;
import info.aaronsmith.demo.cloudplatform.cloudservices.CloudServiceRepo;
import info.aaronsmith.demo.cloudplatform.cloudservices.CloudServiceService;

@SpringBootTest
public class CloudServiceServiceUnitTest {

	@Autowired
	private CloudServiceService service;

	@MockBean
	private CloudServiceRepo repo;

	// CREATE

	@Test
	public void Given_ValidArguments_When_CreateCloudService_Then_ReturnNewCloudService() throws Exception {
		// GIVEN
		final CloudService INPUT_SERVICE = new CloudService(
			"ASure Web Site (Standard)",
			"Host a web-site with static content. 100MB storage. Single instance.",
			295
		);
		final CloudService EXPECTED_SERVICE = new CloudService(
			1,
			"ASure Web Site (Standard)",
			"Host a web-site with static content. 100MB storage. Single instance.",
			295
		);

		// WHEN
		Mockito.when(repo.save(INPUT_SERVICE)).thenReturn(EXPECTED_SERVICE);
		CloudService actual = service.createService(INPUT_SERVICE);

		// THEN
		assertThat(actual).isEqualTo(EXPECTED_SERVICE);
		Mockito.verify(repo, Mockito.times(1)).save(INPUT_SERVICE);
	}

	// READ

	@Test
	public void Given_NoArguments_When_GetCloudService_Then_ReturnListOfAllCloudServices() {
		// GIVEN
		final CloudService[] SERVICE_ARRAY = { 
			new CloudService(
				1,
				"ASure Web Site (Basic)",
				"Host a web-site with static content. 100MB storage. Single instance.",
				295
			),
			new CloudService(
				2,
				"ASure Web Site (Standard)",
				"Host a web-site with static content. 500MB storage. Two instances, providing high-availability.",
				495
			),
			new CloudService(
				3,
				"ASure Web Application (Basic)",
				"Host a back-end web-application using PHP, ASP.NET, or Java. 250MB storage. Single instance.",
				475
			),
			new CloudService(
				4,
				"ASure Web Application (Standard)",
				"Host a back-end web-application using PHP, ASP.NET, or Java. 1GB storage. Single instance.",
				749
			),
			new CloudService(
				5,
				"ASure Web Application (Premium)",
				"Host a back-end web-application using PHP, ASP.NET, or Java. 1GB storage. Two instances, providing high-availability.",
				749
			),
			new CloudService(
				6,
				"ASure Virtual Machine (A1)",
				"A virtual-machine with a number of available operating-systems. 1 x vCPU @ 1.2GHz, 1GB RAM, 20GB storage.",
				462
			),
			new CloudService(
				7,
				"ASure Virtual Machine (B1)",
				"A virtual-machine with a number of available operating-systems. 1 x vCPU @ 2.4GHz, 2GB RAM, 40GB storage.",
				681
			),
			new CloudService(
				8,
				"ASure Virtual Machine (B2)",
				"A virtual-machine with a number of available operating-systems. 2 x vCPU @ 2.4GHz, 4GB RAM, 80GB storage.",
				879
			)
		};
		final List<CloudService> EXPECTED_SERVICES = new ArrayList<CloudService>(Arrays.asList(SERVICE_ARRAY));

		// WHEN
		Mockito.when(repo.findAll()).thenReturn(EXPECTED_SERVICES);
		List<CloudService> actual = service.getService();

		// THEN
		assertThat(actual).isEqualTo(EXPECTED_SERVICES);
		Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	@Test
	public void Given_ValidId_When_GetCloudService_Then_ReturnCloudService() {
		// GIVEN
		final Integer INPUT_ID = 1;
		final CloudService EXPECTED_SERVICE = new CloudService(
			1,
			"ASure Web Site (Basic)",
			"Host a web-site with static content. 100MB storage. Single instance.",
			295
		);

		// WHEN
		Mockito.when(repo.findById(INPUT_ID)).thenReturn(Optional.of(EXPECTED_SERVICE));
		CloudService actual = service.getService(INPUT_ID);

		// THEN
		assertThat(actual).isEqualTo(EXPECTED_SERVICE);
		Mockito.verify(repo, Mockito.times(1)).findById(INPUT_ID);
	}

	@Test
	public void Given_NonExistantId_When_GetCloudService_Then_ThrowCloudServiceNotFoundException() {
		// GIVEN
		final Integer INPUT_ID = 99;
		final Exception EXPECTED_EXCEPTION = new CloudServiceNotFoundException(INPUT_ID);

		// WHEN
		Mockito.when(repo.findById(INPUT_ID)).thenThrow(EXPECTED_EXCEPTION);
		Throwable actual = catchThrowable(() -> service.getService(INPUT_ID));

		// THEN
		assertThat(actual.getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass());
		Mockito.verify(repo, Mockito.times(1)).findById(INPUT_ID);
	}

	// UPDATE

	@Test
	public void Given_ValidIdAndArguments_When_UpdateCloudService_Then_ReturnUpdatedCloudService() {
		// GIVEN
		final Integer INPUT_ID = 1;
		final CloudService INPUT_SERVICE = new CloudService(
			"ASure Web Application (Standard A1)",
			"Host a back-end web-application using PHP, ASP.NET, or Java. 1GB storage. Single instance.",
			778
		);
		final CloudService FOUND_SERVICE = new CloudService(
			4,
			"ASure Web Application (Standard)",
			"Host a back-end web-application using PHP, ASP.NET, or Java. 1GB storage. Single instance.",
			749
		);
		final CloudService EXPECTED_SERVICE = new CloudService(
			4,
			"ASure Web Application (Standard A1)",
			"Host a back-end web-application using PHP, ASP.NET, or Java. 1GB storage. Single instance.",
			778
		);

		// WHEN
		Mockito.when(repo.findById(INPUT_ID)).thenReturn(Optional.of(FOUND_SERVICE));
		Mockito.when(repo.save(FOUND_SERVICE)).thenReturn(EXPECTED_SERVICE);
		CloudService actual = service.updateService(INPUT_ID, INPUT_SERVICE);

		// THEN
		assertThat(actual).isEqualTo(EXPECTED_SERVICE);
		Mockito.verify(repo, Mockito.times(1)).findById(INPUT_ID);
		Mockito.verify(repo, Mockito.times(1)).save(FOUND_SERVICE);
	}

	@Test
	public void Given_NonExistantId_When_UpdateCloudService_Then_ThrowCloudServiceNotFoundException() {
		// GIVEN
		final Integer INPUT_ID = 99;
		final CloudService INPUT_SERVICE = new CloudService(
			"ASure Web Application (Standard)",
			"Host a back-end web-application using PHP, ASP.NET, or Java. 1GB storage. Single instance.",
			749
		);
		final Exception EXPECTED_EXCEPTION = new CloudServiceNotFoundException(INPUT_ID);

		// WHEN
		Mockito.when(repo.findById(INPUT_ID)).thenThrow(EXPECTED_EXCEPTION);
		Throwable actual = catchThrowable(() -> service.updateService(INPUT_ID, INPUT_SERVICE));

		// THEN
		assertThat(actual.getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass());
		Mockito.verify(repo, Mockito.times(1)).findById(INPUT_ID);
	}

	// DELETE

	@Test
	public void Given_ValidId_When_DeleteCloudService_Then_NoReturnValue() {
		// GIVEN
		final Integer INPUT_ID = 1;

		// WHEN
		Mockito.doNothing().when(repo).deleteById(INPUT_ID);
		service.deleteService(INPUT_ID);

		// THEN expect deleteById to have been called once
		Mockito.verify(repo, Mockito.times(1)).deleteById(INPUT_ID);
	}

	@Test
	public void Given_NonExistantId_When_DeleteCloudService_Then_ThrowCloudServiceNotFoundException() {
		// GIVEN
		final Integer INPUT_ID = 99;
		final Exception EXPECTED_EXCEPTION = new CloudServiceNotFoundException(INPUT_ID);

		// WHEN
		Mockito.when(repo.findById(INPUT_ID)).thenThrow(EXPECTED_EXCEPTION);
		Throwable actual = catchThrowable(() -> service.getService(INPUT_ID));

		// THEN
		assertThat(actual.getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass());
		Mockito.verify(repo, Mockito.times(1)).findById(INPUT_ID);
	}
}
