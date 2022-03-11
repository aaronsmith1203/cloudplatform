package info.aaronsmith.demo.cloudplatform.accounts;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Account {
	// 'Account' is the entity modelling a user account
	// used to access the Cloud platform.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	@Column(unique = true, nullable = false)
	private String tenantName;
	
	@NotNull
	private String addressLine1;
	private String addressLine2;
	
	@NotNull
	private String city;
	private String county;
	
	@NotNull
	private String postCode;
	
	// need to validate this
	private String telephoneNumber;
	
	@NotNull
	@Column(unique = true, nullable = false)
	// need to validate this
	private String emailAddress;
	
	public Account() { }
	
	public Account(
			Integer id,
			String title,
			String firstName,
			String lastName,
			String tenantName,
			String addressLine1,
			String addressLine2,
			String city,
			String county,
			String postCode,
			String telephoneNumber,
			String emailAddress
	) {
		this.id = id;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.tenantName = tenantName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.county = county;
		this.postCode = postCode;
		this.telephoneNumber = telephoneNumber;
		this.emailAddress = emailAddress;
	}
	
	public Account(
			String title,
			String firstName,
			String lastName,
			String tenantName,
			String addressLine1,
			String addressLine2,
			String city,
			String county,
			String postCode,
			String telephoneNumber,
			String emailAddress
	) {
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.tenantName = tenantName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.county = county;
		this.postCode = postCode;
		this.telephoneNumber = telephoneNumber;
		this.emailAddress = emailAddress;
	}

	public Integer getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getTenantName() {
		return tenantName;
	}
	
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", title=" + title + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", tenantName=" + tenantName + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", city=" + city + ", county=" + county + ", postCode=" + postCode + ", telephoneNumber="
				+ telephoneNumber + ", emailAddress=" + emailAddress + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, city, county, emailAddress, firstName, id, lastName, postCode,
				telephoneNumber, tenantName, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Account)) {
			return false;
		}
		Account other = (Account) obj;
		return Objects.equals(addressLine1, other.addressLine1) 
				&& Objects.equals(addressLine2, other.addressLine2)
				&& Objects.equals(city, other.city) 
				&& Objects.equals(county, other.county)
				&& Objects.equals(emailAddress, other.emailAddress) 
				&& Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) 
				&& Objects.equals(lastName, other.lastName)
				&& Objects.equals(postCode, other.postCode) 
				&& Objects.equals(telephoneNumber, other.telephoneNumber)
				&& Objects.equals(tenantName, other.tenantName) 
				&& Objects.equals(title, other.title);
	}	
}
