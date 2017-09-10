package com.rusher.bitfinex.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rusher.bitfinex.dto.BitfinexBalance;
import com.rusher.bitfinex.dto.BitfinexTicker;
import com.rusher.bitfinex.dto.BitfinexTradeRequest;
import com.rusher.bitfinex.dto.BitfinexTradeResponse;
import com.rusher.utils.HttpUtilManager;
import com.rusher.utils.JsonMessageMarshaller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by liam on 10/09/2017.
 */
@Service
public class BitfinexServiceImpl implements BitfinexService {
    private HttpUtilManager httpUtil = HttpUtilManager.getInstance();

    private JsonMessageMarshaller marshaller = new JsonMessageMarshaller();

    /**
     * WALLET_TYPE	string	Wallet name (exchange, margin, funding)
     * CURRENCY	string	Currency (fUSD, etc)
     * BALANCE	float	Wallet balance
     * UNSETTLED_INTEREST	float	Unsettled interest
     * BALANCE_AVAILABLE	float / null	Amount not tied up in active orders, positions or funding (null if the value is not fresh enough).
     */
    @Override
    public BitfinexBalance getBalance() {
        final String url = APIURL + "/v2/auth/r/wallets";
        /**
         req.Header.Add("Content-Type", "application/json")
         req.Header.Add("Accept", "application/json")
         req.Header.Add("X-BFX-APIKEY", c.APIKey)
         req.Header.Add("X-BFX-PAYLOAD", encoded)
         req.Header.Add("X-BFX-SIGNATURE", c.signPayload(encoded))

         func (c *Client) signPayload(payload string) string {
         sig := hmac.New(sha512.New384, []byte(c.APISecret))
         sig.Write([]byte(payload))
         return hex.EncodeToString(sig.Sum(nil))
         }
         */


        Map<String, String> header = Maps.newHashMap();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("X-BFX-APIKEY", APIKey);
        header.put("X-BFX-PAYLOAD", encoded);
        header.put("X-BFX-SIGNATURE", signPayload(encoded));

        return null;
    }

    private String signPayload(String encoded) {
        return "";
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
    public BitfinexTradeResponse Trade(BitfinexTradeRequest request) {
        return null;
    }

    public void setMarshaller(JsonMessageMarshaller marshaller) {
        this.marshaller = marshaller;
    }
}
