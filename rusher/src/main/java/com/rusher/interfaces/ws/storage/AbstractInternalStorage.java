package com.rusher.interfaces.ws.storage;

/**
 * Created by liam on 13/09/2017.
 *
 * 此抽象类提供最新内部价格数据，集成了各种交易算法的内部缓存存储接口，和获取交易算法结果的接口（包含是否达到阈值以及具体算出来的阈值）。
 *
 * 每个具体的Storage实现应该包含具体的
 *
 */
public abstract class AbstractInternalStorage {

    private BaseAlgorithm algorithm;

    private Storage storage;

    protected abstract void setAlgorithm(BaseAlgorithm baseAlgorithm);



}
