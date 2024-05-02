package com.learning.services;

import com.learning.dto.StudentDTO;
import com.learning.dto.StudentDTOMapper;
import com.learning.repository.ScoreRepository;
import com.learning.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentDTOMapper studentDTOMapper;
    private final ScoreRepository scoreRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository
            , StudentDTOMapper studentDTOMapper
            , ScoreRepository scoreRepository) {
        this.studentRepository = studentRepository;
        this.studentDTOMapper = studentDTOMapper;
        this.scoreRepository = scoreRepository;
    }

    public Page<StudentDTO> findAll(Pageable pageable) {
        return studentDTOMapper
                .toStudentDTOs(studentRepository.findAll(pageable));
    }

    public StudentDTO findStudentById(Long id) {
        return studentDTOMapper.toStudentDTO(studentRepository.findById(id)
                .orElseThrow(() -> new DataRetrievalFailureException("Student not foumd")));
    }

    @Transactional
    public StudentDTO save(StudentDTO dto) {
        //Could be improved,bit clunky
        ScoreRepository.ScoreSummary sumAndTotalCountOfScoresForStudent =
                scoreRepository.getSumAndTotalCountOfScoresForStudent(dto.getId());

        int newSum = 0;
        int count = 0;

        if (sumAndTotalCountOfScoresForStudent != null) {
            newSum = sumAndTotalCountOfScoresForStudent.getSumOfScores()
                    + dto.getCurrentScore();
            count = sumAndTotalCountOfScoresForStudent.getTotalCount() + 1;
        }

        Integer averageScore = newSum / count;

        dto.setAverageScore(averageScore);

        return studentDTOMapper
                .toStudentDTO(studentRepository
                        .save(studentDTOMapper.toStudent(dto)));
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
