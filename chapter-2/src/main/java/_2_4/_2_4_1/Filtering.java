package _2_4._2_4_1;

import java.util.ArrayList;
import java.util.List;

/**
 * 行为参数化案例一：用ComParator排序
 * 案例详情：使用java.util.Comparator对象传递的代码来参数化List集合中的sort方法的行为
 * 看一下java.util.Comparator接口
 * public interface Comparator<T> {
 *     public int compare(T o1, T o2);
 * }
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

public class Filtering<T> {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", 50));
        apples.add(new Apple("green", 150));
        apples.add(new Apple("red", 200));
        apples.add(new Apple("red", 250));

        // 按照重量升序对库存进行排序
        apples.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
    }
}
