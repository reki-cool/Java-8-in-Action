# 函数描述符兼容Lambda表达式的规则
## 同一个Lambda，可用于不同的函数式接口(只要签名与函数描述符一致即可)    
举例说明：  
```text
Callable<Integer> c = () -> 42;
PrivilegedAction<Integer> p = () -> 42; 
---
Comparator<Apple> c1 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
ToIntBiFunction<Apple, Apple> c2 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
BiFunction<Apple, Apple, Integer> c3 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()); 
```  
## 特殊的void兼容规则（此时Lambda签名与函数描述符只有参数列表一致，比较特殊）  
如果一个Lambda的主体是一个语句表达式，它就和一个返回void的函数描述符兼容（当然需要参数列表也兼容）。  
举例说明:
```text
Lambda: s -> list.add(s)  
Lambda签名为: (String) -> boolean;

// Predicate返回了一个boolean（此时是正常的兼容规则）
Predicate<String> p = s -> list.add(s);
// Consumer返回了一个void（此时使用的是特殊的void兼容规则）
Consumer<String> b = s -> list.add(s);
```