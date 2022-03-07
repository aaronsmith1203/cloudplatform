package info.aaronsmith.demo.cloudplatform.accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BillingPeriod {
	// 'BillingPeriod' is the entity modelling the cost of
	// subscribing to a service over a period of time. A customer
	// can commit to a service for longer periods for lower cost
	// than paying monthly.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public BillingPeriod() { }
	
}
