package uz.akfa.regionservice.communityService.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.akfa.regionservice.communityService.models.Community;
import uz.akfa.regionservice.communityService.service.CommunityService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class communityResource {

    private final CommunityService communityService;

    @GetMapping("/msg")
    public ResponseEntity getMessage(){
        return ResponseEntity.ok(communityService.getMessage());
    }

    @GetMapping("/communities")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(communityService.getAll());
    }

    @GetMapping("/communities/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        return ResponseEntity.ok(communityService.getOne(id));
    }

    @PostMapping("/communities")
    public ResponseEntity addNewCommunity(@RequestBody Community community){
        return ResponseEntity.ok(communityService.save(community));
    }

    @RequestMapping(value = "/communities/is_valid/{id}", method = RequestMethod.GET)
    public ResponseEntity checkValid(@PathVariable Long id){
        return ResponseEntity.ok(communityService.isValidId(id));
    }

    @PostMapping("/communities/{com_id}/people/{person_id}")
    public ResponseEntity addNewPerson(@PathVariable Long com_id, @PathVariable Long person_id){
        return ResponseEntity.ok(communityService.assignPersonToCommunity(com_id, person_id));
    }

    @GetMapping("/communities/{com_id}/people")
    public ResponseEntity getAllPeople(@PathVariable Long com_id){
        return ResponseEntity.ok(communityService.getAllIncludingPeople(com_id));
    }
}
