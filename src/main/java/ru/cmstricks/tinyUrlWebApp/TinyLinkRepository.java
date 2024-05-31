package ru.cmstricks.tinyUrlWebApp;

import org.springframework.data.repository.CrudRepository;

public interface TinyLinkRepository extends CrudRepository<TinyLink, Integer> {

}
