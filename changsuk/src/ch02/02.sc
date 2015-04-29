def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
  def loop(n: Int): Boolean = {
    if (n >= as.length - 1) true
    else if(!ordered(as(n), as(n + 1))) false
    else loop(n + 1)
  }

  loop(0)
}

isSorted(Array(1, 2, 3, 4), (a: Int, b: Int) => a < b)
isSorted(Array(1, 4, 3, 4), (a: Int, b: Int) => a < b)
isSorted(Array(1, 3, 3, 4), (a: Int, b: Int) => a < b)
isSorted(Array(1, 2, 39, 100), (a: Int, b: Int) => a < b)
isSorted(Array(1, 2, 390, 100), (a: Int, b: Int) => a < b)