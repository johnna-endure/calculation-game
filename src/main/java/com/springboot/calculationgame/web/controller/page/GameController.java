package com.springboot.calculationgame.web.controller.page;

import com.springboot.calculationgame.domain.gamecard.ProblemGenerator;
import com.springboot.calculationgame.domain.user.User;
import com.springboot.calculationgame.web.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class GameController {

    private final UserService userService;

    @GetMapping("/game/{id}")
    public String loadGamePage(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id).get();

        ProblemGenerator generator = new ProblemGenerator();
        model.addAttribute("problem", generator.createExpression());

        model.addAttribute("username", user.getUsername());
        return "game";
    }
}
