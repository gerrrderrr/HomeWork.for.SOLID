import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();
        Goods soyMilk = new Liquid("Milk",
                "Soy",
                120,
                1000,
                LocalDate.of(2022, 9, 15),
                LocalDate.of(2022, 11, 22));
        Goods bread = new Solid("Bread",
                "Good Bread",
                60,
                600,
                LocalDate.of(2022, 9, 1),
                LocalDate.of(2022, 9, 9));
        Goods water = new Liquid("Water",
                "Aqua",
                45.5,
                1500,
                LocalDate.of(2022, 8, 2),
                LocalDate.of(2023, 3, 23));
        Goods water2 = new Liquid("Water",
                "Crystal",
                250,
                1500,
                LocalDate.of(2022, 9, 10),
                LocalDate.of(2023, 9, 10));
        Goods candies = new Solid("Candies",
                "Joy",
                100,
                100,
                LocalDate.of(2022, 5, 28),
                LocalDate.of(2023, 1, 12));
        Goods candies2 = new Solid("Candies",
                "Lolly",
                110,
                50,
                LocalDate.of(2022, 8, 20),
                LocalDate.of(2023, 10, 10));
        Goods chocolate = new Solid("Chocolate",
                "Lolly",
                140.5,
                150,
                LocalDate.of(2022, 7, 21),
                LocalDate.of(2023, 10, 1));
        GoodsAvailable list = new GoodsAvailable();
        list.addProduct(soyMilk);
        list.addProduct(bread);
        list.addProduct(water);
        list.addProduct(candies);
        list.addProduct(water2);
        list.addProduct(candies2);
        list.addProduct(chocolate);

        System.out.println("Hello, stranger! \nHere is items for purchasing: \n");
        list.printListOfProducts();
        while (true) {
            printMenu();
            int input = 0;
            String inputLine = scanner.nextLine();
            if (inputLine.equals("exit")) {
                break;
            }
            try {
                input = Integer.parseInt(inputLine);
            } catch (InputMismatchException e) {
                System.out.println("Expected number or \"exit\", but got: " + inputLine);
            }
            if (input != 0) {
                menuChoice(input, list, scanner, cart);
            }
        }
        System.out.println("Bye Bye");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("""
                
                Type "1" to sort products by price from MAX to MIN
                Type "2" to sort products by price from MIN to MAX
                Type "3" to search by brand
                Type "4" to search by product
                Type "5" to start adding products to your cart
                Type "6" to remove item from your cart
                Type "7" to print your cart
                Type "8" to sort products by price from MAX to MIN
                Type "9" to sort products by price from MIN to MAX
                Type "10" check if expired
                Type "exit" to stop shopping
                """);
    }

    private static void menuChoice(int input, GoodsAvailable list, Scanner scanner, Cart cart) {
        switch (input) {
            case 1 -> list.sortProductsByPriceMaxToMin();
            case 2 -> list.sortProductsByPriceMinToMax();
            case 3 -> {
                String brand = scanner.nextLine();
                list.printListOfSameBrand(brand);
            }
            case 4 -> {
                String product = scanner.nextLine();
                list.printListOfSomeProducts(product);
            }
            case 5 -> cart.addProducts(list, scanner);
            case 6 -> {
                cart.printCart();
                System.out.println("Enter name of the product, brand and amount to delete\n");
                String delete = scanner.nextLine();
                cart.removeFromCart(delete);
            }
            case 7 -> cart.printCart();
            case 8 -> cart.sortProductsByPriceMaxToMin();
            case 9 -> cart.sortProductsByPriceMinToMax();
            case 10 -> {
                String item = scanner.nextLine();
                String[] hash = item.split(",");
                final int amountObjectsInArrayNeeded = 2;
                if (hash.length == amountObjectsInArrayNeeded) {
                    int hashCode = (hash[0].trim() + hash[1].trim()).toLowerCase().hashCode();
                    if (list.productExist(hashCode)) {
                        System.out.println(list.getProductByHash(hashCode).isExpired());
                    } else {
                        System.out.println("No product was found");
                    }
                } else {
                    System.out.println("Expected (example): Milk, Soy");
                }
            }
        }
    }
}
