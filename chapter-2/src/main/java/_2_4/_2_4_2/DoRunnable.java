package _2_4._2_4_2;

/**
 * 行为参数化案例二：用Runnable执行代码块
 * 使用Runnable对象传递的代码参数化Thread类中run方法的行为
 */
public class DoRunnable {
    /**
     * // java.lang.Runnable
     * public interface Runnable{
     *  public void run();
     *  }
     * }
     */
    // Runnable接口中，有一个Run方法，通过实现Runnable接口并定义不同行为的run方法的类，就可以创建出执行行为不同的线程
    public static void main(String[] args) {

        // 使用匿名内部类写法
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world");
            }
        });

        // 使用Lambda表达式写法
        t = new Thread(() -> System.out.println("Hello World"));
    }
}
