package tech.bmatch.meituan.model;
/**
 * 菜品类，有名称，价格，对应商品ID
 * @author lcy
 * @data 2018年10月11日 18:14:09
 * */


public class Dishes {
    private String name;
    private double price;
    private String MerchantId;

    public String getMerchantId() {
        return MerchantId;
    }

    public void setMerchantId(String merchantId) {
        MerchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
