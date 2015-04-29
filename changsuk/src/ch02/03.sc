def curry[A,B,C](f: (A, B) => C): A => (B => C) = {
  a => b => f(a, b)
}

val f = (a: Int, b: Int) => a + b

// h(b) = g(a)(b) = f(a, b)
val g = curry(f)
val h = g(10)

h(1)    // g(10)(1) = f(10, 1)
h(10)   // g(10)(10) = f(10, 10)

