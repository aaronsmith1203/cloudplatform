package info.aaronsmith.demo.cloudplatform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {
	// Service is the entity modelling a Cloud service (product)
	// that the platform offers to customers.
	// This class is the 'service' used to manage the business 
	// logic of Cloud 'service' (product) entities.
	
	private ServiceRepo repo;
	
	@Autowired
	public ServiceService(ServiceRepo repo) {
		this.repo = repo;
	}
}
