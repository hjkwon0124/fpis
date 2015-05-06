/**
 * Created by youshin on 15. 4. 28.
 */
object compose_2_5 extends App{
  def compose[A,B,C](f: B => C, g: A => B): A => C = {
    (a:A) => f(g(a))
  }

}


