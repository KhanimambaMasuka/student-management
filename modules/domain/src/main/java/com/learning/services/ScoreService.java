package com.learning.services;

import com.learning.dto.ScoreDTO;
import com.learning.dto.ScoreDTOMapper;
import com.learning.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final ScoreDTOMapper scoreDTOMapper;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository
            , ScoreDTOMapper scoreDTOMapper) {
        this.scoreRepository = scoreRepository;
        this.scoreDTOMapper = scoreDTOMapper;
    }

    public Page<ScoreDTO> findAll(Pageable pageable) {
        return scoreDTOMapper.toScoreDTOs(scoreRepository.findAll(pageable));
    }

    public ScoreDTO findScoreById(Long id) {
        return scoreDTOMapper.toScoreDTO(scoreRepository.findById(id)
                .orElseThrow(() -> new DataRetrievalFailureException("Score not found")));
    }

    public ScoreDTO save(ScoreDTO score) {
        return scoreDTOMapper
                .toScoreDTO(
                        scoreRepository.save(scoreDTOMapper.toScore(score)));
    }

    public void delete(Long id) {
        scoreRepository.deleteById(id);
    }
}
