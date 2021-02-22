package com.rj.bd;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;

/**
 *@desc   高并发测试-concurrencytester
 */
public class Test
{
    public static void main( String[] args ){
    	ConcurrencyTester tester = ThreadUtil.concurrencyTest(100, () -> {
    	    // 测试的逻辑内容
    	    long delay = RandomUtil.randomLong(100, 1000);
    	    ThreadUtil.sleep(delay);
    	    Console.log("{} test finished, delay: {}", Thread.currentThread().getName(), delay);
    	});
    	// 获取总的执行时间，单位毫秒
    	Console.log(tester.getInterval());
    }
}
