package com.example.templateapi.controller;
import com.example.templateapi.dto.TemplateRequest;
import com.example.templateapi.dto.TemplateResponse;
import com.example.templateapi.generator.TemplateGenerator;
import com.example.templateapi.generator.TemplateGeneratorFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TemplateController {
	  @PostMapping("/template")
	    public ResponseEntity<?> generateTemplate(@RequestBody TemplateRequest request) {
	        try {
	            TemplateGenerator generator = TemplateGeneratorFactory.getGenerator(request.language);
	            String template = generator.generate(request);
	            return ResponseEntity.status(HttpStatus.CREATED)
	                    .body(new TemplateResponse(request.language, template));
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	    }
}
