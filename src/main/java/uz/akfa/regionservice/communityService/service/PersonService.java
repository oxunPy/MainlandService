package uz.akfa.regionservice.communityService.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import uz.akfa.regionservice.communityService.models.Community;
import uz.akfa.regionservice.communityService.models.Person;

import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {

    @Value("${communityService.url}")
    private String URL;

    // get message.
    public String getMessage(){
        RestTemplate restTemplate = new RestTemplate();
        String msg = restTemplate.getForObject(URL + "/msg", String.class);
        return msg;
    }

    public List<Person> getAll(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List> entity = new HttpEntity(headers);
        return restTemplate.exchange(URL + "/people", HttpMethod.GET, entity, List.class).getBody();
    }

    public ResponseEntity<Person> save(Person person){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Person> newPerson = restTemplate.postForEntity(URL + "/people", person, Person.class);
        return newPerson;
    }

    public Person getOne(Long person_id){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(URL + "/people/" + String.valueOf(person_id), HttpMethod.GET, entity, Person.class).getBody();
    }

    public Boolean isValidId(Long id){
        return !getOne(id).equals(null);
    }

    public Person saveExch(Person person, String status_code, String gender_code){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(person, headers);
        return restTemplate.exchange(URL + "/people/" + status_code + "/" + gender_code,
                                HttpMethod.POST,
                                entity,
                                Person.class).getBody();
    }
}
