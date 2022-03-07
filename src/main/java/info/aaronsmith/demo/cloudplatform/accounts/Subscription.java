package info.aaronsmith.demo.cloudplatform.accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subscription {
	// 'Subscription' is the entity modelling an ongoing commitment
	// to pay-for and make-use-of a Cloud service (product).

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Subscription() { }
	
}
