package com.example.templateapi.generator;
import com.example.templateapi.dto.TemplateRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
public class JavaTemplateGenerator implements TemplateGenerator {
	private static final Map<String, String> DSL = new HashMap<>() {{
        put("int", "int");
        put("long", "long");
        put("float", "float");
        put("double", "double");
        put("bool", "boolean");
        put("string", "String");
        put("int[]", "int[]");
        put("string[]", "String[]");
        put("List<int[]>", "List<int[]>");
        put("Tree<int>", "TreeNode");
        put("Graph", "Map<Integer, List<Integer>>");
    }};

    @Override
    public String generate(TemplateRequest request) {
        var sig = request.signature;
        String params = sig.parameters.stream()
                .map(p -> DSL.getOrDefault(p.type, p.type) + " " + p.name)
                .collect(Collectors.joining(", "));
        String returnType = DSL.getOrDefault(sig.returns.type, sig.returns.type);

        return String.format("""
            import java.util.*;

            public class Solution {
                public %s %s(%s) {
                    // Write your logic here
                    return null;
                }

                public static void main(String[] args) {
                    Scanner scanner = new Scanner(System.in);
                    // TODO: input parsing
                    Solution sol = new Solution();
                    // System.out.println(sol.%s(...));
                }
            }
            """, returnType, sig.function_name, params, sig.function_name);
    }
}
