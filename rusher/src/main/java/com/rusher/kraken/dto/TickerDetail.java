package com.rusher.kraken.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by liam on 09/09/2017.
 * {"error":[],
 * "result":{
 *      "XETHZUSD":{
 *          "a":["308.75000","1","1.000"],
 *          "b":["307.30000","20","20.000"],
 *          "c":["308.12000","0.88080000"],
 *          "v":["14954.22445640","97271.42597282"],
 *          "p":["306.15693","310.71689"],
 *          "t":[4555,19473],
 *          "l":["300.10000","295.22000"],
 *          "h":["312.00000","336.68000"],
 *          "o":"310.49000"
 *      }
 * }
 * }
 */
public class TickerDetail {
    @JsonProperty("a")
    private List<Double> A;
    @JsonProperty("b")
    private List<Double> B;
    @JsonProperty("c")
    private List<Double> C;
    @JsonProperty("v")
    private List<Double> V;
    @JsonProperty("p")
    private List<Double> P;
    @JsonProperty("t")
    private List<Double> T;
    @JsonProperty("l")
    private List<Double> L;
    @JsonProperty("h")
    private List<Double> H;
    @JsonProperty("o")
    private Double O;

    public List<Double> getA() {
        return A;
    }

    public void setA(List<Double> a) {
        A = a;
    }

    public List<Double> getB() {
        return B;
    }

    public void setB(List<Double> b) {
        B = b;
    }

    public List<Double> getC() {
        return C;
    }

    public void setC(List<Double> c) {
        C = c;
    }

    public List<Double> getV() {
        return V;
    }

    public void setV(List<Double> v) {
        V = v;
    }

    public List<Double> getP() {
        return P;
    }

    public void setP(List<Double> p) {
        P = p;
    }

    public List<Double> getT() {
        return T;
    }

    public void setT(List<Double> t) {
        T = t;
    }

    public List<Double> getL() {
        return L;
    }

    public void setL(List<Double> l) {
        L = l;
    }

    public List<Double> getH() {
        return H;
    }

    public void setH(List<Double> h) {
        H = h;
    }

    public  Double getO() {
        return O;
    }

    public void setO(Double o) {
        O = o;
    }
}
