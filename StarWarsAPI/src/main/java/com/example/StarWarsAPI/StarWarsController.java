package com.example.StarWarsAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;


@Controller
public class StarWarsController {

    @Autowired
    private StarWarsService StarWarsService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/people")
    public String getPeople(@RequestParam(defaultValue = "") String search,
                            @RequestParam(defaultValue = "name") String sortBy,
                            @RequestParam(defaultValue = "asc") String order,
                            Model model) {

        String jsonStringPeople = StarWarsService.getPeople().toString();
        List<StarWarsPeopleDTO> peopleDTOS = new ArrayList<>();
        try {
            JsonNode jsonNodePeople = objectMapper.readTree(jsonStringPeople);
            JsonNode resultsNodePeople = jsonNodePeople.path("results");
            Iterator<JsonNode> elements = resultsNodePeople.iterator();
            while (elements.hasNext()) {
                JsonNode personNode = elements.next();
                StarWarsPeopleDTO personDTO = new StarWarsPeopleDTO();
                personDTO.setName(personNode.path("name").asText());
                personDTO.setCreated(personNode.path("created").asText());
                peopleDTOS.add(personDTO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<StarWarsPeopleDTO> filteredPeople = peopleDTOS.stream()
                .filter(person -> person.getName().toLowerCase().contains(search.toLowerCase()))
                .sorted((p1, p2) -> {
                    int comparison;
                    if ("created".equals(sortBy)) {
                        comparison = p1.getCreated().compareTo(p2.getCreated());
                    } else {
                        comparison = p1.getName().compareTo(p2.getName());
                    }
                    return "desc".equals(order) ? -comparison : comparison;
                })
                .collect(Collectors.toList());

        model.addAttribute("people", filteredPeople);
        model.addAttribute("search", search); // Añade el parámetro de búsqueda al modelo
        model.addAttribute("sortBy", sortBy); // Añade el parámetro de ordenación al modelo
        model.addAttribute("order", order); // Añade el parámetro de orden al modelo

        return "people";
    }


    @GetMapping("/starships")
    public String getStarships(@RequestParam(defaultValue = "") String search,
                               @RequestParam(defaultValue = "name") String sortBy,
                               @RequestParam(defaultValue = "asc") String order,
                               Model model) {
        String jsonStringStarships = StarWarsService.getStarships().toString();
        List<StarWarsStarshipDTO> starshipDTOS = new ArrayList<>();
        try{
            JsonNode jsonNodeStarships = objectMapper.readTree(jsonStringStarships);
            JsonNode resultsNodeStarships = jsonNodeStarships.path("results");
            Iterator<JsonNode> elements = resultsNodeStarships.iterator();
            while (elements.hasNext()) {
                JsonNode starshipNode = elements.next();
                StarWarsStarshipDTO starshipDTO = new StarWarsStarshipDTO();
                starshipDTO.setName(starshipNode.path("name").asText());
                starshipDTO.setCreated(starshipNode.path("created").asText());
                starshipDTOS.add(starshipDTO);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        List<StarWarsStarshipDTO> filteredStarships = starshipDTOS.stream()
                .filter(starship -> starship.getName().toLowerCase().contains(search.toLowerCase()))
                .sorted((s1, s2) -> {
                    int comparison;
                    if ("created".equals(sortBy)) {
                        comparison = s1.getCreated().compareTo(s2.getCreated());
                    } else {
                        comparison = s1.getName().compareTo(s2.getName());
                    }
                    return "desc".equals(order) ? -comparison : comparison;
                })
                .collect(Collectors.toList());

        model.addAttribute("starships", filteredStarships);
        model.addAttribute("search", search);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);

        return "starships";
    }
}
