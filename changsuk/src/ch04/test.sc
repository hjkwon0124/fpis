import ch04._

Some(5).map(_ * 10)
Some(5).filter(_ > 10)
Some(100).filter(_ > 10)

Option.traverse(List(1, 2, 3, 4))(a => Some(a + 10)).getOrElse(-100)