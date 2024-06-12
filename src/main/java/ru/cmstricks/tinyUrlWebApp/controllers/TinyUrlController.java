package ru.cmstricks.tinyUrlWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cmstricks.tinyUrlWebApp.facades.TinyUrlFacade;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.TinyUrl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class TinyUrlController {
    @Autowired
    TinyUrlFacade tinyUrlFacade;

    @Value("${app.domain}")
    private String domain;
    @GetMapping("{possibleLink}")
    public ResponseEntity<String> moveToUrlFromLink(@PathVariable String possibleLink) {
        Optional<TinyUrl> tinyUrlOptional = tinyUrlFacade.getLink(domain, possibleLink);
        if (tinyUrlOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            return ResponseEntity.status(HttpStatus.SEE_OTHER)
                    .location(new URI(tinyUrlOptional.get().getUrl()))
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}