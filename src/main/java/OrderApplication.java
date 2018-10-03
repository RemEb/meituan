import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderApplication {
    public static void main(String[] args) {
        OrderDishes[] orderDishes = new OrderDishes[2];
        OrderDishes orderDishes1 = new OrderDishes();
        orderDishes[0] = orderDishes1;
        Dishes dishes = new Dishes();
        dishes.setName("红烧鸡翅");
        dishes.setPrice(20d);
        orderDishes1.setDishes(dishes);
        orderDishes1.setCount(2);

        orderDishes1 = new OrderDishes();
        orderDishes[1] = orderDishes1;
        dishes = new Dishes();
        dishes.setPrice(33d);
        dishes.setName("清蒸鲍鱼");
        orderDishes1.setDishes(dishes);
        orderDishes1.setCount(3);

        OrderService orderService = new OrderService();
        Order order = new Order();

        try {
            order = orderService.createOrder(orderDishes);
        } catch (NoAddressException e) {
            System.out.println("请输入收货地市");
            initAddress(orderService);
            try{
                order = orderService.createOrder(orderDishes);
            }catch (NoAddressException e1){
                e1.printStackTrace();
            }
        }
        orderService.confirmOrder(order);
        orderService.payOrder(order);

    }

    public static void initAddress(OrderService orderService) {
        String str = new String();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            str = bufferedReader.readLine();

        } catch (IOException e) {

        }
        orderService.setAddress(str);

    }

}