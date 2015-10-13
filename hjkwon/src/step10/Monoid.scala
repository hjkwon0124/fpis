package step10

/**
 * Created by hjkwon on 15. 8. 31.
 */
trait Monoid[A] {
  def op(a1:A, a2 :A) :A
  def zero:A
}

trait Foldable[F[_]]{
    def foldRight[A,B](as:F[A])(z:B)(f: (A,B) => B ):B

}



object Monoid{
  def concatenate[A](as:List[A], m:Monoid[A]) :A =
    as.foldLeft(m.zero)(m.op)

  def foldMap[A,B](as: List[A], m : Monoid[B])(f: A=>B) : B =
  as.foldLeft(m.zero)((b,a) => m.op(b,f(a)))
}

