package com.learning.repository;

import com.learning.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreRepository extends CrudRepository<Score, Long> {
    Page<Score> findAll(Pageable pageable);

    List<Score> findByStudentId(Long studentId);


    @Query(value = "SELECT COALESCE(SUM(s.score_value), 0) AS sumOfScores, " +
            "COALESCE(COUNT(s.score_value), 0) AS totalCount " +
            "FROM score s WHERE s.student_id = :studentId", nativeQuery = true)
    ScoreSummary getSumAndTotalCountOfScoresForStudent(@Param("studentId") Long studentId);


    interface ScoreSummary {
        Integer getSumOfScores();

        Integer getTotalCount();
    }
}
