import ch03._

def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] = {
  List.foldRight(as, List[B]())(
    (a, b) => List.foldRight(f(a), b)(Cons(_, _))
  )
}

// 너무 단순하게 적용함 - 개선 여지 고민해 볼 것 (문제의 의도가 무엇?)
def filter[A](as: List[A])(f: A => Boolean): List[A] = {
  flatMap(as)(a => if(f(a)) List[A](a) else Nil)
}


filter(List(1, 2, 3, 4, 5))(_ % 2 == 0)