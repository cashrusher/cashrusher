package com.rusher.domain.common.translator;

public interface TranslatorEx<S1, S2, D> {

  D translate(S1 source1, S2 source2);

}

