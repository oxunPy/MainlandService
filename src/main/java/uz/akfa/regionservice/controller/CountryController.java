package uz.akfa.regionservice.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.akfa.regionservice.models.Country;
import uz.akfa.regionservice.models.Mainland;
import uz.akfa.regionservice.repos.MainlandRepository;
import uz.akfa.regionservice.service.CountryService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/countries")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(countryService.findAll());
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        return ResponseEntity.ok(countryService.getById(id));
    }

    @PostMapping("/countries")
    public ResponseEntity addNewCountry(@RequestBody Country country){
        return ResponseEntity.ok(countryService.save(country));
    }

    @PutMapping("/countries/{id}")
    public ResponseEntity updateCountry(@PathVariable Long id, @RequestBody Country country){
        if(!countryService.existsById(id)) return ResponseEntity.ok("Error id occurred!");

        Country countryToUpdate = countryService.getById(id);
        countryToUpdate.setName(country.getName());
        countryService.save(countryToUpdate);
        return ResponseEntity.ok(countryToUpdate);
    }


    @PostMapping("countries/{country_id}/regions/{region_id}")
    public ResponseEntity assignNewRegion(@PathVariable Long country_id, @PathVariable Long region_id){
        // assigning new region through our boolean type method that checks validity of ids.
        if(countryService.assignNewRegion(country_id, region_id)){
            return ResponseEntity.ok("New region has been added recently");
        }
        return ResponseEntity.ok("Id error occurred");
    }
}
