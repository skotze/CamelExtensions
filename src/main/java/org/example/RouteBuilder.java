package org.example;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.example.camel.dsl.exchangedata.ExchangeData;
import org.example.camel.dsl.exchangedata.Variable;

import static org.example.camel.dsl.CamelDslExtensions.inline;


public class RouteBuilder extends EndpointRouteBuilder {

    static final Variable<String> VAR_1 = ExchangeData.variable(String.class, "VAR_1");

    @Override
    public void configure() {
        from(direct(""))
                .setBody().body(String.class, String::toString)
                .setHeader(VAR_1.key(), constant("bbbbb"))
                .setBody(VAR_1::getFromExchange)
                // Some route start
                .setBody(inline(
                        ExchangeData.body(String.class),
                        VAR_1,
                        this::testMethod))
                .setHeader(file().fileName(), constant("foo.txt"))
        // Some route end
        ;
    }

    public String testMethod(String a, String b) {
        return a + b;
    }
}