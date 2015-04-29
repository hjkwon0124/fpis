import ch03._

def addOne(l: List[Int]) = {
  List.foldRight(l, List[Int]())((a, b) => Cons(a + 1, b))
}

addOne(List(1, 2, 3, 4, 5))