package uz.akfa.regionservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.akfa.regionservice.models.District;
import uz.akfa.regionservice.service.DistrictService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping("/districts")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(districtService.findAll());
    }

    @GetMapping("/districts/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        return ResponseEntity.ok(districtService.getById(id));
    }

    @PostMapping("/districts")
    public ResponseEntity addNewDistrict(@RequestBody District district){
        return ResponseEntity.ok(districtService.save(district));
    }

    @PutMapping("/districts/{id}")
    public ResponseEntity updateDistrict(@PathVariable Long id, @RequestBody District district){
        District districtToUpdate = districtService.getById(id);
        // set district-name if non-null
        districtToUpdate.setName(district.getName() != null ? district.getName()
                                                            : districtToUpdate.getName());
        // set district-region if non-null
        districtToUpdate.setRegion(district.getRegion() != null ? district.getRegion()
                                                                : districtToUpdate.getRegion());
        // then call save method for updating.
        districtService.save(districtToUpdate);
        return ResponseEntity.ok(districtToUpdate);
    }




}
