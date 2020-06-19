package com.springboot.calculationgame.controller.page;

import com.springboot.calculationgame.domain.user.User;
import com.springboot.calculationgame.service.user.UserService;
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
        model.addAttribute("username", user.getUsername());
        return "game";
    }
}
