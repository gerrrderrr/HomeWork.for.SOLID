public interface ReturnProduct {
    boolean productExist(int hashCode);
    Goods getProductByHash(int hashCode);
}
