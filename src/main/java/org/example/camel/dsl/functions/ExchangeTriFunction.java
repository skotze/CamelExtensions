package org.example.camel.dsl.functions;

import org.apache.camel.Exchange;
import org.apache.commons.lang3.function.TriFunction;
import org.example.camel.dsl.exchangedata.ExchangeDataDefinition;

import java.util.function.Function;

public class ExchangeTriFunction<TV1, TV2, TV3> implements Function<Exchange, Object> {

    public ExchangeTriFunction(TriFunction<TV1, TV2, TV3, Object> triFunction,
                               ExchangeDataDefinition<TV1> exchangeData1,
                               ExchangeDataDefinition<TV2> exchangeData2,
                               ExchangeDataDefinition<TV3> exchangeData3) {

        this.triFunction = triFunction;
        this.exchangeData1 = exchangeData1;
        this.exchangeData2 = exchangeData2;
        this.exchangeData3 = exchangeData3;
    }

    ExchangeDataDefinition<TV1> exchangeData1;
    ExchangeDataDefinition<TV2> exchangeData2;
    ExchangeDataDefinition<TV3> exchangeData3;
    TriFunction<TV1, TV2, TV3, Object> triFunction;

    @Override
    public Object apply(Exchange exchange) {
        return this.triFunction.apply(
                this.exchangeData1.getValueFromExchange(exchange),
                this.exchangeData2.getValueFromExchange(exchange),
                this.exchangeData3.getValueFromExchange(exchange)
        );
    }
}