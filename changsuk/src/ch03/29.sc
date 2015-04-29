import ch03._

// left-fold와 유사하게 동작한다.
def fold[A, B](t: Tree[A])(f: A => B): Tree[B] = {
  case Leaf(_) => Leaf(f(_))
  case Branch(a, b) => Branch(fold(a)(f), fold(b)(f))
}