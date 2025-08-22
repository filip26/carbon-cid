package com.apicatalog.cid.multicodec;

import java.util.Objects;

import com.apicatalog.cid.multibase.Multibase;
import com.apicatalog.controller.key.RawByteKey;
import com.apicatalog.multicodec.Multicodec;

public interface MulticodecEncoded {

    Multicodec codec();

}
