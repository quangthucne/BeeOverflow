package com.beeoverflow.apibeeoverflow.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagBean {
    private int id;
    private String name;
    private int quesId;
}
