package com.apicatalog.cid;

import java.net.URI;

import com.apicatalog.cid.document.IdentifierDocument;

public interface IdentifierDocumentResolver {

    boolean isAccepted(URI id);
    
    IdentifierDocument resolve(URI id);
    
}
