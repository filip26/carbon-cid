package com.apicatalog.cid;

import java.net.URI;
import java.util.Collection;

public interface ServiceEndpoint {

    URI id();

    Collection<String> type();

}
