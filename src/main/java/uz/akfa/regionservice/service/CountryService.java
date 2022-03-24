package uz.akfa.regionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.akfa.regionservice.models.Country;
import uz.akfa.regionservice.models.Region;
import uz.akfa.regionservice.repos.CountryRepository;
import uz.akfa.regionservice.repos.RegionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;

    public List<Country> findAll(){
        return countryRepository.findAll();
    }

    public Country save(Country country){    // saving new entity or update existing entity
        return countryRepository.save(country);
    }

    // get a country by a given id.
    public Country getById(Long id){
        // check if it exists or not.
        if(existsById(id)){
            return countryRepository.findById(id).get();
        }
        return null;
    }

    // check if it exists by given id.
    public boolean existsById(Long id){
        return countryRepository.existsById(id);
    }

    // deleting a country by a given id.
    public boolean delete(Long id){
        if(existsById(id)){
            countryRepository.delete(getById(id));
            return true;
        }
        return false;
    }

    public Boolean assignNewRegion(Long country_id, Long region_id){
        // check for validity for both indexes, so that we'll need to pull regRepos.
        if(existsById(country_id) && regionRepository.existsById(region_id)){
            // get country and region by the given ids.
            Country country = getById(country_id);
            Region region = regionRepository.getById(region_id);
            region.setCountry(country);         // set a country for the given region.
            regionRepository.save(region);      // then update the region.
            return true;
        }
        return false;
    }

    public Boolean deleteRegion(Long country_id, Long region_id){
        // check for validity for both indexes, so that we'll need to pull regRepos.
        if(existsById(country_id) && regionRepository.existsById(region_id)){
            // get country and region by the given ids.
            Country country = getById(country_id);
            Region region = regionRepository.getById(region_id);
            region.setCountry(null);         // set a country for the given region.
            regionRepository.save(region);      // then update the region.
            return true;
        }
        return false;
    }
    public void delete(Country country){
        countryRepository.delete(country);
    }
}
