package uz.akfa.regionservice.communityService.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.akfa.regionservice.communityService.models.Person;
import uz.akfa.regionservice.communityService.service.PersonService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PersonResource {

    private final PersonService personService;

    @GetMapping("/people")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(personService.getAll());
    }

    @GetMapping("/people/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        return ResponseEntity.ok(personService.getOne(id));
    }

    @PostMapping("/people")
    public ResponseEntity save(@RequestBody Person person,
                               @RequestParam String status_code,
                               @RequestParam String gender_code){
        return ResponseEntity.ok(personService.saveExch(person, status_code, gender_code));
    }

    @GetMapping("people/is_valid/{id}")
    public ResponseEntity isValid(@PathVariable Long id){
        return ResponseEntity.ok(personService.isValidId(id));
    }
}
