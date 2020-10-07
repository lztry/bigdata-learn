package com.lz.bigdata.scala

object Test {
  /**
   * lambda表达式初探
   * 分析lambda表达式形式和省略形式
   * scala中,函数可以作为变量,允许方法的类型是函数类型
   * scala中使用 val 语句可以定义函数，def 语句定义方法
   * 方法和函数的区别
   * https://www.cnblogs.com/fangwencai/p/4859662.html
   */
  //普通方法
  def foo(s:String)={println(s+"12")}
  //高阶方法
  def hf(f:String=>Unit)=f("higher")
  var f:String=>Unit = foo
  var fun=(x:Int)=>x+10
  def testify(f:(String,Int)=>String): String = f("higher",123)
  def main(args: Array[String]): Unit = {
    //f作为参数传递
    hf(f);
    //lambda
    hf((s: String) => println(s))
    //简化1,省略了s的类型,定义hf的时候指定了类型,编译器会根据声明的类型来对待
    hf(s => println(s+"简化1"))
    // 简化2,采用了占位符,对于所有的x=g(x)都可以写成g(_),省略了入参和箭头.占位符表示入参
    hf(println(_))
    var words=Set("hive","redis");
    println(words.flatten)
    println(words.map(_+1))

  }

}
