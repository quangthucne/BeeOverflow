package com.beeoverflow.apibeeoverflow.services;

import com.beeoverflow.apibeeoverflow.entities.Account;
import com.beeoverflow.apibeeoverflow.entities.Ranking;
import com.beeoverflow.apibeeoverflow.entities.Reputation;
import com.beeoverflow.apibeeoverflow.jpas.RankJPA;
import com.beeoverflow.apibeeoverflow.jpas.ReputationJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReputationService {

    @Autowired
    ReputationJPA reputationJPA;

    @Autowired
    RankJPA rankJPA;

    public Reputation create(Account account) {
        Reputation reputation = new Reputation();
        Ranking rank = rankJPA.findById(1);

        reputation.setAccount(account);
        reputation.setPoint(0);
        reputation.setRank(rank);

        return reputationJPA.save(reputation);
    }

    public Reputation update(Account account, int point) {
        Reputation reputation = account.getReputation();

        reputation.setPoint(reputation.getPoint() + point);
        return reputationJPA.save(reputation);
    }
}
