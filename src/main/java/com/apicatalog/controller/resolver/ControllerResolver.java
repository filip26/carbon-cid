package com.apicatalog.controller.resolver;

import java.net.URI;

import com.apicatalog.cid.ControllerDocument;

public interface ControllerResolver {

    boolean isAccepted(URI id);
    
    ControllerDocument resolve(URI id);
    
}
