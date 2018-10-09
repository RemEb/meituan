public abstract class AbstractPayService implements PayService {
    public boolean callback(String payId, double amount) {
        System.out.println("核销订单支付");

        return true;
    }
}
