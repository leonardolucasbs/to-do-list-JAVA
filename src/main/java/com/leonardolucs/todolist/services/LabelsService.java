package com.leonardolucs.todolist.services;

import com.leonardolucs.todolist.models.dto.LabelsDTO;
import com.leonardolucs.todolist.models.entities.Label;
import com.leonardolucs.todolist.repositories.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LabelsService {

    private final LabelRepository labelRepository;

    private Label toEntity(LabelsDTO dto) {
        Label label = new Label();
        label.setName(dto.getName());
        return label;
    }

    public Label createLabel(LabelsDTO labelsDTO) {
        Label label = toEntity(labelsDTO);
        return labelRepository.save(label);
    }

    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }

    public Optional<Label> getLabelById(Long id) {
        return labelRepository.findById(id);
    }

    public Label updateLabel(Long id, LabelsDTO labelsDTO) {
        return labelRepository.findById(id).map(existingLabel -> {
            existingLabel.setName(labelsDTO.getName());
            return labelRepository.save(existingLabel);
        }).orElseThrow(() -> new RuntimeException("Label not found"));
    }

    public void deleteLabel(Long id) {
        if (!labelRepository.existsById(id)) {
            throw new RuntimeException("Label not found with id: " + id);
        }
        labelRepository.deleteById(id);
    }
}
