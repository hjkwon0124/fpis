import ch03._

def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
  case Nil => Nil
  case Cons(x, y) => if(f(x)) dropWhile(y, f) else y
}


dropWhile(List(1, 2, 3, 4, 5), (x: Int) => x < 3)