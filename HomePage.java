import java.io.*;
import java.util.*;

public class HomePage {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        MenuManager menuManager = new MenuManager("menu.txt");

        //log in (dif role have dif password)
        System.out.println("Please enter your info.");
        System.out.print("Username: ");
        String name = sc.nextLine();
        System.out.print("ID: ");
        int ID = sc.nextInt();
        System.out.print("Role: ");
        String role = sc.next();
        System.out.print("Password: ");
        int password = sc.nextInt();

        System.out.println("\nWelcome " + name + "!");
        
        int choice;

        do {
            System.out.println("=".repeat(10) + "Main Page" + "=".repeat(10));
            System.out.println("1. Menu");
            System.out.println("2. Order");
            System.out.println("3. Report");
            System.out.println("4. Ingredient");
            System.out.print("Your choice: ");

            choice = sc.nextInt();

            if(choice < 1 || choice > 4) {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);

        switch(choice) {
            case 1:
                int choice2;
                do {
                    System.out.println("=".repeat(10) + "Menu" + "=".repeat(10));
                    System.out.println("1. View Menu Items");
                    System.out.println("2. Add Menu Item");
                    System.out.println("3. Remove Menu Item");
                    System.out.print("Your choice: ");

                    choice2 = sc.nextInt();

                    switch(choice2) {
                        case 1:
                            menuManager.viewMenu();
                            break;
                        case 2: {
                            if(role.equals("Manager")) {
                                sc.nextLine();
                                System.out.print("Enter food name: ");
                                String food = sc.nextLine();
                                System.out.print("Enter category: ");
                                String category = sc.nextLine();
                                System.out.print("Enter food ID: ");
                                int foodId = sc.nextInt();
                                System.out.print("Enter food price: ");
                                double price = sc.nextDouble();
                                System.out.print("Enter food income: ");
                                double income = sc.nextDouble();
                                menuManager.addMenuItem(new MenuItem(food, category, foodId, price, income));
                            } else {
                                System.out.println("You don't have permission to add menu items.");
                            }
                            break;
                        }
                        case 3: {
                            if(role.equals("Manager")) {
                                sc.nextLine();
                                System.out.print("Enter food name to remove: ");
                                String foodToRemove = sc.nextLine();
                                menuManager.removeMenuItem(foodToRemove);
                            } else {
                                System.out.println("You don't have permission to remove menu items.");
                            }
                            
                            break;
                        }
                    }

                    if(choice2 != 1 && choice2 != 2 && choice2 != 3 && choice2 != 4) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } while (choice2 < 1 || choice2 > 4);
                break;

            case 2:
                int choice3;
                do {
                    System.out.println("=".repeat(10) + "Order" + "=".repeat(10));
                    System.out.println("1. View Orders");
                    System.out.println("2. Add Order");
                    System.out.println("3. Remove Order");
                    System.out.print("Your choice: ");

                    choice3 = sc.nextInt();

                    if(choice3 != 1 && choice3 != 2 && choice3 != 3) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } while (choice3 < 1 || choice3 > 3);
              
                break;

            case 3:
                int choice4;
                do {
                    System.out.println("=".repeat(10) + "Report" + "=".repeat(10));
                    System.out.println("1. View daily report");
                    System.out.println("2. View monthly report");
                    System.out.println("3. View annually report");
                    System.out.print("Your choice: ");

                    choice4 = sc.nextInt();

                    if(choice4 != 1 && choice4 != 2 && choice4 != 3) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } while (choice4 != 1 && choice4 != 2 && choice4 != 3);
                break;

            case 4:
                int choice5;
                int choice6;
                do {
                    System.out.println("=".repeat(10) + "Ingredient" + "=".repeat(10));
                    System.out.println("1. View Recipes");
                    System.out.println("2. Check Inventory");
                    System.out.print("Your choice: ");

                    choice5 = sc.nextInt();
                    
                    if(choice5 == 2) {
                            System.out.println("=".repeat(10) + "Inventory" + "=".repeat(10));
                            System.out.println("1. View Inventory");
                            System.out.println("2. Add Inventory");
                            System.out.println("3. Update Inventory");
                            System.out.print("Your choice: ");
                            choice6 = sc.nextInt();

                            while(choice6 != 1 && choice6 != 2 && choice6 != 3) {
                                System.out.println("Invalid choice. Please try again.");
                            }

                            switch(choice6) {
                                case 1:
                                    inventoryManager.viewInventory();
                                    break;
                                case 2:
                                    inventoryManager.addInventory();
                                    break;
                                case 3:
                                    inventoryManager.updateInventory();
                                    break;
                            }
                    }
                    if(choice5 != 1 && choice5 != 2) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } while (choice5 != 1 && choice5 != 2);
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        } 

        sc.close();
    }
    
    public static class Person {
        String name;
        int ID;
        String role;
        int phone;
        int password;
        double salary;

        public Person(String name, int ID, String role, int phone, int password, double salary) {
            this.name = name;
            this.ID = ID;
            this.role = role;
            this.phone = phone;
            this.password = password;
            this.salary = salary;
        }
    }

    public static class Employee extends Person {
        public Employee(String name, int ID, String role, int phone, int password, double salary) {
            super(name, ID, role, phone, password, salary);
        }
    }

    public static class Menu {
        String foodName;
        String category;
        int foodId;
        double foodPrice;
        double foodIncome;

        public Menu(String foodName, String category, int foodId, double foodPrice, double foodIncome) {
            this.foodName = foodName;
            this.category = category;
            this.foodId = foodId;
            this.foodPrice = foodPrice;
            this.foodIncome = foodIncome;
        }

        @Override
        public String toString() {
            return foodName + "|" + category + "|" + foodId + "|" + foodPrice + "|" + foodIncome;
        }

        public static Menu fromString(String line) {
            String[] parts = line.split("\\|");
            return new Menu(parts[0], parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
        }
    }

    public static class MenuItem extends Menu {
        public MenuItem(String foodName, String category, int foodId, double foodPrice, double foodIncome) {
            super(foodName, category, foodId, foodPrice, foodIncome);
        }
    }

    public static class MenuManager {
        private List<MenuItem> menuItems;
        private String filePath;

        public MenuManager(String filePath) {
            this.menuItems = new ArrayList<>();
            this.filePath = filePath;
            loadFromFile();
        }

        public void viewMenu() {
            if (menuItems.isEmpty()) {
                System.out.println("Menu is empty!");
                return;
            }
            System.out.println("\n===== MENU =====");
            for (MenuItem item : menuItems) {
                System.out.println("Name: " + item.foodName + " | Category: " + item.category + " | ID: " + item.foodId + " | Price: Rs." + item.foodPrice + " | Income: Rs." + item.foodIncome);
            }
            System.out.println("================\n");
        }

        public void addMenuItem(MenuItem item) {
            menuItems.add(item);
            saveToFile();
            System.out.println("MenuItem '" + item.foodName + "' added successfully!");
        }

        public void removeMenuItem(String foodName) {
            boolean removed = menuItems.removeIf(item -> item.foodName.equals(foodName));
            if (removed) {
                saveToFile();
                System.out.println("MenuItem '" + foodName + "' removed successfully!");
            } else {
                System.out.println("MenuItem '" + foodName + "' not found!");
            }
        }

        public void saveToFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (MenuItem item : menuItems) {
                    writer.write(item.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error saving menu: " + e.getMessage());
            }
        }

        public void loadFromFile() {
            File file = new File(filePath);
            if (!file.exists()) {
                return;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Menu menu = Menu.fromString(line);
                    menuItems.add(new MenuItem(menu.foodName, menu.category, menu.foodId, menu.foodPrice, menu.foodIncome));
                }
            } catch (IOException e) {
                System.out.println("Error loading menu: " + e.getMessage());
            }
        }

        public class InventoryManager {
            String filePath;
            String itemName;
            int quantity;
            private Map<String, Integer> inventory;

            public InventoryManager() {
                inventory = new HashMap<>();
            }

            public void viewInventory() {
                System.out.println("\n===== INVENTORY =====");
                for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                    System.out.println("Item: " + entry.getKey() + " | Quantity: " + entry.getValue());
                }
                System.out.println("=====================\n");
            }

            public void addInventory() {
                System.out.print("Enter item name: ");
                String itemName = sc.nextLine();
                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();
                inventory.put(itemName, quantity);
                System.out.println("Item '" + itemName + "' added successfully!");
            }

            public void updateInventory() {
                System.out.print("Enter item name: ");
                String itemName = sc.nextLine();
                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();
                inventory.put(itemName, quantity);
                System.out.println("Item '" + itemName + "' updated successfully!");
            }
        }
    }
}