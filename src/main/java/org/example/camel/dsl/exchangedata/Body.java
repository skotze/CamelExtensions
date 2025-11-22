package org.example.camel.dsl.exchangedata;

import org.apache.camel.Exchange;

public class Body<V> extends ExchangeData<V> {

    public Body(Class<V> type) {
        super(type);
    }

    @Override
    public V getFromExchange(Exchange exchange) {
        return exchange.getMessage().getBody(type);
    }
}
