package ru.cmstricks.tinyUrlWebApp.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.PaidLink;

public interface PaidLinkRepository extends CrudRepository<PaidLink, String> {

}
