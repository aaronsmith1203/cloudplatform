package info.aaronsmith.demo.cloudplatform.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

    public Account findAccountByEmailAddress(String emailAddress);
}
