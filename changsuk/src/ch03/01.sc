import ch03._

List(1, 2, 3, 4, 5)

val x = List(1, 2, 3, 4, 5) match {
  case Cons(x, Cons(2, Cons(4, _))) => x  // 3이 빠짐
  case Nil => 42  // Cons 타입임
  case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y // x=1, y=2
  case Cons(h, t) => h + List.sum(t)  // 가능하지만, 위의 case에서 이미 종료
  case _ => 101 // 가능하지만, 위의 case에서 이미 종료
}



