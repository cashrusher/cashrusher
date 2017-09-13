package com.rusher.interfaces.ws.storage;

import com.rusher.interfaces.dto.Ticker;

import java.util.List;

/**
 * Created by liam on 13/09/2017.
 * 提供了数据的存储服务接口和结构
 */
public interface Storage<B> {
    void save(List<Ticker> allTickers);
    B get();

}
