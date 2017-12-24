package com.quickn

/**
  * Created by json2 on 2017/12/24.
  */
object Function {


  def main(args: Array[String]): Unit = {
    delayed(time());
    delay(time())
    printInt(b = 100, a = 99)
    printInt();
    printStrings("a", "b")

    var x = add2(100) + 1;
    println("x" + x)

    var intc = (x: Int) => x + 1;
    println(intc(100) + 1);

    println(matchTest(100, 100));

    collectionTest();

  }


  def collectionTest(): Unit = {

    var list = List(1, 2, 3, 4);
    var set = Set(1, 2, 3, 4);
    var map = Map("key1" -> 1, "key2" -> 2, "key3" -> 3);
    var tuple = new Tuple4(100, "str","dd","str");
    var option: Option[Int] = Some(5);
    map.foreach{
      case (key,value) =>
        println("key:"+key+" value:"+value)
    };
    println(tuple._1);
    println(tuple._2);

  }


  def arrayTest(): Unit = {
    var array = Array("a", "b", "c");
    for (str <- array) {
      println(str);
    }
  }


  //匿名函数
  def add2 = new Function[Int, Int] {
    def apply(x: Int): Int = x + 1;
  }

  def time(): Long = {
    println("时间")
    //System.nanoTime();
    return 300 + 400;
  }

  // => 符号来设置传名调用 将未计算的参数表达式直接应用到函数内部
  def delayed(t: => Long) = {
    println("delayed");
    println("t" + t);
  }

  def delay(t: Long) = {
    println("delay");
    println("t" + t);
  }

  //指定函数参数名
  def printInt(a: Int = 5, b: Int = 7): Unit = {
    println("a:" + a + " b:" + b);
  }

  def printStrings(args: String*): Unit = {
    var i = 0;
    for (arg <- args) {
      println("i:" + i + " arg" + arg);
      i += 1;
    }
    var list = List(343, 334);
    for (int <- list) {
      println(int);
    }
  }

  def matchTest(x: Int, y: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }

  //高阶函数 操作其他函数的函数。

  // 函数 f 和 值 v 作为参数，而函数 f 又调用了参数 v
  def apply(f: Int => String, v: Int) = f(v)

  def layout[A](x: A) = "[" + x.toString() + "]"


}
