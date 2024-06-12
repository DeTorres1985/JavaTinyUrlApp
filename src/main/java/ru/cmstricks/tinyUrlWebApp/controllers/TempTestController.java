package ru.cmstricks.tinyUrlWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.cmstricks.tinyUrlWebApp.dao.TinyUrlDao;
import ru.cmstricks.tinyUrlWebApp.facades.TinyUrlFacade;

import java.util.Optional;

@Controller
@RequestMapping("test")
public class TempTestController {
    public static final String NO_LINK_MESSAGE = "There are no link: devs are aware about this bug";

    @Autowired
    TinyUrlFacade tinyUrlFacade;

    @RequestMapping(path="shortifyUrl", method = RequestMethod.GET)
    public @ResponseBody String getTinyUrl(@RequestParam String url) {
        Optional<TinyUrlDao> tinyUrl = tinyUrlFacade.createFreeTinyUrl(url);
        return tinyUrl.isEmpty() ? NO_LINK_MESSAGE : tinyUrl.get().getUrl();
    }
}
