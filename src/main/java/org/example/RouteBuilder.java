package org.example;

import org.apache.camel.ExchangeProperty;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.example.camel.dsl.exchangedata.Body;
import org.example.camel.dsl.exchangedata.ExchangeData;
import org.example.camel.dsl.exchangedata.Variable;
import org.springframework.stereotype.Component;

import static org.example.camel.dsl.CamelDslExtensions.inline;

@Component
public class RouteBuilder extends EndpointRouteBuilder {

    static final Variable<String> VAR_1 = ExchangeData.variable(String.class, "VAR_1");
    static final Body<String> BODY_IN = ExchangeData.body(String.class);

    @Override
    public void configure() {
        from(direct(""))
                .setBody().body(String.class, String::toString)
                .setHeader(VAR_1.key(), constant("bbbbb"))
                .setBody(VAR_1::getFromExchange)
                // Some route start
                .setBody(inline(BODY_IN, VAR_1, this::testMethod))
                .setHeader(file().fileName(), constant("foo.txt"))
        // Some route end
        ;
    }

    public String testMethod(String a, String b) {
        return a + b;
    }
}