package step13

/**
 * Created by hjkwon on 15. 10. 14.
 */


sealed trait IO[A] {
  def flatMap[B](f: A => IO[B]): IO[B] =
    FlatMap(this, f)
  def map[B](f: A => B): IO[B] =
    flatMap(f andThen (Return(_)))

}
case class Return[A](a: A) extends IO[A]
case class Suspend[A](resume: () => A) extends IO[A]
case class FlatMap[A,B](sub: IO[A], k: A => IO[B]) extends IO[B]

object IO extends Monad[IO] {
  def unit[A](a: => A): IO[A] = Return(a)
  def flatMap[A,B](a: IO[A])(f: A => IO[B]): IO[B] = a flatMap f
  def suspend[A](a: => IO[A]) =
    Suspend(() => ()).flatMap { _ => a }
}

object MonadIO extends App {
  def printLine(s: String): IO[Unit] =
    Suspend(() => Return(println(s)))
  @annotation.tailrec def run[A](io: IO[A]): A = io match {
    case Return(a) => a
    case Suspend(r) => r()
    case FlatMap(x, f) => x match {
      case Return(a) => run(f(a))
      case Suspend(r) => run(f(r()))
      case FlatMap(y, g) => run(y flatMap (a => g(a) flatMap f))
    }
  }
  run(IO.forever(printLine("Still going...")))

}