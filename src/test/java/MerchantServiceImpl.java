package tech.bmatch.meituan.service.impl;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.bmatch.meituan.Application;
import tech.bmatch.meituan.model.Merchant;
import tech.bmatch.meituan.model.MerchantSearchParam;
import tech.bmatch.meituan.service.MerchantService;
import tech.bmatch.meituan.util.DistanceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MerchantServiceImpl extends MerchantFileStoreServiceImpl {

    private Map<String, Merchant> merchants;
    private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Test
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
    public void read(MerchantSearchParam param) {
        super.read(param);
    }


}
