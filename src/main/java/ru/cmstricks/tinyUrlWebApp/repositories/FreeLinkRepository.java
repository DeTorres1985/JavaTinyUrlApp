package ru.cmstricks.tinyUrlWebApp.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.FreeLink;

public interface FreeLinkRepository extends CrudRepository<FreeLink, String> {

}
