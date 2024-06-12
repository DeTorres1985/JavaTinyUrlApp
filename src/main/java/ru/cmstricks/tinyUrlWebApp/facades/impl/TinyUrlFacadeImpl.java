package ru.cmstricks.tinyUrlWebApp.facades.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.cmstricks.tinyUrlWebApp.beans.FreeLinksPool;
import ru.cmstricks.tinyUrlWebApp.dao.TinyUrlDao;
import ru.cmstricks.tinyUrlWebApp.facades.TinyUrlFacade;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.FreeLink;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.TinyUrl;
import ru.cmstricks.tinyUrlWebApp.services.LinksService;
import ru.cmstricks.tinyUrlWebApp.services.TinyUrlService;

import java.util.Optional;

@Component
public class TinyUrlFacadeImpl implements TinyUrlFacade {
    @Value("${app.domain}")
    private String domain;

    @Autowired
    TinyUrlService tinyUrlService;

    @Autowired
    FreeLinksPool freeLinksPool;

    @Autowired
    LinksService linksService;

    Logger logger = LogManager.getLogger(TinyUrlFacadeImpl.class);

    @Override
    @Transactional
    public Optional<TinyUrlDao> createFreeTinyUrl(String url) {
        if (tinyUrlService.isValidUrl(url)) {
            FreeLink link = freeLinksPool.getFreshLink();
            TinyUrl tinyUrl = tinyUrlService.createTinyUrl(domain + link.getLink(), url);
            linksService.deleteFreeLink(link);
            return Optional.of(new TinyUrlDao(tinyUrl.getLink(), tinyUrl.getUrl()));
        }

        logger.warn("Invalid url", url);
        return Optional.empty();
    }

    @Override
    public Optional<TinyUrl> getLink(String domain, String link) {
        return tinyUrlService.getByLink(domain, link);
    }
}
