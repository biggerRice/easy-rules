package org.jeasy.rules.tutorials.weather;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

public class Launcher {

    public static void main(String[] args) {
        // define facts
        Facts facts = new Facts();
        // 参数定义为下雨
        facts.put("rain", true);

        // define rules
        WeatherRule weatherRule = new WeatherRule();
        Rules rules = new Rules();
        rules.register(weatherRule);

        // fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();
        // 执行结果 带伞
        rulesEngine.fire(rules, facts);
    }

}