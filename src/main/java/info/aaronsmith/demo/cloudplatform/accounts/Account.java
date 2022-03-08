package info.aaronsmith.demo.cloudplatform.accounts;

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
	
	@NotNull
	@Column(unique = true, nullable = false)
	private String tenantName;
	
	public Account() { }
	
	public Account(Integer id, String tenantName) {
		this.id = id;
		this.tenantName = tenantName;
	}
	
	public Account(String tenantName) {
		this.tenantName = tenantName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
}
