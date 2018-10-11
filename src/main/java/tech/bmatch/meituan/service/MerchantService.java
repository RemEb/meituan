package tech.bmatch.meituan.service;

import tech.bmatch.meituan.model.Dishes;
import tech.bmatch.meituan.model.Merchant;
import tech.bmatch.meituan.model.MerchantSearchParam;

import java.util.List;

/**
 * 商家服务接口
 * @author Lcy
 * @data 2018年10月11日 16:30:50
 * */

public interface MerchantService {
    /*初始化商家*/
    void init();
    /*添加商户*/
    void add(Merchant merchant);
    /*根据经纬度搜索商家*/
    List<Merchant> search(MerchantSearchParam param);
    /*读取商家数据*/
    void read(MerchantSearchParam param);
    /*添加菜品*/
    void addDishes(Dishes dish);

}
