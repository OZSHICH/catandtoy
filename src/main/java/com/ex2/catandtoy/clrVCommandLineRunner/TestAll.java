package com.ex2.catandtoy.clrVCommandLineRunner;

import com.ex2.catandtoy.beans.*;
import com.ex2.catandtoy.repo.*;
import com.ex2.catandtoy.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;

import org.springframework.web.client.*;

;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TestAll implements CommandLineRunner {

    private final RestTemplate restTemplate;

    @Autowired
    private CatRepo catRepo;

    //    @Autowired
//    private ToyRepo toyRepo;
//
    @Autowired
    private CatService catService;

    @Override
    public void run(String... args) throws Exception {

        Toy t1 = Toy
                .builder()
                .name("Black")
                .build();

        Toy t2 = Toy
                .builder()
                .name("white")
                .build();

        Toy t3 = Toy
                .builder()
                .name("Red")
                .build();

        Toy t4 = Toy
                .builder()
                .name("Blue")
                .build();

        Cat c1 = Cat
                .builder()
                .name("Ronen")
                .weight(2.5f)
                .toy(t1)
                .toy(t2)
                .build();

        Cat c2 = Cat
                .builder()
                .name("Tomer")
                .weight(3.5f)
                .toy(t3)
                .toy(t4)
                .build();


//        catRepo.saveAll(Arrays.asList(c1, c2));
//        c1.setName("asd");
//        catService.updateCat(1, c1);
//        catService.getAllCats().forEach(System.out::println);
//        catService.getAllCatsByNameAndWeight("asd", 2.5f).forEach(System.out::println);
//        catService.getAllCatsByNameOrWeight("asd", 3.5f).forEach(System.out::println);
//        System.out.println("ddsfsdffsd");
//        catService.getAllCatsByWeightOrderAsc().forEach(System.out::println);
//        System.out.println("sfddf");
//        catService.getAllCatsByWeightOrderDesc().forEach(System.out::println);
//        System.out.println(catService.getAllCatsAvgWeight());
//        catService.deleteCat(2);

        ResponseEntity<String> rest1 = restTemplate.postForEntity("http://localhost:8080/api/catsandtoys/cats/", c1, String.class);
        System.out.println("*********addCat********");
        System.out.println((rest1.getStatusCode().is2xxSuccessful()) ? "Good " : "Bad");

        ResponseEntity<String> rest2 = restTemplate.postForEntity("http://localhost:8080/api/catsandtoys/cats/", c2, String.class);
        System.out.println("*********addCat********");
        System.out.println((rest2.getStatusCode().is2xxSuccessful()) ? "Good " : "Bad");

//        System.out.println("*********deleteCat********");
//        restTemplate.delete("http://localhost:8080/api/catsandtoys/cats/1");

        System.out.println("*********getAllCats********");
        ResponseEntity<Cat[]> rest3 = restTemplate.getForEntity("http://localhost:8080/api/catsandtoys/cats", Cat[].class);
        List<Cat> cats = Arrays.asList(rest3.getBody());
        System.out.println(cats);

        System.out.println("*********getOneCatById********");
        Cat rest4 = restTemplate.getForObject("http://localhost:8080/api/catsandtoys/cats/2", Cat.class);
        System.out.println(rest4);

        System.out.println("*********getAllCatByNameAndWeight********");
        ResponseEntity<Cat[]> rest5 = restTemplate.getForEntity("http://localhost:8080/api/catsandtoys/catsbnaw/tomer-3.5", Cat[].class);
        List<Cat> CatByNameAndWeight = Arrays.asList(rest5.getBody());
        System.out.println(CatByNameAndWeight);

        System.out.println("*********getAllCatsByNameOrWeight********");
//        ResponseEntity<Cat[]> rest6 = restTemplate.getForEntity("http://localhost:8080/api/catsandtoys/catsbnow/tomer-2.5", Cat[].class);
//        List<Cat> CatByNameOrWeight = Arrays.asList(rest6.getBody());
//        System.out.println(CatByNameOrWeight);
        catService.getAllCatsByNameOrWeight("asd", 3.5f).forEach(System.out::println);

        System.out.println("*********getAllCatsByWeightOrderAsc********");
        String rest7 = restTemplate.getForObject("http://localhost:8080/api/catsandtoys/catsbwoa/", String.class);
        System.out.println(rest7);

        System.out.println("*********getAllCatsByWeightOrderDesc********");
        String rest8 = restTemplate.getForObject("http://localhost:8080/api/catsandtoys/catsbwod/", String.class);
        System.out.println(rest8);

        System.out.println("*********getAllCatsAvg********");
        String rest9 = restTemplate.getForObject("http://localhost:8080/api/catsandtoys/catsavg", String.class);
        System.out.println(rest9);

        System.out.println("*********catUpdate********");

        Cat updateCat1 = Cat
                .builder()
                .name("Ronen")
                .weight(5.5f)
                .build();
        updateCat1.setId(Objects.requireNonNull(restTemplate.getForEntity("http://localhost:8080/api/catsandtoys/cats/1", Cat.class).getBody()).getId());
        updateCat1.setToys(Objects.requireNonNull(restTemplate.getForEntity("http://localhost:8080/api/catsandtoys/cats/1", Cat.class).getBody()).getToys());
        HttpEntity<Cat> requestUpdate = new HttpEntity<>(updateCat1);
        restTemplate.exchange("http://localhost:8080/api/catsandtoys/cats/1", HttpMethod.PUT, requestUpdate, Cat.class);


        catRepo.findAll().forEach(System.out::println);

    }
}



