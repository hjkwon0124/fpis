import ch03._

def hasSequence[A](sup: List[A], sub: List[A]): Boolean = {
  def compare(x: List[A], y: List[A]): Boolean = (x, y) match {
    case (Nil, Nil) => true
    case (Cons(_,_), Nil) => true
    case (Nil, Cons(_,_)) => false
    case (Cons(xa, xb), Cons(ya, yb)) => {
      if(xa == ya) compare(xb, yb)
      else false
    }
  }

  def loop(x: List[A]): Boolean = x match {
    case Nil => false
    case Cons(a, b) => compare(x, sub) | loop(b)
  }

  loop(sup)
}

hasSequence(List(1, 2, 3, 4), List(2, 3))
hasSequence(List(1, 2, 3, 4), List(4, 5))
hasSequence(List(1, 2, 3, 4), List(4))
