package ru.cmstricks.tinyUrlWebApp.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.TinyUrl;

public interface TinyUrlRepository extends CrudRepository<TinyUrl, Integer> {

}
