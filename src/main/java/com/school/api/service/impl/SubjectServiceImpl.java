package com.school.api.service.impl;

import com.school.api.model.dto.Subject;
import com.school.api.model.entities.SubjectEntity;
import com.school.api.repository.SubjectRepository;
import com.school.api.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> list() {
        List<SubjectEntity> subjectEntities = subjectRepository.findAll();
        return subjectEntities.stream()
                .map(SubjectEntity::toDTO)
                .collect(Collectors.toList());
    }
}
