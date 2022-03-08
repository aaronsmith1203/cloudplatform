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

@SpringBootTest
@ActiveProfiles("test")
@Sql(
	scripts = { 
		"classpath:account-schema.sql",
		"classpath:account-data.sql"
	},
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
@AutoConfigureMockMvc
public class ControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	// CREATE
	
		@Test
		public void Given_ValidArguments_When_CreateAccountRequest_Then_ReturnCreatedAccountAndStatusCreated() throws Exception {
			// GIVEN
			final Account INPUT_ACCOUNT = new Account("mynewtenant209");
			final String INPUT_ACCOUNT_AS_JSON = mapper.writeValueAsString(INPUT_ACCOUNT);
			final RequestBuilder REQUEST = post("/createAccount")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(INPUT_ACCOUNT_AS_JSON);
			
			// WHEN, THEN
			final Account EXPECTED_ACCOUNT = new Account(4, "mynewtenant209");
			final String EXPECTED_ACCOUNT_AS_JSON = mapper.writeValueAsString(EXPECTED_ACCOUNT);
			
			final ResultMatcher statusMatcher = status().isCreated();
			final ResultMatcher contentMatcher = content().json(EXPECTED_ACCOUNT_AS_JSON);
	
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(contentMatcher);
		}
		
		@Test
		public void Given_ExistingTenantName_When_CreateAccountRequest_Then_ReturnExceptionAndStatusBadRequest() throws Exception {
			// GIVEN
			final Account INPUT_ACCOUNT = new Account("qa-training");
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
	
	// READ
	
		@Test
		public void Given_NoArguments_When_GetAccountRequest_Then_ReturnAllAccountsAndStatusOk() throws Exception {
			// GIVEN
			final RequestBuilder REQUEST = get("/getAccounts");
			
			// WHEN, THEN
			final Account[] EXPECTED_ACCOUNTS = {
				new Account(1, "asure"),
				new Account(2, "qa-training"),
				new Account(3, "smith-development")
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
			final Account EXPECTED_ACCOUNT = new Account(2, "qa-training");
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
	
	// UPDATE
		
		@Test
		public void Given_ValidArguments_When_UpdateAccountRequest_Then_ReturnUpdatedAccountAndStatusOk() throws Exception {
			// GIVEN
			
			final Account INPUT_ACCOUNT = new Account("smith-dev");
			final String INPUT_ACCOUNT_AS_JSON = mapper.writeValueAsString(INPUT_ACCOUNT);
			final RequestBuilder REQUEST = put("/updateAccount/3")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(INPUT_ACCOUNT_AS_JSON);
			
			// WHEN, THEN
			final Account EXPECTED_ACCOUNT = new Account(3, "smith-dev");
			final String EXPECTED_ACCOUNT_AS_JSON = mapper.writeValueAsString(EXPECTED_ACCOUNT);
			
			final ResultMatcher statusMatcher = status().isOk();
			final ResultMatcher contentMatcher = content().json(EXPECTED_ACCOUNT_AS_JSON);
	
			mvc.perform(REQUEST).andExpect(statusMatcher)
								.andExpect(contentMatcher);
		}
		
		@Test
		public void Given_NonExistantId_When_UpdateAccountRequest_Then_ReturnExceptionAndStatusNotFound() throws Exception {
			// GIVEN
			final Account INPUT_ACCOUNT = new Account("anothertenant");
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
			final Account INPUT_ACCOUNT = new Account("qa-training");
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
	
	// DELETE
		
		@Test
		public void Given_ValidId_When_DeleteAccountRequest_Then_ReturnMessageAndStatusOk() throws Exception {
			// GIVEN
			final RequestBuilder REQUEST = delete("/deleteAccount/3");
			
			// WHEN, THEN
			final String EXPECTED_MESSAGE = "Successfully deleted account with id (3)";
			
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
	
}
