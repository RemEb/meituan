package tech.bmatch.meituan.service;

import tech.bmatch.meituan.model.Merchant;
import tech.bmatch.meituan.model.MerchantSearchParam;

import java.util.List;

/*
* 商家服务
*@author Lcy
* */

public interface MerchantService {
    public  void init();
    /*添加商户*/
    public void add(Merchant merchant);
    /*根据经纬度搜索商家*/
    public List<Merchant> search(MerchantSearchParam param);

}
