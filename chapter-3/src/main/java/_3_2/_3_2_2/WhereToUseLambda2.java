package _3_2._3_2_2;

/**
 * 在函数描述符上使用Lambda表达式
 */
public class WhereToUseLambda2 {
    /**
     * 什么是函数描述符？
     * 函数式接口的抽象方法的签名就是函数描述符
     * 有时候也会简单地说成：函数式接口的签名
     */

    // ---------------------------------------在函数描述符上使用Lambda表达式 的前提是-------------------------------
    /**
     * 函数描述符 与 要使用的Lambda表达式的Lambda表达式签名必须一致！！！
     */

    /**
     * 举例说明：
     *
     * 正确案例：
     * 将Lambda表达式传递给一个接受函数式接口的方法
     * public void execute (Runnable r){
     *     r.run();
     * }
     * execute(() -> {});
     * 解析：
     * Lambda表达式 "() -> {}" 对应的Lambda表达式签名为 "() -> void"，与Runnable接口中的抽象方法 run 的签名完全匹配
     * 即 此时 函数描述符 与 要使用的 Lambda表达式 的 Lambda表达式签名 是一致的。
     *
     *
     * 正确案例：
     * 将Lambda表达式作为返回值
     * public Callable<String> fetch() {
     *     return () -> "Tricky example ;-)";
     * }
     * 解析：
     * fetch方法的返回类型是Callable<String>，所以具体的返回值应该是一个Callable<String>的实现，而Callable<String>中只定义了一个抽象方法
     * 参考_3_2._3_2_1.WhereToUseLambda中的 java.util.concurrent.Callable 接口可知，此时该抽象方法的签名为 "() -> String"，因为其中的 "V" 被 "String" 给替代了
     * 又有Lambda表达式"() -> "Tricky example ;-)""对应的Lambda表达式签名为 "() -> String", 与 Callable<String> 接口中的抽象方法 call 的签名完全匹配
     * 即 此时 函数描述符 与 要使用的 Lambda表达式 的 Lambda表达式签名 是一致的。
     *
     *
     * 错误案例：
     * 将Lambda表达式赋值给一个变量
     * Predicate<Apple> p = (Apple a) -> a.getWeight();
     * 解析：
     * 错并不是错误不允许这种赋值给变量的形式；
     * 参考 java.util.function.Predicate 可知，此处的 Predicate<Apple> 的 test 方法的签名签名为 "(Apple) -> boolean",
     * 而 Lambda表达式 "(Apple a) -> a.getWeight()" 的方法签名为 "(Apple) -> Integer"
     * 即 此时 函数描述符 与 要使用的 Lambda表达式 的 Lambda表达式签名 是  不一致的 ！！！
     */
}
