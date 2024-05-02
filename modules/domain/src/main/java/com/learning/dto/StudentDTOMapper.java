package com.learning.dto;

import com.learning.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class StudentDTOMapper {
    public abstract StudentDTO toStudentDTO(Student source);

    public Page<StudentDTO> toStudentDTOs(Page<Student> source) {
        return source.map(this::toStudentDTO);
    }

    @Mapping(target = "id", ignore = true)
    public abstract Student toStudent(StudentDTO source);
}
