package tech.bmatch.meituan.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import tech.bmatch.meituan.model.Dishes;
import tech.bmatch.meituan.model.Merchant;
import tech.bmatch.meituan.model.MerchantSearchParam;
import tech.bmatch.meituan.service.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.bmatch.meituan.util.DistanceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 实现商家初始化、保存、读取的功能
 *
 * @author Lcy
 * @data 2018年10月11日
 */

public abstract class MerchantFileStoreServiceImpl implements MerchantService {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static File file = new File("./data.json");
    private static final Logger logger = LoggerFactory.getLogger(MerchantFileStoreServiceImpl.class);

    @Override
    public void init() {
        if (!file.exists()) {
            return;
        }
        try {
            List<Merchant> merchantList = objectMapper.readValue(file,
                    new TypeReference<List<Merchant>>() {
                    });
            for (Merchant merchant : merchantList) {
                add(merchant);
            }
        } catch (IOException e) {
            logger.error("", e);

        }
    }

    public void store(List<Merchant> merchants) {
        if (merchants == null) {
            return;
        }
        try {
            objectMapper.writeValue(file, merchants);
            logger.info("添加数据成功");
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    public void read(MerchantSearchParam param) {
        try {
            List<Merchant> merchantList = objectMapper.readValue(file,
                    new TypeReference<List<Merchant>>() {
                    });

            for (Merchant merchant : merchantList) {
                Double distance = DistanceUtil.getDistance(param.getLon(), param.getLat(),
                        merchant.getLon(), merchant.getLat());
                merchant.setDistance(distance);
            }

            for (Merchant merchant : merchantList) {
                System.out.println("商家 ：" + merchant.getName());
                Double distance = merchant.getDistance();
                System.out.println("距离 ：" + distance.intValue() + "m");
                System.out.println("菜单 :");
                Dishes[] dishes = merchant.getDishes();
                if (dishes == null) {
                    System.out.println("  还没有菜品。。");
                } else {
                    for (Dishes dish : dishes) {
                        System.out.println("  名称：" + dish.getName() + " 价格:" + dish.getPrice()+"元");
                    }
                }
                System.out.println("------------------");
            }
        } catch (IOException e) {
            logger.error("", e);
        }
    }

}
