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
    
    private static void saveChefsData() {
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(CHEFS_FILE), new ArrayList<>(chefsMap.values()));
        } catch (IOException e) {
            System.err.println("Error saving chefs data: " + e.getMessage());
        }
    }
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

                            // Update statistics
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
   
    private static void saveCustomersData() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(new File(CUSTOMERS_FILE), customersMap.values());
        } catch (IOException e) {
            System.err.println("Error saving customer data: " + e.getMessage());
        }
    }
    
    

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
    private static void saveInventoryData() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(new File(INVENTORY_FILE), inventory);
        } catch (IOException e) {
            System.err.println("Error saving inventory data: " + e.getMessage());
        }
    }

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

    private static Customer findCustomerByName(String name) {
        for (Customer customer : customersMap.values()) {
            if ((customer.getFirstName() + " " + customer.getLastName()).equals(name)) {
                return customer;
            }
        }
        return null;
    }

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

    private static void browseAndCustomizeMeals(Scanner scanner) {
        System.out.println("\n=== Available Meals ===");
        for (int i = 0; i < availableMeals.size(); i++) {
            Meal meal = availableMeals.get(i);
            System.out.printf("%d. %s - ₪%.2f%n", i+1, meal.getName(), meal.getPrice());
            System.out.println("   Ingredients: " + String.join(", ", meal.getingredienT()));
        }
        System.out.printf("%d. Custom Meal Builder%n", availableMeals.size()+1);
        System.out.printf("%d. Back to Dashboard%n", availableMeals.size()+2);
        
        System.out.print("\nSelect a meal (1-" + (availableMeals.size()+2) + "): ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            if (choice == availableMeals.size()+2) return;
            
            if (choice == availableMeals.size()+1) {
                buildCustomMeal(scanner);
                return;
            }
            
            if (choice > 0 && choice <= availableMeals.size()) {
                Meal selectedMeal = availableMeals.get(choice-1);
                
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

    private static void placeOrder(String mealName, double price, Scanner scanner) {
        java.time.LocalDate currentDate = java.time.LocalDate.now();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
            .ofPattern("dd/MM/yyyy")
            .withLocale(java.util.Locale.UK); 
        
        String formattedDate = currentDate.format(formatter);
        
        System.out.printf("\nYour order has been successfully placed. You ordered: %s - Price: ₪%.2f%n", 
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
                    double newPrice = meal.getPrice() * 0.9;
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

    
    
    private static double calculateCustomMealPrice(List<String> ingredients) {
        double basePrice = 25.0;
        double pricePerIngredient = 3.5;
        return basePrice + (ingredients.size() * pricePerIngredient);
    }

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
                    System.out.printf("%d. %s - Scheduled for %s ", 
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
    