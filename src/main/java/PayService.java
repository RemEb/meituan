public interface PayService {
    void pay(Order order);
    boolean callback(String payId,double amount);
}
