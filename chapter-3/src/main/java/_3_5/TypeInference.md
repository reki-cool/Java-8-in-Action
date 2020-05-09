## 编译器会自动根据上下文的目标类型推断出适合Lambda的签名  
目的是：这样编译器就能知道Lambda的参数类型，这样在写Lambda时也就可以省略掉Lambda中的参数类型
```text
// 写上参数类型：没有类型推断
Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
// 不写参数类型：自动进行类型推断
Comparator<Apple> c = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());
```
## 当Lambda只有一个参数的时候，因为省略了参数类型，括号也可以省略
```text
Predicate<Apple> p = a -> a.getWeight() > 150;
```