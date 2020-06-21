package com.springboot.calculationgame.web.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String loadRegisterPage() {
        return "register";
    }

}
