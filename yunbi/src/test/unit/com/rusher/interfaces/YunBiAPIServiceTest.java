package com.rusher.interfaces;

import com.alibaba.fastjson.JSONObject;
import com.rusher.domain.common.FiatConverter;
import com.rusher.domain.protocol.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class YunBiAPIServiceTest {

    private AuthorizationKey getAppAccount() {
        AuthorizationKey authorizationKey = new AuthorizationKey();
        authorizationKey.setId(1L);
        authorizationKey.setAccessKey("WNfHT5nDEtcJ9rfEJRxWQBk5bPJF55VM9AvIkgDt"); // todo 替换为access_key
        authorizationKey.setSecretKey("v6OZDWIxj1NDYRS5HPESyp1FJl640j97IUlBXXSt"); // todo 替换为secret_key
        return authorizationKey;
    }

    @Test
    public void testBuyAndCancel() throws Exception {

        Double amount = 0.01;
        Double price = 10.0; // usd
        AbstractMarketApi market = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);
        Long orderId = market.buy(getAppAccount(), amount, price, new SymbolPair(Symbol.btc, Symbol.usd));
        BitOrder order = market.getOrder(getAppAccount(), orderId, null);
        assertNotNull(order);
        assertEquals(OrderStatus.none, order.getStatus());
        assertEquals(amount, order.getOrderAmount());
        assertEquals(new Double("0.0"), order.getProcessedAmount());
        market.cancel(getAppAccount(), orderId, null);
        order = market.getOrder(getAppAccount(), orderId, null);
        assertNotNull(order);
        assertEquals(OrderStatus.cancelled, order.getStatus());
    }

    @Test
    public void testSellAndCancel() throws Exception {

        Double amount = 0.01;
        Double price = 10000.0; // usd
        AbstractMarketApi market = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);
        Long orderId = market.sell(getAppAccount(), amount, price, new SymbolPair(Symbol.btc, Symbol.usd));
        BitOrder order = market.getOrder(getAppAccount(), orderId, null);
        assertNotNull(order);
        assertEquals(OrderStatus.none, order.getStatus());
        assertEquals(amount, order.getOrderAmount());
        assertEquals(new Double(0.0), order.getProcessedAmount());
        market.cancel(getAppAccount(), orderId, null);
        order = market.getOrder(getAppAccount(), orderId, null);
        assertNotNull(order);
        assertEquals(OrderStatus.cancelled, order.getStatus());
    }

    @Test
    public void testGetInfo() throws Exception {
        String accessKey = "WNfHT5nDEtcJ9rfEJRxWQBk5bPJF55VM9AvIkgDt";
        String secretKey = "v6OZDWIxj1NDYRS5HPESyp1FJl640j97IUlBXXSt";

        AbstractMarketApi market = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);
        AuthorizationKey authorizationKey = new AuthorizationKey();
        authorizationKey.setId(1L);
        authorizationKey.setAccessKey(accessKey); //
        authorizationKey.setSecretKey(secretKey); //
        Account account = market.getAccount(authorizationKey);
    }

    @Test
    public void testGetOrder() throws Exception {
        AbstractMarketApi market = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);
        Long orderId = 434669L;
        BitOrder order = market.getOrder(getAppAccount(), orderId, new SymbolPair(Symbol.btc, Symbol.usd));
        assertNotNull(order);
    }

    @Test
    public void testGetRunningOrder() throws Exception {

        AbstractMarketApi market = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);
        List<BitOrder> bitOrders = market.getRunningOrders(getAppAccount());
        assertTrue(bitOrders.size() > 0);
    }


    @Test
    public void testGetKlineDate() throws Exception {
        AbstractMarketApi market = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);
        List<Kline> klines = market.getKlineDate(Symbol.btc);
        for (Kline kline : klines) {
            convertToUsd(market, kline);
        }
        assertTrue(klines.size() > 0);

    }

    @Test
    public void testGetKline5Min() throws Exception {
        AbstractMarketApi market = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);
        List<Kline> klines = market.getKline5Min(Symbol.btc);
        for (Kline kline : klines) {
            convertToUsd(market, kline);
        }
        assertTrue(klines.size() > 0);

    }

    @Test
    public void testGetKline1Min() throws Exception {
        AbstractMarketApi market = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);
        List<Kline> klines = market.getKline1Min(Symbol.btc);
        for (Kline kline : klines) {
            convertToUsd(market, kline);
        }
        assertTrue(klines.size() > 0);
    }

    @Test
    public void testTicker() throws Exception {

        AbstractMarketApi abstractMarketApi = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);
        double ticker = abstractMarketApi.ticker(new SymbolPair(Symbol.btc, Symbol.cny));
        assertTrue(ticker > 0.0);

    }

    @Test
    public void testDepth() throws Exception {

        AbstractMarketApi market = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);
        JSONObject depth = market.get_depth(new SymbolPair(Symbol.btc, Symbol.cny), true);
        assertTrue(depth.containsKey("asks"));
        assertTrue(depth.containsKey("bids"));

    }


    private void convertToUsd(AbstractMarketApi market, Kline kline) {
        if (!market.getMarket().isUsd()) {
            kline.setOpen(FiatConverter.toUsd(kline.getOpen(), kline.getDatetime()));
            kline.setHigh(FiatConverter.toUsd(kline.getHigh(), kline.getDatetime()));
            kline.setLow(FiatConverter.toUsd(kline.getLow(), kline.getDatetime()));
            kline.setClose(FiatConverter.toUsd(kline.getClose(), kline.getDatetime()));
            kline.setVwap(FiatConverter.toUsd(kline.getVwap(), kline.getDatetime()));
        }
    }
}