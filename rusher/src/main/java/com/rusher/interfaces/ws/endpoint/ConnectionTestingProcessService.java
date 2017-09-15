package com.rusher.interfaces.ws.endpoint;

import com.rusher.Authorization;
import com.rusher.Platform;
import com.rusher.interfaces.dto.Status;
import com.rusher.interfaces.dto.TestConnectionRequest;
import com.rusher.interfaces.dto.TestConnectionResponse;
import com.rusher.interfaces.ws.support.ErrorResponseSupport;
import com.rusher.kraken.service.KrakenServiceImpl;
import com.rusher.okcoin.dto.OKCoinAsset;
import com.rusher.okcoin.service.OKCoinService;
import com.rusher.utils.JsonMessageMarshaller;
import com.rusher.ws.WebServiceRequestMessage;
import com.rusher.ws.WebServiceRequestProcessService;
import com.rusher.yunbi.dto.Account;
import com.rusher.yunbi.service.YunBiService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Liam
 * Date: 2017/8/23
 */
@Service("testConnectionProcessService")
public class ConnectionTestingProcessService implements WebServiceRequestProcessService<String, Object> {
    private final Log logger = LogFactory.getLog("ERR_LOG");
    @Autowired
    private KrakenServiceImpl krakenService;

    @Autowired
    private OKCoinService okCoinService;

    @Autowired
    private YunBiService yunBiService;

    @Autowired
    private JsonMessageMarshaller marshaller;

    @Override
    public Object processPost(String request, WebServiceRequestMessage message) {
        try {
            TestConnectionRequest testConnectionRequest = (TestConnectionRequest) marshaller.doUnmarshal(request, TestConnectionRequest.class);
            if (testConnectionRequest.getPlatform().compareTo(Platform.YUNBI) == 0) {
                Account account = yunBiService.getAccount(new Authorization(testConnectionRequest.getAccessKey(), testConnectionRequest.getSecretKey()));
                if (account == null || account.getName() == null) {
                    return ErrorResponseSupport.create("System", "Can not get account info from yunbi, please check your accesskey and secretkey");
                }
                return new TestConnectionResponse(Status.Success, "connection ok, using your yunbi account: " + account.getName());
            } else if (testConnectionRequest.getPlatform().compareTo(Platform.HUOBI) == 0) {
//                Account account = yunBiService.getAccount(new Authorization(testConnectionRequest.getAccessKey(), testConnectionRequest.getSecretKey()));
//                if (account == null || account.getName() == null) {
//                    return ErrorResponseSupport.create("System", "Can not get account info from yunbi, please check your accesskey and secretkey");
//                }
//                return new TestConnectionResponse(Status.Success, "connection ok, using your yunbi account: " + account.getName());
                return ErrorResponseSupport.create("System", "Unsupport platform!");

            } else if (testConnectionRequest.getPlatform().compareTo(Platform.KRAKEN) == 0) {
//                Account account = krakenService.getAccount(new Authorization(testConnectionRequest.getAccessKey(), testConnectionRequest.getSecretKey()));
//                if (account == null || account.getName() == null) {
//                    return ErrorResponseSupport.create("System", "Can not get account info from yunbi, please check your accesskey and secretkey");
//                }
//                return new TestConnectionResponse(Status.Success, "connection ok, using your yunbi account: " + account.getName());
                return ErrorResponseSupport.create("System", "Unsupport platform!");

            } else if (testConnectionRequest.getPlatform().compareTo(Platform.OKCOIN) == 0) {
                OKCoinAsset okCoinAsset = okCoinService.getAsset(new Authorization(testConnectionRequest.getAccessKey(), testConnectionRequest.getSecretKey()));
                if (okCoinAsset == null || okCoinAsset.getInfo().getFunds() == null) {
                    return ErrorResponseSupport.create("System", "Can not get account info from okcoin, please check your accesskey and secretkey");
                }
                return new TestConnectionResponse(Status.Success, "connection ok, using your okcoin account.");
            } else if (testConnectionRequest.getPlatform().compareTo(Platform.BITFINEX) == 0) {
//                Account account = yunBiService.getAccount(new Authorization(testConnectionRequest.getAccessKey(), testConnectionRequest.getSecretKey()));
//                if (account == null || account.getName() == null) {
//                    return ErrorResponseSupport.create("System", "Can not get account info from yunbi, please check your accesskey and secretkey");
//                }
//                return new TestConnectionResponse(Status.Success, "connection ok, using your yunbi account: " + account.getName());
                return ErrorResponseSupport.create("System", "Unsupport platform!");

            }
            return ErrorResponseSupport.create("System", "Unsupport platform!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            return ErrorResponseSupport.create("System", e.getMessage());
        }
    }

    @Override
    public Object processGet() {
      return null;
    }
}
