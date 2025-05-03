package com.shyloostyle.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPAspect {

    @Before("execution(public String com.shyloostyle.aop.controller.AOPController.getTheMessage())")
    public void beforeTheControllerMethod(){
        System.out.println("executing the cross cutting logic before execution of controller method");
    }

    @Before("execution(public * com.shyloostyle.aop.controller.AOPController.*(String))")
    public void gettingProducts(){
        System.out.println("executing the cross cutting logic before execution of product method");
    }

    @Before("@within(* com.shyloostyle.aop.controller.AOPController)")
    public void employee(){
        System.out.println("cross cutting by employee method");
    }
    @Before("@within(* com.shyloostyle.aop.service)")
    public void employeeService(){
        System.out.println("cross cutting by employee method from service");
    }
}
