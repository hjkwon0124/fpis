import ch03._

def drop[A](l: List[A], n: Int): List[A] = (l, n) match {
  case (Nil, _) => Nil
  case (x, 0) => x
  case (Cons(_, b), m) => drop(b, m - 1)
}

drop(List(1, 2, 3, 4, 5), 2)
