package com.qzc.afmanager.communicate;

/**
 * created by qzc at 2019/07/04 17:31
 * desc:
 */
public abstract class FunctionNoParamNoResult extends Function {

    public FunctionNoParamNoResult(String functionName) {
        super(functionName);
    }

    public abstract void function();
}
