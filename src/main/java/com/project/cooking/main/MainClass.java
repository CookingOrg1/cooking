package com.project.cooking.main;

import com.project.cooking.actors.Chef;
import com.project.cooking.actors.KitchenManager;
import com.project.cooking.actors.ChefTask;
import com.project.cooking.actors.SystemAdmin;

import com.project.cooking.actors.Customer;
import com.project.cooking.kitchen.Ingredient;
import com.project.cooking.meals.Meal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.InputMismatchException;
import java.util.Scanner;


public class MainClass {
    private static Map<String, Customer> customersMap = new HashMap<>();
    private static Map<String, Chef> chefsMap = new HashMap<>();
    private static Map<String, KitchenManager> managersMap = new HashMap<>();
    private static boolean firstTaskAssignment = true; 
    private static Map<String, SystemAdmin> adminsMap = new HashMap<>();
    private static final String ADMINS_FILE = "src/main/resources/Files/System Administrator.json";
    private static SystemAdmin currentAdmin = null;
    private static final String CUSTOMERS_FILE = "src/main/resources/Files/Customers.json";
    private static final String CHEFS_FILE = "src/main/resources/Files/Chefs.json";
    private static final String KITCHEN_MANAGERS_FILE = "src/main/resources/Files/Kitchen manager.json";
    private static final String INVENTORY_FILE = "src/main/resources/Files/ingredients_inventory.json";
    private static KitchenManager currentManager = null;
    private static Map<String, Map<String, Object>> inventory = new HashMap<>();

    private static Customer currentCustomer = null;
    private static Chef currentChef = null;
    private static ObjectMapper mapper = new ObjectMapper();
    private static List<Meal> availableMeals = new ArrayList<>();
    private static List<Ingredient> availableIngredients = new ArrayList<>();
    private static boolean isFirstOrder = true;

    /**
     * Initializes the system, loads data, and displays the welcome menu.
     * 
     * @author abood
     */
    public static void main(String[] args) {
    	  initializeMeals();
    	    initializeIngredients();
    	    loadAdminsData();
    	    loadCustomersData();
    	    loadChefsData();
    	    loadManagersData();
    	    loadInventoryData(); 
    	    displayWelcomeMenu();
    	    
    	    
}
  
    /**
     * Initializes predefined meals available in the system.
     * @author abood
     */
    
    private static void initializeMeals() {
        availableMeals = Arrays.asList(
            new Meal("Vegan Pizza", 45.90, Arrays.asList("Tomatoes", "Basil", "Vegan Cheese")),
            new Meal("Pasta Primavera", 38.50, Arrays.asList("Pasta", "Mixed Vegetables")),
            new Meal("Salad Bowl", 32.00, Arrays.asList("Lettuce", "Tomatoes", "Cucumber")),
            new Meal("Chicken Stir-Fry", 52.75, Arrays.asList("Chicken breast", "Olive oil", "Garlic", "Onion", "Bell peppers", "Broccoli")),
            new Meal("Beef and Black Bean Chili", 58.90, Arrays.asList("Ground beef", "Black beans", "Onion", "Garlic", "Bell peppers", "Tomatoes")),
            new Meal("Cheesy Chicken and Rice Casserole", 49.50, Arrays.asList("Chicken breast", "Rice", "Cheese", "Milk", "Garlic", "Onion")),
            new Meal("Pasta with Garlic and Spinach", 42.25, Arrays.asList("Pasta", "Olive oil", "Garlic", "Spinach", "Cheese")),
            new Meal("Vegetable and Potato Bake", 46.80, Arrays.asList("Potatoes", "Carrots", "Onion", "Bell peppers", "Broccoli", "Olive oil")),
            new Meal("Scrambled Eggs with Veggies", 35.90, Arrays.asList("Eggs", "Onion", "Bell peppers", "Spinach", "Olive oil")),
            new Meal("Grilled Chicken with Rice", 55.50, Arrays.asList("Chicken breast", "Rice", "Olive oil", "Garlic", "Salt")),
            new Meal("Vegetable Stir Fry", 39.90, Arrays.asList("Bell peppers", "Broccoli", "Carrots", "Onion", "Garlic", "Olive oil")),
            new Meal("Cheese Pasta", 44.50, Arrays.asList("Pasta", "Cheese", "Milk", "Garlic", "Black pepper"))
        );
    }
    /**
     * Initializes available ingredients with default availability status.
     * @author abood
     */

    private static void initializeIngredients() {
        availableIngredients = Arrays.asList(
            new Ingredient("Chicken breast", true),
            new Ingredient("Olive oil", true),
            new Ingredient("Garlic", true),
            new Ingredient("Onion", true),
            new Ingredient("Bell peppers", true),
            new Ingredient("Tomatoes", true),
            new Ingredient("Rice", true),
            new Ingredient("Pasta", true),
            new Ingredient("Potatoes", true),
            new Ingredient("Carrots", true),
            new Ingredient("Broccoli", true),
            new Ingredient("Spinach", true),
            new Ingredient("Eggs", true),
            new Ingredient("Milk", true),
            new Ingredient("Cheese", true),
            new Ingredient("Black beans", true),
            new Ingredient("Ground beef", true),
            new Ingredient("Salt", true),
            new Ingredient("Black pepper", true),
            new Ingredient("Vegan Cheese", true)
        );
    }

    /**
     * Loads customer data from JSON file into memory.
     * @author abood
     */
    private static void loadCustomersData() {
        try {
            List<Customer> customers = mapper.readValue(
                new File(CUSTOMERS_FILE),
                new TypeReference<List<Customer>>() {}
            );
            
            for (Customer customer : customers) {
                customersMap.put(customer.getUsername(), customer);
            }
        } catch (IOException e) {
            System.err.println("Error loading customer data: " + e.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * Saves chef data to JSON file.
     * 
     * @author abood
     */
    private static void saveChefsData() {
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(CHEFS_FILE), new ArrayList<>(chefsMap.values()));
        } catch (IOException e) {
            System.err.println("Error saving chefs data: " + e.getMessage());
        }
    }

/**
 * Loads system administrator data from JSON file.
 * 
 * @author abood
 */
    private static void loadAdminsData() {
        try {
            List<SystemAdmin> admins = mapper.readValue(
                new File(ADMINS_FILE),
                new TypeReference<List<SystemAdmin>>() {}
            );
            
            for (SystemAdmin admin : admins) {
                adminsMap.put(admin.getUsername(), admin);
            }
        } catch (IOException e) {
            System.err.println("Error loading admin data: " + e.getMessage());
        }
    }
    /**
     * Displays and manages the kitchen manager dashboard interface.
     * 
     * @param scanner User input scanner
     * @author abood
     */
    private static void displayManagerDashboard(Scanner scanner) {
        while (true) {
            System.out.println("\nKitchen Manager Panel");
            System.out.println("---------------------");
            System.out.println("1. Assign Tasks to Chefs");
            System.out.println("2. Track Ingredient Stock Levels");
            System.out.println("3. View Low Stock Alerts");
            System.out.println("4. Logout");
            
            try {
                System.out.print("\nEnter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        assignTasksToChefs(scanner);
                        break;
                    case 2:
                        trackIngredientStockLevels();
                        break;
                   
                    case 3:
                        viewLowStockAlerts();
                        break;
                    case 4:
                        System.out.println("\nLogging out...\n");
                        currentManager = null;
                        return;
                    default:
                        System.out.println("\n⚠ Invalid choice: Please enter a number between 1-4!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n⚠ Error: Please enter a valid number!\n");
                scanner.nextLine();
            }
        }
    }
   
    
    /**
     * Displays the Admin Panel with options for the admin to choose from.
     * It continuously presents the menu and handles user input.
     * Depending on the user's choice, it calls different methods such as:
     * - Auto-suggest restocking
     * - Analyzing order trends
     * - Generating a financial report
     * - Logging out
     * The loop continues until the admin selects the "Logout" option.
     *
     * @author Abood
     * @param scanner The Scanner object used to take user input from the console.
     * @return void This method does not return any value. It loops until the admin logs out.
     */
    private static void displayAdminDashboard(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Panel");
            System.out.println("---------------------");
            System.out.println("1. Auto-Suggest Restocking");
            System.out.println("2. Analyze Order Trends");
            System.out.println("3. Generate Financial Report");
            System.out.println("4. Logout");
            
            try {
                System.out.print("\nEnter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        autoSuggestRestocking(scanner);
                        break;
                    case 2:
                        analyzeOrderTrends();
                        break;
                    case 3:
                        generateFinancialReport();
                        break;
                    case 4:
                        System.out.println("\nLogging out...\n");

                        currentAdmin = null;
                        return;
                    default:
                        System.out.println("\n⚠ Invalid choice: Please enter a number between 1-4!");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid input!");
            }
        }
    }
    /**
     * Auto-suggests restocking for ingredients based on their stock levels.
     * It checks the current inventory against predefined thresholds and suggests restocking for any ingredients
     * that have stock levels below the threshold. The admin can approve or cancel the restocking for each item.
     * If restocking is approved, the inventory is updated and saved.
     *
     * @author Abood
     * @param scanner The Scanner object used to take user input from the console.
     * @return void This method does not return any value. It modifies the inventory based on user input.
     */
    private static void autoSuggestRestocking(Scanner scanner) {
        System.out.println("\n=== Auto Restocking Suggestions ===");
        boolean changesMade = false;
        boolean anyLowStock = false;

        Map<String, Map<String, Object>> inventoryCopy = new HashMap<>(inventory);

        for (Map.Entry<String, Map<String, Object>> entry : inventoryCopy.entrySet()) {
            String ingredient = entry.getKey();
            int current = (int) entry.getValue().get("quantity");
            int threshold = (int) entry.getValue().get("threshold");

            if (current < threshold) {
                anyLowStock = true;
                System.out.printf("\n%s: Current %d (Threshold %d)", ingredient, current, threshold);
                System.out.printf("\nSuggested restock: %d units", threshold * 2);
                
                System.out.print("\nApprove this restocking? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();

                if (response.equals("yes")) {
                    int newQuantity = current + (threshold * 2);
                    entry.getValue().put("quantity", newQuantity);
                    changesMade = true;
                    System.out.printf("✅ Updated %s stock to %d units\n", ingredient, newQuantity);
                } else {
                    System.out.println("🚫 Restocking cancelled for " + ingredient);
                }
            }
        }

        if (!anyLowStock) {
            System.out.println("\nAll ingredients are sufficiently stocked!");
        }

        if (changesMade) {
            inventory = inventoryCopy; 
            saveInventoryData();
            System.out.println("\nInventory updated successfully!");
        }
    }

    /**
     * Analyzes order trends by counting how many times each meal appears in the order history of all customers.
     * It identifies the most popular meals by frequency of orders and displays the top 5 most ordered meals.
     *
     * @author Abood
     * @param void This method does not take any parameters.
     * @return void This method does not return any value. It prints the most popular meals based on order trends.
     */
    private static void analyzeOrderTrends() {
        System.out.println("\n=== Order Trends Analysis ===");
        Map<String, Integer> mealCount = new HashMap<>();
        
        customersMap.values().stream()
            .flatMap(c -> c.getOrderHistory().stream())
            .forEach(order -> {
                String meal = order.split(" - ")[0].replace("Custom ", "");
                mealCount.put(meal, mealCount.getOrDefault(meal, 0) + 1);
            });
        
        System.out.println("Most Popular Meals:");
        mealCount.entrySet().stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .limit(5)
            .forEach(entry -> System.out.printf("- %s (%d orders)%n", entry.getKey(), entry.getValue()));
    }

    /**
     * Generates a financial report for all meals ordered by customers, displaying the quantity ordered and total price for each meal.
     * The report also includes the grand total of all meal sales.
     *
     * @author Abood
     * @param void This method does not take any parameters.
     * @return void This method does not return any value. It prints the financial report to the console.
     */
    private static void generateFinancialReport() {
        System.out.println("\n=== Financial Report ===");
        System.out.println("+--------------------------------------+----------+--------------+");
        System.out.println("| Meal Name                            | Quantity | Total Price  |");
        System.out.println("+--------------------------------------+----------+--------------+");

        try {
            List<Customer> customers = mapper.readValue(
                new File(CUSTOMERS_FILE),
                new TypeReference<List<Customer>>() {}
            );

            Map<String, MealStats> mealStats = new HashMap<>();

            for (Customer customer : customers) {
                if (customer.getOrderHistory() != null) {
                    for (String order : customer.getOrderHistory()) {
                        try {
                            String[] parts = order.split(" - ₪");
                            String mealName = parts[0].replace("Custom ", "").trim();
                            double price = Double.parseDouble(parts[1].split(" ")[0]);

                            mealStats.compute(mealName, (k, v) -> {
                                if (v == null) return new MealStats(1, price);
                                v.quantity++;
                                v.totalPrice += price;
                                return v;
                            });
                        } catch (Exception e) {
                            System.err.println("Skipping invalid order: " + order);
                        }
                    }
                }
            }

            double grandTotal = 0;
            for (Map.Entry<String, MealStats> entry : mealStats.entrySet()) {
                String meal = entry.getKey();
                MealStats stats = entry.getValue();
                grandTotal += stats.totalPrice;

                String formattedMeal = meal.length() > 35 ? 
                    meal.substring(0, 32) + "..." : meal;

                System.out.printf("| %-36s | %,8d | ₪ %,10.2f |%n", 
                    formattedMeal, stats.quantity, stats.totalPrice);
            }

            System.out.println("+--------------------------------------+----------+--------------+");
            System.out.printf("| %-36s |          | ₪ %,10.2f |%n", 
                "GRAND TOTAL", grandTotal);
            System.out.println("+--------------------------------------+----------+--------------+");

        } catch (IOException e) {
            System.err.println("Error loading customer data: " + e.getMessage());
        }
    }
    private static class MealStats {
        int quantity;
        double totalPrice;

        public MealStats(int quantity, double totalPrice) {
            this.quantity = quantity;
            this.totalPrice = totalPrice;
        }
    }
    /**
     * Allows the admin to assign tasks to chefs, ensuring tasks are not duplicated. 
     * The method displays a list of available chefs, prompts for task details, and assigns the task to the selected chef.
     * It also validates input and updates the chefs' task list accordingly.
     *
     * @author Abood
     * @param scanner A Scanner object used to read input from the user for selecting chefs and task details.
     * @return void This method does not return any value. It updates the chefs' task list and prints confirmation messages to the console.
     */
    private static void assignTasksToChefs(Scanner scanner) {
        System.out.println("\n=== Assign Tasks to Chefs ===");

        if (firstTaskAssignment) {
            chefsMap.values().forEach(chef -> chef.getTasks().clear());
            saveChefsData();
            firstTaskAssignment = false;
        }

        List<Chef> chefsList = new ArrayList<>(chefsMap.values());

        System.out.println("\nAvailable Chefs:");
        for (int i = 0; i < chefsList.size(); i++) {
            Chef chef = chefsList.get(i);
            System.out.printf("%d. %s %s (Tasks: %d)%n",
                i+1, chef.getFirstName(), chef.getLastName(), chef.getTasks().size());
        }

        try {
            System.out.print("\nSelect chef to assign task to (number): ");
            int chefChoice = scanner.nextInt();
            scanner.nextLine();

            if (chefChoice > 0 && chefChoice <= chefsList.size()) {
                Chef selectedChef = chefsList.get(chefChoice - 1);

                System.out.print("Enter task description: ");
                String taskDescription = scanner.nextLine().trim();

                boolean isDuplicate = selectedChef.getTasks().stream()
                    .anyMatch(task -> task.getDescription().equalsIgnoreCase(taskDescription));

                if (isDuplicate) {
                    System.out.println("⚠ This task already exists for this chef!");
                    return;
                }

                System.out.print("Enter priority (1-High, 2-Medium, 3-Low): ");
                int priority = scanner.nextInt();
                scanner.nextLine();

                priority = (priority >= 1 && priority <= 3) ? priority : 2;

                selectedChef.addTask(new ChefTask(taskDescription, priority));
                saveChefsData();

                System.out.printf("\n✅ Task assigned to %s %s!%n",
                    selectedChef.getFirstName(), selectedChef.getLastName());
                System.out.printf("Task: '%s' (Priority: %d)%n", taskDescription, priority);
                System.out.printf("Total tasks now: %d%n", selectedChef.getTasks().size());

            } else {
                System.out.println("⚠ Invalid chef selection!");
            }
        } catch (InputMismatchException e) {
            System.out.println("⚠ Error: Please enter a valid number!");
            scanner.nextLine();
        }
    }
  
    /**
     * Tracks and updates the ingredient stock levels after processing all customer orders. 
     * The method loads customer data, processes each order to update ingredient stock, 
     * and then displays the final stock levels of all ingredients.
     * 
     * @author Abood
     * @param void This method does not take any parameters.
     * @return void This method does not return any value. It prints the ingredient stock levels after processing all orders and updates the inventory.
     */
    private static void trackIngredientStockLevels() {
        loadCustomersData();

        for (Customer customer : customersMap.values()) {
            if (customer.getOrderHistory() != null) {
                for (String order : customer.getOrderHistory()) {
                    processSingleOrder(order); 
                }
            }
        }

        System.out.println("\n=== Ingredient Stock Levels After Processing All Orders ===");
        System.out.println("+-------------------------+--------------+----------------+");
        System.out.println("| Ingredient              | Stock Level  | Unit           |");
        System.out.println("+-------------------------+--------------+----------------+");

        for (Map.Entry<String, Map<String, Object>> entry : inventory.entrySet()) {
            String ingredient = entry.getKey();
            int quantity = (int) entry.getValue().get("quantity");
            String unit = (String) entry.getValue().get("unit");

            String formattedIngredient = ingredient.length() > 23 ? ingredient.substring(0, 20) + "..." : ingredient;
            String formattedQuantity = String.format("%,d", quantity);

            System.out.printf("| %-23s | %-12s | %-14s |\n", formattedIngredient, formattedQuantity, unit);
        }
        System.out.println("+-------------------------+--------------+----------------+");

        saveInventoryData(); 
    }
    
    /**
     * Processes a single customer order by updating the inventory stock based on the ingredients used in the meal.
     * It checks whether the meal exists in the available meals list, retrieves its ingredients, 
     * and adjusts the stock levels accordingly.
     * 
     * @author Abood
     * @param order The order string in the format "mealName - ₪price", where the meal name is used to identify the ingredients.
     * @return void This method does not return any value. It updates the inventory based on the order.
     */
    private static void processSingleOrder(String order) {
        try {
            String rawName = order.split(" - ")[0].replace("Custom ", "").trim();

            Optional<Meal> mealOpt = availableMeals.stream()
                .filter(m -> m.getName().equalsIgnoreCase(rawName))
                .findFirst();

            if (mealOpt.isPresent()) {
                Meal meal = mealOpt.get();
                for (String ingredientName : meal.getIngredientss()) {
                    ingredientName = ingredientName.trim(); 

                    if (inventory.containsKey(ingredientName)) {
                        Map<String, Object> data = inventory.get(ingredientName);
                        int current = (int) data.get("quantity");
                        int consumption = getConsumptionAmount(ingredientName); 
                        int updated = Math.max(0, current - consumption);
                        data.put("quantity", updated);
                    } 
                    
                }
            } else {
                System.err.println("Meal not found for order: " + rawName);
            }
        } catch (Exception e) {
            System.err.println("Error processing order: " + order);
            e.printStackTrace();
        }
    }
    /**
     * Retrieves the consumption amount for a specific ingredient. The consumption amount represents
     * how much of an ingredient is used per order when that ingredient is part of a meal.
     * 
     * @author Abood
     * @param ingredient The name of the ingredient for which the consumption amount is needed.
     * @return The consumption amount as an integer. If the ingredient is not in the predefined list, it returns 1 as the default.
     */
    private static int getConsumptionAmount(String ingredient) {
        Map<String, Integer> consumption = new HashMap<>();
        consumption.put("Potatoes", 4);
        consumption.put("Mixed Vegetables", 2);
        consumption.put("Onion", 2);
        consumption.put("Chicken breast", 3);
        consumption.put("Ground beef", 2);
        consumption.put("Cheese", 2);
        consumption.put("Olive oil", 1);
        consumption.put("Bell peppers", 2);
        consumption.put("Eggs", 4);
        consumption.put("Salt", 1);
        consumption.put("Lettuce", 2);
        consumption.put("Broccoli", 3);
        consumption.put("Carrots", 2);
        consumption.put("Basil", 1);
        consumption.put("Black pepper", 1);
        consumption.put("Pasta", 2);
        consumption.put("Garlic", 3);
        consumption.put("Tomatoes", 3);
        consumption.put("Vegan Cheese", 2);
        consumption.put("Spinach", 2);
        consumption.put("Black beans", 2);
        consumption.put("Cucumber", 2);
        consumption.put("Rice", 4);
        consumption.put("Milk", 2);

        return consumption.getOrDefault(ingredient, 1);
    }
    /**
     * Updates the inventory levels based on the ingredients of a given meal.
     * This method processes each ingredient in the provided meal and decrements its inventory 
     * by 1 for each occurrence in the meal.
     * If the ingredient does not exist in the inventory, it is skipped.
     * 
     * @author Abood
     * @param meal The meal for which the inventory is updated. The meal should contain a list of ingredients.
     *             If the meal or its ingredients are null, the method does nothing.
     */
    private static void updateInventoryForMeal(Meal meal) {
        if (meal == null || meal.getIngredients() == null) return;
        
        for (Ingredient ingredient : meal.getIngredients()) {
            String ingredientName = ingredient.getName();
            if (inventory.containsKey(ingredientName)) {
                Map<String, Object> ingredientData = inventory.get(ingredientName);
                
                int currentQuantity = (int) ingredientData.get("quantity");
                int newQuantity = Math.max(0, currentQuantity - 1); 
                
                ingredientData.put("quantity", newQuantity);
            }
        }
        saveInventoryData();
    }

    /**
     * Displays a list of ingredients with low stock based on their current quantities
     * and predefined thresholds. The method loads the inventory from a file and checks
     * each ingredient's quantity against its threshold. If the quantity is below the threshold,
     * a low stock alert is displayed.
     * 
     * @author Abood
     */
    private static void viewLowStockAlerts() {
        try {
            Map<String, Ingredient> inventory = mapper.readValue(
                new File(INVENTORY_FILE),
                new TypeReference<Map<String, Ingredient>>() {}
            );

            System.out.println("+--------------------------+----------+-----------+--------------+");
            System.out.println("| Ingredient               | Quantity | Threshold | Status       |");
            System.out.println("+--------------------------+----------+-----------+--------------+");

            boolean lowStockFound = false;

            for (Map.Entry<String, Ingredient> entry : inventory.entrySet()) {
                String name = entry.getKey();
                Ingredient ing = entry.getValue();

                if (ing.getQuantity() < ing.getThreshold()) {
                    String status = "⚠️ LOW STOCK";
                    System.out.printf("| %-24s | %8d | %9d | %-12s |\n",
                            name, ing.getQuantity(), ing.getThreshold(), status);
                    lowStockFound = true;
                }
            }

            if (!lowStockFound) {
                System.out.println("| No ingredients with low stock found.                        |");
            }

            System.out.println("+--------------------------+----------+-----------+--------------+");

        } catch (IOException e) {
            System.err.println("Error loading inventory data: " + e.getMessage());
        }
    }
    /**
     * Saves the current customer data to a file in JSON format.
     * The data is serialized into a file defined by the constant `CUSTOMERS_FILE`.
     * If an error occurs while saving the data, an error message is printed to the console.
     * 
     * @author Abood
     */
    private static void saveCustomersData() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(new File(CUSTOMERS_FILE), customersMap.values());
        } catch (IOException e) {
            System.err.println("Error saving customer data: " + e.getMessage());
        }
    }
    

    /**
     * Loads the chef data from a file and populates the `chefsMap` with the loaded data.
     * The data is deserialized from a file defined by the constant `CHEFS_FILE`.
     * If the file doesn't exist or is empty, a message is printed and the `chefsMap` is initialized as empty.
     * 
     * @author Abood
     */
    private static void loadChefsData() {
        try {
            File chefsFile = new File(CHEFS_FILE);
            
            if (chefsFile.exists() && chefsFile.length() > 0) {
                List<Chef> chefs = mapper.readValue(
                    chefsFile,
                    new TypeReference<List<Chef>>() {}
                );
                
                chefsMap.clear(); 
                for (Chef chef : chefs) {
                    chefsMap.put(chef.getUsername(), chef);
                }
            } else {
                System.out.println("Chefs file is empty or doesn't exist. Starting with empty chef list.");
            }
        } catch (IOException e) {
            System.err.println("Error loading chef data: " + e.getMessage());
            System.out.println("Starting with empty chef list due to loading error.");
        }
    }
    
    /**
     * Loads the inventory data from a file and populates the `inventory` map.
     * The data is deserialized from a file defined by the constant `INVENTORY_FILE`.
     * If the file doesn't exist or is empty, a message is printed and the `inventory` map is initialized as empty.
     * 
     * The inventory data is a map where the key is the ingredient name (String), and the value is another map containing:
     * - "quantity" (Integer): The quantity of the ingredient in stock.
     * - "unit" (String): The unit of measurement (e.g., "kg", "g", "piece").
     * - "threshold" (Integer): The stock threshold below which a low stock warning is triggered.
     * If any of these fields are missing or invalid in the file, default values are applied.
     * 
     * @author Abood
     */
    private static void loadInventoryData() {
        try {
            File inventoryFile = new File(INVENTORY_FILE);
            if (inventoryFile.exists() && inventoryFile.length() > 0) {
                inventory = mapper.readValue(inventoryFile, 
                    new TypeReference<Map<String, Map<String, Object>>>() {});
                
                inventory.forEach((ingredient, data) -> {
                    if (!data.containsKey("quantity") || data.get("quantity") == null) {
                        data.put("quantity", 0);
                    } else if (data.get("quantity") instanceof Integer) {
                    
                    } else if (data.get("quantity") instanceof Double) {
                        data.put("quantity", ((Double) data.get("quantity")).intValue());
                    }
                    
                    if (!data.containsKey("unit") || data.get("unit") == null) {
                        data.put("unit", "unit"); 
                    }
                    
                    if (!data.containsKey("threshold") || data.get("threshold") == null) {
                        int quantity = (int) data.get("quantity");
                        data.put("threshold", (int)(quantity * 0.2)); 
                    } else if (data.get("threshold") instanceof Double) {
                        data.put("threshold", ((Double) data.get("threshold")).intValue());
                    }
                });
            } else {
                System.out.println("Inventory file missing. Starting with empty inventory.");
                inventory = new HashMap<>();
            }
        } catch (IOException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
            inventory = new HashMap<>();
        }
    }

    /**
     * Saves the current inventory data to a file.
     * The data is serialized into a JSON format and written to a file defined by the constant `INVENTORY_FILE`.
     * 
     * This method uses the Jackson `ObjectMapper` to convert the `inventory` map to JSON format and writes it to the file.
     * If an error occurs while saving the data, an error message is printed.
     * 
     * @author Abood
     */
    private static void saveInventoryData() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(new File(INVENTORY_FILE), inventory);
        } catch (IOException e) {
            System.err.println("Error saving inventory data: " + e.getMessage());
        }
    }
    /**
     * Displays the welcome menu of the Special Cook System and handles user login and exit.
     * This method presents the user with a menu that allows them to either log in or exit the system.
     * Depending on the user's role, it redirects them to the appropriate dashboard after successful authentication.
     * 
     * The method continuously loops until a valid option is selected (login or exit).
     * 
     * If the user chooses to log in, the method checks the credentials using `authenticateUser(scanner)`, 
     * and if authentication is successful, the user is redirected to their respective dashboard 
     * (Customer, Chef, Manager, or Admin).
     * 
     * If the user chooses to exit, the program terminates with a thank you message.
     * 
     * @author Abood
     */
    public static void displayWelcomeMenu() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\t\t\t\t=======================================");
        System.out.println("\t\t\t\t\tSpecial Cook System");
        System.out.println("\t\t\t\t=======================================");
        
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("\nEnter your choice: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                
                if (choice == 1) {
                    if (authenticateUser(scanner)) {
                        if (currentCustomer != null) {
                            displayCustomerDashboard(scanner);
                        } else if (currentChef != null) {
                            displayChefDashboard(scanner);
                        } else if (currentManager != null) {
                            displayManagerDashboard(scanner);  
                        } else if (currentAdmin != null) {
                            displayAdminDashboard(scanner);  
                        }
                        System.out.println("\t\t\t\t=======================================");
                        System.out.println("\t\t\t\t\tSpecial Cook System");
                        System.out.println("\t\t\t\t=======================================");
                    }
                } else if (choice == 2) {
                    System.out.println("\nThank you for using Special Cook System!");
                    System.exit(0);
                } else {
                    System.out.println("⚠ Invalid choice: Please enter 1 or 2 only!");
                }
            } catch (InputMismatchException e) {
                System.out.println("⚠ Error: Please enter a valid number!");
                scanner.nextLine();
            }
        }
    }
    /**
     * Authenticates a user based on the entered username and password.
     * This method checks if the provided credentials match a user in one of the system's user maps (Customer, Chef, Manager, Admin).
     * If authentication is successful, the current user is set based on the user type (Customer, Chef, Manager, or Admin).
     * 
     * The method compares the entered username and password with stored user credentials. If a match is found, 
     * the user is authenticated, and a welcome message is displayed. If authentication fails, an error message is shown.
     * 
     * @param scanner the scanner object used to read user input
     * @return true if the user is authenticated successfully; false otherwise
     */
    private static boolean authenticateUser(Scanner scanner) {
        System.out.println("\n\t\t\t\t===== Login =====");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        Customer customer = customersMap.get(username);
        if (customer != null && customer.getPassword().equals(password)) {
            currentCustomer = customer;
            System.out.println("\nAuthentication successful! Welcome Customer " + customer.getFirstName() + "!");
            return true;
        }
        
        Chef chef = chefsMap.get(username);
        if (chef != null && chef.getPassword().equals(password)) {
            currentChef = chef;
            System.out.println("\nAuthentication successful! Welcome Chef " + chef.getFirstName() + "!");
            return true;
        }
        
        KitchenManager manager = managersMap.get(username);
        if (manager != null && manager.getPassword().equals(password)) {
            currentManager = manager;
            System.out.println("\nAuthentication successful! Welcome Manager " + manager.getFirstName() + "!");
            return true;
        }
        
        SystemAdmin admin = adminsMap.get(username);
        if (admin != null && admin.getPassword().equals(password)) {
            currentAdmin = admin;
            System.out.println("\nAuthentication successful! Welcome Admin " + admin.getUsername() + "!");
            return true;
        }
        
        System.out.println("\n⚠ Invalid username or password!\n");
        return false;
    }
    /**
     * Displays the Chef's dashboard, providing options for the chef to view assigned tasks, dietary preferences,
     * order history, ingredient conflict alerts, and logout. The method continuously prompts the chef for an option
     * until they choose to logout. Invalid inputs will prompt the user to enter a valid option.
     * 
     * The method interacts with the chef by offering a menu of available tasks, such as viewing cooking tasks, 
     * customer preferences, order history, ingredient conflicts, and logging out.
     * It handles user inputs and provides feedback based on the selected option.
     * 
     * @param scanner the scanner object used to read user input
     */
    private static void displayChefDashboard(Scanner scanner) {
        while (true) {
            System.out.println("\nChef Panel");
            System.out.println("-----------------");
            System.out.println("1. View Assigned Cooking Tasks and Notifications for Scheduled Tasks");
            System.out.println("2. View Customer Dietary Preferences");
            System.out.println("3. View Order History for Personalized Planning");
            System.out.println("4. Get Alerts on Ingredient Substitutions");
            System.out.println("5. Logout");
            System.out.println();
            
            try {
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (choice) {
                    case 1:
                        viewAssignedCookingTasks(scanner);
                        break;
                    case 2:
                        viewCustomerDietaryPreferences(scanner);
                        break;
                    case 3:
                        viewOrderHistoryForPlanning();
                        break;
                    case 4:
                        getIngredientConflictAlerts();
                        break;
                    case 5:
                        System.out.println("\nLogging out...\n");
                        currentChef = null;
                        return;
                    default:
                        System.out.println("\n⚠ Invalid choice: Please enter a number between 1-5!");
                }
                
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
                
            } catch (InputMismatchException e) {
                System.out.println("\n⚠ Error: Please enter a valid number!\n");
                scanner.nextLine();
            }
        }
    }
    /**
     * Displays the assigned cooking tasks for the current chef, including the list of assigned customers, 
     * their last order date, and recent meals from their order history.
     * If no customers are assigned to the chef, a message is displayed indicating that no tasks are assigned.
     * 
     * The method retrieves the list of assigned customers for the chef and displays their names. For each customer, 
     * the method shows their last order date and recent meals from the order history if available. If customer data 
     * is not found, it will print a message indicating this.
     * 
     * @param scanner the scanner object used to read user input (currently not used in this method)
     * @author abood
     */
    private static void viewAssignedCookingTasks(Scanner scanner) {
        System.out.println("\n=== Your Assigned Cooking Tasks ===");
        
        if (currentChef.getAssignedCustomers().isEmpty()) {
            System.out.println("You currently have no assigned customers.");
            return;
        }
        
        System.out.println("Assigned Customers:");
        
        for (String customerName : currentChef.getAssignedCustomers()) {
            System.out.println("- " + customerName);
            
            Customer customer = findCustomerByName(customerName);
            
            if (customer != null) {
                if (customer.getLastOrderDate() != null) {
                    System.out.println("  Last Order: " + customer.getLastOrderDate());
                }
                
                if (customer.getOrderHistory() != null && !customer.getOrderHistory().isEmpty()) {
                    System.out.println("  Recent Meals:");
                    for (String order : customer.getOrderHistory()) {
                        System.out.println("  - " + order);
                    }
                } else {
                    System.out.println("  No order history available");
                }
            } else {
                System.out.println("  Customer data not found in system");
            }
            
            System.out.println(); 
        }
    }
    
    
    
    
    

    /**
     * Finds a customer by their full name (first and last name).
     * 
     * This method iterates through the list of customers in the system and matches the provided full name 
     * (first name + last name) with the customer's name. If a match is found, it returns the corresponding 
     * customer object. If no customer is found with the provided name, it returns null.
     * 
     * @param name the full name of the customer to be searched (first and last name)
     * @return the customer object if found, otherwise null
     * @author omar
     */
    private static Customer findCustomerByName(String name) {
        for (Customer customer : customersMap.values()) {
            if ((customer.getFirstName() + " " + customer.getLastName()).equals(name)) {
                return customer;
            }
        }
        return null;
    }
    /**
    * Displays the dietary preferences, allergies, and ingredient preferences for each assigned customer.
    * This method loops through all the customers assigned to the current chef, retrieves their information, 
    * and displays their dietary preferences, allergies, preferred ingredients, and disliked ingredients.
    * If the chef has no assigned customers, a message indicating this is shown.
    *
    * @param scanner The Scanner object used for input (though it's not used directly in this method, it's passed for consistency).
    * @return void This method does not return any value.
    * @author omar
    */
   private static void viewCustomerDietaryPreferences(Scanner scanner) {
       System.out.println("\n=== Your Customers' Dietary Preferences ===");
       if (currentChef.getAssignedCustomers().isEmpty()) {
           System.out.println("You currently have no assigned customers.");
           return;
       }
       
       for (String customerName : currentChef.getAssignedCustomers()) {
           Customer customer = findCustomerByName(customerName);
           if (customer != null) {
               System.out.println("\nCustomer: " + customerName);
               System.out.println("Dietary Preferences: " + 
                   (customer.getDietaryPreferences() != null ? customer.getDietaryPreferences() : "None"));
               System.out.println("Allergies: " + 
                   (customer.getAllergies() != null ? customer.getAllergies() : "None"));
               System.out.println("Preferred Ingredients: " + 
                   (customer.getPreferredIngredients() != null ? String.join(", ", customer.getPreferredIngredients()) : "None"));
               System.out.println("Disliked Ingredients: " + 
                   (customer.getDislikedIngredients() != null ? String.join(", ", customer.getDislikedIngredients()) : "None"));
           }
       }
   }
   /**
    * Displays the order history for each assigned customer to aid in personalized planning.
    * This method checks the order history for each assigned customer and prints their order history.
    * If the order history is available, it will also call the `analyzeOrderPatterns` method to analyze patterns.
    * If the chef has no assigned customers or the customers have no order history, a corresponding message is displayed.
    *
    * @param void This method does not take any parameters.
    * @return void This method does not return any value.
    * @author omar
    */
   private static void viewOrderHistoryForPlanning() {
       System.out.println("\n=== Order History for Personalized Planning ===");
       if (currentChef.getAssignedCustomers().isEmpty()) {
           System.out.println("You currently have no assigned customers.");
           return;
       }
       
       for (String customerName : currentChef.getAssignedCustomers()) {
           Customer customer = findCustomerByName(customerName);
           if (customer != null && customer.getOrderHistory() != null && !customer.getOrderHistory().isEmpty()) {
               System.out.println("\nCustomer: " + customerName);
               System.out.println("Order History:");
               for (String order : customer.getOrderHistory()) {
                   System.out.println("- " + order);
               }
               
               analyzeOrderPatterns(customer);
           }
       }
   }

   /**
    * Analyzes the order patterns for a specific customer to identify their favorite meals and dietary preferences.
    * This method checks the customer's order history, counts how many times each meal has been ordered, and 
    * displays the top 3 most ordered meals. It also prints the customer's dietary preferences if available.
    *
    * @param customer The Customer object whose order patterns and dietary preferences are to be analyzed.
    * @return void This method does not return any value.
    * @author omar
    */
   private static void analyzeOrderPatterns(Customer customer) {
       System.out.println("\nAnalysis for " + customer.getFirstName() + ":");
       
       Map<String, Integer> mealCount = new HashMap<>();
       for (String order : customer.getOrderHistory()) {
           String mealName = order.split(" - ")[0];
           mealCount.put(mealName, mealCount.getOrDefault(mealName, 0) + 1);
       }
       
       if (!mealCount.isEmpty()) {
           System.out.println("Favorite Meals:");
           mealCount.entrySet().stream()
               .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
               .limit(3)
               .forEach(entry -> System.out.println("- " + entry.getKey() + " (ordered " + entry.getValue() + " times)"));
       }
       
       if (customer.getDietaryPreferences() != null && !customer.getDietaryPreferences().isEmpty()) {
           System.out.println("Dietary Pattern: " + customer.getDietaryPreferences());
       }
   }
   /**
    * Checks and alerts for any ingredient conflicts in the orders of the chef's assigned customers.
    * This method goes through each customer's order history, checking if any meal contains ingredients 
    * that the customer is allergic to or dislikes. If any conflicts are found, they are displayed, 
    * including the meal name, order date, and conflicting ingredients.
    * If no conflicts are found, a message is displayed accordingly.
    *
    * @param void This method does not take any parameters.
    * @return void This method does not return any value.
    * @author omar
    */
   private static void getIngredientConflictAlerts() {
       System.out.println("\n=== Meal Ingredient Alerts ===");
       
       if (currentChef.getAssignedCustomers().isEmpty()) {
           System.out.println("No customers assigned to this chef.");
           return;
       }

       boolean foundConflicts = false;

       for (String customerName : currentChef.getAssignedCustomers()) {
           Customer customer = findCustomerByName(customerName);
           if (customer == null || customer.getOrderHistory() == null || 
               customer.getOrderHistory().isEmpty()) {
               continue;
           }

           System.out.println("\nCustomer: " + customerName);
           boolean customerHasConflicts = false;

           for (String order : customer.getOrderHistory()) {
               try {
                   String[] orderParts = order.split(" - ₪");
                   String mealName = orderParts[0].trim();
                   String orderDate = order.contains("(Ordered:") ? 
                       order.substring(order.indexOf("(Ordered:") + 9, order.indexOf(")")) : 
                       "unknown date";

                   String baseMealName = mealName.startsWith("Custom ") ? 
                       mealName.substring(7) : mealName;

                   Optional<Meal> mealOpt = availableMeals.stream()
                       .filter(m -> m.getName().equalsIgnoreCase(baseMealName))
                       .findFirst();

                   if (!mealOpt.isPresent()) {
                       continue; 
                   }

                   Meal meal = mealOpt.get();
                   List<String> allergensFound = new ArrayList<>();
                   List<String> dislikedFound = new ArrayList<>();

                   for (String ingredient : meal.getingredienT()) {
                       if (customer.getAllergies() != null && !customer.getAllergies().isEmpty()) {
                           if (Arrays.stream(customer.getAllergies().split(","))
                               .anyMatch(allergy -> allergy.trim().equalsIgnoreCase(ingredient))) {
                               allergensFound.add(ingredient);
                           }
                       }

                       if (customer.getDislikedIngredients() != null && 
                           !customer.getDislikedIngredients().isEmpty()) {
                           if (customer.getDislikedIngredients().contains(ingredient)) {
                               dislikedFound.add(ingredient);
                           }
                       }
                   }

                   if (!allergensFound.isEmpty() || !dislikedFound.isEmpty()) {
                       customerHasConflicts = true;
                       foundConflicts = true;
                       
                       System.out.println("- " + mealName + " (Ordered: " + orderDate + ")");
                       if (!allergensFound.isEmpty()) {
                           System.out.println("  Contains ALLERGENS: " + String.join(", ", allergensFound));
                       }
                       if (!dislikedFound.isEmpty()) {
                           System.out.println("  Contains DISLIKED: " + String.join(", ", dislikedFound));
                       }
                   }

               } catch (Exception e) {
                   System.err.println("Error processing order: " + order);
                   continue;
               }
           }

           if (!customerHasConflicts) {
               System.out.println("No ingredient conflicts found in orders.");
           }
       }

       if (!foundConflicts) {
           System.out.println("\nNo ingredient conflicts found for any customers.");
       }
   }
   /**
    * Displays the customer dashboard, where the customer can select different options 
    * to manage their profile, dietary preferences, browse meals, and view past orders.
    * This method provides a menu with choices for the customer to interact with the system.
    * If an invalid choice is entered, the user is prompted to re-enter a valid selection.
    * The customer can also log out of the system through the dashboard.
    *
    * @param scanner The Scanner object used for reading user input.
    * @return void This method does not return any value.
    * @author omar
    */
   private static void displayCustomerDashboard(Scanner scanner) {
       while (true) {
           System.out.println("\nCustomer Panel");
           System.out.println("-----------------");
           System.out.println("1. View & Update Profile");
           System.out.println("2. View/Update Dietary Preferences & Allergies");
           System.out.println("3. Browse & Customize Meals");
           System.out.println("4. View Past Orders");
           System.out.println("5. Reorder Favorite Meals");
           System.out.println("6. View Upcoming Deliveries");
           System.out.println("7. Logout");  
           System.out.println();
           
           try {
               System.out.print("Enter your choice: ");
               int choice = scanner.nextInt();
               scanner.nextLine(); 
               
               switch (choice) {
                   case 1:
                       viewAndUpdateProfile(scanner);
                       break;
                   case 2:
                       manageDietaryPreferences(scanner);
                       break;
                   case 3:
                       browseAndCustomizeMeals(scanner);
                       break;
                   case 4:
                       viewPastOrders();
                       break;
                   case 5:
                       reorderFavoriteMeals(scanner);
                       break;
                   case 6:
                       viewUpcomingDeliveries();
                       break;
                   case 7: 
                       System.out.println("\nLogging out...\n");
                       currentCustomer = null;
                       return;
                   default:
                       System.out.println("\n⚠ Invalid choice: Please enter a number between 1-7!");  // Changed from 1-8
               }
               
               System.out.println("\nPress Enter to continue...");
               scanner.nextLine();
               
           } catch (InputMismatchException e) {
               System.out.println("\n⚠ Error: Please enter a valid number!\n");
               scanner.nextLine();
           }
       }
   }
   /**
    * Allows the customer to view and update their profile information.
    * This method displays the current profile details, then prompts the customer to 
    * choose a field to update (First Name, Last Name, Email, Phone, Address, or Payment Method).
    * Once the customer selects a field, they can input a new value, and the profile is updated.
    * After the update, the changes are saved and reflected in the system.
    *
    * @param scanner The Scanner object used for reading user input.
    * @return void This method does not return any value.
    * @author omar
    */
   private static void viewAndUpdateProfile(Scanner scanner) {
       System.out.println("\n=== Your Profile ===");
       System.out.println("1. First Name: " + currentCustomer.getFirstName());
       System.out.println("2. Last Name: " + currentCustomer.getLastName());
       System.out.println("3. Email: " + currentCustomer.getEmail());
       System.out.println("4. Phone: " + currentCustomer.getPhone());
       System.out.println("5. Address: " + currentCustomer.getAddress());
       System.out.println("6. Payment Method: " + currentCustomer.getPaymentMethod());
       System.out.println("7. Back to Dashboard");
       
       System.out.print("\nEnter field number to update (1-6) or 7 to go back: ");
       try {
           int field = scanner.nextInt();
           scanner.nextLine(); 
           
           if (field == 7) return;
           
           System.out.print("Enter new value: ");
           String newValue = scanner.nextLine();
           
           switch (field) {
               case 1: currentCustomer.setFirstName(newValue); break;
               case 2: currentCustomer.setLastName(newValue); break;
               case 3: currentCustomer.setEmail(newValue); break;
               case 4: currentCustomer.setPhone(newValue); break;
               case 5: currentCustomer.setAddress(newValue); break;
               case 6: currentCustomer.setPaymentMethod(newValue); break;
               default:
                   System.out.println("Invalid field number!");
                   return;
           }
           
           customersMap.put(currentCustomer.getUsername(), currentCustomer);
           saveCustomersData();
           System.out.println("Profile updated successfully!");
           
       } catch (InputMismatchException e) {
           System.out.println("Invalid input! Please enter a number.");
           scanner.nextLine();
       }
   }
   /**
    * Allows the customer to view and update their dietary preferences, allergies, preferred ingredients,
    * and disliked ingredients. This method displays the current preferences, then prompts the customer to 
    * select a field to update. The customer can enter new values, which will be saved and reflected in the system.
    * 
    * @param scanner The Scanner object used for reading user input.
    * @return void This method does not return any value.
    * @author omar
    */
   private static void manageDietaryPreferences(Scanner scanner) {
       System.out.println("\n=== Dietary Preferences & Allergies ===");
       System.out.println("1. Dietary Preferences: " + currentCustomer.getDietaryPreferences());
       System.out.println("2. Allergies: " + currentCustomer.getAllergies());
       System.out.println("3. Preferred Ingredients: " + String.join(", ", currentCustomer.getPreferredIngredients()));
       System.out.println("4. Disliked Ingredients: " + String.join(", ", currentCustomer.getDislikedIngredients()));
       System.out.println("5. Back to Dashboard");
       
       System.out.print("\nEnter field number to update (1-4) or 5 to go back: ");
       try {
           int field = scanner.nextInt();
           scanner.nextLine();
           
           if (field == 5) return;
           
           System.out.print("Enter new value (comma separated for ingredients): ");
           String newValue = scanner.nextLine();
           
           switch (field) {
               case 1: 
                   currentCustomer.setDietaryPreferences(newValue);
                   break;
               case 2: 
                   currentCustomer.setAllergies(newValue);
                   break;
               case 3: 
                   currentCustomer.setPreferredIngredients(List.of(newValue.split("\\s*,\\s*")));
                   break;
               case 4: 
                   currentCustomer.setDislikedIngredients(List.of(newValue.split("\\s*,\\s*")));
                   break;
               default:
                   System.out.println("Invalid field number!");
                   return;
           }
           
           customersMap.put(currentCustomer.getUsername(), currentCustomer);
           saveCustomersData();
           System.out.println("Dietary preferences updated successfully!");
           
       } catch (InputMismatchException e) {
           System.out.println("Invalid input! Please enter a number.");
           scanner.nextLine();
       }
   }

   /**
    * Displays a list of available meals to the customer, including a custom meal builder option.
    * Allows the customer to select a meal, check for allergy or dislike conflicts,
    * optionally customize it, and place an order.
    * 
    * @param scanner The Scanner object used to receive user input.
    * @return void This method does not return any value.
    * @author omar
    */
   private static void browseAndCustomizeMeals(Scanner scanner) {
       System.out.println("\n=== Available Meals ===");
       for (int i = 0; i < availableMeals.size(); i++) {
           Meal meal = availableMeals.get(i);
           System.out.printf("%d. %s - ₪%.2f%n", i + 1, meal.getName(), meal.getPrice());
           System.out.println("   Ingredients: " + String.join(", ", meal.getingredienT()));
       }
       System.out.printf("%d. Custom Meal Builder%n", availableMeals.size() + 1);
       System.out.printf("%d. Back to Dashboard%n", availableMeals.size() + 2);

       System.out.print("\nSelect a meal (1-" + (availableMeals.size() + 2) + "): ");
       try {
           int choice = scanner.nextInt();
           scanner.nextLine();

           if (choice == availableMeals.size() + 2) return;

           if (choice == availableMeals.size() + 1) {
               buildCustomMeal(scanner);
               return;
           }

           if (choice > 0 && choice <= availableMeals.size()) {
               Meal selectedMeal = availableMeals.get(choice - 1);

               if (hasAllergyConflict(selectedMeal.getingredienT())) {
                   System.out.println("\n⚠ Warning: This meal contains ingredients you're allergic to!");
               }

               if (hasDislikedIngredients(selectedMeal.getingredienT())) {
                   System.out.println("\n⚠ Note: This meal contains ingredients you've marked as disliked.");
                   System.out.println("Would you like to customize this meal? (yes/no)");
                   String response = scanner.nextLine();

                   if (response.equalsIgnoreCase("yes")) {
                       customizeMeal(selectedMeal, scanner);
                       return;
                   }
               }

               placeOrder(selectedMeal.getName(), selectedMeal.getPrice(), scanner);

           } else {
               System.out.println("Invalid choice!");
           }

       } catch (InputMismatchException e) {
           System.out.println("Invalid input! Please enter a number.");
           scanner.nextLine();
       }
   }
   /**
    * Processes a customer's meal order, confirms satisfaction, records the order date,
    * updates inventory if applicable, and saves the order in the customer's history.
    * 
    * If the user is not satisfied with the price, the order is canceled and they are
    * encouraged to customize a new meal.
    *
    * @param mealName The name of the meal being ordered.
    * @param price The price of the meal.
    * @param scanner The Scanner object used for user input.
    * 
    * @author omar
    */
   private static void placeOrder(String mealName, double price, Scanner scanner) {
       java.time.LocalDate currentDate = java.time.LocalDate.now();
       java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
           .ofPattern("dd/MM/yyyy")
           .withLocale(java.util.Locale.UK);

       String formattedDate = currentDate.format(formatter);

       System.out.printf(
           "\nYour order has been successfully placed. You ordered: %s - Price: ₪%.2f%n",
           mealName, price);
       System.out.println("Order Date: " + formattedDate);

       System.out.print("Are you satisfied with the price? (yes/no): ");
       String satisfaction = scanner.nextLine();

       if (satisfaction.equalsIgnoreCase("yes")) {
           System.out.println("Great! Thank you for your order.");

           if (isFirstOrder) {
               currentCustomer.setOrderHistory(new ArrayList<>());
               isFirstOrder = false;
           }

           String orderWithDate = String.format("%s - ₪%.2f (Ordered: %s)",
               mealName, price, formattedDate);

           Optional<Meal> orderedMeal = availableMeals.stream()
               .filter(m -> m.getName().equalsIgnoreCase(mealName.replace("Custom ", "")))
               .findFirst();

           if (orderedMeal.isPresent()) {
               updateInventoryForMeal(orderedMeal.get());
           }

           currentCustomer.getOrderHistory().add(orderWithDate);
           currentCustomer.setLastOrderDate(formattedDate);
           customersMap.put(currentCustomer.getUsername(), currentCustomer);
           saveCustomersData();

       } else {
           System.out.println("Order canceled. Please try customizing your meal again.");
       }
   }
   /**
    * Allows the customer to customize a selected meal by removing disliked ingredients,
    * adjusting the price accordingly, and optionally placing a modified order.
    *
    * The method presents the original meal details, then gives the user the option to
    * remove disliked ingredients (with a 10% price discount) or cancel the customization.
    *
    * @param meal The Meal object selected by the customer for customization.
    * @param scanner The Scanner object used for receiving user input.
    * 
    * @author omar
    */
   private static void customizeMeal(Meal meal, Scanner scanner) {
       System.out.println("\n=== Customize " + meal.getName() + " ===");
       System.out.println("Original ingredients: " + String.join(", ", meal.getingredienT()));
       System.out.printf("Original price: ₪%.2f%n", meal.getPrice());

       System.out.println("\nOptions:");
       System.out.println("1. Remove disliked ingredients");
       System.out.println("2. Cancel customization");

       System.out.print("\nEnter your choice: ");
       try {
           int choice = scanner.nextInt();
           scanner.nextLine();

           switch (choice) {
               case 1:
                   System.out.println("\nRemoving disliked ingredients...");
                   double newPrice = meal.getPrice() * 0.9; // Apply 10% discount
                   System.out.printf("Meal customized successfully! New price: ₪%.2f%n", newPrice);
                   placeOrder("Custom " + meal.getName(), newPrice, scanner);
                   break;
               case 2:
                   return;
               default:
                   System.out.println("Invalid choice!");
           }

       } catch (InputMismatchException e) {
           System.out.println("Invalid input! Please enter a number.");
           scanner.nextLine(); 
       }
   }
   /**
    * Allows the customer to create a fully customized meal by selecting ingredients from
    * a predefined list. It validates ingredient availability, checks for allergy and 
    * disliked items, and provides substitution suggestions or customization options.
    *
    * If the selected ingredients match an existing meal, the user is given the option
    * to order that predefined meal instead. Otherwise, the user can name and place an
    * order for their custom meal, which is priced based on selected ingredients.
    *
    * @param scanner The Scanner object used for receiving user input.
    *
    * @see #hasAllergyConflict(List)
    * @see #hasDislikedIngredients(List)
    * @see #suggestSubstitutions(List)
    * @see #suggestMealFromIngredients(List)
    * @see #calculateCustomMealPrice(List)
    * @see #placeOrder(String, double, Scanner)
    * 
    * @author omar
    */
   private static void buildCustomMeal(Scanner scanner) {
       System.out.println("\n=== Custom Meal Builder ===");
       System.out.println("Available ingredients: ");
       for (Ingredient ingredient : availableIngredients) {
           System.out.print(ingredient.getName() + ", ");
       }

       System.out.print("\n\nEnter ingredients for your custom meal (comma separated): ");
       String ingredientsInput = scanner.nextLine();
       List<String> ingredients = Arrays.asList(ingredientsInput.split("\\s*,\\s*"));

       List<String> invalidIngredients = new ArrayList<>();
       for (String ingredient : ingredients) {
           boolean found = false;
           for (Ingredient availIng : availableIngredients) {
               if (availIng.getName().equalsIgnoreCase(ingredient.trim())) {
                   found = true;
                   break;
               }
           }
           if (!found) {
               invalidIngredients.add(ingredient);
           }
       }

       if (!invalidIngredients.isEmpty()) {
           System.out.println("\n⚠ The following ingredients are not available: " + String.join(", ", invalidIngredients));
           System.out.println("Please choose from the available ingredients.");
           return;
       }

       if (hasAllergyConflict(ingredients)) {
           System.out.println("\n⚠ Warning: Your selection contains ingredients you're allergic to!");
           suggestSubstitutions(ingredients);
           return;
       }

       if (hasDislikedIngredients(ingredients)) {
           System.out.println("\n⚠ Note: Your selection contains ingredients you've marked as disliked.");
           System.out.println("Would you like to remove them? (yes/no)");
           String response = scanner.nextLine();

           if (response.equalsIgnoreCase("yes")) {
               System.out.println("\nRemoving disliked ingredients...");
               List<String> filteredIngredients = new ArrayList<>();
               for (String ingredient : ingredients) {
                   if (!currentCustomer.getDislikedIngredients().contains(ingredient)) {
                       filteredIngredients.add(ingredient);
                   }
               }
               ingredients = filteredIngredients;
           }
       }

       String suggestedMeal = suggestMealFromIngredients(ingredients);
       if (suggestedMeal != null) {
           System.out.println("\nYour ingredients can make this meal:");
           System.out.println(suggestedMeal);
           System.out.print("\nWould you like to order this meal? (yes/no): ");
           String choice = scanner.nextLine();
           if (choice.equalsIgnoreCase("yes")) {
               for (Meal meal : availableMeals) {
                   if (meal.getName().equalsIgnoreCase(suggestedMeal.split("\n")[0].trim())) {
                       placeOrder(meal.getName(), meal.getPrice(), scanner);
                       return;
                   }
               }
           }
       } else {
           System.out.println("\nYour ingredients don't match any predefined meals.");
           return;
       }

       System.out.print("\nName your custom meal: ");
       String mealName = scanner.nextLine();
       double price = calculateCustomMealPrice(ingredients);
       System.out.printf("\nYour custom meal '%s' has been created! Price: ₪%.2f%n", mealName, price);
       System.out.println("Ingredients: " + String.join(", ", ingredients));
       placeOrder(mealName, price, scanner);
   }
   /**
    * Suggests a meal recipe based on a list of provided ingredients.
    * The method checks if the given ingredients match any predefined recipe 
    * and returns a recipe string including the name and ingredients if a match is found.
    *
    * @author Omar
    * @param ingredients a list of ingredients available
    * @return a string containing the suggested recipe and its ingredients if a match is found,
    *         or null if no matching recipe is available
    */
   private static String suggestMealFromIngredients(List<String> ingredients) {
       if (ingredients.containsAll(Arrays.asList("Chicken breast", "Olive oil", "Garlic", "Onion", "Bell peppers", "Broccoli"))) {
           return "1. Chicken Stir-Fry\n" +
                  "Ingredients:\n" +
                  "- Chicken breast\n" +
                  "- Olive oil\n" +
                  "- Garlic\n" +
                  "- Onion\n" +
                  "- Bell peppers\n" +
                  "- Broccoli\n" +
                  "- Salt\n" +
                  "- Black pepper";
       }

       if (ingredients.containsAll(Arrays.asList("Ground beef", "Black beans", "Onion", "Garlic", "Bell peppers", "Tomatoes"))) {
           return "2. Beef and Black Bean Chili\n" +
                  "Ingredients:\n" +
                  "- Ground beef\n" +
                  "- Black beans\n" +
                  "- Onion\n" +
                  "- Garlic\n" +
                  "- Bell peppers\n" +
                  "- Tomatoes\n" +
                  "- Paprika\n" +
                  "- Salt\n" +
                  "- Black pepper";
       }

       if (ingredients.containsAll(Arrays.asList("Chicken breast", "Rice", "Cheese", "Milk", "Garlic", "Onion"))) {
           return "3. Cheesy Chicken and Rice Casserole\n" +
                  "Ingredients:\n" +
                  "- Chicken breast\n" +
                  "- Rice\n" +
                  "- Cheese\n" +
                  "- Milk\n" +
                  "- Garlic\n" +
                  "- Onion\n" +
                  "- Salt\n" +
                  "- Black pepper";
       }

       if (ingredients.containsAll(Arrays.asList("Pasta", "Olive oil", "Garlic", "Spinach", "Cheese"))) {
           return "4. Pasta with Garlic and Spinach\n" +
                  "Ingredients:\n" +
                  "- Pasta\n" +
                  "- Olive oil\n" +
                  "- Garlic\n" +
                  "- Spinach\n" +
                  "- Cheese\n" +
                  "- Salt\n" +
                  "- Black pepper";
       }

       if (ingredients.containsAll(Arrays.asList("Potatoes", "Carrots", "Onion", "Bell peppers", "Broccoli", "Olive oil"))) {
           return "5. Vegetable and Potato Bake\n" +
                  "Ingredients:\n" +
                  "- Potatoes\n" +
                  "- Carrots\n" +
                  "- Onion\n" +
                  "- Bell peppers\n" +
                  "- Broccoli\n" +
                  "- Olive oil\n" +
                  "- Salt\n" +
                  "- Black pepper";
       }

       if (ingredients.containsAll(Arrays.asList("Eggs", "Onion", "Bell peppers", "Spinach", "Olive oil"))) {
           return "6. Scrambled Eggs with Veggies\n" +
                  "Ingredients:\n" +
                  "- Eggs\n" +
                  "- Onion\n" +
                  "- Bell peppers\n" +
                  "- Spinach\n" +
                  "- Olive oil\n" +
                  "- Salt\n" +
                  "- Black pepper";
       }

       return null;
   }
   /**
    * Calculates the total price of a custom meal based on the number of ingredients.
    * A fixed base price is added to a per-ingredient cost to determine the final price.
    *
    * @author Omar
    * @param ingredients a list of ingredients selected for the custom meal
    * @return the total price of the meal as a double
    */
   private static double calculateCustomMealPrice(List<String> ingredients) {
       double basePrice = 25.0;
       double pricePerIngredient = 3.5;
       return basePrice + (ingredients.size() * pricePerIngredient);
   }

   /**
    * Checks if any of the given ingredients conflict with the customer's known allergies.
    * The method performs a case-insensitive match between the ingredient list and the customer's allergy list.
    *
    * @author Omar
    * @param ingredients a list of ingredients to be checked against the customer's allergies
    * @return true if there is any conflict (i.e., at least one ingredient is an allergen), false otherwise
    */
   private static boolean hasAllergyConflict(List<String> ingredients) {
       if (currentCustomer.getAllergies() == null || currentCustomer.getAllergies().isEmpty()) {
           return false;
       }
       
       for (String ingredient : ingredients) {
           if (currentCustomer.getAllergies().toLowerCase().contains(ingredient.toLowerCase())) {
               return true;
           }
       }
       return false;
   }
   /**
    * Checks if the provided list of ingredients contains any items the customer dislikes.
    * The method compares each ingredient directly against the customer's list of disliked ingredients.
    *
    * @author Omar
    * @param ingredients a list of ingredients to check against the customer's disliked ingredients
    * @return true if any ingredient is disliked by the customer, false otherwise
    */
   private static boolean hasDislikedIngredients(List<String> ingredients) {
       if (currentCustomer.getDislikedIngredients() == null || currentCustomer.getDislikedIngredients().isEmpty()) {
           return false;
       }

       for (String ingredient : ingredients) {
           if (currentCustomer.getDislikedIngredients().contains(ingredient)) {
               return true;
           }
       }
       return false;
   }
   /**
    * Suggests ingredient substitutions for items that conflict with the customer's allergies.
    * Uses a predefined map to offer alternatives and prints them to the console.
    *
    * Substitutions are suggested only for ingredients found in the customer's list of allergies.
    *
    * @author Omar
    * @param originalIngredients a list of original ingredients to evaluate for possible substitutions
    */
   private static void suggestSubstitutions(List<String> originalIngredients) {
       System.out.println("\n=== Suggested Substitutions ===");
       Map<String, String> substitutionMap = Map.of(
           "Cheese", "Vegan Cheese",
           "Peanuts", "Sunflower Seeds",
           "Milk", "Almond Milk"
       );
       
       for (String ingredient : originalIngredients) {
           if (currentCustomer.getAllergies().toLowerCase().contains(ingredient.toLowerCase())) {
               String substitute = substitutionMap.getOrDefault(ingredient, "No substitution available");
               System.out.println(ingredient + " → " + substitute);
           }
       }
   }
   /**
    * Displays the customer's order history, including the last order date and a list of past orders.
    * If the customer hasn't placed any orders, a message is displayed indicating that no orders exist.
    *
    * @author Omar
    */
   private static void viewPastOrders() {
       System.out.println("\n=== Your Order History ===");
       if (currentCustomer.getOrderHistory() == null || currentCustomer.getOrderHistory().isEmpty()) {
           System.out.println("You haven't placed any orders yet.");
           return;
       }
       
       System.out.println("Last order date: " + currentCustomer.getLastOrderDate());
       System.out.println("\nYour past orders:");
       for (String order : currentCustomer.getOrderHistory()) {
           System.out.println("- " + order);
       }
   }
   /**
    * Allows the customer to reorder a meal from their order history.
    * The customer selects a meal by its number from the list of past orders, 
    * and the order is placed again with the current date and price.
    * The order history is updated, and the last order date is set.
    * 
    * If the customer hasn't placed any orders, they will be notified.
    * If an invalid selection is made, an error message is displayed.
    *
    * @author Omar
    * @param scanner the Scanner object used to read user input for meal selection
    */
   private static void reorderFavoriteMeals(Scanner scanner) {
       System.out.println("\n=== Reorder Favorite Meals ===");
       if (currentCustomer.getOrderHistory() == null || currentCustomer.getOrderHistory().isEmpty()) {
           System.out.println("You haven't placed any orders yet.");
           return;
       }
       
       System.out.println("Your previous orders:");
       List<String> pastOrders = currentCustomer.getOrderHistory();
       for (int i = 0; i < pastOrders.size(); i++) {
           System.out.println((i+1) + ". " + pastOrders.get(i));
       }
       
       System.out.print("\nEnter the number of the meal you want to reorder (or 0 to cancel): ");
       try {
           int choice = scanner.nextInt();
           scanner.nextLine(); 
           if (choice == 0) return;
           
           if (choice > 0 && choice <= pastOrders.size()) {
               String selectedOrder = pastOrders.get(choice-1);
               String[] parts = selectedOrder.split(" - ₪");
               String mealName = parts[0];
               double price = Double.parseDouble(parts[1].split(" ")[0]);
               
               java.time.LocalDate currentDate = java.time.LocalDate.now();
               String formattedDate = currentDate.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
               
               System.out.printf("\nYour order has been successfully placed. You ordered: %s - Price: ₪%.2f%n", 
                   mealName, price);
               System.out.println("Order Date: " + formattedDate);
               
               String reorderWithDate = mealName + " - ₪" + price + " (Reordered: " + formattedDate + ")";
               currentCustomer.getOrderHistory().add(reorderWithDate);
               
               currentCustomer.setLastOrderDate(formattedDate);
               customersMap.put(currentCustomer.getUsername(), currentCustomer);
               saveCustomersData();
           } else {
               System.out.println("Invalid selection!");
           }
           
       } catch (InputMismatchException e) {
           System.out.println("Invalid input! Please enter a number.");
           scanner.nextLine();
       } catch (Exception e) {
           System.out.println("Error processing your reorder. Please try again.");
       }
   }
   /**
    * Displays the customer's upcoming deliveries by checking the order history for scheduled delivery dates.
    * If an order has a scheduled delivery that is later than the current date, it will be displayed.
    * If there are no upcoming deliveries, a message will indicate this.
    * 
    * The delivery date is calculated as one day after the order date.
    * 
    * @author Omar
    */
   private static void viewUpcomingDeliveries() {
       System.out.println("\n=== Your Upcoming Deliveries ===");
       
       if (currentCustomer.getOrderHistory() == null || currentCustomer.getOrderHistory().isEmpty()) {
           System.out.println("You have no upcoming deliveries.");
           return;
       }
       
       LocalDateTime now = LocalDateTime.now();
       DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       
       boolean hasUpcomingDeliveries = false;
       int deliveryNumber = 1;
       
       for (String order : currentCustomer.getOrderHistory()) {
           try {
               String datePart = order.substring(order.indexOf("Ordered: ") + 9, order.indexOf(")"));
               LocalDate orderDate = LocalDate.parse(datePart, dateFormatter);
               
               LocalDate deliveryDate = orderDate.plusDays(1);
               
               if (deliveryDate.isAfter(now.toLocalDate())) {  
                   hasUpcomingDeliveries = true;
                   String mealName = order.substring(0, order.indexOf(" - ₪"));
                   System.out.printf("%d. %s - Scheduled for %s%n", 
                       deliveryNumber++, 
                       mealName, 
                       deliveryDate.format(dateFormatter));
               }
           } catch (Exception e) {
               continue;
           }
       }
       
       if (!hasUpcomingDeliveries) {
           System.out.println("No upcoming deliveries found.");
       }
   }
   /**
    * Loads the kitchen managers' data from a JSON file and populates the `managersMap`.
    * <p>
    * This method reads a JSON file containing a list of `KitchenManager` objects, deserializes the data using Jackson's `ObjectMapper`,
    * and stores each manager's information in a `managersMap`, where the key is the manager's username.
    * If any unknown properties are encountered during deserialization, they are ignored.
    * </p>
    * 
    * @author Omar
    */
   private static void loadManagersData() {
       try {
           ObjectMapper mapper = new ObjectMapper();
           mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
           
           List<KitchenManager> managers = mapper.readValue(
               new File(KITCHEN_MANAGERS_FILE),
               new TypeReference<List<KitchenManager>>() {}
           );
           
           for (KitchenManager manager : managers) {
               managersMap.put(manager.getUsername(), manager);
           }
       } catch (IOException e) {
           System.err.println("Error loading managers data: " + e.getMessage());
       }
   }
}
    