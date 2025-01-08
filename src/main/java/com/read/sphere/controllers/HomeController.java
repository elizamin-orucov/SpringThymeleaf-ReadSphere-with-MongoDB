package com.read.sphere.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.http.HttpRequest;

@Controller
public class HomeController {

    @GetMapping
    public String index(
            Model theModel
    ){
        return "index/index.html";
    }

}
