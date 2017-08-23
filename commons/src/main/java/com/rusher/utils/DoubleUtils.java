package com.rusher.utils;

import java.text.DecimalFormat;

public class DoubleUtils {

    public static Double toFourDecimal(double a) {
        DecimalFormat dcmFmt = new DecimalFormat("#.####");
        return Double.parseDouble(dcmFmt.format(a));
    }
    public static Double toThreeDecimal(double a) {
        DecimalFormat dcmFmt = new DecimalFormat("#.###");
        return Double.parseDouble(dcmFmt.format(a));
    }
}
