def fib(n: Int): Int = {
  def next(a: Int, b: Int, n: Int): Int = {
    if(n == 0) a
    else next(b, a + b, n - 1)
  }

  next(0, 1, n)
}

fib(0)
fib(1)
fib(2)
fib(3)
fib(4)
fib(5)
