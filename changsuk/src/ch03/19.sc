import ch03._

def filter[A](as: List[A])(f: A => Boolean): List[A] = {
  List.foldRight(as, List[A]())(
    (a, b) => if (f(a)) Cons(a, b) else b
  )
}

filter(List(1, 2, 3, 4, 5))(_ % 2 == 0)
