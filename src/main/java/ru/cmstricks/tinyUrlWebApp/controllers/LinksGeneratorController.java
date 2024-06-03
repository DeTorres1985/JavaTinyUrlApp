package ru.cmstricks.tinyUrlWebApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.cmstricks.tinyUrlWebApp.services.LinksService;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.PaidLink;

import java.util.Optional;

@Controller
@RequestMapping(path = "/adminPanel")
public class LinksGeneratorController {
    private static final String ANSWER_TO_LIFE_TO_UNIVERSE_AND_EVERYTHING = "42";

    @Autowired
    LinksService linksService;

    @PostMapping(path = "/createLinks")
    public @ResponseBody String createlinks(@RequestParam String answer) {
        if (answer.isEmpty() || !answer.equals(ANSWER_TO_LIFE_TO_UNIVERSE_AND_EVERYTHING)) {
            return "You are not ready";
        }
        Integer createdLinksCount = linksService.createPreLinks();
        return  "Created " + createdLinksCount + " links in DB";
    }

    @GetMapping(path = "/checkLink")
    public @ResponseBody String checkLink(@RequestParam(value = "id", defaultValue = "A") String id) {
        Optional<PaidLink> paidLink = linksService.getPaidLinkById(id);
        return paidLink.isPresent() ? paidLink.get().getlink() : "No link with id " + id;
    }
}
