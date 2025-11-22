package org.example.camel.dsl.exchangedata;

public abstract class KeyValueExchangeDataDefinition<V> extends ExchangeDataDefinition<V> {
    protected Class<V> type;
    protected String name;

    public KeyValueExchangeDataDefinition(Class<V> type, String name) {
        super(type);
        this.name = name;
    }

    public String name() {
        return this.name;
    }
}

