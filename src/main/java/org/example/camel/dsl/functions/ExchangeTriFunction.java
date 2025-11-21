package org.example.camel.dsl.functions;

import org.apache.camel.Exchange;
import org.apache.commons.lang3.function.TriFunction;
import org.example.RouteBuilder;
import org.example.camel.dsl.ExchangeData;

import java.util.function.Function;

public class ExchangeTriFunction<TV1, TV2, TV3> implements Function<Exchange, Object> {

    public ExchangeTriFunction(ExchangeData<TV1> exchangeData1,
                               ExchangeData<TV2> exchangeData2,
                               ExchangeData<TV3> exchangeData3,
                               TriFunction<TV1, TV2, TV3, Object> triFunction) {

        this.triFunction = triFunction;
        this.exchangeData1 = exchangeData1;
        this.exchangeData2 = exchangeData2;
        this.exchangeData3 = exchangeData3;
    }

    ExchangeData<TV1> exchangeData1;
    ExchangeData<TV2> exchangeData2;
    ExchangeData<TV3> exchangeData3;
    TriFunction<TV1, TV2, TV3, Object> triFunction;

    @Override
    public Object apply(Exchange exchange) {
        return this.triFunction.apply(
                exchangeData1.getFromExchange(exchange),
                exchangeData2.getFromExchange(exchange),
                exchangeData3.getFromExchange(exchange)
        );
    }
}