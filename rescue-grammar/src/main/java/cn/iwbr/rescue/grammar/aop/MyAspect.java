package cn.iwbr.rescue.grammar.aop;

import cn.hutool.core.util.StrUtil;
import cn.iwbr.rescue.grammar.annotation.MyAnnotation;
import cn.iwbr.rescue.grammar.entity.Human;
import cn.iwbr.rescue.grammar.entity.Staff;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @description: 自定义切面方案
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @date: 2024/02/06
 */
@Slf4j
@Aspect
@Component
public class MyAspect {

    @Pointcut("@annotation(cn.iwbr.rescue.grammar.annotation.MyAnnotation)")
    public void myPointcut() {
    }

    @Pointcut("@annotation(annotation)")
    public void myPointcutPro(MyAnnotation annotation) {
    }

//    @Around(value = "myPointcut()")
//    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        System.out.println("AOP切面--前切！");
//        Object proceed = proceedingJoinPoint.proceed();
//        System.out.println("AOP切面--后切！");
//        return proceed;
//    }

    @Around(value = "myPointcutPro(myAnnotation)")
    public Object myAroundPro(ProceedingJoinPoint proceedingJoinPoint, MyAnnotation myAnnotation) throws Throwable {

        Object[] args = beforeCut(proceedingJoinPoint, myAnnotation);
        Object proceed = proceedingJoinPoint.proceed(args);
        if (proceed instanceof Staff) {
            return afterCut((Staff) proceed);
        }
        return proceed;
    }

    /**
     * AOP切面--前切，在方法执行之前的切面逻辑（日志记录、校验接口参数，格式化接口参数等操作）
     *
     * @return {@link Object[]}
     */
    private Object[] beforeCut(ProceedingJoinPoint proceedingJoinPoint, MyAnnotation myAnnotation) {
        log.info("AOP切面--前切！");
        // 获取注解中传递的参数内容
        String requestParamsType = myAnnotation.requestParamsType();
        String paramName = myAnnotation.paramName();
        Class<?> bodyDataType = myAnnotation.bodyDataType();

        // 获取接口参数中传递的数据
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        // 获取方法名
        String methodName = methodSignature.getName();
        // 获取参数名
        String[] parameterNames = methodSignature.getParameterNames();
        // 获取参数内容
        Object[] args = proceedingJoinPoint.getArgs();

        // form-data格式传参
        if (StrUtil.equals(requestParamsType, MyAnnotation.PARAM)) {
            if (parameterNames.length > 0) {
                for (int i = 0; i < parameterNames.length; i++) {
                    String name = parameterNames[i];
                    if (StrUtil.equals(name, paramName)) {
                        log.info("修改参数【{}】，原本参数值为：【{}】，修改后值为：【{}】", name, args[i], "姓名为：" + args[i]);
                        args[i] = "姓名为：" + args[i];
                        break;
                    }
                }
            }
        } else if (StrUtil.equals(requestParamsType, MyAnnotation.BODY)) {
            // body方式传参
            if (Human.class.isAssignableFrom(bodyDataType)) {
                Human human = null;
                for (Object arg : args) {
                    if (Human.class.isAssignableFrom(arg.getClass())) {
                        human = (Human) arg;
                        break;
                    }
                }
                if (human != null) {
                    log.info("修改参数【{}】，原本参数值为：【{}】，修改后值为：【{}】", paramName, human.getName(), "姓名为：" + human.getName());
                    human.setName("姓名为：" + human.getName());
                }
            }
        }

        return args;
    }

    /**
     * AOP切面--后切，在方法执行之后的切面逻辑（日志记录、修改接口返回值等操作）
     *
     * @return {@link Object}
     */
    private Staff afterCut(Staff staff) {
        log.info("AOP切面--后切！");
        staff.setTest("这是AOP切面--后切加的属性信息！");
        return staff;
    }
}
