package _3_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

/**
 * JAVA API 中几个常见的函数式接口用法
 */
public class CommonFunctionalInterfacesInJavaAPI {
    // --------------------------------------------------------------

    /**
     * java.util.function.Predicate<T>
     * Predicate 谓词
     * 谓词的函数描述符：(T) -> boolean
     */
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T s : list) {
            if (p.test(s)) {
                results.add(s);
            }
        }
        return results;
    }

    // --------------------------------------------------------------

    /**
     * java.util.function.Consumer<T>
     * Consumer 消费者
     * 消费者的函数描述符：(T) -> void
     */
    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T i : list) {
            c.accept(i);
        }
    }

    // --------------------------------------------------------------

    /**
     * java.util.function.Function<T, R>
     * Function 函数
     * 函数的函数描述符：(T) -> R
     */
    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }

    public static <T, R> List<R> map(List<T> list,
                                     Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }

    // --------------------------------------------------------------


    public static void main(String[] args) {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("a");
        listOfStrings.add("b");
        listOfStrings.add("");


        // 谓词使用
        // 定义一个谓词行为：字符串不可以为空的行为
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);

        Predicate<Integer> evenInteger = (Integer i) -> i % 2 == 0;
        // 注意这里直接调用谓词的test方法，其它的函数式接口也可以如此使用
        boolean even = evenInteger.test(10000);    // 这里进行了装箱|操作  10000(int) -> Integer(10000)，即 原始类型的特化
        System.out.println(even); // true


        IntPredicate oddInteger = (int i) -> i % 2 == 1;
        // 注意这里直接调用谓词的test方法，其它的函数式接口也可以如此使用
        boolean odd = oddInteger.test(10000);    // 这里没有进行装箱操作  10000 本就是 int 原始类型
        System.out.println(odd); // false



        // 消费者使用
        forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));

        // 函数使用
        List<Integer> lengthList = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());
    }
}
