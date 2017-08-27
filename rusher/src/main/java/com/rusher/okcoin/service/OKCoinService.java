package com.rusher.okcoin.service;

import com.rusher.Authorization;
import com.rusher.Currency;
import com.rusher.okcoin.dto.OKCoinAsset;
import com.rusher.okcoin.dto.OKCoinTicker;
import com.rusher.utils.JsonMessageMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.rusher.okcoin.constant.OKCOIN_URL;
import static com.rusher.okcoin.constant.currencyAndSymbolMap;

/**
 * Created by liam on 26/08/2017.
 */
@Service
public class OKCoinService {
    @Autowired
    private JsonMessageMarshaller marshaller;

    public OKCoinAsset getAsset(Authorization auth) {
        IStockRestApi stockPost = new StockRestApiService(OKCOIN_URL, auth.getApiKey(), auth.getSecretKey());
        try {
            String user = stockPost.userinfo();
            System.out.println(user);
            return (OKCoinAsset) marshaller.doUnmarshal(user, OKCoinAsset.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public OKCoinTicker getTicker(Currency currency) {
        IStockRestApi stockGet = new StockRestApiService(OKCOIN_URL);
        try {
            String ticker = stockGet.ticker(currencyAndSymbolMap.get(currency));
            System.out.println(ticker);
            return (OKCoinTicker) marshaller.doUnmarshal(ticker, OKCoinTicker.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public JsonMessageMarshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(JsonMessageMarshaller marshaller) {
        this.marshaller = marshaller;
    }
}
