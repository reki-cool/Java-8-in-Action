package _1_2._1_2_1;

import java.io.File;
import java.io.FileFilter;

/**  

* 将方法作为一等公民：将方法作为值传递给方法

* @author duyanhan  

* @date 2020年4月30日  

*/
public class Test {

	/**
	 * 筛选一个目录中的所有隐藏文件的两种写法比较
	 * @param args
	 */
	public static void main(String[] args) {
		
		// 传递对象引用
		File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.isHidden();
			}
		});
		
		// 传递方法引用
		// 只需用Java 8的方法引用::语法（即“把isHidden()这个方法作为值”）将其传给listFiles方法
		File[] hiddenFiles2 = new File(".").listFiles(File::isHidden);
		// 此时方法不再是二等值了，而是一等值。
		// 与用对象引用传递对象类似（对象引用是用new创建的），在Java 8里写下File::isHidden的时候，你就创建了一个方法引用，此时就是用方法引用来传递方法。
		
		// 上面两种写法效果一样，相比第一种，第二种更好理解，写起来也更简单。
	}

}
