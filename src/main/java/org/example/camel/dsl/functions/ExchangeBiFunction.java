package org.example.camel.dsl.functions;

import org.apache.camel.Exchange;
import org.example.camel.dsl.exchangedata.ExchangeDataDefinition;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ExchangeBiFunction<TV1, TV2> implements Function<Exchange, Object> {

    public ExchangeBiFunction(BiFunction<TV1, TV2, Object> function,
                              ExchangeDataDefinition<TV1> exchangeData1,
                              ExchangeDataDefinition<TV2> exchangeData2) {

        this.biFunction = function;
        this.exchangeData1 = exchangeData1;
        this.exchangeData2 = exchangeData2;
    }

    ExchangeDataDefinition<TV1> exchangeData1;
    ExchangeDataDefinition<TV2> exchangeData2;
    BiFunction<TV1, TV2, Object> biFunction;

    @Override
    public Object apply(Exchange exchange) {
        return this.biFunction.apply(
                this.exchangeData1.getValueFromExchange(exchange),
                this.exchangeData2.getValueFromExchange(exchange)
        );
    }
}