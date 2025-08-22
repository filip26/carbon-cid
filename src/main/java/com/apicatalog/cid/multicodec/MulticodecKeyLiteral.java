package com.apicatalog.cid.multicodec;

import com.apicatalog.linkedtree.LinkedLiteral;
import com.apicatalog.multibase.Multibase;
import com.apicatalog.multicodec.Multicodec;

public record MulticodecKeyLiteral(
        String lexicalValue,
        String datatype,
        Multicodec codec,
        Multibase base,
        byte[] rawBytes) implements LinkedLiteral, MulticodecKey {

}
