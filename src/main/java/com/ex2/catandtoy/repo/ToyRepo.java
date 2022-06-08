package com.ex2.catandtoy.repo;

import com.ex2.catandtoy.beans.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface ToyRepo extends JpaRepository<Toy, Integer> {
}
