import ch03._

def zipWith(a: List[Int], b: List[Int]): List[Int] = (a, b) match {
  case (Nil, Nil) => Nil
  case (Nil, Cons) => b
  case (Cons, Nil) => a
  case (Cons(x, y), Cons(z, w)) => Cons(x + z, zipWith(y, w))
}

zipWith(List(1, 2, 3), List(4, 5, 6, 7))

