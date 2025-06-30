package com.example.templateapi.generator;
import com.example.templateapi.dto.Parameter;
import com.example.templateapi.dto.TemplateRequest;

import java.util.stream.Collectors;
public class JsTemplateGenerator implements TemplateGenerator  {
	  @Override
	    public String generate(TemplateRequest request) {
	        var sig = request.signature;

	        String params = sig.parameters.stream()
	                .map(p -> p.name)
	                .collect(Collectors.joining(", "));

	        return String.format("""
	            function %s(%s) {
	                // Write your logic here
	                return null;
	            }

	            const readline = require('readline');

	            const rl = readline.createInterface({
	                input: process.stdin,
	                output: process.stdout,
	                terminal: false
	            });

	            let input = '';
	            rl.on('line', function(line) {
	                input += line;
	            });

	            rl.on('close', function() {
	                const data = JSON.parse(input);
	                console.log(JSON.stringify(%s(...Object.values(data))));
	            });
	            """, sig.function_name, params, sig.function_name);
	    }
}
