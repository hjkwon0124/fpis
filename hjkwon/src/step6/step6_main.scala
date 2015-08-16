package step6

/**
 * Created by hjkwon on 15. 6. 4.
 */
object step6_main extends App{
  val b=  new SimpleRNG(2)
  val bb = new SimpleRNG(3)
//  val c= new SimpleRNG(3)
  val a = SimpleRNG(40).nonNegativeInt(b)
  val aa = SimpleRNG(40).nonNegativeInt(bb)
  val c = SimpleRNG(20).nonNegativeEven
//  val d = SimpleRNG(20).nonNegativeEven(b)
  println(a)
  println(aa)
//  b.map(n => n+1)
//  println(b._ints(5)(c))
//
//  println(List(1)::List(4))
//
//  println(b.ints(5)(c))
//  println(c)


  val stateTest = State(new SimpleRNG(100).nonNegativeInt).map(n => 1 + n % (100))
  println(stateTest)
  println(stateTest.run)
  println(stateTest.run(b))
}
