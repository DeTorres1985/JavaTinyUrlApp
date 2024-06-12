package ru.cmstricks.tinyUrlWebApp.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.FreeLink;

public interface FreeLinkCrudRepository extends CrudRepository<FreeLink, String> {
    long count();
}
