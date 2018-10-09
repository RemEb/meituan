public class PayServiceTest {
    public static void main(String[] args) {
        PayService payService = new WeixinPayService();
        Order order = new Order();
        order.setTotalAmount(100d);
        order.setOrderId("123");
        payService.pay(order);

        payService.callback("123",100d);
    }
}
