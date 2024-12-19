package com.airbnb.util;

import java.util.function.Consumer;

public class HelpUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private HelpUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Updates a field using the provided setter function if the field is not null.
     *
     * @param requestField the field value to be checked for null and updated if non-null
     * @param setter a {@code Consumer} function that performs the update operation on the field
     * @param <T> the type of the field being updated
     */
    public static <T> void updateFieldIfNotNull(T requestField, Consumer<T> setter) {
        if (requestField != null) {
            setter.accept(requestField);
        }
    }
}
