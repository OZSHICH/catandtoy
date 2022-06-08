package com.ex2.catandtoy.service;

import com.ex2.catandtoy.beans.*;
import com.ex2.catandtoy.exception.*;

import java.util.*;

public interface CatService {

    void addCat(Cat cat) throws CatCustomException;

    void updateCat(int id, Cat cat) throws CatCustomException;

    void deleteCat(int id) throws CatCustomException;

    List<Cat> getAllCats();

    Cat getOneCat(int id) throws CatCustomException;

    List<Cat> getAllCatsByNameAndWeight(String name, float weight);

    List<Cat> getAllCatsByNameOrWeight(String name, float weight);

    List<Cat> getAllCatsByWeightOrderAsc() throws CatCustomException;

    List<Cat> getAllCatsByWeightOrderDesc() throws CatCustomException;

    float getAllCatsAvgWeight();




}
