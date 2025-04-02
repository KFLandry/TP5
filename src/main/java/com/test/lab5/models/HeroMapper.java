package com.test.lab5.models;

import com.test.lab5.dto.HeroDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HeroMapper {

    HeroDto heroToHeroDto(Hero hero);

    Hero heroDtoToHero(HeroDto heroDto);
}
