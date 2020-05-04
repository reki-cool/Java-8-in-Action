package _2_3._2_3_4;

import java.util.ArrayList;
import java.util.List;

/**
 * 行为参数化的大前提下使用Lambda表达式传参并对List类型抽象化突破类型限制
 * 优点：突破只是对Apple类进行筛选的限制，如此就可以用于其他类型的筛选，例如香蕉、梨子、数值等等等等
 */
class Apple{
    private String color;
    private Integer weight;

    /**
     * 无参构造
     */
    public Apple() {
        super();
    }
    /**
     * @param color
     * @param weight
     */
    public Apple(String color, Integer weight) {
        super();
        this.color = color;
        this.weight = weight;
    }
    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }
    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }
    /**
     * @return the weight
     */
    public Integer getWeight() {
        return weight;
    }
    /**
     * @param weight the weight to set
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}

interface ApplePredicate<T>{
    boolean test (T t);
}

public class Filtering<T> {
    // 静态泛型方法需要泛化，参考：https://blog.csdn.net/ruan130/article/details/23607571
    public static <T> List<T> filter(List<T> list, ApplePredicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t: list) {
            // 注意这行代码
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", 100));
        apples.add(new Apple("green", 150));
        apples.add(new Apple("red", 100));
        apples.add(new Apple("red", 150));

        // 筛选绿苹果
        List<Apple> greenApples = filter(apples, (Apple apple) -> "green".equals(apple.getColor()));

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);

        // 筛选偶数
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
    }
}
