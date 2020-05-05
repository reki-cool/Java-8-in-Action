package _3_2._3_2_1;

/**
 * 在函数式接口上使用Lambda表达式
 */
public class WhereToUseLambda {
    /**
     * 什么是函数式接口？ 有且仅有一个抽象方法的接口！！！  注意“有且仅有一个”这六个字
     * 现在很多接口中定义了默认方法（default开头的已默认实现的方法），这个无论有几个，都不影响接口是否是函数式接口
     */


    /**
     * Java API 中几个常见的函数式接口
     *
     * // java.util.Comparator
     * public interface Comparator<T> {
     *     int compare(T o1, T o2); // 这个就是 java.util.Comparator 接口中的唯一的抽象方法（未具体实现的方法，即只有方法定义）
     * }
     *
     * // java.lang.Runnable
     * public interface Runnable {
     *     void run(); // 这个就是 java.lang.Runnable 接口中的唯一的抽象方法（未具体实现的方法，即只有方法定义）
     * }
     *
     * // java.awt.event.ActionListener
     * public interface ActionListener extends EventListener {
     *     void actionPerformed(ActionEvent e); // 这个就是 java.awt.event.ActionListener 接口中的唯一的抽象方法（未具体实现的方法，即只有方法定义）
     * }
     * 注意：这个 ActionListener 虽然继承了 EventListener 接口，但是除了 ActionListener 接口本身的 actionPerformed 这个抽象方法以外，父接口中并没有其他的抽象方法被继承过来，所以仍然只有一个抽象方法
     * 否则，如果除了 actionPerformed 这个抽象方法，另外还继承了别的抽象方法，那么 ActionListener 就不再是函数式接口了
     *
     * // java.util.concurrent.Callable
     * public interface Callable<V> {
     *     V call(); // 这个就是 java.util.concurrent.Callable 接口中的唯一的抽象方法（未具体实现的方法，即只有方法定义）
     * }
     *
     * // java.security.PrivilegedAction
     * public interface PrivilegedAction<V> {
     *     V run(); // 这个就是 java.security.PrivilegedAction 接口中的唯一的抽象方法（未具体实现的方法，即只有方法定义）
     * }
     */
}
