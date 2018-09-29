import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class OrderService {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Order createOrder(OrderDishes[] orderDishes) throws NoAddressException {
        if (address == null) {
            throw new NoAddressException();
        }
        Order order = new Order();
        double totalAmount = 0d;
        for (OrderDishes orderDish : orderDishes) {
            double amount = orderDish.getDishes().getPrice() * orderDish.getCount();
            totalAmount = totalAmount + amount;
        }
        order.setTotalAmount(totalAmount);
        order.setOrderDishes(orderDishes);
        return order;
    }
    public void confirmOrder(Order order){
        System.out.println("收货地址是："+address);
        System.out.println("-------------------");
        System.out.println("本次下单的商品信息是");
        System.out.println("------");
        for (OrderDishes orderDish : order.getOrderDishes()) {
            System.out.println("菜品名称-"+orderDish.getDishes().getName());
            System.out.println("菜品价格-"+orderDish.getDishes().getPrice());
            System.out.println("菜品数量-"+orderDish.getCount());
            System.out.println("-----------");
        }
        System.out.println("所要支付的总金额是："+order.getTotalAmount());
    }
    public void payOrder(Order order){
        boolean isTrue = false;
        while (!isTrue){
            String str = null;
            try{
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                str = bufferedReader.readLine();
            }catch (IOException e){
                e.printStackTrace();
            }
            double amount = Double.valueOf(str);
            if (amount == order.getTotalAmount()){
                isTrue = true;
                System.out.println("金额正确，订单已完成");
            }else{
                System.out.println("输错金额啦");
            }
        }
    }
}
