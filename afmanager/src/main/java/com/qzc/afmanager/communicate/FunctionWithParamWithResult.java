package com.qzc.afmanager.communicate;

/**
 * created by qzc at 2019/07/04 17:40
 * desc:
 */
public abstract class FunctionWithParamWithResult<Param, Result> extends Function {

    public FunctionWithParamWithResult(String functionName) {
        super(functionName);
    }

    public abstract Result function(Param param);
}
