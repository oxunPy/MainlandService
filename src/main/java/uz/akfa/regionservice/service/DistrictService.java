package uz.akfa.regionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.akfa.regionservice.models.Country;
import uz.akfa.regionservice.models.District;
import uz.akfa.regionservice.repos.CountryRepository;
import uz.akfa.regionservice.repos.DistrictRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictRepository districtRepository;

    public List<District> findAll(){
        return districtRepository.findAll();
    }

    public District save(District district){    // saving new entity or update existing entity
        return districtRepository.save(district);
    }

    public District getById(Long id){
        // check if it exists or not.
        if(existsById(id)){
            return districtRepository.findById(id).get();
        }
        return null;
    }

    // check if it exists by given id.
    public boolean existsById(Long id){
        return districtRepository.existsById(id);
    }

    public boolean delete(Long id){
        if(existsById(id)){
            districtRepository.delete(getById(id));
            return true;
        }
        return false;
    }


}
