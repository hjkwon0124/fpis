package step10

/**
 * Created by hjkwon on 15. 8. 31.
 */
object monoid_main extends App{
   println("hello")

  val intAddition: Monoid[Int] = new Monoid[Int] {
    def op(a1:Int, a2:Int) = a1 + a2
    def zero = 0
  }

  val intMultiplication: Monoid[Int] = new Monoid[Int]{
    def op(a1:Int, a2:Int) = a1 * a2
    def zero = 1
  }


}
