package info.aaronsmith.demo.cloudplatform.cloudservices.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import info.aaronsmith.demo.cloudplatform.cloudservices.CloudService;

@SpringBootTest
public class CloudServiceUnitTest {

	@Test
	public void Given_CloudServiceWithDifferentID_When_CompareWithEquals_Then_ReturnFalse() {

		// GIVEN
		CloudService cloudService1 = new CloudService(
			1,
			"ASure Web Site (Basic)",
			"Host a web-site with static content. 100MB storage. Single instance.",
			295
		);
		CloudService cloudService2 = new CloudService(
			2,
			"ASure Web Site (Basic)",
			"Host a web-site with static content. 100MB storage. Single instance.",
			295
		);
		
		// WHEN
		boolean actual = cloudService1.equals(cloudService2);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_CloudServiceWithDifferentName_When_CompareWithEquals_Then_ReturnFalse() {

		// GIVEN
		CloudService cloudService1 = new CloudService(
			1,
			"ASure Web Site (Basic)",
			"Host a web-site with static content. 100MB storage. Single instance.",
			295
		);
		CloudService cloudService2 = new CloudService(
			1,
			"ASure Web Site (Standard)",
			"Host a web-site with static content. 100MB storage. Single instance.",
			295
		);
		
		// WHEN
		boolean actual = cloudService1.equals(cloudService2);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_CloudServiceWithAllAttributesDiffered_When_CompareWithEquals_Then_ReturnFalse() {

		// GIVEN
		CloudService cloudService1 = new CloudService(
			1,
			"ASure Web Site (Basic)",
			"Host a web-site with static content. 100MB storage. Single instance.",
			295
		);
		CloudService cloudService2 = new CloudService(
			2,
			"ASure Web Site (Standard)",
			"Host a web-site with static content. 500MB storage. Two instances, providing high-availability.",
			495
		);
		
		// WHEN
		boolean actual = cloudService1.equals(cloudService2);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_TwoDifferentObjectTypes_When_CompareWithEquals_Then_ReturnFalse() {
		// GIVEN
		CloudService cloudService = new CloudService(
			1,
			"ASure Web Site (Basic)",
			"Host a web-site with static content. 100MB storage. Single instance.",
			295
		);
		String myString = new String("This is not a cloud service.");
		
		// WHEN
		boolean actual = cloudService.equals(myString);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_NullInput_When_CompareWithEquals_Then_ReturnFalse() {
		// GIVEN
		CloudService cloudService1 = new CloudService(
			1,
			"ASure Web Site (Basic)",
			"Host a web-site with static content. 100MB storage. Single instance.",
			295
		);
		CloudService cloudService2 = null;
		
		// WHEN
		boolean actual = cloudService1.equals(cloudService2);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_CloudServiceWithSameAttributes_When_CompareWithEquals_Then_ReturnTrue() {

		// GIVEN
		CloudService cloudService1 = new CloudService(
			1,
			"ASure Web Site (Basic)",
			"Host a web-site with static content. 100MB storage. Single instance.",
			295
		);
		CloudService cloudService2 = new CloudService(
			1,
			"ASure Web Site (Basic)",
			"Host a web-site with static content. 100MB storage. Single instance.",
			295
		);
		
		// WHEN
		boolean actual = cloudService1.equals(cloudService2);
		
		// THEN
		assertThat(actual).isTrue();
	}
	
	@Test
	public void Given_ACloudService_When_OutputToString_Then_ReturnAString() {

		// GIVEN
		CloudService cloudService = new CloudService(
			1,
			"ASure Web Site (Basic)",
			"Host a web-site with static content. 100MB storage. Single instance.",
			295
		);
		String expected = 
			"CloudService [id=1, " + 
			"name=ASure Web Site (Basic), " + 
			"description=Host a web-site with static content. 100MB storage. Single instance., " +
			"costInPence=295]";
		
		// WHEN
		String actual = cloudService.toString();
		
		// THEN
		assertThat(actual).isEqualTo(expected);
	}
	
}
