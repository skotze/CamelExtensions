package org.example.camel.dsl.exchangedata;

import org.apache.camel.Exchange;

public class HeaderDefinition<V> extends KeyValueExchangeDataDefinition<V> {

    public HeaderDefinition(Class<V> type, String key) {
        super(type, key);
    }

    @Override
    public V getFromExchange(Exchange exchange) {
        return exchange.getMessage().getHeader(name(), this.type);
    }
}