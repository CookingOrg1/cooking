# Cooking Management System

Cooking Management System is a Java-based software engineering project that simulates the core logic of a meal ordering and kitchen management platform. The project was developed as a course project and focuses on backend/domain logic, object-oriented programming, and behavior-driven development rather than a graphical user interface.

The system is designed to support the main operations of a cooking service where customers can order customized meals, chefs can prepare meals based on customer needs, kitchen managers can monitor inventory and assign tasks, and system administrators can manage financial reporting workflows.

## Project Description

The main idea of the project is to organize the relationship between customers, chefs, kitchen managers, ingredients, orders, and financial operations inside a cooking management system.

Customers can store personal information such as name, email, phone number, address, dietary preferences, allergies, preferred ingredients, disliked ingredients, payment method, and previous order history. Based on this information, the system can recommend suitable meals and help generate personalized meal plans that match the customer's preferences and restrictions.

The project also includes meal and order management logic. Meals can contain ingredients and prices, while orders can be created, stored, retrieved, and added to a customer's order history. The system supports viewing past meal orders and reordering meals through the tested scenarios.

For kitchen operations, the system includes ingredient and stock management. Ingredients can have stock quantities, reorder thresholds, availability status, units, and expiry dates. The system can detect low stock, urgent out-of-stock situations, and ingredients that are close to expiry. It can also suggest alternative ingredients when an ingredient is unavailable or when it does not match a dietary restriction.

Chef-related features include task assignment, workload tracking, meal customization, and notifications. A kitchen manager can assign tasks to chefs, and the system sends notifications when a task is assigned. Chefs can also receive alerts about ingredient substitutions and approve or reject them.

The system also includes billing and financial reporting functionality. It can generate invoices with customer name, invoice number, item quantity, item price, taxes, transaction date, and total amount. It also includes financial report logic for revenue analysis, transaction counts, and custom date ranges.

## Main Features

- Customer profile management
- Dietary preferences and allergy handling
- Personalized meal recommendations
- Meal customization
- Cart management
- Order creation and order history
- Past order viewing and reordering scenarios
- Ingredient availability checking
- Alternative ingredient suggestions
- Stock monitoring and low-stock alerts
- Expiry-date alerts for ingredients
- Chef task assignment
- Chef workload tracking
- Notification management
- Billing and invoice generation
- Financial reporting workflows
- Sample JSON data for customers, chefs, managers, admins, and inventory
- Unit testing and BDD acceptance testing

## Technologies Used

- Java 17
- Maven
- JUnit
- Cucumber
- Jackson Databind
- JaCoCo
- GitHub Actions / SonarCloud workflow

## Testing

The project uses JUnit tests and Cucumber BDD feature files to validate the system behavior. The Cucumber scenarios describe real user workflows such as meal customization, customer dietary preferences, stock alerts, chef task assignment, order management, billing, and financial reporting.

Overall, this project demonstrates object-oriented design, modular Java development, automated testing, and software engineering practices through a practical cooking and kitchen management system.


