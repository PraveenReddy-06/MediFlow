package com.klu.scheduler;

import java.util.Map;

public class SymptomMap {

    private static final Map<String, SymptomDetails> problemMap = Map.of(
        "CHEST_PAIN", new SymptomDetails(5, 20),
        "FEVER", new SymptomDetails(5, 10),
        "SKIN_RASH", new SymptomDetails(5, 15)
    );

    public static SymptomDetails getProblemDetails(String problemName) {
        return problemMap.get(problemName);
    }

}