package org.example;

import org.apache.camel.Exchange;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.support.ExpressionSupport;
import org.apache.camel.support.builder.ExpressionBuilder;
import org.example.camel.dsl.ExchangeData;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.example.camel.dsl.CamelDslExtensions.inline;


public class RouteBuilder extends EndpointRouteBuilder {

    @Override
    public void configure() {
        from(direct(""))
                .setBody().body(String.class, String::toString)

                // Some route start
                .setBody(inline(
                        ExchangeData.body(String.class),
                        ExchangeData.variable(String.class, "VAR_1"),
                        this::testMethod))
                .setHeader(file().fileName(), constant("foo.txt"))
        // Some route end
        ;
    }

    public String testMethod(String a, String b) {
        return a + b;
    }
}