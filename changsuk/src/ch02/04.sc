def uncurry[A,B,C](f: A => B => C): (A, B) => C = {
  (a, b) => f(a)(b)
}


val f = (a: Int) => (b: Int) => a + b
val g = uncurry(f)

g(1, 2)
