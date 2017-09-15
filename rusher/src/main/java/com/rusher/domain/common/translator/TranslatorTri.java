package com.rusher.domain.common.translator;

public interface TranslatorTri<S1, S2, S3, D> {

  D translate(S1 source1, S2 source2, S3 source3);

}

