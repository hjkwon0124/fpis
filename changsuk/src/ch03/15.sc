import ch03._

def flatten[A](l: List[List[A]]) = {
  List.foldRight(l, List[A]())(List.foldRight(_, _)(Cons(_, _)))
}

val l = List(List(1, 2, 3), List(4, 5, 6))

flatten(l)