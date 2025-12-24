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
        sc.nextLine();
        System.out.print("Role: ");
        String role = sc.next();
        System.out.print("Password: ");
        int password = sc.nextInt();

        System.out.println("\nWelcome " + name + "!");
        
        // Create InventoryManager instance
        MenuManager.InventoryManager inventoryManager = menuManager.new InventoryManager(sc);
        
        int choice;

        do {
            System.out.println("=".repeat(10) + "Main Page" + "=".repeat(10));
            System.out.println("1. Menu");
            System.out.println("2. Order");
            System.out.println("3. Report");
            System.out.println("4. Ingredient");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");

            choice = sc.nextInt();

            if(choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            switch(choice) {
                case 1:
                    handleMenuOption(sc, menuManager, role);
                    break;
                case 2:
                    handleOrderOption(sc, menuManager);
                    break;
                case 3:
                    handleReportOption(sc);
                    break;
                case 4:
                    handleIngredientOption(sc, inventoryManager);
                    break;
                case 5:
                    System.out.println("Thank you for using the system. Goodbye!");
                    break;
            }
        } while (choice != 5);

        sc.close();
    }
     static final String ORDER_FILE = "orders.txt";
     static int lastOrderId = 0;   
    // View orders
    static void viewOrders() {
        try (BufferedReader br = new BufferedReader(new FileReader(ORDER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No orders found.");
        }
    }

    // Save order
    static void saveOrder(Order order) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ORDER_FILE, true))) {
            bw.write("ORDER_ID:" + order.orderId);
            bw.newLine();
            bw.write("CUSTOMER:" + order.customer);
            bw.newLine();

            for (OrderItem item : order.items) {
                bw.write(item.toString());
                bw.newLine();
            }

            bw.write("TOTAL=" + order.total);
            bw.newLine();
            bw.write("INCOME=" + order.income);
            bw.newLine();
            bw.write("-----");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving order.");
        }
    }
    
    static void removeOrder(int orderId) {
    File input = new File(ORDER_FILE);
    File temp = new File("temp.txt");
    boolean found = false;

    try (
        BufferedReader br = new BufferedReader(new FileReader(input));
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp))
    ) {
        String line;
        boolean skip = false;

        while ((line = br.readLine()) != null) {
            if (line.equals("ORDER_ID:" + orderId)) {
                skip = true;
                found = true;
            }

            if (!skip) {
                bw.write(line);
                bw.newLine();
            }

            if (skip && line.equals("-----")) {
                skip = false;
            }
        }
    } catch (IOException e) {
        System.out.println("Error removing order.");
        return;
    }

    input.delete();
    temp.renameTo(input);

    System.out.println(found ? "Order removed successfully." : "Order not found.");
}
    
    // Handle Menu operations
    private static void handleMenuOption(Scanner sc, MenuManager menuManager, String role) {
        int choice2;
        do {
            System.out.println("=".repeat(10) + "Menu" + "=".repeat(10));
            System.out.println("1. View Menu Items");
            System.out.println("2. Add Menu Item");
            System.out.println("3. Remove Menu Item");
            System.out.println("4. Back to Main");
            System.out.print("Your choice: ");

            choice2 = sc.nextInt();

            if(choice2 < 1 || choice2 > 4) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

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
                case 4:
                    return;
            }
        } while (choice2 != 4);
    }

    // Handle Order operations
    private static void handleOrderOption(Scanner sc, MenuManager menuManager) {
        int choice3;
        do {
            System.out.println("=".repeat(10) + "Order" + "=".repeat(10));
            System.out.println("1. View Orders");
            System.out.println("2. Add Order");
            System.out.println("3. Remove Order");
            System.out.println("4. Back to Main");
            System.out.print("Your choice: ");

            choice3 = sc.nextInt();
            sc.nextLine();

            if(choice3 < 1 || choice3 > 4) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            switch(choice3) {
                case 1:
                    System.out.println("Viewing orders...");
                    viewOrders();
                    break;
                case 2:
                    System.out.println("Adding new order...");
                    sc.nextLine(); 

                    System.out.print("Customer name: ");
                    String cname = sc.nextLine();

                    System.out.print("Customer ID: ");
                    int cid = sc.nextInt();

                    System.out.print("Phone: ");
                    int phone = sc.nextInt();
                    sc.nextLine();

                    Customer customer = new Customer(cname, cid, phone);
                    Order order = new Order(++lastOrderId, customer);

                    char more;
                    do {
                        menuManager.viewMenu(); 

                        System.out.print("Food name: ");
                        String foodName = sc.nextLine();

                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();
                        sc.nextLine();

                        MenuItem selected = null;
                        for (MenuItem item : menuManager.getMenuItems()) {
                            if (item.foodName.equalsIgnoreCase(foodName.trim())) { 
                                selected = item;
                                break;
                            }
                        }

                        if (selected != null) {
                            order.addItem(new OrderItem(
                                selected.foodName,
                                qty,
                                selected.foodPrice,
                                selected.foodIncome
                            ));
                        } else {
                            System.out.println("Food not found.");
                        }

                        System.out.print("Add more items? (y/n): ");
                        more = sc.next().toLowerCase().charAt(0);
                        sc.nextLine();

                    } while (more == 'y');

                    saveOrder(order);
                    System.out.println("Order placed successfully!");
                    System.out.println("Total: Rs." + order.total);
                    System.out.println("Restaurant income: Rs." + order.income);
                    break;
                case 3:
                    System.out.println("Removing order...");
                    System.out.print("Enter Order ID: ");
                    int orderId = sc.nextInt();
                    sc.nextLine(); 
                    removeOrder(orderId);
                    break;
                case 4:
                    return;
            }
        } while (choice3 != 4);
    }

    // Handle Report operations
    private static void handleReportOption(Scanner sc) {
        int choice4;
        do {
            System.out.println("=".repeat(10) + "Report" + "=".repeat(10));
            System.out.println("1. View daily report");
            System.out.println("2. View monthly report");
            System.out.println("3. View annually report");
            System.out.println("4. Back to Main");
            System.out.print("Your choice: ");

            choice4 = sc.nextInt();

            if(choice4 < 1 || choice4 > 4) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            switch(choice4) {
                case 1:
                    System.out.println("Displaying daily report...");
                    // TODO: Implement daily report functionality
                    break;
                case 2:
                    System.out.println("Displaying monthly report...");
                    // TODO: Implement monthly report functionality
                    break;
                case 3:
                    System.out.println("Displaying annual report...");
                    // TODO: Implement annual report functionality
                    break;
                case 4:
                    return;
            }
        } while (choice4 != 4);
    }

    // Handle Ingredient/Inventory operations
    private static void handleIngredientOption(Scanner sc, MenuManager.InventoryManager inventoryManager) {
        int choice5;
        do {
            System.out.println("=".repeat(10) + "Ingredient" + "=".repeat(10));
            System.out.println("1. View Recipes");
            System.out.println("2. Check Inventory");
            System.out.println("3. Back to Main");
            System.out.print("Your choice: ");

            choice5 = sc.nextInt();

            if(choice5 < 1 || choice5 > 3) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            switch(choice5) {
                case 1:
                    System.out.println("Viewing recipes...");
                    // TODO: Implement view recipes functionality
                    break;
                case 2:
                    handleInventoryOption(sc, inventoryManager);
                    break;
                case 3:
                    return;
            }
        } while (choice5 != 3);
    }

    // Handle Inventory sub-menu
    private static void handleInventoryOption(Scanner sc, MenuManager.InventoryManager inventoryManager) {
        int choice6;
        do {
            System.out.println("=".repeat(10) + "Inventory" + "=".repeat(10));
            System.out.println("1. View Inventory");
            System.out.println("2. Add Inventory");
            System.out.println("3. Update Inventory");
            System.out.println("4. Back");
            System.out.print("Your choice: ");

            choice6 = sc.nextInt();

            if(choice6 < 1 || choice6 > 4) {
                System.out.println("Invalid choice. Please try again.");
                continue;
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
                case 4:
                    return;
            }
        } while (choice6 != 4);
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
        public List<MenuItem> getMenuItems() {
            return menuItems;
        }
        public void viewMenu() {
            if (menuItems.isEmpty()) {
                System.out.println("Menu is empty!");
                return;
            }
            System.out.println("\n===== MENU =====");

            for (MenuItem item : menuItems) {
                System.out.println(
                "Name: " + item.foodName +
                " | Category: " + item.category +
                " | ID: " + item.foodId +
                " | Price: Rs." + item.foodPrice +
                " | Income: Rs." + item.foodIncome
            );
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
            private Map<String, Integer> inventory;
            private Scanner sc;

            public InventoryManager(Scanner scanner) {
                this.sc = scanner;
                this.inventory = new HashMap<>();
            }

            public void viewInventory() {
                if (inventory.isEmpty()) {
                    System.out.println("Inventory is empty!");
                    return;
                }
                System.out.println("\n===== INVENTORY =====");
                for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                    System.out.println("Item: " + entry.getKey() + " | Quantity: " + entry.getValue());
                }
                System.out.println("=====================\n");
            }

            public void addInventory() {
                sc.nextLine(); // Clear buffer
                System.out.print("Enter item name: ");
                String itemName = sc.nextLine();
                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();
                inventory.put(itemName, quantity);
                System.out.println("Item '" + itemName + "' added successfully!");
            }

            public void updateInventory() {
                sc.nextLine(); // Clear buffer
                System.out.print("Enter item name: ");
                String itemName = sc.nextLine();
                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();
                if (inventory.containsKey(itemName)) {
                    inventory.put(itemName, quantity);
                    System.out.println("Item '" + itemName + "' updated successfully!");
                } else {
                    System.out.println("Item '" + itemName + "' not found!");
                }
            }
        }
    }
    static class Customer extends Person {
        public Customer(String name, int ID, int phone) {
            super(name, ID, "Customer", phone, 0, 0);
        }

        @Override
        public String toString() {
            return name + "|" + ID + "|" + phone;
        }
    }

    static class OrderItem {
        String foodName;
        int quantity;
        double price;
        double income;

        public OrderItem(String foodName, int quantity, double price, double income) {
            this.foodName = foodName;
            this.quantity = quantity;
            this.price = price;
            this.income = income;
        }

        public double totalPrice() {
            return price * quantity;
        }

        public double totalIncome() {
            return income * quantity;
        }

        @Override
        public String toString() {
            return foodName + "," + quantity + "," + price + "," + income;
        }
    }

    static class Order {
        int orderId;
        Customer customer;
        List<OrderItem> items = new ArrayList<>();
        double total = 0;
        double income = 0;

        public Order(int orderId, Customer customer) {
            this.orderId = orderId;
            this.customer = customer;
        }

        public void addItem(OrderItem item) {
            items.add(item);
            total += item.totalPrice();
            income += item.totalIncome();
        }
    }
}
