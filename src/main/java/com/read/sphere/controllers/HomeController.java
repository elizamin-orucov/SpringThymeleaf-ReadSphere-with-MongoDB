package com.read.sphere.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.http.HttpRequest;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String index(
            Model theModel
    ){
        return "index/index.html";
    }

}
