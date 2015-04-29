import ch03._

// 컴파일 오류
def depth[A](t: Tree[A]): Int = {
  case Leaf(_) => 1
  case Branch(left, right) => {
    (depth(left) max depth(right)) + 1
  }
}

depth(Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Leaf(4))))
depth(Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Branch(Leaf(4), Leaf(5)))))
