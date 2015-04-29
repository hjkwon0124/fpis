import ch03._

def tail[A](x: List[A]) = x match {
  case Nil => Nil
  case Cons(a, b) => b
}

tail(List(1, 2, 3, 4, 5))
