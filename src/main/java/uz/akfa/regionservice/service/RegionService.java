package uz.akfa.regionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.akfa.regionservice.models.Country;
import uz.akfa.regionservice.models.District;
import uz.akfa.regionservice.models.Region;
import uz.akfa.regionservice.repos.CountryRepository;
import uz.akfa.regionservice.repos.DistrictRepository;
import uz.akfa.regionservice.repos.RegionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;

    public List<Region> findAll(){
        return regionRepository.findAll();
    }

    public Region save(Region region){    // saving new entity or update existing entity
        return regionRepository.save(region);
    }

    public Region getById(Long id){
        // check if it exists or not.
        if(existsById(id)){
            return regionRepository.findById(id).get();
        }
        return null;
    }

    // check if it exists by given id.
    public boolean existsById(Long id){
        return regionRepository.existsById(id);
    }

    public boolean delete(Long id){
        if(existsById(id)){
            regionRepository.delete(getById(id));
            return true;
        }
        return false;
    }

    public boolean assignNewDistrict(Long region_id, Long district_id){
        // check for validity for both indexes, so that we'll need to pull distRepos.
        if(existsById(region_id) && districtRepository.existsById(district_id)){
            // get region and district by the given ids.
            Region region = getById(region_id);
            District district = districtRepository.getById(district_id);
            district.setRegion(region);         // set a region for the given district.
            districtRepository.save(district);      // then update the district.
            return true;
        }
        return false;
    }

    public boolean deleteDistrict(Long region_id, Long district_id){
        // check for validity for both indexes, so that we'll need to pull distRepos.
        if(existsById(region_id) && districtRepository.existsById(district_id)){
            // get region and district by the given ids.
            Region region = getById(region_id);
            District district = districtRepository.getById(district_id);
            district.setRegion(null);         // remove the region for the given district.
            districtRepository.save(district);      // then update the district.
            return true;
        }
        return false;
    }

}
