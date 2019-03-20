package com.demo.adminsystem.core.configure;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:15
 * @version: V1.0
 * @detail: 事务拦截
 **/
@Aspect
@Configuration
public class TxAdviceConfiguration {
    private int transactionTimeout = 60;
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.longyu.core.service..*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
//         /*只读事务，不做更新操作*/
//        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
//        readOnlyTx.setReadOnly(true);
//        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);

        /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(
                Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(transactionTimeout);
        requiredTx.setTimeout(10);
        Map<String, TransactionAttribute> txMap = new HashMap<>();

        //通用增删改 需要开启事务
        //增
        txMap.put("save*", requiredTx);
        txMap.put("create*", requiredTx);
        txMap.put("add*", requiredTx);
        txMap.put("insert*", requiredTx);
        //改
        txMap.put("update*", requiredTx);
        txMap.put("modify*", requiredTx);
        txMap.put("edit*", requiredTx);
        txMap.put("merge*", requiredTx);
        //删
        txMap.put("delete*", requiredTx);
        txMap.put("remove*", requiredTx);
        //通用开启事务方法以do开头
        txMap.put("do*", requiredTx);

        //查询默认不开启事务
//        txMap.put("count*", readOnlyTx);
//        txMap.put("find*", readOnlyTx);
//        txMap.put("get*", readOnlyTx);
//        txMap.put("query*", readOnlyTx);
//        txMap.put("list*", readOnlyTx);
//        txMap.put("search*", readOnlyTx);
//        txMap.put("load*", readOnlyTx);
//        txMap.put("*", readOnlyTx);

        source.setNameMap(txMap);
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
        return txAdvice;
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
