package com.test.lab5.controllers;

import com.test.lab5.configurations.Post;
import com.test.lab5.configurations.PostService;
import com.test.lab5.dto.HeroDto;
import com.test.lab5.services.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/heroes")
@RequiredArgsConstructor
public class HeroController {

    @Value("${student.name}")
    private String name;

    @Value("${baseUrl}")
    private String baseUrl;

    private final HeroService service;

    private final PostService postService;


    @GetMapping("arena/register")
    public ResponseEntity<String> register(){
        Post post =  Post.builder()
                .baseUrl(baseUrl)
                .studentName(name)
                .build();

        return ResponseEntity.ok(postService.register(post));
    }

    @GetMapping
    public ResponseEntity<List<HeroDto>> getHeroes() {
        return ResponseEntity.ok(service.getAllHeros());
    }

    @PostMapping
    public ResponseEntity<Object> createHero(@RequestBody HeroDto hero) {
        long caracts  = hero.getAccuracy()+ hero.getDefense()+ hero.getIntelligence()+ hero.getSpeed()+ hero.getLuck()+ hero.getStrength();
        if (caracts > 300){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 400);
            errorResponse.put("error", "Bad Request");
            errorResponse.put("message", "La somme des caractéristiques du Hero doit être inférieure ou égale à 300. Pour "
                    + hero.getName() + " est de " + caracts);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        return ResponseEntity.ok(service.createHero(hero));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroDto> getHeroById(@PathVariable("id") int id) {
        return ResponseEntity.ok(service.getHeroById(id));
    }

    @GetMapping(params = "names")
    public ResponseEntity<HeroDto> getHeroByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(service.getHeroByName(name));
    }

    @GetMapping("/random")
    public ResponseEntity<HeroDto> getRandomHero() {
        return ResponseEntity.ok(service.getRandomHero());
    }

    @PatchMapping
    public ResponseEntity<HeroDto> updateHero(@RequestBody HeroDto hero) {
        return ResponseEntity.ok(service.updateHero(hero));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHero(@PathVariable long id) {
        return ResponseEntity.ok(service.deleteHero(id));
    }
}
