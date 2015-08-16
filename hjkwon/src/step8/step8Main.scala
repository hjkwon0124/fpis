package step8

import step6.SimpleRNG

/**
 * Created by hjkwon on 15. 7. 27.
 */
object step8Main extends App{
  val gen1 = Gen.choose(1,100)
  println(gen1)
  println(gen1.sample)
  println(gen1 .sample.run(SimpleRNG(10)))
}
