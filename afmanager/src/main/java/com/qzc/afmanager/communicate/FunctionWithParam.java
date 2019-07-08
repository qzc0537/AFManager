package com.qzc.afmanager.communicate;

/**
 * created by qzc at 2019/07/04 17:37
 * desc:
 */
public abstract class FunctionWithParam<Param> extends Function {


    public FunctionWithParam(String functionName) {
        super(functionName);
    }

    public abstract void function(Param param);
}
