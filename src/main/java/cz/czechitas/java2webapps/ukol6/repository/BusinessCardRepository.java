package cz.czechitas.java2webapps.ukol6.repository;

import cz.czechitas.java2webapps.ukol6.entity.BusinessCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessCardRepository extends CrudRepository<BusinessCard, Integer> {
}
