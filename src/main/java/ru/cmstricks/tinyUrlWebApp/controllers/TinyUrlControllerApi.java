package ru.cmstricks.tinyUrlWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cmstricks.tinyUrlWebApp.dao.TinyUrlDao;
import ru.cmstricks.tinyUrlWebApp.facades.TinyUrlFacade;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/tinyUrl/")
public class TinyUrlControllerApi {

    @Autowired
    TinyUrlFacade tinyUrlFacade;

    @RequestMapping(path = "create", method = RequestMethod.POST)
    public @ResponseBody TinyUrlDao getTinyUrl(@RequestBody Map<String, String> jsonData) {
        String url = jsonData.get("url");

        Optional<TinyUrlDao> tinyUrl = tinyUrlFacade.createFreeTinyUrl(url);
        return tinyUrl.isEmpty() ? new TinyUrlDao("", "") : tinyUrl.get();
    }

}