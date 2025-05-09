package com.project.cooking.kitchen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides services for managing ingredient substitutions,
 * availability, and dietary compliance in recipes.
 * 
 * @author abood
 */
public class IngredientService {

    /**
     * Map containing ingredient substitutions based on availability and dietary restrictions.
     * Key: Type of restriction (e.g., "availability", "gluten-free")
     * Value: Map of ingredient substitutions.
     * 
     * @author abood
     */
    private static final Map<String, Map<String, String>> SUBSTITUTIONS = new HashMap<>();

    /**
     * Map holding dietary restriction types and the ingredients that violate them.
     * Key: Restriction type (e.g., "gluten-free")
     * Value: Comma-separated list of non-compliant ingredients.
     * 
     * @author abood
     */
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

    /**
     * Finds a substitute for a given ingredient based on availability or dietary restriction.
     *
     * @param ingredient The ingredient to be substituted.
     * @param restriction The type of restriction (e.g., "gluten-free").
     * @param isDietary True if the restriction is dietary; false if it's due to availability.
     * @return The substitute ingredient, or null if no substitute is found.
     * 
     * @author abood
     */
    public String findAlternative(String ingredient, String restriction, boolean isDietary) {
        String key = isDietary ? restriction : "availability";
        return SUBSTITUTIONS.getOrDefault(key, new HashMap<>()).get(ingredient);
    }

    /**
     * Checks if a given ingredient is compliant with a specific dietary restriction.
     *
     * @param ingredient The ingredient to check.
     * @param restriction The dietary restriction (e.g., "gluten-free").
     * @return True if the ingredient is compliant; false if it violates the restriction.
     * 
     * @author abood
     */
    public boolean isDietaryCompliant(String ingredient, String restriction) {
        String nonCompliant = DIETARY_RESTRICTIONS.getOrDefault(restriction, "");
        return !nonCompliant.contains(ingredient);
    }

    /**
     * Checks whether an ingredient is currently available in stock.
     *
     * @param ingredient The ingredient to check.
     * @return True if the ingredient is available; false otherwise.
     * 
     * @author abood
     */
    public boolean isAvailable(String ingredient) {
        List<String> availableIngredients = List.of("carrot", "rice", "salt", "pepper"); 
        return availableIngredients.contains(ingredient.toLowerCase());
    }
}