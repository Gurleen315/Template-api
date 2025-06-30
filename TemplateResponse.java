package com.example.templateapi.dto;

public class TemplateResponse {
	public String language;
    public String template;

    public TemplateResponse(String language, String template) {
        this.language = language;
        this.template = template;
    }
}
