package com.centime.userservice.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This Aspect class tries to find all the methods which have LogMethodParam
 * annotation. Once found this method logs the arguments to those methods.
 * 
 * @author prkala
 *
 */
@Aspect
@Component
@Slf4j
public class LogMethodParamAspect {

	@Before("execution(* *.*(..)) && @annotation(LogMethodParam)")
	public void logMethodParams(JoinPoint joinPoint) {
		Object[] signatureArgs = joinPoint.getArgs();

		for (Object signatureArg : signatureArgs) {
			log.debug("Method Arg: " + signatureArg);
		}

	}

}
