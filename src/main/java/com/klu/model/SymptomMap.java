package com.klu.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SymptomMap {

public static void main(String[] args) {

    HashMap<String, List<String>> Symptoms = new HashMap<>();
    
    Symptoms.put("Gastroenterology", Arrays.asList(
            "Difficulty swallowing food or liquids",
            "Persistent acidity or heartburn",
            "Frequent bloating after meals",
            "Chronic constipation or diarrhea",
            "Sharp or recurring abdominal pain" ));

    Symptoms.put("Cardiology", Arrays.asList(
            "Chest pain or tightness",
            "Shortness of breath during mild activity",
            "Irregular or rapid heartbeat",
            "Swelling in legs or ankles",
            "Frequent dizziness or near-fainting" ));

    Symptoms.put("Neurology", Arrays.asList(
            "Frequent or severe headaches",
            "Sudden numbness or weakness in limbs",
            "Persistent dizziness or loss of balance",
            "Seizures or unexplained blackouts",
            "Memory loss or confusion" ));

    Symptoms.put("Orthopedics", Arrays.asList(
            "Joint pain or stiffness",
            "Back or neck pain",
            "Difficulty moving arms or legs",
            "Swelling around joints",
            "Sports injuries or fractures" ));

    Symptoms.put("Dermatology", Arrays.asList(
            "Persistent acne or pimples",
            "Rashes or skin irritation",
            "Unusual hair loss",
            "Skin infections or itching",
            "Changes in moles or skin color" ));

    }
}

