package org.example.camel.dsl.functions;

import org.apache.camel.Exchange;
import org.example.RouteBuilder;
import org.example.camel.dsl.ExchangeData;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ExchangeBiFunction<TV1, TV2> implements Function<Exchange, Object> {

    public ExchangeBiFunction(ExchangeData<TV1> exchangeData1,
                              ExchangeData<TV2> exchangeData2,
                              BiFunction<TV1, TV2, Object> function) {

        this.biFunction = function;
        this.exchangeData1 = exchangeData1;
        this.exchangeData2 = exchangeData2;
    }

    ExchangeData<TV1> exchangeData1;
    ExchangeData<TV2> exchangeData2;
    BiFunction<TV1, TV2, Object> biFunction;

    @Override
    public Object apply(Exchange exchange) {
        return biFunction.apply(
                exchangeData1.getFromExchange(exchange),
                exchangeData2.getFromExchange(exchange)
        );
    }
}