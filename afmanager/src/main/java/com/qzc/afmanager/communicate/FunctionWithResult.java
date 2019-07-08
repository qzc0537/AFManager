package com.qzc.afmanager.communicate;

/**
 * created by qzc at 2019/07/04 17:41
 * desc:
 */
public abstract class FunctionWithResult<Result> extends Function {

    public FunctionWithResult(String functionName) {
        super(functionName);
    }

    public abstract Result function();
}
