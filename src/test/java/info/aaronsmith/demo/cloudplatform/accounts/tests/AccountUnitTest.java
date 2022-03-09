package info.aaronsmith.demo.cloudplatform.accounts.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import info.aaronsmith.demo.cloudplatform.accounts.Account;

@SpringBootTest
public class AccountUnitTest {

	@Test
	public void Given_AccountWithDifferentID_When_CompareWithEquals_Then_ReturnFalse() {

		// GIVEN
		Account account1 = new Account(
			1,
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		Account account2 = new Account(
			2,
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		
		// WHEN
		boolean actual = account1.equals(account2);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_AccountWithDifferentName_When_CompareWithEquals_Then_ReturnFalse() {

		// GIVEN
		Account account1 = new Account(
			1,
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		Account account2 = new Account(
			1,
			"Mr",
			"Aaron",
			"Smith",
			"NOTasuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		
		// WHEN
		boolean actual = account1.equals(account2);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_AccountWithDifferentNameAndId_When_CompareWithEquals_Then_ReturnFalse() {

		// GIVEN
		Account account1 = new Account(
			1,
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		Account account2 = new Account(
			2,
			"Mr",
			"Aaron",
			"Smith",
			"NOTasuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		
		// WHEN
		boolean actual = account1.equals(account2);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_TwoDifferentObjectTypes_When_CompareWithEquals_Then_ReturnFalse() {
		// GIVEN
		Account account = new Account(
			1,
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		String myString = new String("This is not an account.");
		
		// WHEN
		boolean actual = account.equals(myString);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_NullInput_When_CompareWithEquals_Then_ReturnFalse() {
		// GIVEN
		Account account1 = new Account(
			1,
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		Account account2 = null;
		
		// WHEN
		boolean actual = account1.equals(account2);
		
		// THEN
		assertThat(actual).isFalse();
	}
	
	@Test
	public void Given_AccountWithSameAttributes_When_CompareWithEquals_Then_ReturnTrue() {

		// GIVEN
		Account account1 = new Account(
			1,
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		Account account2 = new Account(
			1,
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		
		// WHEN
		boolean actual = account1.equals(account2);
		
		// THEN
		assertThat(actual).isTrue();
	}
	
}
