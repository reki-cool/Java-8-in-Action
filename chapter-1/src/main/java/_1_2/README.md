## 补充说明
 - 1.2.2 中 isGreenApple 和 isHeavyApple 方法为什么能够作为参数传递给Predicate<Apple> p 这个参数？  
    因为这两个方法接受Apple参数，并返回一个 boolean（true/false) 给 filterApples  
    而谓词 Predicate<Apple> p 这里也是接受Apple参数，并返回一个 boolean（true/false) 给 filterApples  
    虽然使用 filterApples(List<Apple> inventory, Boolean isCondition) 看起来似乎简单明了  
    但是 filterApples(List<Apple> inventory, Predicate<Apple> p)  这样写更标准
    