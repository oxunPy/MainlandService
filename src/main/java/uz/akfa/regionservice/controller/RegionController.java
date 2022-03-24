package uz.akfa.regionservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.akfa.regionservice.models.Region;
import uz.akfa.regionservice.models.RegionType;
import uz.akfa.regionservice.service.RegionService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/regions")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(regionService.findAll());
    }

    @GetMapping("/regions/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        return ResponseEntity.ok(regionService.getById(id));
    }

    @PostMapping("/regions")
    public ResponseEntity addNewRegion(@RequestParam(name = "regionType") String regionTypeCode, @RequestBody Region region){
        region.setRegionType(RegionType.decode(regionTypeCode));
        return ResponseEntity.ok(regionService.save(region));
    }

    @PutMapping("/regions/{id}")
    public ResponseEntity updateRegion(@PathVariable Long id, @RequestBody Region region, @RequestParam String code){
        Region regionToUpdate = regionService.getById(id);
        // set region-type if it is non-null value
        regionToUpdate.setRegionType(code != null ? RegionType.decode(code) : regionToUpdate.getRegionType());
        // set region-name if it is non-null value
        regionToUpdate.setName(region.getName() != null ? region.getName() : regionToUpdate.getName());
        regionService.save(regionToUpdate);
        return ResponseEntity.ok(regionToUpdate);
    }

    @PostMapping("/regions/{region_id}/districts/{district_id}")
    public ResponseEntity assignNewDistrict(@PathVariable Long region_id, @PathVariable Long district_id){
        // our boolean type method for assigning new entity checks validity of ids.
        if(regionService.assignNewDistrict(region_id, district_id)){
            // printing some message about all is good!
            return ResponseEntity.ok("New district has been added successfully");
        }
        // printing a message about non-existent id
        return ResponseEntity.ok("Id-error occurred");
    }

}
