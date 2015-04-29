import ch03._

def zipWith[A](a: List[A], b: List[A])(f: (A, A) => A): List[A] = (a, b) match {
  case (Nil, Nil) => Nil
  case (Cons(_, _), Nil) => a
  case (Nil, Cons(_, _)) => b
  case (Cons(x, y), Cons(u, v)) => Cons(f(x, u), zipWith(y, v)(f))
}

zipWith(List(1, 2, 3), List(4, 5, 6, 7))((a, b) => a + b)
