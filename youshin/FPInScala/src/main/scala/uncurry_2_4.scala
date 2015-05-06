/**
 * Created by youshin on 15. 4. 28.
 */
object uncurry_2_4 extends App{
  def uncurry[A,B,C](f: A => B => C): (A, B) => C = {
    (a:A, b:B) => f(a)(b)    
  }
}
