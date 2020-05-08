package _3_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Function;

/**
 * Lambda表达式的异常处理
 */
public class LambdasWithException {
    /**
     * 参考Java 异常结构图：https://img-my.csdn.net/uploads/201211/27/1354002637_9416.png
     *     Throwable是所有异常的根，java.lang.Throwable
     *     Error是错误，java.lang.Error 是系统级错误，不需要程序处理，一般比较严重，由java虚拟机生成并抛出
     *     Exception是异常，java.lang.Exception
     *
     *     RuntimeException  (运行时异常，不需要捕获)
     *     Checked 受检异常  （可处理，需要捕获；不可处理，需要抛出）
     */

    /**
     * 由于运行时异常不用捕获，所以任何时候，我们需要讨论的主要是受检异常
     */

    /**
     * 注意：
     * JAVA API中任何函数式接口都不允许抛出受检异常（checked exception），例如Function<T,R>等
     * 因此如果需要Lambda表达式可以抛出受检异常，有以下两种方式
     */

    /**
     * 方式一：自定义一个可抛出受检异常的函数式接口
     */
    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }

    BufferedReaderProcessor p = (BufferedReader br) -> br.readLine();

    /**
     * 方式二：正在使用JavaAPI中的函数式接口，例如Function<T,R>,此时无需额外定义一个可抛出受检异常的函数式接口
     * 则：只需要借助try/catch进行显示捕获受检异常即可
     * 此时的Lambda表达式会因为try/catch块的影响，而变成多行，所以可以单独的将此lambda表达式赋值给对应的函数式接口变量再进行使用
     */
    Function<BufferedReader, String> f = (BufferedReader b) -> {
        try {
            return b.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

}
