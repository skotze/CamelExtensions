package org.example;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.example.camel.dsl.exchangedata.BodyDefinition;
import org.example.camel.dsl.exchangedata.VariableDefinition;
import org.springframework.stereotype.Component;

import static org.example.camel.dsl.CamelDslExtensions.inline;
import static org.example.camel.dsl.exchangedata.ExchangeDataDefinition.bodyDefinition;
import static org.example.camel.dsl.exchangedata.ExchangeDataDefinition.variableDefinition;

@Component
public class RouteBuilder extends EndpointRouteBuilder {

    static final VariableDefinition<String> VAR_1 = variableDefinition(String.class, "VAR_1");
    static final BodyDefinition<String> BODY_IN = bodyDefinition(String.class);

    @Override
    public void configure() {
        from(direct(""))
                .setBody().body(String.class, String::toString)
                .setHeader(VAR_1.name(), constant("bbbbb"))
                .setBody(VAR_1::getValueFromExchange)
                // Some route start
                .setBody(inline(this::testMethod, BODY_IN, VAR_1))
                .setHeader(file().fileName(), constant("foo.txt"));
        ;
    }

    public String testMethod(String a, String b) {
        return a + b;
    }
}