***

<div align="center">
    <b><em>Easy Rules</em></b><br>
    简单、高效的Java规则引擎
</div>

<div align="center">

[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Coverage](https://coveralls.io/repos/j-easy/easy-rules/badge.svg?style=flat&branch=master&service=github)](https://coveralls.io/github/j-easy/easy-rules?branch=master)
[![Build Status](https://github.com/j-easy/easy-rules/workflows/Java%20CI/badge.svg)](https://github.com/j-easy/easy-rules/actions)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.jeasy/easy-rules-core/badge.svg?style=flat)](http://search.maven.org/#artifactdetails|org.jeasy|easy-rules-core|3.4.0|)
[![Javadoc](https://www.javadoc.io/badge/org.jeasy/easy-rules-core.svg)](http://www.javadoc.io/doc/org.jeasy/easy-rules-core)
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/j-easy/easy-rules)

</div>

***

## 什么是 Easy Rules?

Easy Rules 是一个Java规则引擎受一篇叫做[Should I use a Rules Engine?](http://martinfowler.com/bliki/RulesEngine.html)"的文章启发。

> 你自己可以构建一个简单的规则引擎，你需要创建一列系带有条件和行为的对象，存储在一个集合里面，执行时评估对象的条件来执行行为。

这就是规则引擎做的事情,它提供了规则抽象来创建条件和行为，并提供API运行一系列的规则条件和行为。

## 核心特色

 * 轻量级库和易学的API
 * 基于POJO开发带有注解的编程模型
 * 有用的抽象来定义业务规则、Java很容易实现
 * 可以基于简单规则创建组合规则
 * 可以用表达式语言定义规则如 (如 MVEL and SpEL)

## 例子

### 1. 第一步 定义规则

#### 方式一：带有注解的声明:

```java
@Rule(name = "weather rule", description = "if it rains then take an umbrella" )
public class WeatherRule {

    @Condition
    public boolean itRains(@Fact("rain") boolean rain) {
        return rain;
    }
    
    @Action
    public void takeAnUmbrella() {
        System.out.println("It rains, take an umbrella!");
    }
}
```

#### 方式二：利用流式API编程

```java
Rule weatherRule = new RuleBuilder()
        .name("weather rule")
        .description("if it rains then take an umbrella")
        .when(facts -> facts.get("rain").equals(true))
        .then(facts -> System.out.println("It rains, take an umbrella!"))
        .build();
```

#### 方式三：利用表达式语言:

```java
Rule weatherRule = new MVELRule()
        .name("weather rule")
        .description("if it rains then take an umbrella")
        .when("rain == true")
        .then("System.out.println(\"It rains, take an umbrella!\");");
```

#### 方式四 规则描述器:

Like in the following `weather-rule.yml` example file:

```yaml
name: "weather rule"
description: "if it rains then take an umbrella"
condition: "rain == true"
actions:
  - "System.out.println(\"It rains, take an umbrella!\");"
```

```java
MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
Rule weatherRule = ruleFactory.createRule(new FileReader("weather-rule.yml"));
```

### 2. 第二步 验证!

```java
public class Test {
    public static void main(String[] args) {
        // define facts
        Facts facts = new Facts();
        facts.put("rain", true);

        // define rules
        Rule weatherRule = ...
        Rules rules = new Rules();
        rules.register(weatherRule);

        // fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }
}
```

这是Easy Rules hello world例子，在wiki中你可以发现其它例子如 [Shop](https://github.com/j-easy/easy-rules/wiki/shop), [Airco](https://github.com/j-easy/easy-rules/wiki/air-conditioning) or [WebApp](https://github.com/j-easy/easy-rules/wiki/web-app) 教程。


## 其它语言支持

* [EasyRulesGo](https://github.com/CrowdStrike/easyrulesgo) : A port of EasyRules to Golang by [@jiminoc](https://github.com/jiminoc)
* [EasyRulesGroovy](https://github.com/will-gilbert/easyrules-tutorials-groovy) : A port of EasyRules tutorials to Groovy by [@will-gilbert](https://github.com/will-gilbert)
* [EasyRulesCsharp](https://github.com/feldrim/EasyRulesCsharp) : A port of EasyRules to CSharp (WIP) by [@feldrim](https://github.com/feldrim)
