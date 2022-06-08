package com.ex2.catandtoy.controller;

import com.ex2.catandtoy.beans.*;
import com.ex2.catandtoy.exception.*;
import com.ex2.catandtoy.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

import java.util.*;

@RestController
@RequestMapping("api/catsandtoys")
public class CatsController {

    @Autowired
    private CatService catService;
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/cats/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCat(@RequestBody Cat cat) throws CatCustomException{
        catService.addCat(cat);
    }
    @PutMapping("/cats/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void catUpdate(@PathVariable int id,@RequestBody Cat cat) throws CatCustomException{
        catService.updateCat(id, cat);
    }
//    @RequestMapping(value = "/cats/{id}",method = RequestMethod.PUT)
//    public String updateCat(@PathVariable("id") String id,@RequestBody Cat cat){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<Cat> catHttpEntity = new HttpEntity<Cat>(cat,headers);
//
//        return restTemplate.exchange("http://localhost:8080/api/catsandtoys/cats/",HttpMethod.PUT,catHttpEntity,String.class).getBody();
//    }


    @DeleteMapping("/cats/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void catDelete(@PathVariable int id) throws CatCustomException{
        catService.deleteCat(id);
    }
    @GetMapping("/cats")
    public List<Cat> getAllCat(){
        return catService.getAllCats();
    }

    @GetMapping("/cats/{id}")
    public Cat getOneCat(@PathVariable int id) throws CatCustomException {
        return catService.getOneCat(id);
    }

    @GetMapping("/catsbnaw/{name}-{weight}")
    public List<Cat> getAllCatByNameAndWeight(@PathVariable String name,@PathVariable float weight){
        return catService.getAllCatsByNameAndWeight(name, weight);
    }

    @GetMapping("/catsbnow/{name}-{weight}")
    public List<Cat> getAllCatsByNameOrWeight(@PathVariable String name,@PathVariable float weight){
        return catService.getAllCatsByNameOrWeight(name, weight);
    }

    @GetMapping("/catsbwoa/")
    public List<Cat> getAllCatsByWeightOrderAsc() throws CatCustomException {
        return catService.getAllCatsByWeightOrderAsc();
    }

    @GetMapping("/catsbwod/")
    public List<Cat> getAllCatsByWeightOrderDesc() throws CatCustomException {
        return catService.getAllCatsByWeightOrderDesc();
    }
    @GetMapping("/catsavg")
    public double getAllCatsYearsAvg() {
        return catService.getAllCatsAvgWeight();
    }


}
