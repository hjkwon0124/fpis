package step6

/**
 * Created by hjkwon on 15. 6. 4.
 */
object step6_main extends App{
  val b=  new SimpleRNG(2)
  val c= new SimpleRNG(3)
//  val a = SimpleRNG(40).nonNegativeInt(b)
//  val c = SimpleRNG(20).nonNegativeEven
//  val d = SimpleRNG(20).nonNegativeEven(b)

  println(b._ints(5)(c))

  println(List(1)::List(4))
//
//  println(b.ints(5)(c))
//  println(c)

}
