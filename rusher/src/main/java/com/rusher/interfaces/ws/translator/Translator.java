package com.rusher.interfaces.ws.translator;

public interface Translator<S, D> {

    D translate(S source);

}
