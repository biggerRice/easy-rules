/**
 * The MIT License
 *
 *  Copyright (c) 2020, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package org.jeasy.rules.api;

import org.jeasy.rules.core.RuleProxy;

import java.util.*;

/**
 * 封装一个规则集合，代表多个规则组成的规则集合空间
 * 在同一个命名空间内 规则名字唯一
 *
 * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 */
public class Rules implements Iterable<Rule> {

    /**
     * 存储有序的规则集合
     */
    private Set<Rule> rules = new TreeSet<>();

    /**
     * 构造函数
     *
     * @param rules to register
     */
    public Rules(Set<Rule> rules) {
        this.rules = new TreeSet<>(rules);
    }

    /**
     * 构造函数
     *
     * @param rules to register
     */
    public Rules(Rule... rules ) {
        Collections.addAll(this.rules, rules);
    }

    /**
     * 构造函数
     *
     * @param rules to register
     */
    public Rules(Object... rules ) {
        for (Object rule : rules) {
            this.register(rule);
        }
    }

    /**
     * Register a new rule.
     *
     * @param rule to register
     */
    public void register(Object rule) {
        Objects.requireNonNull(rule);
        rules.add(RuleProxy.asRule(rule));
    }

    /**
     * 移除一个规则
     *
     * @param rule to unregister
     */
    public void unregister(Object rule) {
        Objects.requireNonNull(rule);
        rules.remove(RuleProxy.asRule(rule));
    }

    /**
     * 根据规则名字移除规则
     *
     * @param ruleName the name of the rule to unregister
     */
    public void unregister(final String ruleName){
        Objects.requireNonNull(ruleName);
        Rule rule = findRuleByName(ruleName);
        if(rule != null) {
            unregister(rule);
        }
    }

    /**
     * 验证集合是否为空
     *
     * @return true if the rule set is empty, false otherwise
     */
    public boolean isEmpty() {
        return rules.isEmpty();
    }

    /**
     * 清空规则集合
     */
    public void clear() {
        rules.clear();
    }

    /**
     * 迭代下一个规则
     * @return
     */
    @Override
    public Iterator<Rule> iterator() {
        return rules.iterator();
    }


    /**
     * 根据规则名字查找规则
     * @param ruleName
     * @return
     */
    private Rule findRuleByName(String ruleName){
        for(Rule rule : rules){
            if(rule.getName().equalsIgnoreCase(ruleName))
                return rule;
        }
        return null;
    }
}
