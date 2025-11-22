package org.example.camel.dsl.exchangedata;

import org.apache.camel.Exchange;

public class Variable<V> extends KeyValueExchangeData<V> {

    public Variable(Class<V> type, String key) {
        super(type, key);
    }

    @Override
    public V getFromExchange(Exchange exchange) {
        return exchange.getVariable(this.key, this.type);
    }
}