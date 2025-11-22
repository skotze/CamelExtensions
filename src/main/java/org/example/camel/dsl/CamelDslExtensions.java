package org.example.camel.dsl;

import org.apache.commons.lang3.function.TriFunction;
import org.example.camel.dsl.exchangedata.ExchangeData;
import org.example.camel.dsl.functions.ExchangeBiFunction;
import org.example.camel.dsl.functions.ExchangeTriFunction;

import java.util.function.BiFunction;

public class CamelDslExtensions {

    public static <TV1, TV2> ExchangeBiFunction<TV1, TV2> inline(
            ExchangeData<TV1> accessor1,
            ExchangeData<TV2> accessor2,
            BiFunction<TV1, TV2, Object> biFunction
    ) {
        return new ExchangeBiFunction<>(accessor1, accessor2, biFunction);
    }

    public static <TV1, TV2, TV3> ExchangeTriFunction<TV1, TV2, TV3> inline(
            ExchangeData<TV1> accessor1,
            ExchangeData<TV2> accessor2,
            ExchangeData<TV3> accessor3,
            TriFunction<TV1, TV2, TV3, Object> triFunction
    ) {
        return new ExchangeTriFunction<>(accessor1, accessor2, accessor3, triFunction);
    }

}
