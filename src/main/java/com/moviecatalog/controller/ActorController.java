package com.moviecatalog.controller;

import com.moviecatalog.model.Actor;
import com.moviecatalog.model.Movie;
import com.moviecatalog.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class ActorController {

    @Autowired
    private ActorService actServ;

    @GetMapping("/actors")
    public List<Actor> index() {
        return actServ.findAll();
    }

    @GetMapping("/actor/{id}")
    public ResponseEntity<Actor> show(@PathVariable Integer id) {
        return actServ.findById(id);
    }

    @PostMapping("/actor")
    public ResponseEntity<Actor> save(@RequestBody Actor actor) {
        return actServ.save(actor);
    }

    @PutMapping("/actor/{id}")
    public ResponseEntity<Actor> update(@PathVariable Integer id, @RequestBody Actor actor) {
        return actServ.update(id, actor);
    }

    @DeleteMapping("/actor/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Integer id) {
        return actServ.delete(id);
    }

    @GetMapping("/actor/{id}/movies")
    public ResponseEntity<Set<Movie>> findAllMovies(@PathVariable Integer id) {
        return actServ.findAllMovies(id);
    }

}
