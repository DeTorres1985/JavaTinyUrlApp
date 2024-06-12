package ru.cmstricks.tinyUrlWebApp.services;

import ru.cmstricks.tinyUrlWebApp.repositories.entities.TinyUrl;

import java.util.Optional;

public interface TinyUrlService {
    boolean isValidUrl(String url);

    TinyUrl createTinyUrl(String link, String url);

    Optional<TinyUrl> getByLink(String domain, String link);
}
