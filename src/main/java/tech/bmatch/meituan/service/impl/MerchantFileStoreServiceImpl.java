package tech.bmatch.meituan.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.org.objectweb.asm.TypeReference;
import tech.bmatch.meituan.model.Merchant;
import tech.bmatch.meituan.model.MerchantSearchParam;
import tech.bmatch.meituan.service.MerchantService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class MerchantFileStoreServiceImpl
    implements MerchantService{

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static File file = new File("./data.json");

    @Override
    public void init() {
        if (!file.exists()){
            return;
        }
        try {
            List<Merchant> merchantList = objectMapper.readValue(file,
                    new com.fasterxml.jackson.core.type.TypeReference<List<Merchant>>(){});
            for (Merchant merchant : merchantList) {
                add(merchant);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void store(List<Merchant> merchants){
        if (merchants == null) {
            return;
        }
        try {
            objectMapper.writeValue(file,merchants);
        }catch (IOException e){
            e.printStackTrace();
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
