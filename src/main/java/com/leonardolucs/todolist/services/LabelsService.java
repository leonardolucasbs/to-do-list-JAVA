package com.leonardolucs.todolist.services;

import com.leonardolucs.todolist.models.dto.LabelsDTO;
import com.leonardolucs.todolist.models.entities.Labels;
import com.leonardolucs.todolist.repositories.LabelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LabelsService {

    private final LabelsRepository labelsRepository;

    private Labels toEntity(LabelsDTO dto) {
        Labels label = new Labels();
        label.setName(dto.getName());
        return label;
    }

    public Labels createLabel(LabelsDTO labelsDTO) {
        Labels label = toEntity(labelsDTO);
        return labelsRepository.save(label);
    }

    public List<Labels> getAllLabels() {
        return labelsRepository.findAll();
    }

    public Optional<Labels> getLabelById(Long id) {
        return labelsRepository.findById(id);
    }

    public Labels updateLabel(Long id, LabelsDTO labelsDTO) {
        return labelsRepository.findById(id).map(existingLabel -> {
            existingLabel.setName(labelsDTO.getName());
            return labelsRepository.save(existingLabel);
        }).orElseThrow(() -> new RuntimeException("Label not found with id: " + id));
    }

    public void deleteLabel(Long id) {
        if (!labelsRepository.existsById(id)) {
            throw new RuntimeException("Label not found with id: " + id);
        }
        labelsRepository.deleteById(id);
    }
}
