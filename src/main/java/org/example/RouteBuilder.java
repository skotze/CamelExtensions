package org.example;

import lombok.experimental.ExtensionMethod;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.example.camel.dsl.RouteBuilderExtensions;
import org.example.camel.dsl.exchangedata.BodyDefinition;
import org.example.camel.dsl.exchangedata.VariableDefinition;
import org.springframework.stereotype.Component;

import static org.example.camel.dsl.CamelDslExtensions.function;
import static org.example.camel.dsl.exchangedata.ExchangeDataDefinition.bodyDefinition;
import static org.example.camel.dsl.exchangedata.ExchangeDataDefinition.variableDefinition;

@Component
@ExtensionMethod(RouteBuilderExtensions.class)
public class RouteBuilder extends EndpointRouteBuilder {

    static final VariableDefinition<String> VAR_1 = variableDefinition(String.class, "VAR_1");
    static final VariableDefinition<String> VAR_2 = variableDefinition(String.class, "VAR_2");
    static final VariableDefinition<String> VAR_3 = variableDefinition(String.class, "VAR_3");

    static final BodyDefinition<String> BODY_AS_STRING = bodyDefinition(String.class);

    @Override
    public void configure() {

        from(direct(""))
                .setVariable(VAR_1, constant(""))
                .setBody(VAR_1::getFromExchange)
                .setBody(function(this::testMethod, BODY_AS_STRING, VAR_1))
                .enrichSendExpression(direct(""), constant(""), VAR_2)
                .enrichSendExpression(direct(""), constant("")).variableReceive(VAR_3)
                .setHeader(file().fileName(), constant("foo.txt"));
    }

    public String testMethod(String a, String b) {
        return a + b;
    }
}