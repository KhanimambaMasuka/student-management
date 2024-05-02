package com.learning.controller;

import com.learning.dto.ScoreDTO;
import com.learning.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping
    public Page<ScoreDTO> getAllScores(Pageable pageable) {
        return scoreService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreDTO> getScoreById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(scoreService.findScoreById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<ScoreDTO> saveScore(@RequestBody ScoreDTO scoreDTO) {
        ScoreDTO savedScore = scoreService.save(scoreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedScore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable("id") Long id) {
        scoreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
