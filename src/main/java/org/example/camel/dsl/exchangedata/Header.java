package org.example.camel.dsl.exchangedata;

import org.apache.camel.Exchange;

public class Header<V> extends KeyValueExchangeData<V> {

    public Header(Class<V> type, String key) {
        super(type, key);
    }

    @Override
    public V getFromExchange(Exchange exchange) {
        return exchange.getMessage().getHeader(this.key, this.type);
    }
}