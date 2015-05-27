package step5

/**
 * Created by hjkwon on 15. 5. 26.
 */
sealed trait Stream[+A]
{
  def foldRight[B](z: => B)(f: (A, =>B) => B) : B =
    this match{
      case Cons(h,t) => f( h(), t().foldRight(z)(f) )
      case _ => z
    }

  def append[B>:A](s: => Stream[B]): Stream[B] =
    foldRight(s)((h,t) => Stream.cons(h,t))

  def headOption[A] = this match
  {
    case Empty => None
    case Cons(h,t) => Some(h())
  }

  def map[B](f: A => B):Stream[B] =
    foldRight(Stream.empty[B])( (h,t) => Stream.cons(f(h),t) )

  def filter(f:A=> Boolean):Stream[A] =
  foldRight(Stream.empty[A])((h,t) =>
    if(f(h))
      Stream.cons(h,t)
    else t)


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

  def takeWhile(p:A => Boolean):Stream[A] = this match{
    case Cons(h,t) if p(h())=> Stream.cons(h(),t().takeWhile(p))
   // case Cons(h,t) => t().takeWhile(p)
    case _ => Stream.empty
  }

  def takeWhile_1(f: A => Boolean): Stream[A] =
    foldRight(Stream.empty[A])((h,t) =>
      if (f(h)) Stream.cons(h,t)
      else      Stream.empty)


  def forAll(p: A => Boolean):Boolean = this match{
    case Cons(h,t) => p(h()) && t().forAll(p)
    case _ => true
  }

  def forAll1(p: A => Boolean):Boolean =
  foldRight(true)((h,t) => p(h) && t  )



//  ??????????? what is case
//  val fibsViaUnfold =
//    Stream.unfold((0,1)) { case (f0,f1) => Some((f0,(f1,f0+f1))) }


  def tails: Stream[Stream[A]] = {
    Stream.unfold(this) {
      case Empty => None
      case s => Some((s, s drop 1))
    } append Empty

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

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] =
    f(z) match {
      case Some((h,s)) => cons(h, unfold(s)(f))
      case None => empty
    }

  def empty[A]:Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty else cons(as.head,apply(as.tail: _*))

  def from(n:Int):Stream[Int] ={
    Stream.cons(n,from(n+1))
  }


}
