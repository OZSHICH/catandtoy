package com.ex2.catandtoy.repo;

import com.ex2.catandtoy.beans.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CatRepo extends JpaRepository<Cat, Integer> {

    List<Cat> findAllByNameAndWeight(String name, float weight);

    List<Cat> findAllByNameOrWeight(String name, float weight);

    //List<Cat> findAllByOrderByWeightAsc();

    //List<Cat> findAllByOrderByWeightDesc();

    @Query(value = "SELECT avg(weight) from cats", nativeQuery = true)
    float avg();


}
