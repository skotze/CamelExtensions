package org.example.camel.dsl.exchangedata;

public abstract class KeyValueExchangeData<V> extends ExchangeData<V> {
    protected Class<V> type;
    protected String key;

    public KeyValueExchangeData(Class<V> type, String key) {
        super(type);
        this.key = key;
    }

    public String key() {
        return this.key;
    }
}

