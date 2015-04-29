import ch03._

def sum(x: List[Int]): Int = {
  List.foldLeft(x, 0)(_ + _)
}

def product(x: List[Double]): Double = {
  List.foldLeft(x, 1.0)(_ * _)
}

def length[A](x: List[A]): Int = {
  List.foldLeft(x, 0)((x, y) => x + 1)
}

sum(List(1, 2, 3, 4, 5))
product(List(1, 2, 3, 4, 5))
length(List(1, 2, 3, 4, 5))

