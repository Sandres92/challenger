package com.kor.challenger.domain;

public class Post {
    private Long id;
    private String text;
    private Content[] contents;

    public Post() {
    }

    public Post(Long id, String text) {
        this.id = id;
        this.text = text;
        contents = new Content[10];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Content[] getContents() {
        return contents;
    }

    public void setContents(Content[] contents) {
        this.contents = contents;
    }
}
