package com.beeoverflow.apibeeoverflow.beans;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagBean {
    private int id;
    private List<String> name;
    private int quesId;
}
