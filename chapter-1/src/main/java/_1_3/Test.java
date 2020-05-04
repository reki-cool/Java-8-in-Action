package _1_3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java8的流式API（Stream API）顺序处理 和 并行处理
 */
class Apple {
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

/**  

* 传递代码：利用谓词传递代码，来筛选苹果

* @author duyanhan  

* @date 2020年4月30日  

*/
public class Test {
	
	
	public static void main(String[] args) {
		// 通过这种方式筛选苹果，如果很多个条件需要很多个筛选方法，并且每个筛选方法内容大量重复
		List<Apple> apples = new ArrayList<>();
		apples.add(new Apple("green", 100));
		apples.add(new Apple("green", 150));
		apples.add(new Apple("red", 100));
		apples.add(new Apple("red", 150));

		// ----------------------------------------------------Java8的流式API（Stream API）顺序处理-----------------------------------------------
		// 筛选重量超过150的苹果
		List<Apple> heavyApples = apples
				.stream()
				.filter((Apple apple) -> apple.getWeight() > 150)
				.collect(Collectors.toList());
		
		// ----------------------------------------------------Java8的流式API（Stream API）并行处理-----------------------------------------------
		// 筛选重量超过150的苹果
		heavyApples = apples
				.parallelStream()
				.filter((Apple apple) -> apple.getWeight() > 150)
				.collect(Collectors.toList());

		// 注意点：这里为什么filter方法可以直接传递一个Lambda（匿名函数），
		// 因为filter的具体定义为 Stream<T> filter(Predicate<? super T> var1);参数是一个谓词
		// 使用的Predicate为import java.util.function.Predicate;

		// 小总结：并行处理利用了多核CPU的优势（真正意义上的并行），它的处理速度比多线程的并发要快
	}
}
