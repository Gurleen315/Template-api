package com.example.templateapi.generator;

public class TemplateGeneratorFactory {
	public static TemplateGenerator getGenerator(String language) {
        return switch (language.toLowerCase()) {
            case "java" -> new JavaTemplateGenerator();
            case "python" -> new PythonTemplateGenerator();
            case "c++" -> new CppTemplateGenerator();
            case "javascript", "js", "node" -> new JsTemplateGenerator();
            default -> throw new IllegalArgumentException("Unsupported language: " + language);
        };
    }
}
