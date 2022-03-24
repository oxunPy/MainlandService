package uz.akfa.regionservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.akfa.regionservice.models.Country;
import uz.akfa.regionservice.models.Region;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    /*
    @Query("update id from Region region and Country country " +
            "where region.country_id == :country_id and country.getRegions().contains(region)")
    public void deleteAssignedRegion(@Param("country_id") long country_id, Region region);
    */
}

