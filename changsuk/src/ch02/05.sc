def compose[A,B,C](f: B => C, g: A => B): A => C = {
  a => f(g(a))
}

val h = compose((b: Int) => b * 10, (a: Int) => a + 10)

h(10)
