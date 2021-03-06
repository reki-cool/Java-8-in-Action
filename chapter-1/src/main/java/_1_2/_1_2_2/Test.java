package _1_2._1_2_2;

import java.util.ArrayList;
import java.util.List;

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
	// ----------------------------------------------------古老的判断方式：根据不同的条件写很多个filterXXXApples方法-----------------------------------------------
	/**
	 * 筛选颜色是绿色的苹果
	 * @param inventory
	 * @return
	 */
	public static List<Apple> filterGreenApples(List<Apple> inventory){
		List<Apple> result = new ArrayList<>();
		for (Apple apple: inventory){
			if ("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	/**
	 * 筛选重量大于150的苹果
	 * @param inventory
	 * @return
	 */
	public static List<Apple> filterHeavyApples(List<Apple> inventory){
		List<Apple> result = new ArrayList<>();
		for (Apple apple: inventory){
			if (apple.getWeight() > 150) {
				result.add(apple);
			}
		}
		return result;
	} 
	// ----------------------------------------------------新颖的判断方式：利用谓词来传递isXXXApples方法中的代码（本质上还是传递方法）-----------------------------------------------
	/**
	 * 筛选颜色是绿色的苹果
	 * @param apple
	 * @return
	 */
	public static boolean isGreenApple(Apple apple) {
		return "green".equals(apple.getColor());
	}
	/**
	 * 筛选重量大于150的苹果
	 * @param apple
	 * @return
	 */
	public static boolean isHeavyApple(Apple apple) {
		return apple.getWeight() > 150;
	}
	
	/**
	 * 内部接口：predicate(谓词)，数学上通常用来代表一个类似函数的东西，它接收一个参数值，并返回true或者false
	 */
	public interface Predicate<T> {
		boolean test(T t);
	}
	
	/**
	 * 需要借助谓词来过滤苹果的方法
	 * @param inventory
	 * @param p
	 * @return
	 */
	static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple: inventory) {
			// 注意这行代码
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
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
		// ----------------------------------------------------古老的判断方式：根据不同的条件写很多个filterXXXApples方法-----------------------------------------------
		// 筛选绿苹果
		List<Apple> greenApples = Apple.filterGreenApples(apples);
		// 筛选重量超过150的苹果
		List<Apple> heavyApples = Apple.filterHeavyApples(apples);
		// 使用这种方式来筛选苹果，需要根据不同的条件写很多个 filterXXXApples 的筛选方法，并且这些方法除了核心判断条件，其它内容都是重复的，非常不好
		
		// ----------------------------------------------------新颖的判断方式：利用谓词来传递isXXXApples方法中的代码（本质上还是传递方法）-----------------------------------------------
		// 筛选绿苹果
		greenApples = Apple.filterApples(apples, Apple::isGreenApple);
		// 筛选重量超过150的苹果
		heavyApples = Apple.filterApples(apples, Apple::isHeavyApple);
		
		// 使用这种方式来筛选苹果，就不用写很多个 filterXXXApples 的筛选方法了;
		// 只需要根据条件定义不同的 isXXXApple 方法，然后将它们的内部代码通过谓词的形式传递进去判断;

		// 仍然有疑惑，可以参考：https://github.com/reki-cool/Java-8-in-Action/tree/master/chapter-1/src/main/java/_1_2 下的README.md文档


		/**
		 *  这里 isGreenApple 和 isHeavyApple 方法为什么能够作为参数传递给Predicate<Apple> p 这个参数？
		 *     因为这两个方法接受Apple参数，并返回一个 boolean（true/false) 给 filterApples
		 *     而谓词 Predicate<Apple> p 这里也是接受Apple参数，并返回一个 boolean（true/false) 给 filterApples
		 *     虽然使用 filterApples(List<Apple> inventory, Boolean isCondition) 看起来似乎简单明了
		 *     但是使用谓词 filterApples(List<Apple> inventory, Predicate<Apple> p)  更标准
		 */

		/** 如果比较难以理解
		 * 	这里可以参考		3.2.2函数描述符 将 Lambda表达式 赋值给一个签名相同的函数式接口变量
		 * 	还可以参考		3.3 环绕执行模式下：利用Lambda表达式让执行行为多样化
		 *
		 * 	这里虽然不是使用Lambda表达式，但是方法引用也是相似的东西，
		 * 	都是传递行为，只不过方法引用使用的前提还是需要额外定义方法，
		 * 	对于一些永久只用一次的行为，单独定义为一个方法，实际上是一种浪费
		 */
	}
}
