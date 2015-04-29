import ch03._

def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = as match {
  case Nil => z
  case Cons(x, y) => foldLeft(y, f(z, x))(f)
}

