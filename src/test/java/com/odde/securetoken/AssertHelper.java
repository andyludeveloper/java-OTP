package com.odde.securetoken;

import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.argThat;

public class AssertHelper {
    public static <T> T should(Consumer<T> assertion) {
        return argThat(argument -> {
            assertion.accept(argument);
            return true;
        });
    }
}
