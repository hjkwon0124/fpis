/**
 * Created by youshin on 15. 4. 28.
 */
object partialApplied_2_3 extends App {
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = {
    (a: A) => (b: B) => f(a, b)
  }
}