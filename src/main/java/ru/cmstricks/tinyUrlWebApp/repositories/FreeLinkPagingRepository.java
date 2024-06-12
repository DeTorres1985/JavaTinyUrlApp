package ru.cmstricks.tinyUrlWebApp.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.FreeLink;

public interface FreeLinkPagingRepository extends PagingAndSortingRepository<FreeLink, String> {
}
