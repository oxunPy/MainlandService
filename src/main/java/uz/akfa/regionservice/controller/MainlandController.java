package uz.akfa.regionservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.akfa.regionservice.models.Mainland;
import uz.akfa.regionservice.repos.MainlandRepository;
import uz.akfa.regionservice.service.CountryService;
import uz.akfa.regionservice.service.MainlandService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainlandController {

    private final MainlandService mainlandService;
    private final CountryService countryService;

    @GetMapping("/mainlands")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(mainlandService.findAll());
    }

    @GetMapping("/mainlands/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        return ResponseEntity.ok(mainlandService.getById(id));
    }

    @PostMapping("/mainlands")
    public ResponseEntity addNewMainland(@RequestBody Mainland mainland){
        return ResponseEntity.ok(mainlandService.save(mainland));
    }

    @PutMapping("/mainlands/{id}")
    public ResponseEntity updateMainland(@PathVariable Long id, @RequestBody Mainland newMainland){
        // check for the validity of id.
        if(!mainlandService.existsById(id)) return ResponseEntity.ok("Mainland not found!");

        Mainland mainlandToUpdate = mainlandService.getById(id);
        // update name of mainland extra check for whether non-value or not.
        mainlandToUpdate.setName(newMainland.getName() != null ? newMainland.getName() : mainlandToUpdate.getName());
        mainlandService.save(mainlandToUpdate);
        return ResponseEntity.ok(mainlandToUpdate);
    }

    @PostMapping("/mainlands/{mainland_id}/countries/{country_id}")
    public ResponseEntity assignNewCountry(@PathVariable Long mainland_id, @PathVariable Long country_id){
        // assigning new country through our boolean type method that checks validity.
        if(mainlandService.assignNewCountry(mainland_id, country_id)){
            return ResponseEntity.ok("New country has been added recently.");
        }
        return ResponseEntity.ok("Id error has been occurred");
    }

    @DeleteMapping("/mainlands/{mainland_id}/countries/{country_id}")
    public ResponseEntity deleteCountry(@PathVariable Long mainland_id, @PathVariable Long country_id){
        // assigning new country through our boolean type method that checks validity.
        if(mainlandService.deleteCountry(mainland_id, country_id)){
            return ResponseEntity.ok("The country has been deleted succesfully.");
        }
        return ResponseEntity.ok("Id error has been occurred");
    }


}
