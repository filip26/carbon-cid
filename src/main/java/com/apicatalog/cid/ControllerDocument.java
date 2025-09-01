package com.apicatalog.cid;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

/**
 * https://www.w3.org/TR/cid-1.0/
 */
public interface ControllerDocument {

    URI id();

    Collection<URI> controller();

    Collection<VerificationMethod> verification();

    Collection<URI> alsoKnownAs();

    Set<VerificationMethod> authentication();

    Set<VerificationMethod> assertion();

    Set<VerificationMethod> keyAgreement();

    Set<VerificationMethod> capabilityInvocation();

    Set<VerificationMethod> capabilityDelegation();

    Set<Service> service();
}
