# <center>tool-router</center>

[![](https://jitpack.io/v/Voilance/voitools.svg)](https://jitpack.io/#Voilance/voitools)

## 简介
> 该功能模块提供了组件化开发中模块间路由、通信、解耦等功能。

## 使用

1. [引入并启用插件](../README.md)，添加 `tool-router` 功能模块。
```groovy
classpath "com.github.Voilance.voitools:tool-router:$voitools_version"
```

2. 在需要支持路由的 `Activity` 添加注解
```java
// 注解中的值需唯一，同值路由会覆盖
@VoiRoute("MainActivity")
public class MainActivity extends Activity {
    ...
}
```

3. 发起路由操作
```java
// 1. 简单使用
VoiRouter.from(this) // 起始 Context
         .to("TargetActivity") // 目标路由
         .go()
// 或
VoiRouter.from(this) // 起始 Context
         .to(TargetActivity.class) // 目标路由
         .go()

// 2. 更简单使用
VoiRouter.go(this, "TargetActivity");
// 或
VoiRouter.go(this, TargetActivity.class);

// 带参使用
VoiRouter.from(this) // 起始 Context
         .to("TargetActivity") // 目标路由
         .requestCode(1) // StartActivityForResult
         .putExtra("keyInt", 1) // k-v 参数
         .finish(true) // 是否结束起始 Context
         .addFlags(0)
         ...
         .go();
```

## More
> 更多功能开发中，欢迎交流~ :)