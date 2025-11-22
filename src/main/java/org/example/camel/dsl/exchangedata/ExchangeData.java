package org.example.camel.dsl.exchangedata;

import org.apache.camel.Exchange;

public abstract class ExchangeData<V> {

    protected Class<V> type;

    public ExchangeData(Class<V> type) {
        this.type = type;
    }

    public abstract V getFromExchange(Exchange exchange);


    public static <V> ExchangeData<V> body(Class<V> type) {
        return new Body<>(type);
    }

    public static <V> Variable<V> variable(Class<V> type, String key) {
        return new Variable<>(type, key);
    }

    static <V> ExchangeProperty<V> exchangeProperty(Class<V> type, String key) {
        return new ExchangeProperty<>(type, key);
    }

    static <V> Header<V> header(Class<V> type, String key) {
        return new Header<>(type, key);
    }
}