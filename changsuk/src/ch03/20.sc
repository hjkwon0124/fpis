import ch03._

def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] = {
  List.foldRight(as, List[B]())(
    (a, b) => List.foldRight(f(a), b)(Cons(_, _))
  )
}

flatMap(List(1, 2, 3))(i => List(i, i))