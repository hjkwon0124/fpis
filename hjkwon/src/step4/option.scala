package step4

/**
 * Created by hjkwon on 15. 5. 26.
 */
sealed trait Option[+A]{
  def map[B](f: A => B): Option[B] = this match{
      case None => None
      case Some(a) => Some(f(a))
    }

  def getOrElse[B >: A](default: => B) : B = this match{
    case None => default
    case Some(a) => a
  }

  def flatMap[B](f: A => Option[B]): Option[B] ={
   map(f).getOrElse(None)
  }

  def filter(f: A => Boolean):Option[A] =
    flatMap( a => if(f(a))Some(a) else None)


}
case class Some[+A](get:A) extends Option[A]
case object None extends Option[Nothing]

