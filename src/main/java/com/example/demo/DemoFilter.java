package com.example.demo;

import com.example.demo.annotations.Sample;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@Component
public class DemoFilter extends OncePerRequestFilter {
    final AbstractHandlerMethodMapping methodMapping;

    public DemoFilter(AbstractHandlerMethodMapping methodMapping) {
        this.methodMapping = methodMapping;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final HandlerExecutionChain handlerExecutionChain = this.getHandler(request);
        if (handlerExecutionChain == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            System.out.println("non api");
            return;
        }

        final Method method = ((HandlerMethod) handlerExecutionChain.getHandler()).getMethod();
        Sample annotation = AnnotationUtils.getAnnotation(method, Sample.class);
        if (annotation == null) {
            Class clazz = method.getDeclaringClass();
            annotation = AnnotationUtils.getAnnotation(clazz, Sample.class);
        }
        final SampleType sampleType = annotation != null ? annotation.type() : SampleType.DEFAULT;

        switch (sampleType) {
            case FOO:
                System.out.println("FOO!!");
                break;
            case BAR:
                System.out.println("BAR!!");
                break;
            case DEFAULT:
                System.out.println("DEFAULT!!");
                break;
            default:
                throw new IllegalStateException("Case " + sampleType + " is not found!!");
        }

        filterChain.doFilter(request, response);
    }

    @Nullable
    private HandlerExecutionChain getHandler(HttpServletRequest request) {
        try {
            return this.methodMapping.getHandler(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
