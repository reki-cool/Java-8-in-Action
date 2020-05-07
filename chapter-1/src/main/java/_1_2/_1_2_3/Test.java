package _1_2._1_2_3;

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

		// ----------------------------------------------------新颖的判断方式：利用谓词来传递isXXXApples方法中的代码（本质上还是传递方法）-----------------------------------------------
		// 筛选绿苹果
		List<Apple> greenApples = Apple.filterApples(apples, Apple::isGreenApple);
		// 筛选重量超过150的苹果
		List<Apple> heavyApples = Apple.filterApples(apples, Apple::isHeavyApple);
		
		// ----------------------------------------------------用传递Lambda（匿名函数）的方式--------------------------------------
		// 因为Apple类中的isGreenApple、isHeavyApple这样的判断函数本身就只有一行，非常简单，且只需要使用一次，那么就没有必要去专门定义
		// 可以直接写成如下形式
		// 筛选绿苹果
		greenApples = Apple.filterApples(apples, (Apple apple) -> "green".equals(apple.getColor()));
		// 筛选重量超过150的苹果
		heavyApples = Apple.filterApples(apples, (Apple apple) -> apple.getWeight() > 150);
		// 注意，这里也是一样地利用了谓词来传递代码，这次传递的是Lambda（匿名函数），而不是传递方法引用了；
		// "（Apple apple)" 相当于要传递的方法引用的参数列表，"->"后面跟随的是只需要使用一次的判断条件

		// 小总结：传递Lambda（匿名函数）的方式相比传递方法引用，就有点像将“方法”的核心代码直接单独拿出来传递


		/**
		 * 	这里可以参考  “3.2.2函数描述符” 将 Lambda表达式 赋值给一个签名相同的函数式接口变量
		 */
	}
}
