package ch05

sealed trait Stream[+A] {
  /*
  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h())
  }
  */

  // 1
  def toList: List[A] = this match {
    case Empty => List()
    case Cons(h, t) => h() :: t().toList
  }

  // 2-1
  /*
  def take(n: Int): Stream[A] = {
    if (n == 0) Empty
    else this match {
      case Empty => Empty
      case Cons(h, t) => Cons(h, () => t().take(n - 1))
    }
  }
  */

  // 2-2
  def drop(n: Int): Stream[A] = {
    if (n == 0) this
    else this match {
      case Empty => Empty
      case Cons(h, t) => t().drop(n - 1)
    }
  }

  // 3
  /*
  def takeWhile(p: A => Boolean): Stream[A] = this match {
    case Empty => Empty
    case Cons(h, t) => {
      if (p(h())) Cons(h, () => t().takeWhile(p))
      else Empty
    }
  }
  */

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Cons(h, t) => f(h(), t().foldRight(z)(f))
    case _ => z
  }

  def exists(p: A => Boolean): Boolean = {
    foldRight(false)((a, b) => p(a) || b)
  }

  // 4
  def forAll(p: A => Boolean): Boolean = {
    foldRight(true)((a, b) => p(a) && b)
  }

  // 5
  /*
  def takeWhile(p: A => Boolean): Stream[A] = {
    foldRight(Stream.empty[A])((a, b) => {
      if(p(a)) Stream.cons(a, b.takeWhile(p))
      else Empty
    })
  }
  */

  // 6
  def headOption: Option[A] = {
    foldRight(None: Option[A])((a, b) => Some(a))
  }

  // 7-1
  /*
  def map[B](f: A => B): Stream[B] = {
    foldRight(Stream.empty[B])((a, b) => Stream.cons(f(a), b))
  }
  */

  // 7-2
  def filter(p: A => Boolean): Stream[A] = {
    foldRight(Stream.empty[A])((a, b) => {
      if (p(a)) Stream.cons(a, b.filter(p))
      else b.filter(p)
    })
  }

  // 7-3
  def append[B >: A](s: => Stream[B]): Stream[B] = {
    foldRight(s)((a, b) => Stream.cons(a, b))
  }

  // 7-4
  def flatMap[B](f: A => Stream[B]): Stream[B] = {
    foldRight(Stream.empty[B])((a, b) => f(a).append(b))
  }

  // 13-1
  def map[B](f: A => B): Stream[B] = {
    Stream.unfold[B, Stream[A]](this) {
      case Cons(h, t) => Some(f(h()), t())
      case _ => None
    }
  }

  // 13-2
  def take(n: Int): Stream[A] = {
    Stream.unfold((this, n)) {
      case (Cons(h, t), m) if m > 0 => Some(h(), (t(), m - 1))
      case _ => None
    }
  }

  // 13-3
  def takeWhile(p: A => Boolean): Stream[A] = {
    Stream.unfold(this) {
      case Cons(h, t) if p(h()) => Some(h(), t())
      case _ => None
    }
  }

  // 13-4
  def zipWith[B >: A](s: Stream[B])(f: (B, B) => B): Stream[B] = {
    Stream.unfold((this, s)) {
      case (_, Empty) => None
      case (Empty, _) => None
      case (Cons(ah, at), Cons(bh, bt)) => Some(f(ah(), bh()), (at(), bt()))
    }
  }

  // 13-5
  def zipAll[B](s: Stream[B]): Stream[(Option[A], Option[B])] = {
    Stream.unfold((this, s)) {
      case (Cons(ah, at), Empty) => Some((Some(ah()), Option.empty[B]), (at(), Stream.empty[B]))
      case (Empty, Cons(bh, bt)) => Some((Option.empty[A], Some(bh())), (Stream.empty[A], bt()))
      case (Cons(ah, at), Cons(bh, bt)) => Some((Some(ah()), Some(bh())), (at(), bt()))
      case _ => None
    }
  }

  // 14
  def startsWith[B >: A](s: Stream[B]): Boolean = (this, s) match {
    case (_, Empty) => true
    case (Empty, _) => false
    case (Cons(h1, t1), Cons(h2, t2)) => h1() == h2() && t1().startsWith(t2())
  }

  // 15
  def tails: Stream[Stream[A]] = {
    Stream.unfold(this) {
      case s@Cons(h, t) => Some(s, t())
      case _ => None
    }
  }

  def hasSubsequence[B >: A](s: Stream[B]): Boolean = {
    tails exists (_ startsWith s)
  }
}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  // 11
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = {
    println("fold!") // to watch lazy application
    f(z) match {
      case None => Stream.empty[A]
      case Some((a, s)) => Stream.cons(a, unfold(s)(f))
    }
  }

  def apply[A](as: A*): Stream[A] = {
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
  }
}