package com.springboot.chapter15.service;

import com.springboot.chapter15.pojo.PurchaseRecordPo;

import java.util.List;

public interface PurchaseService {
    public boolean purchase(Long userId,Long productId,int quantity);
    boolean purchaseRedis(Long userId,Long productId,int quantity);
    boolean dealRedisPurchase(List<PurchaseRecordPo> prpList);
}
