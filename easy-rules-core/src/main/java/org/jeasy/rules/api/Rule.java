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

/**
 * Abstraction for a rule that can be fired by the rules engine.
 *
 * Rules are registered in a rule set of type <code>Rules</code> in which they must have a <strong>unique</strong> name.
 *
 * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 */
public interface Rule extends Comparable<Rule> {

    /**
     * 默认的规则名
     */
    String DEFAULT_NAME = "rule";

    /**
     * 默认的规则描述
     */
    String DEFAULT_DESCRIPTION = "description";

    /**
     * 默认的规则优先级，默认最大
     */
    int DEFAULT_PRIORITY = Integer.MAX_VALUE - 1;

    /**
     * 获取规则名
     * @return the rule name
     */
    String getName();

    /**
     * 获取规则描述
     * @return rule description
     */
    String getDescription();

    /**
     * 获取优先级
     * @return rule priority
     */
    int getPriority();

    /**
     * 规则引擎判断条件
     * 如果提供的facts被应用到规则上返回true,否则返回false
     * <strong>Implementations should handle any runtime exception and return true/false accordingly</strong>
     *
     * @return true if the rule should be applied given the provided facts, false otherwise
     */
    boolean evaluate(Facts facts);

    /**
     * 如果规则引擎返回true，则执行该方法
     * @throws Exception thrown if an exception occurs during actions performing
     */
    void execute(Facts facts) throws Exception;

}
