package ru.cmstricks.tinyUrlWebApp.facades;

import ru.cmstricks.tinyUrlWebApp.dao.TinyUrlDao;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.TinyUrl;

import java.util.Optional;

public interface TinyUrlFacade {
    Optional<TinyUrlDao> createFreeTinyUrl(String url);

    Optional<TinyUrl> getLink(String domain, String link);
}
