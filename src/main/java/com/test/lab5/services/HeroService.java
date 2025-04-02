package com.test.lab5.services;

import com.test.lab5.dto.HeroDto;
import com.test.lab5.models.Hero;
import com.test.lab5.models.HeroMapper;
import com.test.lab5.repositories.HeroRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final  HeroRepo repo;

    private final HeroMapper heroMapper;

    public HeroDto createHero(HeroDto hero) {
        return heroMapper.heroToHeroDto(repo.save(heroMapper.heroDtoToHero(hero)));
    }

    public HeroDto getHeroById(long id) {
        return heroMapper.heroToHeroDto(repo.findById(id).orElseThrow());
    }

    public HeroDto getHeroByName(String name) {
        return heroMapper.heroToHeroDto(repo.getHeroByName(name));
    }

    public List<HeroDto> getAllHeros() {
        List<Hero> heros = (List<Hero>) repo.findAll();
        return heros.stream().map(heroMapper::heroToHeroDto).toList();
    }

    public HeroDto getRandomHero() {
        List<HeroDto> heros = getAllHeros();
        return heros.isEmpty() ? null :heros
                .stream()
                .skip(new Random().nextInt((heros.size())))
                .findFirst().orElseThrow();
    }

    public HeroDto updateHero(HeroDto hero) {
        Hero heroToUpdate  =  repo.findById(hero.getId()).orElseThrow(null);

        heroToUpdate.setName(Optional.ofNullable(hero.getName()).orElse(heroToUpdate.getName()));
        heroToUpdate.setUniverse(Optional.ofNullable(hero.getUniverse()).orElse(heroToUpdate.getUniverse()));

        return heroMapper.heroToHeroDto(repo.save(heroToUpdate));
    }

    public String deleteHero(long id) {
        repo.deleteById(id);
        return "Deletion succeed";
    }
}

