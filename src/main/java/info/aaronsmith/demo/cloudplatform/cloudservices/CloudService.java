package info.aaronsmith.demo.cloudplatform.cloudservices;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class CloudService {
	// Service is the entity modelling a Cloud service (product)
	// that the platform offers to customers.
	// Not to be confused with the service classes that we use 
	// to manage the business logic of entities. 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String name;

	@Column(length = 2040)
	private String description;	
	
    @OneToMany(
		cascade = CascadeType.ALL,
		orphanRemoval = true)
	@JoinColumn(name = "id")
    private List<CloudServiceTerm> serviceTerms;
	
	public CloudService() {}
	
	public CloudService(
		Integer id,
		String name,
		String description,
		List<CloudServiceTerm> serviceTerms
	) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.serviceTerms = serviceTerms;
	}
	
	public CloudService(
		String name,
		String description,
		List<CloudServiceTerm> serviceTerms
	) {
		this.name = name;
		this.description = description;
		this.serviceTerms = serviceTerms;
	}

	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CloudServiceTerm> getServiceTerms() {
		return serviceTerms;
	}

	public void setServiceTerms(List<CloudServiceTerm> serviceTerms) {
		this.serviceTerms = serviceTerms;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, serviceTerms);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CloudService)) {
			return false;
		}
		CloudService other = (CloudService) obj;
		return Objects.equals(id, other.id) && 
			   Objects.equals(description, other.description) && 
			   Objects.equals(name, other.name) && 
			   Objects.equals(serviceTerms, other.serviceTerms);
	}

	@Override
	public String toString() {
		return "CloudService [id=" + id + ", name=" + name + ", description=" + description + ", serviceTerms=" + serviceTerms + "]";
	}
}
