import ch05._

// 8
def constant[A](a: A): Stream[A] = {
  Stream.cons(a, constant(a))
}

constant(1).take(7).toList

// 9
def from(n: Int): Stream[Int] = {
  Stream.cons(n, from(n + 1))
}

from(10).take(7).toList

// 10
def fibs(): Stream[Int] = {
  def next(first: Int, second: Int): Stream[Int] = {
    Stream.cons(first, next(second, first + second))
  }

  next(0, 1)
}

fibs().take(10).toList

// 11
def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = {
  f(z) match {
    case None => Stream.empty[A]
    case Some((a, s)) => Stream.cons(a, unfold(s)(f))
  }
}

unfold(1)(a => Some(a, a + 1)).take(7).toList

