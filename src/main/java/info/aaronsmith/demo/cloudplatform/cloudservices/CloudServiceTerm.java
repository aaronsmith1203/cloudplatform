package info.aaronsmith.demo.cloudplatform.cloudservices;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CloudServiceTerm {
	// CloudServiceTerm is the entity modelling the different
	// purchase terms offered to customers. A CloudService will
	// often have multiple subscription (renewal) lengths that
	// offer price-breaks for committing to a longer subscription.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private Integer costInPence = 0;

	@NotNull
	private Integer billingPeriod; // in months

	public CloudServiceTerm() {
	}

	public CloudServiceTerm(
			Integer id,
			Integer costInPence,
			Integer billingPeriod) {
		this.id = id;
		this.costInPence = costInPence;
		this.billingPeriod = billingPeriod;
	}

	public CloudServiceTerm(
			Integer costInPence,
			Integer billingPeriod) {
		this.costInPence = costInPence;
		this.billingPeriod = billingPeriod;
	}

	public Integer getId() {
		return id;
	}

	public Integer getCostInPence() {
		return costInPence;
	}

	public void setCostInPence(Integer costInPence) {
		this.costInPence = costInPence;
	}

	public Integer getBillingPeriod() {
		return billingPeriod;
	}

	public void setBillingPeriod(Integer billingPeriod) {
		this.billingPeriod = billingPeriod;
	}

	@Override
	public int hashCode() {
		return Objects.hash(billingPeriod, costInPence, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CloudServiceTerm)) {
			return false;
		}
		CloudServiceTerm other = (CloudServiceTerm) obj;
		return id == other.id &&
				Objects.equals(billingPeriod, other.billingPeriod) &&
				Objects.equals(costInPence, other.costInPence);
	}

	@Override
	public String toString() {
		return "CloudServiceTerm [" +
				"id=" + id + ", " +
				"costInPence=" + costInPence + ", " +
				"billingPeriod=" + billingPeriod + "]";
	}
}
