package com.test.lab5.configurations;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;


@HttpExchange(url = "/arena/register")
public interface PostClient {

    @PostExchange()
    String register(@RequestBody Post post);
}