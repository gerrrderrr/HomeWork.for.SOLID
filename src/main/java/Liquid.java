import java.time.LocalDate;

public class Liquid extends Goods {

    public Liquid(String productName,
                  String productBrand,
                  double price,
                  int weight,
                  LocalDate manufactureDate,
                  LocalDate expirationDate) {
        super(productName, productBrand, price, weight, manufactureDate, expirationDate);
    }

    @Override
    public void wasBought(int amount) {
        System.out.println("You bought " + amount + " "+ getProductName().toLowerCase()
                + " for " + (getPrice() * amount) + " rubles, weighing total "
                + (getWeight() * amount) + " " + getMeasuredIn() + ".");
    }

    @Override
    public String getMeasuredIn() {
        return "ml";
    }

    @Override
    public String toString() {
        return getProductName() + " \"" + getProductBrand()
                + "\" (" + getWeight() + " " + getMeasuredIn() + ") "
                + getPrice() + " rubles. "
                + "Manufacture date: " + getManufactureDate() + ". "
                + "Best before: " + getExpirationDate();
    }
}
