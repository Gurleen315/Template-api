package com.example.templateapi.dto;
import java.util.List;
public class TemplateRequest {
	public String question_id;
    public String title;
    public String description;
    public Signature signature;
    public String language;

    public static class Signature {
        public String function_name;
        public List<Parameter> parameters;
        public ReturnType returns;
}
}
