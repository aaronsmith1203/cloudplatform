package info.aaronsmith.demo.cloudplatform.cloudservices;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@NotNull
	private Integer costInPence = 0;
	
	public CloudService() {}
	
	public CloudService(
		Integer id,
		String name,
		String description,
		Integer costInPence
	) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.costInPence = costInPence;
	}
	
	public CloudService(
		String name,
		String description,
		Integer costInPence
	) {
		this.name = name;
		this.description = description;
		this.costInPence = costInPence;
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

	public Integer getCostInPence() {
		return costInPence;
	}

	public void setCostInPence(Integer costInPence) {
		this.costInPence = costInPence;
	}

	@Override
	public int hashCode() {
		return Objects.hash(costInPence, description, id, name);
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
		return Objects.equals(costInPence, other.costInPence) && 
			   Objects.equals(description, other.description) && 
			   Objects.equals(id, other.id) && 
			   Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "CloudService [id=" + id + ", name=" + name + ", description=" + description + ", costInPence=" + costInPence + "]";
	}
}
