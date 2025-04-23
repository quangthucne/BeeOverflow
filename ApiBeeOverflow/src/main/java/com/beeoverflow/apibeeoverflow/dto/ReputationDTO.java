package com.beeoverflow.apibeeoverflow.dto;

import com.beeoverflow.apibeeoverflow.entities.Ranking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReputationDTO {
    private Integer id;
    private Integer point;
    private RankingDTO rank;
}
