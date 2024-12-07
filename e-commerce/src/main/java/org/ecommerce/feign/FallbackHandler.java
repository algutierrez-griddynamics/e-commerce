package org.ecommerce.feign;

import org.ecommerce.exceptions.ServiceUnavailableException;

public interface FallbackHandler <T> {

    default boolean handleBooleanMethodsFallback(Long id, Throwable cause) {
        throw new ServiceUnavailableException(cause);
    }

    default T handleObjectMethodsFallback(Long id, Throwable cause) {
        throw new ServiceUnavailableException(cause);
    }

    default String handleFallbackTest(Throwable cause) {
        throw new ServiceUnavailableException(cause);
    }

}
