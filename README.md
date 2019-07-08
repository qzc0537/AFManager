# AFManager
一款简单的Activity和Fragment的通信库

[![](https://jitpack.io/v/qzc0537/AFManager.svg)](https://jitpack.io/#qzc0537/AFManager)


使用
--
1.project build.gradle下添加：
maven { url 'https://jitpack.io' }

如下：

```
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

2.app build.gradle下添加依赖 ：

```
implementation 'com.github.qzc0537:AFManager:1.0.1'
```

3.愉快的使用：
```
private val tag = "MainActivity"
private val function1 = "function1"
private val function2 = "function2"
private val function3 = "function3"
private val function4 = "function4"

接收消息：
 AFManager.getInstance().addFunction(object : FunctionNoParamNoResult(function1) {
            override fun function() {
                Log.i(tag, "Receive function1")
            }
        }).addFunction(object : FunctionWithParam<Int>(function2) {
            override fun function(param: Int?) {
                Log.i(tag, "Receive function2, param->$param")
            }
        }).addFunction(object : FunctionWithResult<String>(function3) {
            override fun function(): String {
                Log.i(tag, "Receive function3, return 666")
                return "666"
            }
        }).addFunction(object : FunctionWithParamWithResult<Int, String>(function4) {
            override fun function(param: Int?): String {
                Log.i(tag, "Receive function4, param->$param return 888")
                return "888"
            }
        })
        
发送消息：
AFManager.getInstance().invokeFunction(function1)
AFManager.getInstance().invokeFunction(function2, 222)
val result = AFManager.getInstance().invokeFunction(function3, String::class.java)
Log.i(tag, "function3 result->$result")
val result = AFManager.getInstance().invokeFunction(function4,888,String::class.java)
Log.i(tag, "function3 result->$result")
