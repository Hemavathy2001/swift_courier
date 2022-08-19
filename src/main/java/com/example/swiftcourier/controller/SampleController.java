package com.example.swiftcourier.controller;

import javax.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SampleController {
	 
	 // sample test end point 
	 @GetMapping("/test")
	 @RolesAllowed("ROLE_MANAGER")
     public String getHelloWorld() {
    	 return "Hello world" ;
     }
}
