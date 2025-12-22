import java.util.Scanner;

public class HomePage {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

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

        System.out.println("Welcome " + name + "!");
        System.out.println("MainPage: ");
        
        int choice;

        do {
            System.out.println("Please enter your choice: ");
            System.out.println("1. Menu");
            System.out.println("2. Order");
            System.out.println("3. Report");
            System.out.print("Your choice: ");

            choice = sc.nextInt();

            if(choice < 1 || choice > 3) {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 1 && choice != 2 && choice != 3);

        switch(choice) {
            case 1:
                int choice2;
                do {
                    System.out.println("Please enter your choice: ");
                    System.out.println("1. View Menu Items");
                    System.out.println("2. Add Menu Item");
                    System.out.println("3. Remove Menu Item");
                    System.out.print("Your choice: ");

                    choice2 = sc.nextInt();

                    switch(choice2) {
                        case 1:
                            System.out.println("Menu Items: ");
                            break;
                        case 2:
                            System.out.println("Add Menu Item: ");
                            break;
                        case 3:
                            System.out.println("Remove Menu Item: ");
                            break;
                    }

                    if(choice2 != 1 && choice2 != 2 && choice2 != 3) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } while (choice2 < 1 || choice2 > 3);
                break;

            case 2:
                int choice3;
                do {
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
                    System.out.println("1. View daily report");
                    System.out.println("2. View monthly report");
                    System.out.println("3. View annually report");
                    System.out.print("Your choice: ");

                    choice4 = sc.nextInt();

                    if(choice4 != 1 && choice4 != 2 && choice4 != 3) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } while (choice4 != 1 && choice4 != 2 && choice4 != 3); {
                    
                }
                break;
        }





        sc.close();
    }
    
    public class Person {
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

    public class employee extends Person {
        public employee(String name, int ID, String role, int phone, int password, double salary) {
            super(name, ID, role, phone, password, salary);
        }
    }

    public class Menu {
        String food;
        String drink;
        double price;

        public Menu(String food, String drink, double price) {
            this.food = food;
            this.drink = drink;
            this.price = price;
        }
    }

    public class MenuItem extends Menu {
        public MenuItem(String food, String drink, double price) {
            super(food, drink, price);
        }

        public void ViewMenu() {
            System.out.println("Menu Items: ");
        }

        public void AddMenuItem() {
            System.out.println("Add Menu Item: ");
        }

        public void RemoveMenuItem() {
            System.out.println("Remove Menu Item: ");
        }
    }

    
}
