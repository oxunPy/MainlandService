package uz.akfa.regionservice.repos;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.akfa.regionservice.models.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
