package info.aaronsmith.demo.cloudplatform.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

	
@Service
public class SubscriptionService {
	// 'Subscription' is the entity modelling an ongoing commitment
	// to pay-for and make-use-of a Cloud service (product).
	
	private SubscriptionRepo repo;
	
	@Autowired
	public SubscriptionService(SubscriptionRepo repo) {
		this.repo = repo;
	}
}
