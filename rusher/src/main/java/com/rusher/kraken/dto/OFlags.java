package com.rusher.kraken.dto;

/**
 * Created by liam on 09/09/2017.
 */
public enum OFlags {
    /**
     * comma delimited list of order flags (optional):
     * viqc = volume in quote currency (not available for leveraged orders)
     * fcib = prefer fee in base currency
     * fciq = prefer fee in quote currency
     * nompp = no market price protection
     * post = post only order (available when ordertype = limit)
     */
    vigc, fcib, fciq, nompp, post
}
