package com.project.cooking.kitchen;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientService {
	
    private static final Map<String, Map<String, String>> SUBSTITUTIONS = new HashMap<>();
    private static final Map<String, String> DIETARY_RESTRICTIONS = new HashMap<>();


    static {
        Map<String, String> availabilitySubs = new HashMap<>();
        availabilitySubs.put("wheat flour", "gluten-free flour");
        availabilitySubs.put("fresh basil", "dried basil");
        SUBSTITUTIONS.put("availability", availabilitySubs);

        Map<String, String> glutenFreeSubs = new HashMap<>();
        glutenFreeSubs.put("soy sauce", "tamari");
        glutenFreeSubs.put("soy", "rice");
        SUBSTITUTIONS.put("gluten-free", glutenFreeSubs);

        DIETARY_RESTRICTIONS.put("gluten-free", "soy sauce,wheat flour");
    }

    public String findAlternative(String ingredient, String restriction, boolean isDietary) {
        String key = isDietary ? restriction : "availability";
        return SUBSTITUTIONS.getOrDefault(key, new HashMap<>()).get(ingredient);
    }

    public boolean isDietaryCompliant(String ingredient, String restriction) {
        String nonCompliant = DIETARY_RESTRICTIONS.getOrDefault(restriction, "");
        return !nonCompliant.contains(ingredient);
    }
    public boolean isAvailable(String ingredient) {
        
        List<String> availableIngredients = List.of("carrot", "rice", "salt", "pepper"); 
        return availableIngredients.contains(ingredient.toLowerCase());
    }
}