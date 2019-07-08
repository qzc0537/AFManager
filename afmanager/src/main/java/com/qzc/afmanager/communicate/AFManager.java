package com.qzc.afmanager.communicate;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * created by qzc at 2019/07/04 14:19
 * desc:
 */
public class AFManager {
    private static class SingleTon {
        private static final AFManager INSTANCE = new AFManager();
    }

    private AFManager() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        map3 = new HashMap<>();
        map4 = new HashMap<>();
    }

    public static AFManager getInstance() {
        return SingleTon.INSTANCE;
    }

    private HashMap<String, FunctionNoParamNoResult> map1;
    private HashMap<String, FunctionWithParam> map2;
    private HashMap<String, FunctionWithResult> map3;
    private HashMap<String, FunctionWithParamWithResult> map4;

    public AFManager addFunction(FunctionNoParamNoResult function) {
        if (map1.containsKey(function.mFunctionName)) return this;
        map1.put(function.mFunctionName, function);
        return this;
    }

    public AFManager addFunction(FunctionWithParam function) {
        if (map2.containsKey(function.mFunctionName)) return this;
        map2.put(function.mFunctionName, function);
        return this;
    }

    public AFManager addFunction(FunctionWithResult function) {
        if (map3.containsKey(function.mFunctionName)) return this;
        map3.put(function.mFunctionName, function);
        return this;
    }

    public AFManager addFunction(FunctionWithParamWithResult function) {
        if (map4.containsKey(function.mFunctionName)) return this;
        map4.put(function.mFunctionName, function);
        return this;
    }

    public AFManager removeFunction(String functionName) {
        if (TextUtils.isEmpty(functionName)) return this;
        if (map1.remove(functionName) != null) return this;
        if (map2.remove(functionName) != null) return this;
        if (map3.remove(functionName) != null) return this;
        if (map4.remove(functionName) != null) return this;
        return this;
    }

    public void invokeFunction(String functionName) {
        if (TextUtils.isEmpty(functionName)) return;
        FunctionNoParamNoResult f = map1.get(functionName);
        if (f != null) {
            f.function();
        } else {
            throw new RuntimeException("No function with " + functionName);
        }
    }

    public <Param> void invokeFunction(String functionName, Param param) {
        if (TextUtils.isEmpty(functionName)) return;
        FunctionWithParam f = map2.get(functionName);
        if (f != null) {
            f.function(param);
        } else {
            throw new RuntimeException("No function with " + functionName);
        }
    }

    public <Result> Result invokeFunction(String functionName, Class<Result> clazz) {
        if (TextUtils.isEmpty(functionName)) return null;
        FunctionWithResult f = map3.get(functionName);
        if (f != null) {
            if (clazz != null) {
                return clazz.cast(f.function());
            } else {
                return (Result) (f.function());
            }
        } else {
            throw new RuntimeException("No function with " + functionName);
        }
    }

    public <Result, Param> Result invokeFunction(String functionName, Param param, Class<Result> result) {
        if (TextUtils.isEmpty(functionName)) return null;
        FunctionWithParamWithResult f = map4.get(functionName);
        if (f != null) {
            if (result != null) {
                return result.cast(f.function(param));
            } else {
                return (Result) (f.function(param));
            }
        } else {
            throw new RuntimeException("No function with " + functionName);
        }
    }
}
