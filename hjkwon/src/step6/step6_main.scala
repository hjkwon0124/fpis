package step6

/**
 * Created by hjkwon on 15. 6. 4.
 */
object step6_main extends App{
  val b=  SimpleRNG(2)
//  val a = SimpleRNG(40).nonNegativeInt(b)
  val c = SimpleRNG(20).nonNegativeEven
  val d = SimpleRNG(20).nonNegativeEven(b)


  println(c)
  println(d)
}
