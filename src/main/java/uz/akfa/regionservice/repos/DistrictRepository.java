package uz.akfa.regionservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.akfa.regionservice.models.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

}
