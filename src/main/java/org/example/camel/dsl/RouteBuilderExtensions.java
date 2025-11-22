package org.example.camel.dsl;

import lombok.experimental.UtilityClass;
import org.apache.camel.Expression;
import org.apache.camel.model.ProcessorDefinition;
import org.example.camel.dsl.exchangedata.ExchangePropertyDefinition;
import org.example.camel.dsl.exchangedata.HeaderDefinition;
import org.example.camel.dsl.exchangedata.VariableDefinition;

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
}