package com.rusher.interfaces.ws.service;

import com.google.common.collect.Lists;
import com.rusher.Authorization;
import com.rusher.Platform;
import com.rusher.bitfinex.dto.BitfinexBalance;
import com.rusher.bitfinex.service.BitfinexService;
import com.rusher.interfaces.dto.AssetResponse;
import com.rusher.interfaces.dto.Detail;
import com.rusher.interfaces.dto.PlatformAsset;
import com.rusher.interfaces.dto.Total;
import com.rusher.interfaces.ws.repository.AuthorizationRepository;
import com.rusher.kraken.dto.KrakenBalance;
import com.rusher.kraken.service.KrakenServiceImpl;
import com.rusher.okcoin.dto.Funds;
import com.rusher.okcoin.dto.OKCoinAsset;
import com.rusher.okcoin.service.OKCoinService;
import com.rusher.yunbi.dto.Account;
import com.rusher.yunbi.dto.AccountAsset;
import com.rusher.yunbi.service.YunBiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Liam
 * Date: 2017/9/15
 */
@Service
public class PersonalAssetService {
  @Autowired
  private KrakenServiceImpl krakenService;

  @Autowired
  private OKCoinService okCoinService;

  @Autowired
  private YunBiService yunBiService;

  @Autowired
  private BitfinexService bitfinexService;

  @Autowired
  private AuthorizationRepository authorizationRepository;

  public AssetResponse getPersonalAsset() {
    OKCoinAsset okCoinAsset = okCoinService.getAsset(authorizationRepository.getAuth(Platform.OKCOIN));
    Account yunbiAccount = yunBiService.getAccount(authorizationRepository.getAuth(Platform.YUNBI));
    KrakenBalance krakenBalance = krakenService.getBalance(authorizationRepository.getAuth(Platform.KRAKEN));
    BitfinexBalance bitfinexBalance = bitfinexService.getBalance(authorizationRepository.getAuth(Platform.BITFINEX));

    return createAssetResponse(okCoinAsset, yunbiAccount);

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
