package org.example.camel.dsl;

import org.apache.commons.lang3.function.TriFunction;
import org.example.camel.dsl.exchangedata.ExchangeDataDefinition;
import org.example.camel.dsl.functions.ExchangeBiFunction;
import org.example.camel.dsl.functions.ExchangeTriFunction;

import java.util.function.BiFunction;

public class CamelDslExtensions {

    public static <TV1, TV2> ExchangeBiFunction<TV1, TV2> inline(
            BiFunction<TV1, TV2, Object> biFunction,
            ExchangeDataDefinition<TV1> accessor1,
            ExchangeDataDefinition<TV2> accessor2
    ) {
        return new ExchangeBiFunction<>(biFunction, accessor1, accessor2);
    }

    public static <TV1, TV2, TV3> ExchangeTriFunction<TV1, TV2, TV3> inline(
            TriFunction<TV1, TV2, TV3, Object> triFunction,
            ExchangeDataDefinition<TV1> accessor1,
            ExchangeDataDefinition<TV2> accessor2,
            ExchangeDataDefinition<TV3> accessor3

    ) {
        return new ExchangeTriFunction<>(triFunction, accessor1, accessor2, accessor3);
    }

}
