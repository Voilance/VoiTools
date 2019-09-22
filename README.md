# <center>VoiTools</center>

[![](https://jitpack.io/v/Voilance/voitools.svg)](https://jitpack.io/#Voilance/voitools)

## 简介
> **VoiTools** 是一款以 Gradle Plugin 形式为安卓移动应用提供功能支持的可扩展工具集。目标是通过 AOP ，在最大程度不侵入原项目的情况下给项目提供功能支持，实现如注解、统计、收集信息、优化应用等功能。

## 使用
> 在项目根目录下的 build.gradle 引用 voitools 插件，然后在 app 模块下的 build.gradle 中启用 voitools 插件。最后根据需要，在根目录下的 build.gradle 通过 classpath 引入功能模块。
```groovy
// root build.gradle
buildscript {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
    ext.voitools_version = '0.2'
    dependencies {
        ...
        classpath "com.github.Voilance.voitools:plugin:$voitools_version"
    }
}

allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```groovy
// app build.gradle
apply plugin: 'com.android.application'
apply plugin: 'com.voilance.voitool'
```

## 功能概览
> 当前版本支持以下功能。

- [tool-router](./tool-router/README.md) - 应用内路由
```groovy
classpath "com.github.Voilance.voitools:tool-router:$voitools_version"
```

## More
> 更多功能开发中，欢迎交流~ :)