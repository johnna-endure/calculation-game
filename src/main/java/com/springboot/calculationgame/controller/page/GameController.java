package com.springboot.calculationgame.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @GetMapping("/game")
    public String loadGamePage() {
        return "game";
    }
}
