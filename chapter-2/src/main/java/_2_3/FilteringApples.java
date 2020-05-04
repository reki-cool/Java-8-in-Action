package _2_3;

import java.util.ArrayList;
import java.util.List;

/**
 * 行为参数化：filterApples方法的行为取决于通过ApplePredicate对象传递的代码，即把filterApples方法的行为参数化了
 * 行为参数化的两点：1、传递代码（行为）；2、可以设定多种行为，只需要传给同一个参数
 * 采用的设计模式：策略设计模式
 * 利用谓词筛选苹果
 * 简易程度：有点麻烦，不得不声明好几个实现ApplePredicate接口的类，然后再实例化并传递给filterApples方法
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
interface ApplePredicate{
    boolean test (Apple apple);
}
class AppleGreenColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
class AppleHeavyWeightPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
public class FilteringApples {
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            // 注意这行代码
            if (p.test(apple)) {
                result.add(apple);
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
        List<Apple> greenApples = filterApples(apples, new AppleGreenColorPredicate());
        // 筛选重量超过150的苹果
        List<Apple> heavyApples = filterApples(apples, new AppleHeavyWeightPredicate());
    }
}
