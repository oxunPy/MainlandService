package uz.akfa.regionservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.akfa.regionservice.models.Mainland;

@Repository
public interface MainlandRepository extends JpaRepository<Mainland, Long> {
}
