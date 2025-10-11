package com.leonardolucs.todolist.controllers;

import com.leonardolucs.todolist.models.dto.LabelsDTO;
import com.leonardolucs.todolist.models.entities.Label;
import com.leonardolucs.todolist.services.LabelsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelsController {

    private final LabelsService labelsService;

    @PostMapping
    public ResponseEntity<Label> createLabel(@Valid @RequestBody LabelsDTO labelsDTO) {
        Label createdLabel = labelsService.createLabel(labelsDTO);
        return new ResponseEntity<>(createdLabel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Label>> getAllLabels() {
        List<Label> labels = labelsService.getAllLabels();
        return ResponseEntity.ok(labels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Label> getLabelById(@PathVariable Long id) {
        return labelsService.getLabelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Label> updateLabel(@PathVariable Long id, @Valid @RequestBody LabelsDTO labelsDTO) {
        try {
            Label updatedLabel = labelsService.updateLabel(id, labelsDTO);
            return ResponseEntity.ok(updatedLabel);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabel(@PathVariable Long id) {
        try {
            labelsService.deleteLabel(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
