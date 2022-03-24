package uz.akfa.regionservice.communityService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import uz.akfa.regionservice.communityService.models.Community;
import uz.akfa.regionservice.communityService.models.Person;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    @Value("${communityService.url}")
    private String URL;
    // we have to get the instance of personService for the purpose of assigning person to a community.
    private final PersonService personService;


    // get message.
    public String getMessage(){
        RestTemplate restTemplate = new RestTemplate();
        String msg = restTemplate.getForObject(URL + "/msg", String.class);
        return msg;
    }

    public List<Community> getAll(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List> entity = new HttpEntity(headers);
        return restTemplate.exchange(URL + "/communities", HttpMethod.GET, entity, List.class).getBody();
    }

    public ResponseEntity<Community> save(Community community){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Community> newCommunity = restTemplate.postForEntity(URL + "/communities", community, Community.class);
        return newCommunity;
    }

    public Community getOne(Long com_id){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
//        restTemplate.getForObject(URL + "/communities/" + String.valueOf(com_id), Community.class);
        return restTemplate.exchange(URL + "/communities/" + String.valueOf(com_id), HttpMethod.GET, entity, Community.class).getBody();
    }

    public Person assignPersonToCommunity(Long com_id, Long per_id){
        // check for validity of both keys.
        if(isValidId(com_id) && personService.isValidId(per_id)){
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            Person person = personService.getOne(per_id);
            HttpEntity<Person> entity = new HttpEntity(person, headers);
            return restTemplate.exchange(URL + "/communities/" + String.valueOf(com_id) + "/people/" + String.valueOf(per_id),
                                        HttpMethod.POST, entity, Person.class).getBody();
        }
        return null;
    }

    public List<Person> getAllIncludingPeople(Long com_id){
        // check for validity
        if(isValidId(com_id)){
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<List> entity = new HttpEntity<>(headers);
            return restTemplate.exchange(URL + "/communities/" + String.valueOf(com_id) + "/people",
                                            HttpMethod.GET,
                                            entity,
                                            List.class).getBody();
        }
        return null;
    }

    public Boolean isValidId(Long id){
        return !getOne(id).equals(null);
    }


}
