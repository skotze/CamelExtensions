package org.example.camel.dsl;

import lombok.experimental.UtilityClass;
import org.apache.camel.Expression;
import org.apache.camel.builder.EndpointConsumerBuilder;
import org.apache.camel.builder.ExpressionClause;
import org.apache.camel.builder.endpoint.dsl.DirectEndpointBuilderFactory;
import org.apache.camel.model.EnrichDefinition;
import org.apache.camel.model.ProcessorDefinition;
import org.example.camel.dsl.exchangedata.ExchangePropertyDefinition;
import org.example.camel.dsl.exchangedata.HeaderDefinition;
import org.example.camel.dsl.exchangedata.VariableDefinition;

import java.util.UUID;

@UtilityClass
public class RouteBuilderExtensions {
    public static ProcessorDefinition<?> setVariable(ProcessorDefinition<?> route,
                                                     VariableDefinition<?> variableDefinition,
                                                     Expression expression) {

        return route.setVariable(variableDefinition.name(), expression);
    }

    public static ProcessorDefinition<?> setHeader(ProcessorDefinition<?> route,
                                                   HeaderDefinition<?> headerDefinition,
                                                   Expression expression) {

        return route.setHeader(headerDefinition.name(), expression);
    }

    public static ProcessorDefinition<?> setProperty(ProcessorDefinition<?> route,
                                                     ExchangePropertyDefinition<?> propertyDefinition,
                                                     Expression expression) {

        return route.setProperty(propertyDefinition.name(), expression);
    }


    public static EnrichDefinition enrichWithExpression(ProcessorDefinition<?> route,
                                                        String destination,
                                                        Expression expression) {
        String enrichVariableName = "ENRICH:" + UUID.randomUUID();
        route.setVariable(enrichVariableName, expression);
        return route.enrich().constant(destination).variableSend(enrichVariableName);
    }


    public static EnrichDefinition enrichWithExpression(ProcessorDefinition<?> route,
                                                        EndpointConsumerBuilder endpointConsumerBuilder,
                                                        Expression expression,
                                                        VariableDefinition<?> variableReceive) {
        return enrichWithExpression(route, endpointConsumerBuilder.getRawUri(), expression, variableReceive);
    }

    public static EnrichDefinition enrichWithExpression(ProcessorDefinition<?> route,
                                                        EndpointConsumerBuilder endpointConsumerBuilder,
                                                        Expression expression) {
        return enrichWithExpression(route, endpointConsumerBuilder.getRawUri(), expression);
    }


    public static EnrichDefinition enrichWithExpression(ProcessorDefinition<?> route,
                                                        String destination,
                                                        Expression expression,
                                                        VariableDefinition<?> variableReceive
    ) {
        return variableReceive(
                enrichWithExpression(route, destination, expression),
                variableReceive);
    }

    public static EnrichDefinition variableReceive(EnrichDefinition enrichDefinition,
                                                   VariableDefinition<?> variableReceive) {

        return enrichDefinition.variableReceive(variableReceive.name());
    }


}