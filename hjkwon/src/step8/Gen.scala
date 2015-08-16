package step8

import step6.{State, RNG, SimpleRNG}

/**
 * Created by hjkwon on 15. 7. 27.
 */
case class Gen[A](sample : State[RNG,A]) {

}

object Gen{
  def unit[A](a: => A): Gen[A] =
  Gen(State.unit(a))
//  Gen(State( SimpleRNG(10).unit(a)))


  def choose(start:Int, stopExclusive: Int) : Gen[Int] =
   Gen(State(SimpleRNG(10).nonNegativeInt).map(n => start + n % (stopExclusive - start)))
}