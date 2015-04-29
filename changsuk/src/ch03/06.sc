import ch03._

def init[A](l: List[A]): List[A] = l match {
  case Nil => Nil
  case Cons(x, Nil) => Nil
  case Cons(x, y) => Cons(x, init(y))
}

init(List(1, 2, 3, 4, 5))