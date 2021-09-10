package com.jobscehdular.jobesceduler.repositories;


import com.jobscehdular.jobesceduler.Entities.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import  java.util.List;
public interface FruitRepository extends JpaRepository<Fruit,Integer> {
    Integer countByName(String name);
    @Query(value = "select distinct name from fruits",nativeQuery = true)
    List<String> findUniqueFruitName();
}
