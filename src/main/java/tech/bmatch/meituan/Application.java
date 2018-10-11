package tech.bmatch.meituan;

import tech.bmatch.meituan.model.Dishes;
import tech.bmatch.meituan.model.Merchant;
import tech.bmatch.meituan.model.MerchantSearchParam;
import tech.bmatch.meituan.service.MerchantService;
import tech.bmatch.meituan.service.impl.MerchantServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static MerchantService merchantService = new MerchantServiceImpl();


    public static void main(String[] args) {
        /*init();

        MerchantSearchParam param = new MerchantSearchParam();
        param.setLon(120.560148);
        param.setLat(31.421065);

        List<Merchant> merchantList = merchantService.search(param);
        for (Merchant merchant : merchantList) {
            System.out.println("商家："+merchant.getName());
            Double distance = merchant.getDistance();
            System.out.println("距离："+distance.intValue()+"m");
            System.out.println("------------------");
        }*/
        merchantService.init();


        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("请输入指令");
        System.out.println("添加商家的指令 add 商户ID 商户名称 经度 纬度");
        System.out.println("查询商家的指令 search 商户名称[可选]");
        System.out.println("添加商家菜品的指令 addDish 商户ID 菜品名称 菜品价格");
        System.out.println("查询商家菜品的指令 showDish 商户ID");
        System.out.println("查询所有商家的指令 show all");

        while (true) {
            String command = null;
            try {
                command = bufferedReader.readLine();
                System.out.println(command);

                if (command.startsWith("add ")) {
                    String content = command.replaceAll("add ", "");
                    String[] items = content.split(" ");
                    initMerchant(items);
                }
                if (command.startsWith("search ")) {
                    String content = command.replaceAll("search ", "");
                    search(content);
                }
                if (command.startsWith("show all")) {
                    showAllMerchants();
                }
                if (command.startsWith("addDish ")) {
                    String content = command.replaceAll("addDish ", "");
                    String[] items = content.split(" ");
                    initMerchantDish(items);
                }
                if (command.startsWith("showDish ")) {
                    String content = command.replaceAll("addDish ", "");
                    showMerchantDishes(content);
                }

            } catch (IOException e) {
                logger.error("", e);
            }
        }

    }

    private static void initMerchant(String[] items) {
        Merchant merchant = new Merchant();
        merchant.setId(items[0]);
        merchant.setName(items[1]);
        merchant.setLon(Double.valueOf(items[2]));
        merchant.setLat(Double.valueOf(items[3]));

        merchantService.add(merchant);
    }

    private static void initMerchantDish(String[] items) {
        Dishes dish =new Dishes();
        dish.setMerchantId(items[0]);
        dish.setName(items[1]);
        dish.setPrice(Double.valueOf(items[2]));

        merchantService.addDishes(dish);
    }

    private static void search(String name) {
        MerchantSearchParam param = new MerchantSearchParam();

        param.setLon(120.560148);
        param.setLat(31.421065);
        param.setName(name);

        List<Merchant> merchantList = merchantService.search(param);

        for (Merchant merchant : merchantList) {
            System.out.println("商家：" + merchant.getName());
            Double distance = merchant.getDistance();
            System.out.println("距离：" + distance.intValue() + "m");
            System.out.println("------------------");
        }
    }

    private static void showAllMerchants() {
        MerchantSearchParam param = new MerchantSearchParam();

        param.setLon(120.560148);
        param.setLat(31.421065);

        merchantService.read(param);
    }

    private static void showMerchantDishes(String id){

    }

  /*  public static void init() {
        Merchant merchant = new Merchant();
        merchant.setId("1");
        merchant.setName("肯德基 黄埭中翔");
        merchant.setLon(120.56084);
        merchant.setLat(31.42035);
        merchantService.add(merchant);

        merchant = new Merchant();
        merchant.setId("2");
        merchant.setName("华莱士 黄埭");
        merchant.setLon(120.555878);
        merchant.setLat(31.434591);
        merchantService.add(merchant);

        merchant = new Merchant();
        merchant.setId("3");
        merchant.setName("南京大牌档 苏州");
        merchant.setLon(120.677361);
        merchant.setLat(31.319289);
        merchantService.add(merchant);
    }*/
}
