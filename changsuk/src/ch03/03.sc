import ch03._

def setHead[A](h: A, x: List[A]) = x match {
  case Nil => List(h)
  case Cons(a, b) => Cons(h, b)
}