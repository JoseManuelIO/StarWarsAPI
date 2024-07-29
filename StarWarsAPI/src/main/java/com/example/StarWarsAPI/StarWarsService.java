package com.example.StarWarsAPI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class StarWarsService {


    private final RestTemplate restTemplate;
    public StarWarsService() {
        this.restTemplate = new RestTemplate();
    }

    public String getPeople() {
        return restTemplate.getForObject("https://swapi.dev/api/people/", String.class);
    }

    public String getStarships() {
        return restTemplate.getForObject("https://swapi.dev/api/starships/", String.class);
    }

}
