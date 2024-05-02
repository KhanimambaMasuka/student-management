package com.learning.dto;

import com.learning.Score;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class ScoreDTOMapper {
    public abstract ScoreDTO toScoreDTO(Score source);

    @Mapping(target = "id", ignore = true)
    public abstract Score toScore(ScoreDTO source);

    public Page<ScoreDTO> toScoreDTOs(Page<Score> source) {
        return source.map(this::toScoreDTO);
    }

}
