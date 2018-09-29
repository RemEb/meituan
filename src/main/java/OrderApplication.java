import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderApplication {
    public static void main(String[] args) throws NoAddressException {
        OrderDishes[] orderDishes = new OrderDishes[2];
        OrderDishes orderDishes1 = new OrderDishes();
        orderDishes[0] = orderDishes1;
        Dishes dishes = new Dishes();
        dishes.setPrice(20d);
        dishes.setName("红烧排骨");
        orderDishes1.setDishes(dishes);
        orderDishes1.setCount(2);

        orderDishes1 = new OrderDishes();
        orderDishes[1] = orderDishes1;
        dishes = new Dishes();
        dishes.setPrice(10d);
        dishes.setName("肉末茄子");
        orderDishes1.setDishes(dishes);
        orderDishes1.setCount(1);

        OrderService orderService = new OrderService();
        Order order = null;
        try {
            order = orderService.createOrder(orderDishes);
        }catch (NoAddressException e){
            System.out.println("亲，请输入收货地址！");
            initAddress(orderService);
            try {
                order = orderService.createOrder(orderDishes);
            }catch (NoAddressException e1){
                e1.printStackTrace();
            }
        }
        orderService.confirmOrder(order);
        orderService.payOrder(order);
    }
    public static void initAddress(OrderService orderService){
        String str = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            str = bufferedReader.readLine();
        }catch (IOException e){

        }
        orderService.setAddress(str);
    }
}