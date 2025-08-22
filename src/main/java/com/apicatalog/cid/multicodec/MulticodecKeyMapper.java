package com.apicatalog.cid.multicodec;

import com.apicatalog.cid.multibase.Multibase;
import com.apicatalog.cid.multibase.MultibaseLiteral;
import com.apicatalog.linkedtree.adapter.NodeAdapterError;
import com.apicatalog.linkedtree.orm.mapper.ObjectMapper;
import com.apicatalog.multicodec.Multicodec.Tag;
import com.apicatalog.multicodec.MulticodecDecoder;
import com.apicatalog.uvarint.UVarInt;

public class MulticodecKeyMapper implements ObjectMapper<MultibaseLiteral, MulticodecEncoded> {

    public static final MulticodecDecoder CODECS = MulticodecDecoder.getInstance(Tag.Key);

    protected final MulticodecDecoder decoder;

    public MulticodecKeyMapper() {
        this(CODECS);
    }

    protected MulticodecKeyMapper(final MulticodecDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public MulticodecEncoded object(MultibaseLiteral literal) throws NodeAdapterError {

        // TODO check bases

        return getKey(literal.base(), literal.byteArrayValue());
    }

    protected final MulticodecEncoded getKey(final Multibase base, final byte[] encodedKey) throws NodeAdapterError {

        if (encodedKey == null || encodedKey.length == 0) {
            return null;
        }

        return decoder.getCodec(encodedKey)
                .map(codec -> new GenericMulticodecKey(codec, base, codec.decode(encodedKey)))
                .orElseThrow(() -> new NodeAdapterError("Unsupported multicodec code=" + UVarInt.decode(encodedKey) + "."));
    }

    @Override
    public MultibaseLiteral literal(MulticodecEncoded value) {

        if (value == null || value.rawBytes() == null || value.rawBytes().length == 0) {
            return null;
        }

        byte[] codec = value.codec().encode(value.rawBytes());

        return MultibaseLiteral.of(Multibase.BASE_58_BTC, codec);
    }
}
