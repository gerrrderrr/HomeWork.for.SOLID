import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Goods {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final String productName;
    private final String productBrand;
    private final double price;
    private final int weight;
    private final LocalDate manufactureDate;
    private final LocalDate expirationDate;

    public Goods(String productName,
                 String productBrand,
                 double price,
                 int weight,
                 LocalDate manufactureDate,
                 LocalDate expirationDate) {

        this.productName = productName;
        this.productBrand = productBrand;
        this.price = price;
        this.weight = weight;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public String getManufactureDate() {
        return manufactureDate.format(format);
    }

    public String getExpirationDate() {
        return expirationDate.format(format);
    }

    public String getProductName() {
        return productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public int daysLeftBeforeExpired() {
        return expirationDate.compareTo(LocalDate.now());
    }

    public boolean isExpired() {
        return daysLeftBeforeExpired() <= 0;
    }

    @Override
    public int hashCode() {
        return (productName + productBrand).toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return null;
    }

    public abstract void wasBought(int amount);

    public abstract String getMeasuredIn();
}
