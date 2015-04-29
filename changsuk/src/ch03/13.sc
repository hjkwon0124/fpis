import ch03._

def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
  val reversed = List.foldRight(as, List[A]())(
    (in, out) => List.foldRight(out, List(in))((a, b) => Cons(a, b))
  )
  List.foldRight(reversed, z)((a, b) => f(b, a))
}

def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
  val reversed = List.foldLeft(as, List[A]())(
    (out, in) => Cons(in, out)
  )
  List.foldLeft(reversed, z)((b, a) => f(a, b))
}

val list = List(1, 2, 3, 4, 5)

foldLeft(list, 1)(_ + _)
foldRight(list, 1)(_ + _)

List.foldLeft(list, 0)((b, a) => a)
foldLeft(list, 0)((b, a) => a)

List.foldRight(list, 0)((a, b) => a)
foldRight(list, 0)((a, b) => a)