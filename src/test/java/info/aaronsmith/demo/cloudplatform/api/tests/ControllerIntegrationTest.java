package info.aaronsmith.demo.cloudplatform.api.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import info.aaronsmith.demo.cloudplatform.accounts.Account;
import info.aaronsmith.demo.cloudplatform.accounts.AccountNotFoundException;
import info.aaronsmith.demo.cloudplatform.accounts.TenantNameUnavailableException;
import info.aaronsmith.demo.cloudplatform.cloudservices.CloudService;
import info.aaronsmith.demo.cloudplatform.cloudservices.CloudServiceNotFoundException;

@SpringBootTest
@ActiveProfiles("test")
@Sql(
	scripts = { 
		"classpath:account-schema.sql",
		"classpath:cloudservice-schema.sql",
		"classpath:account-data.sql",
		"classpath:cloudservice-data.sql",
	},
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
@AutoConfigureMockMvc
public class ControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	/////////////
	// ACCOUNT //
	/////////////
	
		// CREATE //
		
		@Test
		public void Given_ValidArguments_When_CreateAccountRequest_Then_ReturnCreatedAccountAndStatusCreated() throws Exception {
			// GIVEN
			final Account INPUT_ACCOUNT = new Account(
				"Mr",
				"Ruvid",
				"Willran",
				"mynewtenant209",
				"83 Apple Turnover",
				"Bakery Way",
				"Mockassin",
				"Javerley",
				"AP12 1TO",
				"0123 456 7890",
				"ruvid.willran@somesuch.com"
			);
			final String INPUT_ACCOUNT_AS_JSON = mapper.writeValueAsString(INPUT_ACCOUNT);
			final RequestBuilder REQUEST = post("/createAccount")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(INPUT_ACCOUNT_AS_JSON);
			
			// WHEN, THEN
			final Account EXPECTED_ACCOUNT = new Account(
				4,
				"Mr",
				"Ruvid",
				"Willran",
				"mynewtenant209",
				"83 Apple Turnover",
				"Bakery Way",
				"Mockassin",
				"Javerley",
				"AP12 1TO",
				"0123 456 7890",
				"ruvid.willran@somesuch.com"
			);
			final String EXPECTED_ACCOUNT_AS_JSON = mapper.writeValueAsString(EXPECTED_ACCOUNT);
			
			final ResultMatcher statusMatcher = status().isCreated();
			final ResultMatcher contentMatcher = content().json(EXPECTED_ACCOUNT_AS_JSON);
	
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(contentMatcher);
		}
		
		@Test
		public void Given_ExistingTenantName_When_CreateAccountRequest_Then_ReturnExceptionAndStatusBadRequest() throws Exception {
			// GIVEN
			final Account INPUT_ACCOUNT = new Account(
				"Mr",
				"Ruvid",
				"Willran",
				"qa-training",
				"83 Apple Turnover",
				"Bakery Way",
				"Mockassin",
				"Javerley",
				"AP12 1TO",
				"0123 456 7890",
				"ruvid.willran@somesuch.com"
			);
			final String INPUT_ACCOUNT_AS_JSON = mapper.writeValueAsString(INPUT_ACCOUNT);
			final RequestBuilder REQUEST = post("/createAccount")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(INPUT_ACCOUNT_AS_JSON);
			
			// WHEN, THEN
			final TenantNameUnavailableException EXPECTED_EXCEPTION =
					new TenantNameUnavailableException("qa-training");
			final String EXPECTED_MESSAGE = "Tenant name (qa-training) is unavailable for use.";
			
			final ResultMatcher statusMatcher = status().isBadRequest();
			final ResultMatcher exceptionMatcher = result -> assertThat(result.getResolvedException().getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass()); 
			final ResultMatcher messageMatcher = result -> assertThat(result.getResolvedException().getMessage()).isEqualTo(EXPECTED_MESSAGE);
			
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(exceptionMatcher)
								.andExpect(messageMatcher);
		}
	
		// READ //
	
		@Test
		public void Given_NoArguments_When_GetAccountRequest_Then_ReturnAllAccountsAndStatusOk() throws Exception {
			// GIVEN
			final RequestBuilder REQUEST = get("/getAccounts");
			
			// WHEN, THEN
			final Account[] EXPECTED_ACCOUNTS = {
				new Account(
					1,
					"Mr",
					"Donald",
					"Gibson",
					"asure",
					"Flat 4",
					"22 Roadington Road",
					"Mockleton",
					"Java",
					"JA1 2JA",
					"01234 567890",
					"donald.gibson@asure.com"
				),
				new Account(
					2,
					"Miss",
					"Jennifer",
					"Green",
					"qa-training",
					"22a Road Avenue",
					"",
					"Mockiton",
					"Javashire",
					"MO22 8JA",
					"0123 4567 890",
					"jengreen@qa.com"
				),
				new Account(
					3,
					"Mr",
					"Aaron",
					"Smith",
					"smith-development",
					"83 Apple Turnover",
					"Bakery Way",
					"Mockassin",
					"Javerley",
					"AP12 1TO",
					"0123 456 7890",
					"asmith@mydomain.com"
				)
			};
			final List<Account> EXPECTED_ACCOUNTS_LIST = Arrays.asList(EXPECTED_ACCOUNTS);
			final String EXPECTED_ACCOUNTS_AS_JSON = mapper.writeValueAsString(EXPECTED_ACCOUNTS_LIST);
			
			final ResultMatcher statusMatcher = status().isOk();
			final ResultMatcher contentMatcher = content().json(EXPECTED_ACCOUNTS_AS_JSON);
	
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(contentMatcher);
		}
	
		@Test
		public void Given_ValidId_When_GetAccountRequest_Then_ReturnAccountAndStatusOk() throws Exception {
			// GIVEN
			final RequestBuilder REQUEST = get("/getAccount/2");
			
			// WHEN, THEN
			final Account EXPECTED_ACCOUNT = new Account(
				2,
				"Miss",
				"Jennifer",
				"Green",
				"qa-training",
				"22a Road Avenue",
				"",
				"Mockiton",
				"Javashire",
				"MO22 8JA",
				"0123 4567 890",
				"jengreen@qa.com"
			);
			final String EXPECTED_ACCOUNT_AS_JSON = mapper.writeValueAsString(EXPECTED_ACCOUNT);
			final ResultMatcher EXPECTED_STATUS = status().isOk();
			final ResultMatcher EXPECTED_CONTENT = content().json(EXPECTED_ACCOUNT_AS_JSON);
	
			mvc.perform(REQUEST).andExpect(EXPECTED_CONTENT).andExpect(EXPECTED_STATUS);
		}
		
		@Test
		public void Given_NonExistantId_When_GetAccountRequest_Then_ReturnExceptionAndStatusNotFound() throws Exception {
			// GIVEN
			final RequestBuilder REQUEST = get("/getAccount/4")
					.contentType(MediaType.APPLICATION_JSON);
			
			// WHEN, THEN
			final AccountNotFoundException EXPECTED_EXCEPTION =
					new AccountNotFoundException(4);
			final String EXPECTED_MESSAGE = "Account with id (4) does not exist.";
			
			final ResultMatcher statusMatcher = status().isNotFound();
			//final ResultMatcher EXPECTED_ERROR = status().reason(EXPECTED_MESSAGE);
			//final ResultMatcher test2 = jsonPath("$.['message']", is(equalTo(EXPECTED_MESSAGE)));
			final ResultMatcher exceptionMatcher = result -> assertThat(result.getResolvedException().getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass()); 
			final ResultMatcher messageMatcher = result -> assertThat(result.getResolvedException().getMessage()).isEqualTo(EXPECTED_MESSAGE);
			
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(exceptionMatcher)
								.andExpect(messageMatcher);
		}
	
	 // UPDATE //
		
		@Test
		public void Given_ValidArguments_When_UpdateAccountRequest_Then_ReturnUpdatedAccountAndStatusOk() throws Exception {
			// GIVEN
			
			final Account INPUT_ACCOUNT = new Account(
				"Mr",
				"Aaron",
				"Smith",
				"smith-dev",
				"83 Apple Turnover",
				"Bakery Way",
				"Mockassin",
				"Javerley",
				"AP12 1TO",
				"0123 456 7890",
				"asmith@mydomain.com"
			);		
			final String INPUT_ACCOUNT_AS_JSON = mapper.writeValueAsString(INPUT_ACCOUNT);
			final RequestBuilder REQUEST = put("/updateAccount/3")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(INPUT_ACCOUNT_AS_JSON);
			
			// WHEN, THEN
			final Account EXPECTED_ACCOUNT = new Account(
				3,
				"Mr",
				"Aaron",
				"Smith",
				"smith-dev",
				"83 Apple Turnover",
				"Bakery Way",
				"Mockassin",
				"Javerley",
				"AP12 1TO",
				"0123 456 7890",
				"asmith@mydomain.com"
			);
			final String EXPECTED_ACCOUNT_AS_JSON = mapper.writeValueAsString(EXPECTED_ACCOUNT);
			
			final ResultMatcher statusMatcher = status().isOk();
			final ResultMatcher contentMatcher = content().json(EXPECTED_ACCOUNT_AS_JSON);
	
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(contentMatcher);
		}
		
		@Test
		public void Given_NonExistantId_When_UpdateAccountRequest_Then_ReturnExceptionAndStatusNotFound() throws Exception {
			// GIVEN
			final Account INPUT_ACCOUNT = new Account(
				"Mr",
				"Ruvid",
				"Willran",
				"mynewtenant209",
				"83 Apple Turnover",
				"Bakery Way",
				"Mockassin",
				"Javerley",
				"AP12 1TO",
				"0123 456 7890",
				"ruvid.willran@somesuch.com"
			);
			final String INPUT_ACCOUNT_AS_JSON = mapper.writeValueAsString(INPUT_ACCOUNT);
			final RequestBuilder REQUEST = put("/updateAccount/99")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(INPUT_ACCOUNT_AS_JSON);
			
			// WHEN, THEN
			final AccountNotFoundException EXPECTED_EXCEPTION =
					new AccountNotFoundException(99);
			final String EXPECTED_MESSAGE = "Account with id (99) does not exist.";
			
			final ResultMatcher statusMatcher = status().isNotFound();
			final ResultMatcher exceptionMatcher = result -> assertThat(result.getResolvedException().getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass()); 
			final ResultMatcher messageMatcher = result -> assertThat(result.getResolvedException().getMessage()).isEqualTo(EXPECTED_MESSAGE);
			
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(exceptionMatcher)
								.andExpect(messageMatcher);
		}
		
		@Test
		public void Given_ExistingTenantName_When_UpdateAccountRequest_Then_ReturnExceptionAndStatusBadRequest() throws Exception {
			// GIVEN
			final Account INPUT_ACCOUNT = new Account(
				"Mr",
				"Aaron",
				"Smith",
				"qa-training",
				"83 Apple Turnover",
				"Bakery Way",
				"Mockassin",
				"Javerley",
				"AP12 1TO",
				"0123 456 7890",
				"asmith@mydomain.com"
			);
			final String INPUT_ACCOUNT_AS_JSON = mapper.writeValueAsString(INPUT_ACCOUNT);
			final RequestBuilder REQUEST = put("/updateAccount/3")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(INPUT_ACCOUNT_AS_JSON);
			
			// WHEN, THEN
			final TenantNameUnavailableException EXPECTED_EXCEPTION =
					new TenantNameUnavailableException("qa-training");
			final String EXPECTED_MESSAGE = "Tenant name (qa-training) is unavailable for use.";
			
			final ResultMatcher statusMatcher = status().isBadRequest();
			final ResultMatcher exceptionMatcher = result -> assertThat(result.getResolvedException().getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass()); 
			final ResultMatcher messageMatcher = result -> assertThat(result.getResolvedException().getMessage()).isEqualTo(EXPECTED_MESSAGE);
			
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(exceptionMatcher)
								.andExpect(messageMatcher);
		}
	
	 // DELETE //
		
		@Test
		public void Given_ValidId_When_DeleteAccountRequest_Then_ReturnMessageAndStatusOk() throws Exception {
			// GIVEN
			final RequestBuilder REQUEST = delete("/deleteAccount/3");
			
			// WHEN, THEN
			final String EXPECTED_MESSAGE = "Successfully deleted Account with id (3)";
			
			final ResultMatcher statusMatcher = status().isOk();
			final ResultMatcher contentMatcher = content().string(EXPECTED_MESSAGE);
	
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(contentMatcher);
		}
		
		@Test
		public void Given_NonExistantId_When_DeleteAccountRequest_Then_ReturnExceptionAndStatusNotFound() throws Exception {
			// GIVEN
			final RequestBuilder REQUEST = delete("/deleteAccount/99");
			
			// WHEN, THEN
			final AccountNotFoundException EXPECTED_EXCEPTION =
					new AccountNotFoundException(99);
			final String EXPECTED_MESSAGE = "Account with id (99) does not exist.";
			
			final ResultMatcher statusMatcher = status().isNotFound();
			final ResultMatcher exceptionMatcher = result -> assertThat(result.getResolvedException().getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass()); 
			final ResultMatcher messageMatcher = result -> assertThat(result.getResolvedException().getMessage()).isEqualTo(EXPECTED_MESSAGE);
			
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(exceptionMatcher)
								.andExpect(messageMatcher);
		}

	///////////////////
	// CLOUD SERVICE //
	///////////////////
	
		// CREATE //
		
		@Test
		public void Given_ValidArguments_When_CreateCloudServiceRequest_Then_ReturnCreatedServiceAndStatusCreated() throws Exception {
			// GIVEN
			final CloudService INPUT_CLOUD_SERVICE = new CloudService(
				"ASure Virtual Machine (B3)",
				"A virtual-machine with a number of available operating-systems. 2 x vCPU @ 2.4GHz, 8GB RAM, 120GB storage.",
				924
			);
			final String INPUT_CLOUD_SERVICE_AS_JSON = mapper.writeValueAsString(INPUT_CLOUD_SERVICE);
			final RequestBuilder REQUEST = post("/createService")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(INPUT_CLOUD_SERVICE_AS_JSON);
			
			// WHEN, THEN
			final CloudService EXPECTED_CLOUD_SERVICE = new CloudService(
				9,
				"ASure Virtual Machine (B3)",
				"A virtual-machine with a number of available operating-systems. 2 x vCPU @ 2.4GHz, 8GB RAM, 120GB storage.",
				924
			);
			final String EXPECTED_CLOUD_SERVICE_AS_JSON = mapper.writeValueAsString(EXPECTED_CLOUD_SERVICE);
			
			final ResultMatcher statusMatcher = status().isCreated();
			final ResultMatcher contentMatcher = content().json(EXPECTED_CLOUD_SERVICE_AS_JSON);
	
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(contentMatcher);
		}
	
		// READ //
	
		@Test
		public void Given_NoArguments_When_GetCloudServiceRequest_Then_ReturnAllServicesAndStatusOk() throws Exception {
			// GIVEN
			final RequestBuilder REQUEST = get("/getServices");
			
			// WHEN, THEN
			final CloudService[] EXPECTED_CLOUD_SERVICES = {
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
			final List<CloudService> EXPECTED_CLOUD_SERVICES_LIST = Arrays.asList(EXPECTED_CLOUD_SERVICES);
			final String EXPECTED_CLOUD_SERVICES_AS_JSON = mapper.writeValueAsString(EXPECTED_CLOUD_SERVICES_LIST);
			
			final ResultMatcher statusMatcher = status().isOk();
			final ResultMatcher contentMatcher = content().json(EXPECTED_CLOUD_SERVICES_AS_JSON);
	
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(contentMatcher);
		}
	
		@Test
		public void Given_ValidId_When_GetCloudServiceRequest_Then_ReturnServiceAndStatusOk() throws Exception {
			// GIVEN
			final RequestBuilder REQUEST = get("/getService/2");
			
			// WHEN, THEN
			final CloudService EXPECTED_CLOUD_SERVICE = new CloudService(
				2,
				"ASure Web Site (Standard)",
				"Host a web-site with static content. 500MB storage. Two instances, providing high-availability.",
				495
			);
			final String EXPECTED_CLOUD_SERVICE_AS_JSON = mapper.writeValueAsString(EXPECTED_CLOUD_SERVICE);
			final ResultMatcher EXPECTED_STATUS = status().isOk();
			final ResultMatcher EXPECTED_CONTENT = content().json(EXPECTED_CLOUD_SERVICE_AS_JSON);
	
			mvc.perform(REQUEST).andExpect(EXPECTED_CONTENT).andExpect(EXPECTED_STATUS);
		}
		
		@Test
		public void Given_NonExistantId_When_GetCloudServiceRequest_Then_ReturnExceptionAndStatusNotFound() throws Exception {
			// GIVEN
			final RequestBuilder REQUEST = get("/getService/10")
					.contentType(MediaType.APPLICATION_JSON);
			
			// WHEN, THEN
			final CloudServiceNotFoundException EXPECTED_EXCEPTION =
					new CloudServiceNotFoundException(4);
			final String EXPECTED_MESSAGE = "CloudService with id (10) does not exist.";
			
			final ResultMatcher statusMatcher = status().isNotFound();
			final ResultMatcher exceptionMatcher = result -> assertThat(result.getResolvedException().getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass()); 
			final ResultMatcher messageMatcher = result -> assertThat(result.getResolvedException().getMessage()).isEqualTo(EXPECTED_MESSAGE);
			
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(exceptionMatcher)
								.andExpect(messageMatcher);
		}
	
	 // UPDATE //
		
		@Test
		public void Given_ValidArguments_When_UpdateCloudServiceRequest_Then_ReturnUpdatedServiceAndStatusOk() throws Exception {
			// GIVEN
			
			final CloudService INPUT_CLOUD_SERVICE = new CloudService(
				"ASure Web Application (Standard A1)",
				"Host a back-end web-application using PHP, ASP.NET, or Java. 1GB storage. Single instance.",
				778
			);
			final String INPUT_CLOUD_SERVICE_AS_JSON = mapper.writeValueAsString(INPUT_CLOUD_SERVICE);
			final RequestBuilder REQUEST = put("/updateService/4")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(INPUT_CLOUD_SERVICE_AS_JSON);
			
			// WHEN, THEN
			final CloudService EXPECTED_CLOUD_SERVICE = new CloudService(
				4,
				"ASure Web Application (Standard A1)",
				"Host a back-end web-application using PHP, ASP.NET, or Java. 1GB storage. Single instance.",
				778
			);

			final String EXPECTED_CLOUD_SERVICE_AS_JSON = mapper.writeValueAsString(EXPECTED_CLOUD_SERVICE);
			
			final ResultMatcher statusMatcher = status().isOk();
			final ResultMatcher contentMatcher = content().json(EXPECTED_CLOUD_SERVICE_AS_JSON);
	
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(contentMatcher);
		}
		
		@Test
		public void Given_NonExistantId_When_UpdateCloudServiceRequest_Then_ReturnExceptionAndStatusNotFound() throws Exception {
			// GIVEN
			final CloudService INPUT_CLOUD_SERVICE = new CloudService(
				"ASure Web Application (Standard A1)",
				"Host a back-end web-application using PHP, ASP.NET, or Java. 1GB storage. Single instance.",
				778
			);
			final String INPUT_CLOUD_SERVICE_AS_JSON = mapper.writeValueAsString(INPUT_CLOUD_SERVICE);
			final RequestBuilder REQUEST = put("/updateService/10")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(INPUT_CLOUD_SERVICE_AS_JSON);
			
			// WHEN, THEN
			final CloudServiceNotFoundException EXPECTED_EXCEPTION =
					new CloudServiceNotFoundException(99);
			final String EXPECTED_MESSAGE = "CloudService with id (10) does not exist.";
			
			final ResultMatcher statusMatcher = status().isNotFound();
			final ResultMatcher exceptionMatcher = result -> assertThat(result.getResolvedException().getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass()); 
			final ResultMatcher messageMatcher = result -> assertThat(result.getResolvedException().getMessage()).isEqualTo(EXPECTED_MESSAGE);
			
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(exceptionMatcher)
								.andExpect(messageMatcher);
		}
	
	 // DELETE //
		
		@Test
		public void Given_ValidId_When_DeleteCloudServiceRequest_Then_ReturnMessageAndStatusOk() throws Exception {
			// GIVEN
			final RequestBuilder REQUEST = delete("/deleteService/3");
			
			// WHEN, THEN
			final String EXPECTED_MESSAGE = "Successfully deleted CloudService with id (3)";
			
			final ResultMatcher statusMatcher = status().isOk();
			final ResultMatcher contentMatcher = content().string(EXPECTED_MESSAGE);
	
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(contentMatcher);
		}
		
		@Test
		public void Given_NonExistantId_When_DeleteCloudServiceRequest_Then_ReturnExceptionAndStatusNotFound() throws Exception {
			// GIVEN
			final RequestBuilder REQUEST = delete("/deleteService/99");
			
			// WHEN, THEN
			final CloudServiceNotFoundException EXPECTED_EXCEPTION =
					new CloudServiceNotFoundException(99);
			final String EXPECTED_MESSAGE = "CloudService with id (99) does not exist.";
			
			final ResultMatcher statusMatcher = status().isNotFound();
			final ResultMatcher exceptionMatcher = result -> assertThat(result.getResolvedException().getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass()); 
			final ResultMatcher messageMatcher = result -> assertThat(result.getResolvedException().getMessage()).isEqualTo(EXPECTED_MESSAGE);
			
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(exceptionMatcher)
								.andExpect(messageMatcher);
		}
}
