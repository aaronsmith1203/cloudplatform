package info.aaronsmith.demo.cloudplatform.accounts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TenantNameUnavailableException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TenantNameUnavailableException(String name) {
		super("Tenant name (" + name + ") is unavailable for use.");
	}
}
