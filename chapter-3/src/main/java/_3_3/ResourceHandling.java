package _3_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 环绕执行模式下：利用Lambda表达式让执行行为多样化
 * 此处，以资源处理为例，并不限制于资源处理
 */
public class ResourceHandling {
    public static final String FILE_PATH = "D:\\Users\\Downloads\\Java-8-in-Action\\chapter-3\\src\\main\\java\\_3_3\\ResourceHandling.java";

//-------------------------------------------------最最古老的方式---------------------------------------------------
    /**
     * 先看看最古老的资源处理方式（JDK7 之前资源的关闭姿势，且如果是单个资源的情况）
     */
    public static String processFile1() throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FILE_PATH));
            // ... 对br的各种使用
            return br.readLine();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    // 如果有多个资源的话，要想正确关闭则更为复杂和麻烦
    // 参考：https://blog.csdn.net/imasmallbird/article/details/3718973
//-------------------------------------------------次古老的方式---------------------------------------------------
    /**
     * JDK 7 之后资源的关闭姿势
     * 无论是否有多个资源，都是以下这种方式
     * 参考：https://juejin.im/post/5b8f9fa05188255c6f1df755
     */
    public static String processFile2() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));) {
            // ... 对br的各种使用
            return br.readLine();
        }
    }
//-------------------------------------------------新的方式---------------------------------------------------
    /**
     * 现在JDK8来了
     * 可以看到上面的processFile2方法中，从37 ~ 40 中，只有39行是核心代码，真正的核心代码，它的上下围绕着同样的一段打开和关闭资源的冗余代码
     * 所以可以考虑行为参数化，即需要[一种方法]把行为传递给processFile方法,
     * 以便它可以利用BufferedReader执行不同的行为
     * 既然是利用BufferReader，并且期望获得的是字符串，那么基本上就能确定表示这个行为的Lambda表达式的方法签名：(BufferedReader) -> String
     * 从而达到以下的效果：
     * String result = processFile((BufferedReader br) -> br.readLine());
     * 接下来我们就定义一下上述的“[一种方法]”，自然是通过定义一个函数式接口的方式，并且要求这个函数式接口的唯一抽象方法是可以抛出IOException的
     */
    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    /**
     * 接下来我们就只需要将这个接口，作为processFile方法的参数，就可以达到目的（用它的process方法把行为传递给processFile方法）
     * 并且在processFile方法中定义要执行这个行为（不论这个行为具体是怎样的）
     */
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            return p.process(br);
        }
    }
    /**
     * 之后，是需要通过传递Lambda表达式来使用它就行，具体的行为是怎样的，由Lambda表达式说了算
     */
    public static void main(String[] args) throws IOException{
        // 例如，我需要返回它的一行内容
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        // 例如，我需要返回它的三行内容
        String threeLines = processFile((BufferedReader br) -> br.readLine() + br.readLine() + br.readLine());

        System.out.println(oneLine);
        System.out.println(threeLines);
    }

    /**
     * 小总结（四个步骤）：
     * 1、观察原方法，查看他的核心代码是什么，如果要使之行为（核心代码的行为）参数化，需要一个怎样签名的Lambda表达式
     * 2、根据Lambda表达式的签名，定义一个符合该签名的函数式接口（准确来说是该接口中的抽象方法签名与Lambda表达式签名一致）
     * 3、改造原方法的参数列表，并在原方法中书写执行通用行为的语句，形成一个新的方法
     * 4、传递各种行为不同的Lambda表达式给新方法，以达到使用的目的
     */
}
