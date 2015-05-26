package step5

/**
 * Created by hjkwon on 15. 5. 26.
 */
object main extends App{
  val testStream = Stream(1,2,3,4,5,6,7,8,9,10)
  val ones:Stream[Int] = Stream.cons(1,ones)
  val evaltest1 = Stream(eval(1),eval(2),eval(3))
  val evaltest2:Stream[eval] = Stream.cons(eval(2),evaltest2)

  val test = Stream.unfold(1)( x => Option(x,x*2))
  println(test.take(5).toList)
}


case class eval(a:Int){
  println("EVAL"+a)
}
