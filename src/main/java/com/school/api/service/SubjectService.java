package com.school.api.service;

import com.school.api.model.dto.BestNoteDto;
import com.school.api.model.dto.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> list();
    List<BestNoteDto> getBestNoteBySubject();
}
