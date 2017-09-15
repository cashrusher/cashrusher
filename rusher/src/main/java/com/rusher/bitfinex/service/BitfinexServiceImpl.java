package com.rusher.bitfinex.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rusher.Authorization;
import com.rusher.bitfinex.dto.BitfinexBalance;
import com.rusher.bitfinex.dto.BitfinexTicker;
import com.rusher.bitfinex.dto.BitfinexTradeRequest;
import com.rusher.bitfinex.dto.BitfinexTradeResponse;
import com.rusher.bitfinex.exception.BalanceException;
import com.rusher.kraken.utils.Signature;
import com.rusher.utils.HttpUtilManager;
import com.rusher.utils.JsonMessageMarshaller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by liam on 10/09/2017.
 */
@Service
public class BitfinexServiceImpl implements BitfinexService {
    private HttpUtilManager httpUtil = HttpUtilManager.getInstance();

    private JsonMessageMarshaller marshaller = new JsonMessageMarshaller();

    /**
     * 参考Bitfinex官网的BitfinexBalance结构应为如下结构：
     * WALLET_TYPE	string	Wallet name (exchange, margin, funding)
     * CURRENCY	string	Currency (fUSD, etc)
     * BALANCE	float	Wallet balance
     * UNSETTLED_INTEREST	float	Unsettled interest
     * BALANCE_AVAILABLE	float / null	Amount not tied up in active orders, positions or funding (null if the value is not fresh enough).
     */
    @Override
    public BitfinexBalance getBalance(Authorization authorization) throws Exception {
        final String url = APIURL + "/v2/auth/r/wallets";

        String nonce = String.valueOf(System.currentTimeMillis() * 1000000);
        Map<String, String> header = Maps.newHashMap();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("bfx-nonce", nonce);
        header.put("bfx-apikey", authorization.getApiKey());
        // 参考官网js的示例代码组成加密所需要的payload：  /api/${apiPath}${nonce}${rawBody}
        String payload = "/api/v2/auth/r/wallets" + nonce + "";
        header.put("bfx-signature", Signature.getHMacSha384(payload.getBytes(), authorization.getSecretKey().getBytes()));

        String content = httpUtil.requestHttpPost(url, Maps.newHashMap(), header);
        System.out.println(content);
        List<List<Object>> objectList = (List) marshaller.doUnmarshal(content, List.class);

        if (objectList.size() == 0) {
            String exceptionMsg = "";
            for (Object o : objectList) {
                exceptionMsg += o + ",";
            }
            throw new BalanceException("Can not get balance params: " + exceptionMsg.substring(0, exceptionMsg.length() > 0 ? exceptionMsg.length() - 1 : 0));
        }
        for (List<Object> objects : objectList) {
            if (Objects.equals((String) objects.get(1), "ETH")) {
                BitfinexBalance balance = new BitfinexBalance();
                balance.setWalletType((String) objects.get(0));
                balance.setCurrency((String) objects.get(1));
                balance.setBalance(getDouble(objects.get(2)));
                balance.setUnsettledInterest(getDouble(objects.get(3)));
                balance.setBalanceAvailable(getDouble(objects.get(4)));
                return balance;
            }
        }
        return null;
    }

    private Double getDouble(Object object) {
        if (object instanceof Double) {
            return (Double) object;
        } else if (object instanceof Integer) {
            return 1.0 * (Integer) object;
        } else if (object instanceof String) {
            return Double.valueOf((String) object);
        }
        return -1.0;
    }

    /**
     * SYMBOL,
     * BID,
     * BID_SIZE,
     * ASK,
     * ASK_SIZE,
     * DAILY_CHANGE,
     * DAILY_CHANGE_PERC,
     * LAST_PRICE,
     * VOLUME,
     * HIGH,
     * LOW
     * <p>
     * FRR	float	Flash Return Rate - average of all fixed rate funding over the last hour
     * BID	float	Price of last highest bid
     * BID_PERIOD	int	Bid period covered in days
     * BID_SIZE	float	Size of the last highest bid
     * ASK	float	Price of last lowest ask
     * ASK_PERIOD	int	Ask period covered in days
     * ASK_SIZE	float	Size of the last lowest ask
     * DAILY_CHANGE	float	Amount that the last price has changed since yesterday
     * DAILY_CHANGE_PERC	float	Amount that the price has changed expressed in percentage terms
     * LAST_PRICE	float	Price of the last trade
     * VOLUME	float	Daily volume
     * HIGH	float	Daily high
     * LOW	float	Daily low
     */

    @Override
    public List<BitfinexTicker> getTicker(String... pairs) {
        List<BitfinexTicker> tickers = Lists.newArrayList();
        String url = APIURL + "/v2/tickers?";
        String param = "symbols=";
        for (String pair : pairs) {
            param = param + "t" + pair + ",";
        }
        param = param.substring(0, param.length() - 1);
        try {
            String content = httpUtil.requestHttpGet(url, param);
            System.out.println(content);
            List<List<Object>> tickerList = (List<List<Object>>) marshaller.doUnmarshal(content, List.class);
            for (List<Object> objectList : tickerList) {
                BitfinexTicker ticker = new BitfinexTicker();
                ticker.setSymbol((String) objectList.get(0));
                ticker.setPriceOfLastHighestBid((Double) objectList.get(1));
                ticker.setSizeOfLastHighestBid((Double) objectList.get(2));
                ticker.setPriceOfLastLowestAsk((Double) objectList.get(3));
                ticker.setSizeOfLastLowestAsk((Double) objectList.get(4));
                ticker.setDailyChange((Double) objectList.get(5));
                ticker.setDailyChangePercentage((Double) objectList.get(6));
                ticker.setLastPrice((Double) objectList.get(7));
                ticker.setDailyVolume((Double) objectList.get(8));
                ticker.setDailyHigh((Double) objectList.get(9));
                ticker.setDailyLow((Double) objectList.get(10));
                tickers.add(ticker);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickers;
    }

    @Override
    public BitfinexTradeResponse Trade(Authorization authorization,BitfinexTradeRequest request) {

        return null;
    }

    public void setMarshaller(JsonMessageMarshaller marshaller) {
        this.marshaller = marshaller;
    }

}
