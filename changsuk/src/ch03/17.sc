import ch03._

def toStrings(l: List[Double]) = {
  List.foldRight(l, List[String]())((a, b) => Cons(a.toString, b))
}

toStrings(List(1.0, 2.0, 3.0))