package info.aaronsmith.demo.cloudplatform.cloudservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudServiceService {
	// CloudService is the entity modelling a product
	// that the platform offers to customers.
	// This class is the 'service' used to manage the business 
	// logic of CloudService (product) entities.
	
	private CloudServiceRepo repo;
	
	@Autowired
	public CloudServiceService(CloudServiceRepo repo) {
		this.repo = repo;
	}
	
	private CloudService saveServiceToDatabase(CloudService service) {
		return repo.save(service);
	}
	
	// CREATE
	public CloudService createService(CloudService service) {
		return saveServiceToDatabase(service);
	}
	
	// READ
	public List<CloudService> getService() {
		return repo.findAll();
	}
	
	// READ
	public CloudService getService(Integer id) throws CloudServiceNotFoundException {
		try {
			return repo.findById(id).get();
		}
		catch (Exception e) {
			throw new CloudServiceNotFoundException(id);
		}
	}
	
	// UPDATE
	public CloudService updateService(Integer id, CloudService service) throws CloudServiceNotFoundException {
		// get existing CloudService from database
		CloudService foundService = getService(id);
		
		// update CloudService attributes
		foundService.setName(service.getName());
		foundService.setDescription(service.getDescription());
		foundService.setCostInPence(service.getCostInPence());
		
		// save CloudService back to database
		return saveServiceToDatabase(foundService);
	}
	
	// DELETE
	public void deleteService(Integer id) throws CloudServiceNotFoundException {
		try {
			repo.deleteById(id);
		}
		catch (Exception e) {
			throw new CloudServiceNotFoundException(id);
		}
	}
}
