package com.rusher.application.translator;

public interface Translator<S, D> {

    D translate(S source);

}
