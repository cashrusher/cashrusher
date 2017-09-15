package com.rusher.domain.common.translator;

public interface Translator<S, D> {

    D translate(S source);

}
