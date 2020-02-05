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

import java.util.List;
import java.util.Map;

import org.jeasy.rules.core.RulesEngineParameters;

/**
 * 规则引擎接口
 *
 * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 */
public interface RulesEngine {

    /**
     * 返回规则引擎的参数
     *
     * @return The rules engine parameters
     */
    RulesEngineParameters getParameters();

    /**
     * 返回已注册的规则监听器列表
     *
     * @return the list of registered rule listeners
     */
    List<RuleListener> getRuleListeners();

    /**
     * 返回已注册的规则引擎监听器列表
     *
     * @return the list of registered rules engine listeners
     */
    List<RulesEngineListener> getRulesEngineListeners();

    /**
     * 在给定的facts上开启所有已注册的规则
     */
    void fire(Rules rules, Facts facts);

    /**
     * 检查规则和facts是否符合
     * @return a map with the result of evaluation of each rule
     */
    Map<Rule, Boolean> check(Rules rules, Facts facts);
}
