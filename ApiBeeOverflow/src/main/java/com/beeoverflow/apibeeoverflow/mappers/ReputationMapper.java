package com.beeoverflow.apibeeoverflow.mappers;

import com.beeoverflow.apibeeoverflow.dto.ReputationDTO;
import com.beeoverflow.apibeeoverflow.entities.Reputation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RankingMapper.class})
public interface ReputationMapper {
//    @Mapping(source = "rank", target = "rank")
//    ReputationDTO toReputationDTO(Reputation reputation);

}
