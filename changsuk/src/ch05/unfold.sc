import ch05.{Cons, Empty, Stream}

// from 11 (copied)
def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = {
  f(z) match {
    case None => Stream.empty[A]
    case Some((a, s)) => Stream.cons(a, unfold(s)(f))
  }
}

// 12-1
def one: Stream[Int] = {
  unfold(1)(_ => Some((1, 1)))
}

// 12-2
def constant[A](a: A): Stream[A] = {
  unfold(a)(_ => Some((a, a)))
}

constant(1).take(7).toList

// 12-3
def from(n: Int): Stream[Int] = {
  unfold(n)(n => Some((n, n + 1)))
}

from(10).take(7).toList

// 12-4
def fibs(): Stream[Int] = {
  unfold((0, 1)) {
    case (a, b) => Some(a, (b, a + b))
  }
}

fibs().take(10).toList

val a = Stream(1, 2, 3, 4, 5)

// 13-1
def map[A, B](s: Stream[A])(f: A => B): Stream[B] = {
  unfold[B, Stream[A]](s) {
    case Cons(h, t) => Some(f(h()), t())
    case _ => None
  }
}

map(a)(_ * 2).toList

// 13-2
def take[A](s: Stream[A])(n: Int): Stream[A] = {
  unfold((s, n)) {
    case (Cons(h, t), m) if m > 0 => Some(h(), (t(), m - 1))
    case _ => None
  }
}

take(a)(2).toList
take(a)(100).toList

// 13-3
def takeWhile[A](s: Stream[A])(p: A => Boolean): Stream[A] = {
  unfold(s) {
    case Cons(h, t) if p(h()) => Some(h(), t())
    case _ => None
  }
}

takeWhile(a)(_ < 3).toList
takeWhile(a)(_ > 10).toList

val b = Stream(1, 2, 3)
// 13-4
def zipWith[A](a: Stream[A], b: Stream[A])(f: (A, A) => A): Stream[A] = {
  unfold((a, b)) {
    case (_, Empty) => None
    case (Empty, _) => None
    case (Cons(ah, at), Cons(bh, bt)) => Some(f(ah(), bh()), (at(), bt()))
  }
}

zipWith(a, b)(_ * _).toList


// 13-5
def zipAll[A, B](s1: Stream[A], s2: Stream[B]) = {
  unfold((s1, s2)) {
    case (Cons(ah, at), Empty) => Some((Some(ah()), Option.empty[B]), (at(), Stream.empty[B]))
    case (Empty, Cons(bh, bt)) => Some((Option.empty[A], Some(bh())), (Stream.empty[A], bt()))
    case (Cons(ah, at), Cons(bh, bt)) => Some((Some(ah()), Some(bh())), (at(), bt()))
    case _ => None
  }
}

zipAll(a, b).toList

