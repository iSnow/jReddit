package com.github.jreddit.request.entity;

import com.github.jreddit.parser.entity.Thing;

public class SubmissionEntity extends Thing {
    private String text;

    public SubmissionEntity(String parentName, String text) {
        super(parentName);
        this.text = text;
    }
    @Override
    public int compareTo(Thing thing) {
        return 0;
    }
}
