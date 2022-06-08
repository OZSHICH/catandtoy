package com.ex2.catandtoy.service;

import com.ex2.catandtoy.beans.*;
import com.ex2.catandtoy.exception.*;
import com.ex2.catandtoy.repo.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.factory.config.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CatServiceImpl implements CatService{

    private Cat cat;

    @Autowired
    private CatRepo catRepo;
    @Autowired
    private ToyRepo toyRepo;


    @Override
    public void addCat(Cat cat) throws CatCustomException {
        if (catRepo.existsById(cat.getId())){
            throw new CatCustomException(ErrMsg.CAT_ALREADY_EXIST);
        }
        catRepo.save(cat);

    }

    @Override
    public void updateCat(int id, Cat cat) throws CatCustomException {
        if (!catRepo.existsById(id)){
            throw new CatCustomException(ErrMsg.CAT_NOT_EXIST);
        }
        catRepo.saveAndFlush(cat);
    }

    @Override
    public void deleteCat(int id) throws CatCustomException {
        if (!catRepo.existsById(id)){
            throw new CatCustomException(ErrMsg.CAT_NOT_EXIST);
        }
        catRepo.deleteById(id);
        System.out.println("Cat number " + id + " has been deleted");

    }

    @Override
    public List<Cat> getAllCats() {

        return catRepo.findAll();
    }


    @Override
    public Cat getOneCat(int id) throws CatCustomException {
        return catRepo.findById(id).orElseThrow(()->new CatCustomException(ErrMsg.CAT_NOT_EXIST));
    }

    @Override
    public List<Cat> getAllCatsByNameAndWeight(String name, float weight) {
        return catRepo.findAllByNameAndWeight(name,weight);
    }

    @Override
    public List<Cat> getAllCatsByNameOrWeight(String name, float weight) {
        return catRepo.findAllByNameOrWeight(name,weight);
    }

    @Override
    public List<Cat> getAllCatsByWeightOrderAsc() throws CatCustomException {
        List<Cat> cats = catRepo.findAll();
        for (Cat cat:cats ) {
            if (cat.getWeight() <= 0 ){
                throw new CatCustomException(ErrMsg.CAT_IS_DEAD);
            }
        }
        return catRepo.findAll(Sort.by(Sort.Direction.ASC,"weight"));
        //return catRepo.findAll(Sort.by(Sort.Order.asc("weight")));
    //return catRepo.findAllByOrderByWeightAsc();
    }

    @Override
    public List<Cat> getAllCatsByWeightOrderDesc() throws CatCustomException {
        List<Cat> cats = catRepo.findAll();
        for (Cat cat:cats ) {
            if (cat.getWeight() <= 0) {
                throw new CatCustomException(ErrMsg.CAT_IS_DEAD);
            }
        }
        return catRepo.findAll(Sort.by(Sort.Direction.DESC,"weight"));
    }

    @Override
    public float getAllCatsAvgWeight() {

        return catRepo.avg();
    }
}
