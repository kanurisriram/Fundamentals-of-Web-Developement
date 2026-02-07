import java.util.Scanner;

public class OnlineShopping {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Products
        String[] products = {"Laptop", "Mobile", "Headphones", "Keyboard"};
        int[] prices = {50000, 20000, 2000, 1500};

        int[] cart = new int[products.length];
        int choice;

        do {
            System.out.println("\n===== ONLINE SHOPPING =====");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nAvailable Products:");
                    for (int i = 0; i < products.length; i++) {
                        System.out.println((i + 1) + ". " + products[i] + " - Rs." + prices[i]);
                    }
                    break;

                case 2:
                    System.out.print("Enter product number to add to cart: ");
                    int productNo = sc.nextInt();

                    if (productNo >= 1 && productNo <= products.length) {
                        cart[productNo - 1]++;
                        System.out.println(products[productNo - 1] + " added to cart.");
                    } else {
                        System.out.println("Invalid product number!");
                    }
                    break;

                case 3:
                    System.out.println("\nYour Cart:");
                    boolean empty = true;
                    for (int i = 0; i < cart.length; i++) {
                        if (cart[i] > 0) {
                            System.out.println(products[i] + " x " + cart[i]);
                            empty = false;
                        }
                    }
                    if (empty) {
                        System.out.println("Cart is empty!");
                    }
                    break;

                case 4:
                    int total = 0;
                    System.out.println("\nBill Details:");
                    for (int i = 0; i < cart.length; i++) {
                        if (cart[i] > 0) {
                            int cost = cart[i] * prices[i];
                            total += cost;
                            System.out.println(products[i] + " x " + cart[i] + " = Rs." + cost);
                        }
                    }
                    System.out.println("Total Amount = Rs." + total);
                    System.out.println("Thank you for shopping!");
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}