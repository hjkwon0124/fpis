import ch03._

def appendR[A](xa: List[A], xb: List[A]): List[A] = {
  List.foldRight(xa, xb)((a, b) => Cons(a, b))
}

def appendL[A](xa: List[A], xb: List[A]): List[A] = {
  val reversedFront = List.foldLeft(xa, List[A]())((b, a) => Cons(a, b))
  List.foldLeft(reversedFront, xb)((b, f) => Cons(f, b))
}

appendR(List(1, 2, 3), List(4, 5, 6))
appendL(List(1, 2, 3), List(4, 5, 6))