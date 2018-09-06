package com.cihan.repository;

import com.cihan.domain.RandomCity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomCityRepository extends CrudRepository<RandomCity, Long> {
}
