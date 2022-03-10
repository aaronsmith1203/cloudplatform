package info.aaronsmith.demo.cloudplatform.cloudservices;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import info.aaronsmith.demo.cloudplatform.utils.Cost;

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
	@Column(nullable = false)
	private String name;

	private String description;	
	
	private Integer costInPence = 0;
	
	@Transient // not persisted in database
	private String cost;
	
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
		this.setCost(this.costInPence);
	}
	
	public CloudService(
		String name,
		String description,
		Integer costInPence
	) {
		this.name = name;
		this.description = description;
		this.costInPence = costInPence;
		this.setCost(this.costInPence);
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
		this.setCost(this.costInPence);
	}

	public String getCost() {
		this.setCost(this.costInPence);
		return cost;
	}
	
	// private so not called outside class
	private void setCost(Integer pence) {
		this.cost = (new Cost(pence)).toString();
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
		return "Service [id=" + id + ", name=" + name + ", description=" + description + ", costInPence=" + costInPence
				+ ", cost=" + cost + "]";
	}
}
