package tech.bmatch.meituan.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.bmatch.meituan.model.Dishes;
import tech.bmatch.meituan.model.Merchant;
import tech.bmatch.meituan.model.MerchantSearchParam;
import tech.bmatch.meituan.util.DistanceUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 商家服务类实现，处理增加商家、查询商家、增加菜品的功能
 * @author lcy
 * @data 2018年10月11日
 */

public class MerchantServiceImpl extends MerchantFileStoreServiceImpl {

    private Map<String, Merchant> merchants;
    private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);


    public void add(Merchant merchant) {
        // 初始化merchants
        if (merchants == null) {
            merchants = new HashMap<String, Merchant>();
        }
        // 数据校验
        if (merchant == null) {
            return;
        }
        //检验有没有相同merchant
        for (Map.Entry<String, Merchant> merchantEntry : merchants.entrySet()) {
            Merchant merchant1 = merchantEntry.getValue();
            if (merchant1.getName().equals(merchant.getName()) || merchant1.getId().equals(merchant.getId())) {
                System.out.println("已相同店家或者ID重复，请重新输入");
                return;
            }

        }

        // 往map中添加数据
        merchants.put(merchant.getId(), merchant);

        List<Merchant> merchantList = merchants.values().stream().collect(Collectors.toList());

        store(merchantList);
    }

    public List<Merchant> search(MerchantSearchParam param) {
        List<Merchant> merchantList = new ArrayList<Merchant>();

        for (Map.Entry<String, Merchant> merchantEntry : merchants.entrySet()) {
            Merchant merchant = merchantEntry.getValue();
            Double distance = DistanceUtil.getDistance(param.getLon(), param.getLat(),
                    merchant.getLon(), merchant.getLat());
            merchant.setDistance(distance);
            merchantList.add(merchant);
        }

        Stream<Merchant> stream = merchantList.stream();

        if (param.getName() != null && !param.getName().equals("")) {
            stream = stream.filter(m ->
                    m.getName().indexOf(param.getName()) >= 0);
        }

        stream = stream.sorted((m1, m2) ->
                ((Double) m2.getDistance()).intValue() -
                        ((Double) m1.getDistance()).intValue());

        return stream.collect(Collectors.toList());
    }

    @Override
    public void addDishes(Dishes dish) {
        List<Merchant> merchantList = new ArrayList<Merchant>();
        List<Dishes> dishesList = new ArrayList<Dishes>();
        Dishes[] dishes1 = new Dishes[1];

        for (Map.Entry<String, Merchant> merchantEntry : merchants.entrySet()) {
            Merchant merchant = merchantEntry.getValue();
            if (merchant.getId().equals(dish.getMerchantId())) {
                Dishes[] dishes = merchant.getDishes();

                if (dishes == null) {
                    dishes1[0] = dish;
                    merchant.setDishes(dishes1);
                } else {
                    for (int i = 0; i < dishes.length; i++) {
                        if (dishes[i].getName().equals(dish.getName())){
                            System.out.println("已经有重复菜品。");
                            return;
                        }
                        dishesList.add(dishes[i]);
                    }
                    //dishesList=Arrays.asList(dishes);
                    dishesList.add(dish);
                    merchant.setDishes(dishesList.toArray(dishes));
                }
            }
            merchantList.add(merchant);
        }
        store(merchantList);
    }

}
