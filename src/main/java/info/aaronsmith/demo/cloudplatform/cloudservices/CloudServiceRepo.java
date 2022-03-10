package info.aaronsmith.demo.cloudplatform.cloudservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudServiceRepo extends JpaRepository<CloudService, Integer> {

}
