import ch03._

// 컴파일 오류
def map[A, B](t: Tree[A])(f: A => B): Tree[B] = {
  case Leaf(_) => Leaf(f(_))
  case Branch(a, b) => Branch(map(a)(f), map(b)(f))
}