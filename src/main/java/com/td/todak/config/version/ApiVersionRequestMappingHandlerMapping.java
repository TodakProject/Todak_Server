package com.td.todak.config.version;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {

        RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
        ApiVersion annotation = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);

        if (info != null && annotation != null) {
            String[] patterns = {"api/v" + annotation.value()};
            PatternsRequestCondition condition = new PatternsRequestCondition(
                    patterns,
                    this.getUrlPathHelper(),
                    this.getPathMatcher(),
                    false,
                    false
                    );

            info = new RequestMappingInfo(
                    condition,
                    new RequestMethodsRequestCondition(),
                    new ParamsRequestCondition(),
                    new HeadersRequestCondition(),
                    new ConsumesRequestCondition(),
                    new ProducesRequestCondition(),
                    this.getCustomTypeCondition(handlerType)
            ).combine(info);
        }

        return info;
    }
}
