package info.aaronsmith.demo.cloudplatform.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingPeriodService {
	// 'BillingPeriod' is the entity modelling the cost of
	// subscribing to a service over a period of time. A customer
	// can commit to a service for longer periods for lower cost
	// than paying monthly.
	
	private BillingPeriodRepo repo;
	
	@Autowired
	public BillingPeriodService(BillingPeriodRepo repo) {
		this.repo = repo;
	}
}
