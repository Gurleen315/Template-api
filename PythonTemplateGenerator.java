package com.example.templateapi.generator;
import com.example.templateapi.dto.Parameter;
import com.example.templateapi.dto.TemplateRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
public class PythonTemplateGenerator implements TemplateGenerator {
	  private static final Map<String, String> DSL_MAP = new HashMap<>() {{
	        put("int", "int");
	        put("long", "int");
	        put("float", "float");
	        put("double", "float");
	        put("bool", "bool");
	        put("string", "str");
	        put("int[]", "List[int]");
	        put("string[]", "List[str]");
	        put("List<int[]>", "List[List[int]]");
	        put("Tree<int>", "TreeNode");
	        put("Graph", "Dict[int, List[int]]");
	    }};

	    @Override
	    public String generate(TemplateRequest request) {
	        var sig = request.signature;
	        String returnType = map(sig.returns.type);
	        String params = sig.parameters.stream()
	                .map(p -> p.name + ": " + map(p.type))
	                .collect(Collectors.joining(", "));

	        return String.format("""
	            from typing import List, Dict
	            
	            class Solution:
	                def %s(self, %s) -> %s:
	                    # Write your logic here
	                    pass

	            if __name__ == "__main__":
	                import sys, json
	                data = json.loads(sys.stdin.read())
	                print(json.dumps(Solution().%s(**data)))
	            """, sig.function_name, params, returnType, sig.function_name);
	    }

	    private String map(String dsl) {
	        return DSL_MAP.getOrDefault(dsl, "Any");
	    }
}
