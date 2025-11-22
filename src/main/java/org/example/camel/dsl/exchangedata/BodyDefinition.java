package org.example.camel.dsl.exchangedata;

import org.apache.camel.Exchange;

public class BodyDefinition<V> extends ExchangeDataDefinition<V> {

    public BodyDefinition(Class<V> type) {
        super(type);
    }

    @Override
    public V getValueFromExchange(Exchange exchange) {
        return exchange.getMessage().getBody(type);
    }
}
