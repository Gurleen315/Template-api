package com.example.templateapi.generator;
import com.example.templateapi.dto.Parameter;
import com.example.templateapi.dto.TemplateRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
public class CppTemplateGenerator implements TemplateGenerator  {
	private static final Map<String, String> DSL_MAP = new HashMap<>() {{
        put("int", "int");
        put("long", "long long");
        put("float", "float");
        put("double", "double");
        put("bool", "bool");
        put("string", "string");
        put("int[]", "vector<int>");
        put("string[]", "vector<string>");
        put("List<int[]>", "vector<vector<int>>");
        put("Tree<int>", "TreeNode*");
        put("Graph", "unordered_map<int, vector<int>>");
    }};

    @Override
    public String generate(TemplateRequest request) {
        var sig = request.signature;
        String returnType = map(sig.returns.type);
        String params = sig.parameters.stream()
                .map(p -> map(p.type) + " " + p.name)
                .collect(Collectors.joining(", "));

        return String.format("""
            #include <bits/stdc++.h>
            using namespace std;

            class Solution {
            public:
                %s %s(%s) {
                    // Write your logic here
                    return {};
                }
            };

            int main() {
                // Do not edit below this line
                // TODO: Add deserialization logic
                Solution sol;
                // auto result = sol.%s(...);
                // cout << result;
                return 0;
            }
            """, returnType, sig.function_name, params, sig.function_name);
    }

    private String map(String dsl) {
        return DSL_MAP.getOrDefault(dsl, "auto");
    }
}
