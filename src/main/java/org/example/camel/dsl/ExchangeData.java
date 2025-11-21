package org.example.camel.dsl;

import org.apache.camel.Exchange;

public abstract class ExchangeData<V> {

    protected Class<V> type;

    public ExchangeData(Class<V> type) {
        this.type = type;
    }

    public abstract V getFromExchange(Exchange exchange);


    static <V> ExchangeData<V> body(Class<V> type) {
        return new Body<>(type);
    }

    static <V> ExchangeData<V> variable(Class<V> type, String key) {
        return new Variable<>(type, key);
    }

    static <V> ExchangeData<V> exchangeProperty(Class<V> type, String key) {
        return new ExchangeProperty<>(type, key);
    }

    static <V> ExchangeData<V> header(Class<V> type, String key) {
        return new Header<>(type, key);
    }


    static class Body<V> extends ExchangeData<V> {

        public Body(Class<V> type) {
            super(type);
        }

        @Override
        public V getFromExchange(Exchange exchange) {
            return exchange.getMessage().getBody(type);
        }
    }

    abstract static class KeyValueExchangeData<V> extends ExchangeData<V> {
        protected Class<V> type;
        protected String key;

        public KeyValueExchangeData(Class<V> type, String key) {
            super(type);
            this.key = key;
        }
    }

    static class Variable<V> extends KeyValueExchangeData<V> {

        public Variable(Class<V> type, String key) {
            super(type, key);
        }

        @Override
        public V getFromExchange(Exchange exchange) {
            return exchange.getVariable(this.key, this.type);
        }
    }

    static class ExchangeProperty<V> extends KeyValueExchangeData<V> {

        public ExchangeProperty(Class<V> type, String key) {
            super(type, key);
        }

        @Override
        public V getFromExchange(Exchange exchange) {
            return exchange.getProperty(this.key, this.type);
        }
    }

    static class Header<V> extends KeyValueExchangeData<V> {

        public Header(Class<V> type, String key) {
            super(type, key);
        }

        @Override
        public V getFromExchange(Exchange exchange) {
            return exchange.getMessage().getHeader(this.key, this.type);
        }
    }
}