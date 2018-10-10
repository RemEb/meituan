package tech.bmatch.meituan.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import tech.bmatch.meituan.model.Merchant;
import tech.bmatch.meituan.model.MerchantSearchParam;
import tech.bmatch.meituan.service.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class MerchantFileStoreServiceImpl
    implements MerchantService{

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static File file = new File("./data.json");
    private static final Logger logger = LoggerFactory.getLogger(MerchantFileStoreServiceImpl.class);

    @Override
    public void init() {
        if (!file.exists()){
            return;
        }
        try {
            List<Merchant> merchantList = objectMapper.readValue(file,
                    new TypeReference<List<Merchant>>(){});
            for (Merchant merchant : merchantList) {
                add(merchant);
            }
        }catch (IOException e){
            logger.error("",e);

        }
    }

    public void store(List<Merchant> merchants){
        if (merchants == null) {
            return;
        }
        try {
            objectMapper.writeValue(file,merchants);
            logger.info("添加数据成功");
        }catch (IOException e){
            logger.error("",e);
        }
    }

    public void read(){
        try {
            List<Merchant> merchantList = objectMapper.readValue(file,
                    new TypeReference<List<Merchant>>(){});
            for (Merchant merchant : merchantList) {
                System.out.println("商家 ："+merchant.getName());
                Double distance = merchant.getDistance();
                System.out.println("距离 ："+distance.intValue()+"m");
                System.out.println("------------------");
            }
        } catch (IOException e) {
            logger.error("",e);
        }
    }

    @Override
    public void add(Merchant merchant) {

    }

    @Override
    public List<Merchant> search(MerchantSearchParam param) {
        return null;
    }
}
