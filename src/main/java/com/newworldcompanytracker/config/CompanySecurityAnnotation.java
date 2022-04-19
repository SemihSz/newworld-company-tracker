package com.newworldcompanytracker.config;

import com.newworldcompanytracker.service.auth.IsAuthenticationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Created by Semih, 2.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface CompanySecurityAnnotation {

    String authToken() default "";

    String companyName() default "";


}

@Aspect
@Component
@Slf4j
class AspectClass  {

    public static final String CRYPTO_AUTH_TOKEN = "authToken";

    public static final String USER_ID = "companyName";

    private final IsAuthenticationTokenService isAuthenticationTokenService;

    AspectClass(IsAuthenticationTokenService isAuthenticationTokenService) {
        this.isAuthenticationTokenService = isAuthenticationTokenService;
    }


    @Around(" @annotation(com.newworldcompanytracker.config.CompanySecurityAnnotation)")
    public Object validateAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        final Object[] objects = proceedingJoinPoint.getArgs();
        final Method method = signature.getMethod();
        final CompanySecurityAnnotation validateAction = method.getAnnotation(CompanySecurityAnnotation.class);
        final ExpressionParser expressionParser = new SpelExpressionParser();

        final String authToken = request.getHeader(CRYPTO_AUTH_TOKEN);

        String companyName = "";

        if (StringUtils.isNotEmpty(validateAction.companyName())) {
            companyName = (String) expressionParser.parseExpression(validateAction.companyName()).getValue(objects);
        }

        if (StringUtils.isNotBlank(validateAction.authToken()) && StringUtils.isNotBlank(validateAction.authToken())) {
            isAuthenticationTokenService.accept(companyName, authToken);
        }

        return proceedingJoinPoint.proceed();
    }

}
