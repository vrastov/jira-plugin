package ru.cbr.jira.plugins.jira.customfields;

import com.atlassian.jira.issue.customfields.impl.FieldValidationException;

import java.net.MalformedURLException;
import java.net.URL;

public class Parser {
    public Dto parse(String s){
        if (s == null || "".equals(s)){
            return null;
        }
        int spaceIndex = s.indexOf(" ");
        if (spaceIndex < 0){
            throw new FieldValidationException("Url and Titlr not set!!!");
        }
        String url = s.substring(0,spaceIndex);
        String title = s.substring(spaceIndex + 1);
        try {
            return new Dto(new URL(url), title);
        } catch (MalformedURLException e) {
            throw new FieldValidationException("Url not good!!!");
        }
    }
}
