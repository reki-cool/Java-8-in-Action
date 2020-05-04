package _2_3._2_3_2;

import java.util.ArrayList;
import java.util.List;

/**
 * 行为参数化的大前提下使用匿名内部类传参
 * 缺点：代码不易读，维护麻烦
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
        List<Apple> greenApples = filterApples(apples, new ApplePredicate(){
            @Override
            public boolean test(Apple apple) {
                return "green".equals(apple.getColor());
            }
        });
        // 筛选重量超过150的苹果
        List<Apple> heavyApples = filterApples(apples, new ApplePredicate(){
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() > 150;
            }
        });
    }
}
