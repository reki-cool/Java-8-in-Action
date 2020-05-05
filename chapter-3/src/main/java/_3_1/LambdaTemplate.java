package _3_1;

/**
 *  Lambda表达式定义 && 语法 && 常见Lambda表达式案例
 */
public class LambdaTemplate {
    /**
     * Lambda表达式是什么：是匿名函数
     * 说它匿名？因为它不像普通方法拥有一个明确的名称
     * 说它是函数？因为它不像方法属于一个特定的类，并且与方法一样拥有参数列表、函数主体、返回类型，还有可能拥有抛出的异常列表
     *
     * Lambda表达式（匿名函数）含有三个部分
     *      1、参数列表
     *      2、箭头    ->  用于分隔 参数列表 和 Lambda主体
     *      3、Lambda主体
     */

    /**
     * （String s) -> s.length();
     *
     * 此Lambda表达式具有一个String类型参数并返回一个int。
     * 注意：Lambda没有return语句，因为已经隐含了return
     *
     *
     * (Apple a) -> a.getWeight() > 150
     * 此Lambda表达式有一个Apple类型的参数并返回一个boolean（苹果的重量是否超过150克）
     *
     *
     * (int x, int y) -> {
     *     System.out.println("Result:");
     *     System.out.println(x + y);
     * }
     * 此Lambda表达式具有两个int类型的参数但没有返回值（void返回）。
     * 注意：Lambda表达式可以包含多行语句（使用花括号{}），这里是两行
     *
     *
     * () -> 42
     * 此Lambda表达式没有参数并返回一个int整数42
     *
     *
     * (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
     * 此Lambda表达式具有两个Apple类型参数并返回一个int：比较两个Apple的重量
     */

    /**
     * 总结：
     * Lambda表达式基本语法：
     * (parameters) -> expression            此时Lambda表达式主体只有单个语句，无需分号结尾，无需使用大括号括起来
     * 或者
     * (parameters) -> {expression;}         此时Lambda表达式主体可能有多个语句，只要使用了大括号括起来，就一定要使用分号作为语句结尾
     *
     */

    // ---------------------------------------------使用案例-------------------------------------

    /**
     *  1、布尔表达式
     *  (List<String> list) -> list.isEmpty()
     *
     *  2、创建对象（这里没有参数，说明使用的是等价于无参构造方法的匿名函数即Lambda表达式）
     *  () -> new Apple(10)
     *
     *  3、消费一个对象
     *  (Apple a) -> {
     *      System.out.println(a.getWeight());
     *  }
     *
     *  4、从一个对象中选择/抽取
     *  (String s) -> s.length()
     *
     *  5、组合两个值
     *  (int a, int b) -> a * b
     *
     *  6、比较两个对象
     *  (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
     */
}
