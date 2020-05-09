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

## 在Lambda的主体中使用外部变量限制是个final值  
被Lambda主体使用的变量必须必须显式声明为final或事实上是final（只有第一次初始化的时候被赋值，之后它的值永远都没被改变过，无论他是否有被final关键字给修饰过）。  
因为Lambda表达式只能捕获指派给它们的局部变量一次。

### 错误案例
```text
int portNumber = 1337;
Runnable r = () -> System.out.println(portNumber);
portNumber = 31337; 

在Lambda定义完后，又对被Lambda使用的这个变量进行重新赋值，说明这个变量不是final的。
```

### 正确案例（注意与上面对比）
```text
int portNumber = 1337;
Runnable r = () -> System.out.println(portNumber);
// 之后没有对portNumber修改，即它永远只被使用一次，相当于是个final变量

或

final int portNumber = 1337;
Runnable r = () -> System.out.println(portNumber);
// 相比上面只是多加个final，看起来更加明显

或

int port = 1337;
final int portNumber = port; // 或写成 int portNumber = port;
Runnable r = () -> System.out.println(portNumber);
port = 1000;
// 这里的port虽然被重复赋值，但是被lambda使用的portNumber永久只被赋值一次，后续再没被修改过。
```

### 总结
lambda使用其主体外的局部变量时，无论是实例变量（当前对象的变量）还是静态变量（当前类成员变量）或是方法内临时变量，  
**务必在声明此变量时，加上final关键词进行修饰（看起来更加明显突出！！！）**