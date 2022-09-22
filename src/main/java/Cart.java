import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cart implements Sort {
    private final Map<Goods, Integer> cart = new HashMap<>();

    private void addToCart(Goods product, int amount) {
        if (!cart.containsKey(product)) {
            cart.put(product, amount);
            product.wasBought(amount);
        } else {
            int newAmount = cart.get(product) + amount;
            cart.replace(product, cart.get(product), newAmount);
            System.out.println(amount + " products was added to existed amount");
        }
    }

    public void removeFromCart(String input) {
        String[] delete = input.split(",");
        final int amountObjectsInArrayNeeded = 3;
        if (delete.length == amountObjectsInArrayNeeded) {
            int amountToDel = 0;
            int hashCode = (delete[0].trim() + delete[1].trim()).toLowerCase().hashCode();
            try {
                amountToDel = Integer.parseInt(delete[2].trim());
            } catch (NumberFormatException e) {
                System.out.println("Expected NUM");
            }
            if (containProduct(hashCode) && amountToDel != 0) {
                int deleted;
                Goods product = returnProduct(hashCode);
                if (cart.get(product) < amountToDel) {
                    deleted = cart.get(product);
                    cart.remove(product);
                    System.out.println("You wanted to delete " + amountToDel
                            + ", but in your cart was found and successfully deleted " + deleted + " items");
                } else if (cart.get(product) == amountToDel) {
                    cart.remove(product);
                    System.out.println("Deleted " + amountToDel + " products");
                } else {
                    int amountNew = cart.get(product) - amountToDel;
                    cart.replace(product, cart.get(product), amountNew);
                    System.out.println("Deleted " + amountToDel + " products");
                }
            } else {
                System.out.println("Item doesn't exist");
            }
        }
    }

    public void printCart() {
        if (!cart.isEmpty()) {
            for (Goods product : cart.keySet()) {
                System.out.println(cart.get(product) + " | " + product);
            }
        } else {
            System.out.println("There is nothing to print");
        }
    }

    @Override
    public void sortProductsByPriceMinToMax() {
        if (!cart.isEmpty()) {
            cart.keySet().stream().sorted(Comparator.comparing(Goods::getPrice)).forEach(System.out::println);
        } else {
            System.out.println("There is nothing to sort");
        }
    }

    @Override
    public void sortProductsByPriceMaxToMin() {
        if (!cart.isEmpty()) {
            cart.keySet().stream().sorted(Comparator.comparing(Goods::getPrice).reversed()).forEach(System.out::println);
        } else {
            System.out.println("There is nothing to sort");
        }
    }

    public void addProducts(GoodsAvailable list, Scanner scanner) {
        list.printListOfProducts();
        while (true) {
            System.out.println("""

                    Type product name, brand and amount (separated by commas) to add product to your cart\s
                    or type "exit" to exit to the main menu""");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            String[] product = input.split(",");
            final int amountObjectsInArrayNeeded = 3;
            if (product.length == amountObjectsInArrayNeeded) {
                int hashOfProduct = (product[0].trim() + product[1].trim()).toLowerCase().hashCode();
                int amount = 0;
                try {
                    amount = Integer.parseInt(product[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Amount has to be NUM");
                }
                if (list.productExist(hashOfProduct) && amount != 0) {
                    addToCart(list.getProductByHash(hashOfProduct), amount);
                } else if (!list.productExist(hashOfProduct)) {
                    System.out.println("Product doesn't exist");
                }
            } else {
                System.out.println("You entered: " + input + ". Expected(example): \"Bread, Good Bread, 4\"");
            }
        }
    }

    public boolean containProduct(int hashCode) {
        for (Goods product : cart.keySet()) {
            if (product.hashCode() == hashCode) {
                return true;
            }
        }
        return false;
    }

    public Goods returnProduct(int hashCode) {
        for (Goods product : cart.keySet()) {
            if (product.hashCode() == hashCode) {
                return product;
            }
        }
        return null;
    }
}
