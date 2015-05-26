package step5

/**
 * Created by hjkwon on 15. 5. 26.
 */
sealed trait Stream[+A]
{
  def headOption[A] = this match
  {
    case Empty => None
    case Cons(h,t) => Some(h())
  }

  def exists(p:A => Boolean) : Boolean = this match {
    case Cons(h,t) => p(h()) || t().exists(p)
    case _ => false
  }

  def toList:List[A] = this match{
    case Cons(h,t) => h() :: t().toList
    case _ => List()
  }

  def take(n:Int):Stream[A] = this match{
    case Cons(h,t) if n > 0 => Stream.cons(h(),t().take(n-1))
    case _ => Empty
  }

  //  def take(n: Int): Stream[A] = this match {
  //    case Cons(h, t) if n > 1 => Stream.cons(h(), t().take(n - 1))
  //    case Cons(h, _) if n == 1 => Stream.cons(h(), Stream.empty)
  //    case _ => Stream.empty
  //  }


  def drop(n:Int):Stream[A] = this match{
    case Cons(h,t) if n > 0 => t().drop(n-1)
    case _ => this
  }

}
case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]):Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons( () => head, () => tail )
  }

  def empty[A]:Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty else cons(as.head,apply(as.tail: _*))

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] =
    f(z) match {
      case Some((h,s)) => cons(h, unfold(s)(f))
      case None => empty
    }
}
