package com.eniro.aspectjtest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.Assert;

@Aspect
public class OpenableAspect {

    @Around("execution(public void com.eniro.aspectjtest.Openable.open())")
    public void openMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            ((Openable) joinPoint.getTarget()).isOpen();
            System.out.println("Page " + joinPoint.getTarget().getClass().getSimpleName() + " already open!");
        } catch (AssertionError e) {
            System.out.println("Opening page " + joinPoint.getTarget().getClass().getSimpleName());
            joinPoint.proceed();
            ((Openable) joinPoint.getTarget()).isOpen();
        }
    }

    @AfterThrowing(pointcut = "execution(public void com.eniro.aspectjtest.Openable.isOpen())", throwing = "error")
    public void isOpenMethod(JoinPoint joinPoint, Throwable error) {
        Assert.fail(joinPoint.getTarget().getClass().getSimpleName() + " is not open");;
    }
}
