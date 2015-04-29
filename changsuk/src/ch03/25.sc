import ch03._

def size[A](t: Tree[A]): Int = t match {
  case Leaf(_) => 1
  case Branch(left, right) => {
    size(left) + size(right) + 1
  }
}

size(Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Leaf(4))))
size(Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Branch(Leaf(4), Leaf(5)))))
