package org.example.camel.dsl.exchangedata;

import org.apache.camel.Exchange;

public class ExchangePropertyDefinition<V> extends KeyValueExchangeDataDefinition<V> {

    public ExchangePropertyDefinition(Class<V> type, String key) {
        super(type, key);
    }

    @Override
    public V getValueFromExchange(Exchange exchange) {
        return exchange.getProperty(name(), this.type);
    }
}