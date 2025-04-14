package com.beeoverflow.apibeeoverflow.service;

import com.beeoverflow.apibeeoverflow.beans.TagBean;
import com.beeoverflow.apibeeoverflow.entities.Question;
import com.beeoverflow.apibeeoverflow.entities.Tag;
import com.beeoverflow.apibeeoverflow.jpas.TagJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    TagJPA tagJPA;

    public List<Tag> getTags() {
        return tagJPA.findAll();
    }

    public Tag createTag(Tag tag) {
        return tagJPA.save(tag);
    }
}
