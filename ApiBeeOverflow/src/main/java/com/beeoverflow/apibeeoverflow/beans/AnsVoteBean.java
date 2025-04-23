package com.beeoverflow.apibeeoverflow.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnsVoteBean {
    private int id;

    private int questionId;

    private Integer count;

    private int type; // 0 -> downvote 1 -> upvote
}
