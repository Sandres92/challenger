package com.kor.challenger.domain;

public class Content {
    private String filename;

    public Content() {
    }

    public Content(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
