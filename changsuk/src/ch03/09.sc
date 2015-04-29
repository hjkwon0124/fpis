import ch03._

def length[A](as: List[A]): Int = {
  List.foldRight(as, 0)((x, y) => y + 1)
}

length(List(1, 2, 3, 4, 5))