package ru.cmstricks.tinyUrlWebApp.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

public interface TinyLinkRepository extends CrudRepository<TinyLink, Integer> {

}
