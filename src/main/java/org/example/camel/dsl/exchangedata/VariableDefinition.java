package org.example.camel.dsl.exchangedata;

import org.apache.camel.Exchange;

public class VariableDefinition<V> extends KeyValueExchangeDataDefinition<V> {

    public VariableDefinition(Class<V> type, String key) {
        super(type, key);
    }

    @Override
    public V getFromExchange(Exchange exchange) {
        return exchange.getVariable(name(), this.type);
    }
}