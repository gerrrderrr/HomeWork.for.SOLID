import java.util.*;

public class GoodsAvailable implements Sort, ReturnProduct {
    private final Map<Integer, Goods> goods = new HashMap<>();

    public void addProduct(Goods product) {
        goods.put(product.hashCode(), product);
    }

    public void printListOfProducts() {
        for (Goods product : goods.values()) {
            System.out.println(product);
        }
    }

    public void printListOfSomeProducts(String getProducts) {
        int count = 0;
        for (Goods product : goods.values()) {
            if (getProducts.toLowerCase().contains(product.getProductName().toLowerCase())) {
                System.out.println(product);
                count++;
            }
        }
        if (count <= 0) {
            System.out.println("There is no items matching: \"" + getProducts + "\"");
        }
    }

    public void printListOfSameBrand(String getBrand) {
        int count = 0;
        for (Goods product : goods.values()) {
            if (getBrand.toLowerCase().contains(product.getProductBrand().toLowerCase())) {
                System.out.println(product);
                count++;
            }
        }
        if (count <= 0) {
            System.out.println("There is no items matching: \"" + getBrand + "\"");
        }
    }

    @Override
    public void sortProductsByPriceMinToMax() {
        goods.values().stream().sorted(Comparator.comparing(Goods::getPrice)).forEach(System.out::println);
    }

    @Override
    public void sortProductsByPriceMaxToMin() {
        goods.values().stream().sorted(Comparator.comparing(Goods::getPrice).reversed()).forEach(System.out::println);
    }

    @Override
    public boolean productExist(int hashCode) {
        return goods.containsKey(hashCode);
    }

    @Override
    public Goods getProductByHash(int hashCode) {
        return goods.get(hashCode);
    }
}
