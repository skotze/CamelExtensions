package org.example.camel.dsl.exchangedata;

import org.apache.camel.Exchange;

public abstract class ExchangeDataDefinition<V> {

    protected Class<V> type;

    public ExchangeDataDefinition(Class<V> type) {
        this.type = type;
    }

    public abstract V getFromExchange(Exchange exchange);

    public static <V> BodyDefinition<V> bodyDefinition(Class<V> type) {
        return new BodyDefinition<>(type);
    }

    public static <V> VariableDefinition<V> variableDefinition(Class<V> type, String name) {
        return new VariableDefinition<>(type, name);
    }

    static <V> ExchangePropertyDefinition<V> exchangePropertyAccessor(Class<V> type, String name) {
        return new ExchangePropertyDefinition<>(type, name);
    }

    static <V> HeaderDefinition<V> headerAccessor(Class<V> type, String name) {
        return new HeaderDefinition<>(type, name);
    }
}