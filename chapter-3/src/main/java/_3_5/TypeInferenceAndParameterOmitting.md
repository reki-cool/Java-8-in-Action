## Lambda类型推断和参数类型省略
### 编译器会自动根据上下文的目标类型推断出适合Lambda的签名  
目的是：这样编译器就能知道Lambda的参数类型，这样在写Lambda时也就可以省略掉Lambda中的参数类型
```text
// 写上参数类型：没有类型推断
Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
// 不写参数类型：自动进行类型推断
Comparator<Apple> c = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());
```
### 当Lambda只有一个参数的时候，因为省略了参数类型，括号也可以省略
```text
Predicate<Apple> p = a -> a.getWeight() > 150;
```

## Lambda捕获的 局部变量 必须必须必须是final的！！！
即使这个局部变量没有被final修饰，那他也只能在第一次声明时，直接被初始化赋值，然后在lambda捕获之后，不能再发生修改。
### final变量
除了一开始被初始化赋值，后续不会再对其进行任何修改的变量
### 捕获
捕获是指在Lambda的主体内部使用不属于参数的外部变量
### 错误案例（在方法中进行如下使用）
```text
// 方法中定义如下代码，此时portNumber为局部变量
int portNumber = 1337;
Runnable r = () -> System.out.println(portNumber);
portNumber = 31337; 
```
## Lambda对于 实例变量 和 静态变量 可以无限制捕获
思考一下，很好理解
Lambda捕获实例变量之所以能够自由捕获，是因为这些实例变量都属于this的属性，  
实际上Lambda还是有且只有一次的捕获了当前实例对象（this），所以能够无限制捕获该实例对象下的各个实例成员变量     
对于静态变量同理。Lambda有且只有一次的捕获了当前类实例，所以能够无限制捕获该类实例下的各个类静态成员变量  

## 实例变量与局部变量的区别
前者是保存在堆中的，后者局部变量是保存在栈中的；  
如果Lambda可以直接访问局部变量，而且Lambda是在一个线程中使用的，则使用Lambda的线程，可能会在分配该变量的“亲爹”线程将这个变量收回之后，去访问该变量。  
这样肯定不行！！！！

## 总结：可以用lambda捕获外部的变量（实例和静态变量），但不推荐去修改他们，因为会阻碍并行处理的执行