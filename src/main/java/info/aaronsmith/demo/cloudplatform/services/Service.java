package info.aaronsmith.demo.cloudplatform.services;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Service {
	// Service is the entity modelling a Cloud service (product)
	// that the platform offers to customers.
	// Not to be confused with the service classes that we use 
	// to manage the business logic of entities. 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Service() { }
	
}
