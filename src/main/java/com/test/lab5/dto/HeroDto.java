package com.test.lab5.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeroDto {

    private Long id;
    private String name;
    private String universe;
    private long strength;
    private long defense;
    private long speed;
    private long accuracy;
    private long intelligence;
    private long luck;
}
