package info.aaronsmith.demo.cloudplatform.accounts.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import info.aaronsmith.demo.cloudplatform.accounts.Account;

@SpringBootTest
public class AccountUnitTests {

	@Test
	public void Given_AccountWithDifferentID_When_CompareWithEquals_Then_ReturnFalse() {

		// GIVEN
		Account account1 = new Account(1, "asuretenant");
		Account account2 = new Account(2, "asuretenant");
		
		// WHEN
		boolean actual = account1.equals(account2);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_AccountWithDifferentName_When_CompareWithEquals_Then_ReturnFalse() {

		// GIVEN
		Account account1 = new Account(1, "asuretenant");
		Account account2 = new Account(1, "notasuretenant");
		
		// WHEN
		boolean actual = account1.equals(account2);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_AccountWithDifferentNameAndId_When_CompareWithEquals_Then_ReturnFalse() {

		// GIVEN
		Account account1 = new Account(1, "asuretenant");
		Account account2 = new Account(2, "notasuretenant");
		
		// WHEN
		boolean actual = account1.equals(account2);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_TwoDifferentObjectTypes_When_CompareWithEquals_Then_ReturnFalse() {
		// GIVEN
		Account account = new Account(1, "asuretenant");
		String myString = new String("This is not an account.");
		
		// WHEN
		boolean actual = account.equals(myString);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_AccountWithSameAttributes_When_CompareWithEquals_Then_ReturnTrue() {

		// GIVEN
		Account account1 = new Account(1, "asuretenant");
		Account account2 = new Account(1, "asuretenant");
		
		// WHEN
		boolean actual = account1.equals(account2);
		
		// THEN
		assertThat(actual).isTrue();
	}
	
}
