package com.jobscehdular.jobesceduler.controllers;

import com.jobscehdular.jobesceduler.Entities.Fruit;
import com.jobscehdular.jobesceduler.jobs.TestJobData;
import com.jobscehdular.jobesceduler.services.FruitService;
import org.quartz.SchedulerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruits")
public class FruitController {
    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }
    @GetMapping("/")
    public ResponseEntity<List<Fruit>> getAll() {
        return ResponseEntity.ok().body(fruitService.getAll());

    }
    @PostMapping("/")
    public ResponseEntity<Fruit> create(@RequestBody Fruit fruit) {
        return ResponseEntity.ok().body(fruitService.save(fruit));

    }
    @PostMapping("/schedule")
    public ResponseEntity<Void> createJob(@RequestBody TestJobData testJobData) {
        fruitService.schedule(testJobData);
        return ResponseEntity.ok().build();

    }
    @GetMapping("/count/{name}")
    public ResponseEntity<Long> countByFruitName(@PathVariable String name) {
        return ResponseEntity.ok().body(fruitService.countByFruitName(name));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(int id) {
        fruitService.deleteFruit(id);
        return ResponseEntity.noContent().build();
    }
}
