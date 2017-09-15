package com.rusher.interfaces.ws.endpoint;

import com.google.common.collect.Lists;
import com.rusher.Authorization;
import com.rusher.Currency;
import com.rusher.interfaces.dto.*;
import com.rusher.interfaces.model.db.SystemSetting;
import com.rusher.interfaces.ws.service.db.APIAuthService;
import com.rusher.interfaces.ws.service.db.SystemSettingService;
import com.rusher.interfaces.ws.support.AlgorithmSupportJiaCha;
import com.rusher.domain.common.translator.TranslatorEx;
import com.rusher.domain.common.translator.TranslatorTri;
import com.rusher.kraken.dto.KrakenTicker;
import com.rusher.kraken.service.KrakenServiceImpl;
import com.rusher.kraken.utils.Constant;
import com.rusher.okcoin.dto.Funds;
import com.rusher.okcoin.dto.OKCoinAsset;
import com.rusher.okcoin.dto.OKCoinTicker;
import com.rusher.okcoin.service.OKCoinService;
import com.rusher.ws.WebServiceRequestMessage;
import com.rusher.ws.WebServiceRequestProcessService;
import com.rusher.yunbi.dto.Account;
import com.rusher.yunbi.dto.AccountAsset;
import com.rusher.yunbi.dto.YunBiTicker;
import com.rusher.yunbi.service.YunBiService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Liam
 * Date: 2017/8/23
 */
@Service("jiachadataProcessService")
public class JiachaDataProcessService implements WebServiceRequestProcessService<JiaChaDataResponse, Object> {
    private final Log logger = LogFactory.getLog("ERR_LOG");
    @Autowired
    private KrakenServiceImpl krakenService;

    @Autowired
    private OKCoinService okCoinService;

    @Autowired
    private YunBiService yunBiService;

    @Autowired
    private SystemSettingService systemSettingService;

    @Autowired
    private APIAuthService APIAuthService;

    @Autowired
    @Qualifier("okCoinTicker2TickerTranslator")
    private TranslatorEx okCoinTicker2TickerTranslator;

    @Autowired
    @Qualifier("yunBiTicker2Ticker")
    private TranslatorEx yunBiTicker2Ticker;

    @Override
    public Object processPost(JiaChaDataResponse response, WebServiceRequestMessage message) {
        OKCoinAsset okCoinAsset = okCoinService.getAsset(new Authorization("14d0881c-68b8-4de7-8ef5-b2140ba2780c", "0440198DB0B9D02BBF0F240AB220208A"));

        return null;
    }

    @Override
    public Object processGet() {
        OKCoinTicker okCoinTicker = okCoinService.getTicker(Currency.ETH);
        YunBiTicker yunBiTicker = yunBiService.getTicker(Currency.ETH);
        KrakenTicker krakenTicker = krakenService.getTicker(Constant.CurrencyAndSymbolMap.get(Currency.ETH));
        SystemSetting systemSetting = systemSettingService.getSystemSetting();
//        APIAuth apiAuth=APIAuthService.getAPIAuth(Platform.KRAKEN);
        AlgorithmSupportJiaCha.getHighestBuy();
        return null;
    }

    private AssetResponse createAssetResponse(OKCoinAsset okCoinAsset, Account yunbiAccount) {
        AssetResponse response = new AssetResponse();
        response.setTotal(createTotal(okCoinAsset.getInfo().getFunds().getAsset().getTotal()));
        response.setDetail(createDetail(okCoinAsset.getInfo().getFunds(), yunbiAccount.getAccountAssets()));
        return response;
    }

    private Detail createDetail(Funds funds, List<AccountAsset> accountAssets) {
        Detail detail = new Detail();
        detail.setOkcoin(deleteZeroAsset(createOKCoin(funds)));
        detail.setYunbi(deleteZeroAsset(createYunBi(accountAssets)));
        return detail;
    }

    private List<PlatformAsset> createYunBi(List<AccountAsset> accountAssets) {
        List<PlatformAsset> platformAssets = Lists.newArrayList();
        for (AccountAsset accountAsset : accountAssets) {
            platformAssets.add(createAsset(accountAsset.getCurrency(), accountAsset.getAvailBalance(), accountAsset.getAvailBalance(), accountAsset.getLockedBalance()));
        }
        return platformAssets;
    }

    private List<PlatformAsset> deleteZeroAsset(List<PlatformAsset> platformAssets) {
        List<PlatformAsset> cleanPlatformAsset = Lists.newArrayList();
        for (PlatformAsset platformAsset : platformAssets) {
            if (platformAsset.getAvailable() > 0d || platformAsset.getLocked() > 0d) {
                cleanPlatformAsset.add(platformAsset);
            }
        }
        return cleanPlatformAsset;
    }

    private List<PlatformAsset> createOKCoin(Funds funds) {
        List<PlatformAsset> platformAssets = Lists.newArrayList();
        platformAssets.add(createAsset("cny", funds.getFree().getCNY(), funds.getFree().getCNY(), funds.getFreezed().getCNY()));
        platformAssets.add(createAsset("eth", funds.getFree().getETH(), funds.getFree().getETH(), funds.getFreezed().getETH()));
        platformAssets.add(createAsset("bcc", funds.getFree().getBCC(), funds.getFree().getBCC(), funds.getFreezed().getBCC()));
        platformAssets.add(createAsset("btc", funds.getFree().getBTC(), funds.getFree().getBTC(), funds.getFreezed().getBTC()));
        platformAssets.add(createAsset("etc", funds.getFree().getETC(), funds.getFree().getETC(), funds.getFreezed().getETC()));
        platformAssets.add(createAsset("ltc", funds.getFree().getLTC(), funds.getFree().getLTC(), funds.getFreezed().getLTC()));
        return platformAssets;
    }

    private PlatformAsset createAsset(String currency, double cny, double avail, double locked) {
        PlatformAsset asset = new PlatformAsset();
        asset.setCurrency(currency);
        asset.setCny(cny);
        asset.setAvailable(avail);
        asset.setLocked(locked);
        return asset;
    }


    private Total createTotal(double total) {
        Total total1 = new Total();
        total1.setCny(total);
        return total1;
    }
}
