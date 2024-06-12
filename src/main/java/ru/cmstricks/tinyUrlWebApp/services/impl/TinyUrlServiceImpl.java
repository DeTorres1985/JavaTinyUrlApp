package ru.cmstricks.tinyUrlWebApp.services.impl;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmstricks.tinyUrlWebApp.repositories.TinyUrlRepository;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.TinyUrl;
import ru.cmstricks.tinyUrlWebApp.services.TinyUrlService;

import java.util.Optional;

@Service
public class TinyUrlServiceImpl implements TinyUrlService {
    @Autowired
    TinyUrlRepository tinyUrlRepository;
    @Override
    public boolean isValidUrl(String url) {
        UrlValidator validator = new UrlValidator();
        return validator.isValid(url);
    }

    @Override
    public TinyUrl createTinyUrl(String link, String url) {
        return tinyUrlRepository.save(new TinyUrl(link, url));
    }

    @Override
    public Optional<TinyUrl> getByLink(String domain, String link) {
        if(link.matches("^[A-Z0-9]+$")) {
            return tinyUrlRepository.findById(domain + link);
        }
        return Optional.empty();
    }
}
