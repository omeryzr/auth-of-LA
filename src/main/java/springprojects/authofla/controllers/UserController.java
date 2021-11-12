package springprojects.authofla.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/examplePath")
    public String showToFatih(){
        return "This method is working Fatih.";
    }

    @GetMapping("/examplePath1")
    public String secondMethodinFirstExample(){
        return "same page second module";
    }

    @GetMapping("/examplePath2")
    public String thirdMethodinFirstExample(){
        return "fatihin yaptigi controller";
    }

}



