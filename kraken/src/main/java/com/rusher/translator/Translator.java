package com.rusher.translator;

public interface Translator<S, D> {

    D translate(S source);

}
