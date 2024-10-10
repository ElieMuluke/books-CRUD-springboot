package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
// @RestController <- for RESTAPI Endpoints
@RequestMapping("/")
public class HomeController {
  @GetMapping
  // @RequestMapping("/home")
  public String getHomePage() {
      return "home_page";
  }
}
