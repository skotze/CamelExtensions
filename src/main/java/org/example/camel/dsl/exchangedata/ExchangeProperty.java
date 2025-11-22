package org.example.camel.dsl.exchangedata;

import org.apache.camel.Exchange;

public class ExchangeProperty<V> extends KeyValueExchangeData<V> {

    public ExchangeProperty(Class<V> type, String key) {
        super(type, key);
    }

    @Override
    public V getFromExchange(Exchange exchange) {
        return exchange.getProperty(this.key, this.type);
    }
}