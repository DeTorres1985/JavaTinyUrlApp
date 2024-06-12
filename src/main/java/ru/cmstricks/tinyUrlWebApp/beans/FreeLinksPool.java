package ru.cmstricks.tinyUrlWebApp.beans;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.cmstricks.tinyUrlWebApp.repositories.FreeLinkPagingRepository;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.FreeLink;

import java.util.ArrayList;
import java.util.List;


@Component
public class FreeLinksPool {
    @Value("${links.precached.size}")
    private Integer pageSize;

    @Autowired
    FreeLinkPagingRepository freeLinkPagingRepository;

    Logger logger = LogManager.getLogger(FreeLinksPool.class);

    private final List<FreeLink> freeLinkArrayList = new ArrayList<>();

    public synchronized FreeLink getFreshLink() {
        if (freeLinkArrayList.isEmpty()) {
            loadFreeLinks();
        }
        return freeLinkArrayList.removeFirst();
    }

    private void loadFreeLinks() {
        Pageable paging = PageRequest.of(0, pageSize);
        Page<FreeLink> pagedResult = freeLinkPagingRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            freeLinkArrayList.addAll(pagedResult.getContent());
        } else {
            logger.error("Exception while loading Free Links in Pool, there are no prelinks");
        }
    }
}
