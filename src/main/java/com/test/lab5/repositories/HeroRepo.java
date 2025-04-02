package com.test.lab5.repositories;

import com.test.lab5.models.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepo  extends CrudRepository<Hero, Long> {
    Hero getHeroByName(String name);
}
