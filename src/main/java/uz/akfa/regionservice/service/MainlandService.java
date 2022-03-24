package uz.akfa.regionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.akfa.regionservice.models.Country;
import uz.akfa.regionservice.models.Mainland;
import uz.akfa.regionservice.models.Region;
import uz.akfa.regionservice.repos.CountryRepository;
import uz.akfa.regionservice.repos.MainlandRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainlandService {

    private final MainlandRepository mainlandRepository;
    private final CountryRepository countryRepository;

    public List<Mainland> findAll(){
        return mainlandRepository.findAll();
    }

    public Mainland save(Mainland mainland){    // saving new entity or update existing entity
        return mainlandRepository.save(mainland);
    }

    public Mainland getById(Long id){
        // check if it exists or not.
        if(existsById(id)){
            return mainlandRepository.findById(id).get();
        }
        return null;
    }

    // check if it exists by given id.
    public boolean existsById(Long id){
        return mainlandRepository.existsById(id);
    }

    public boolean delete(Long id){
        if(existsById(id)){
            mainlandRepository.delete(getById(id));
            return true;
        }
        return false;
    }

    public boolean assignNewCountry(Long mainland_id, Long country_id){
        // check for validity for both indexes, so that we'll need to pull countRepos.
        if(existsById(mainland_id) && countryRepository.existsById(country_id)){
            // get mainland and country by the given ids.
            Mainland mainland = getById(mainland_id);
            Country country = countryRepository.getById(country_id);
            country.setMainland(mainland);         // set a country for the given region.
            countryRepository.save(country);      // then update the region.
            return true;
        }
        return false;
    }

    public boolean deleteCountry(Long mainland_id, Long country_id){
        // check for validity for both indexes, so that we'll need to pull countRepos.
        if(existsById(mainland_id) && countryRepository.existsById(country_id)){
            // get mainland and country by the given ids.
            Mainland mainland = getById(mainland_id);
            Country country = countryRepository.getById(country_id);
            country.setMainland(null);         // remove the country for the given region.
            countryRepository.save(country);      // then update the country.
            return true;
        }
        return false;
    }
}
