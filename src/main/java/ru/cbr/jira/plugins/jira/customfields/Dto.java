package ru.cbr.jira.plugins.jira.customfields;

import java.net.URL;

public class Dto {
    private URL url;
    private String title;

    public Dto(URL url, String title) {
        this.url = url;
        this.title = title;
    }

    public URL getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
