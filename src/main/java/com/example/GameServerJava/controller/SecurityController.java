package com.example.GameServerJava.controller;


import com.example.GameServerJava.entity.Test;

import com.example.GameServerJava.service.PlayerService;
import com.example.GameServerJava.utils.WebUtils;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Slf4j
@RequiredArgsConstructor
public class SecurityController {

    private final PlayerService playerService;

    @RequestMapping("/lobby")
    public String Lobby(Model model) {
        model.addAttribute("title", "Welcome to Lobby");
        model.addAttribute("players", playerService.getPlayers());

        List<Test> list = new ArrayList<>();
        list.add(new Test("Name1", "Value1"));
        list.add(new Test("Name2", "Value2"));
        list.add(new Test("Name3", "Value3"));
        list.add(new Test("Name4", "Value4"));
        model.addAttribute("testList", list);
        return "lobby";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("title", "Welcome to Server");
        model.addAttribute("message", "This is Welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accesDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName()
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }
        return "403Page";
    }
}
