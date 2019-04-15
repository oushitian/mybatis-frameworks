package com.fd.mybatis.executor.statement;

import com.fd.mybatis.executor.result.ResultHandler;

/**
 * @Description 抽象模板类
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName BaseStatementHandler
 * @Date 2019-04-10 16:50
 */
public abstract class BaseStatementHandler implements StatementHandler{

    protected final ResultHandler resultHandler;

    public BaseStatementHandler(ResultHandler resultHandler){
        this.resultHandler = resultHandler;
    }

}
