import java.util.Date;

public class Order {
    private String orderId;
    private String address;
    private Date orderTime;
    private Dishes[] orderDishes;
    private double totalAmount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Dishes[] getOrderDishes() {
        return orderDishes;
    }

    public void setOrderDishes(Dishes[] orderDishes) {
        this.orderDishes = orderDishes;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
