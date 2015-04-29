import ch03._

// 컴파일 오류
def maximum(t: Tree[Int]): Int = {
  case Leaf(x: Int) => x
  case Branch(left: Tree[Int], right: Tree[Int]) => {
    maximum(left) max maximum(right)
  }
}
