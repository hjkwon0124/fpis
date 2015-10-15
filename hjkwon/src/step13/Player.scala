package step13

/**
 * Created by hjkwon on 15. 10. 13.
 */

case class Player(name:String, score: Int)

trait IO_01{ def run: Unit }

// Monoid
trait IO_02{ self =>
  def run: Unit
  def ++(io:IO_02):IO_02 = new IO_02
  {
     def run: Unit = {self.run; io.run}
  }
}

object IO_02{
  def empty:IO_02 = new IO_02{def run =()}
}

object result{

  // 현재의 IO_02 를 가지고는 입력을 받을수 없다. run이 return이 Unit 이므로
//  def converter:IO[Unit] = {
//    println("Enter a temperture in degrees :")
//    val d = readLine.toDouble
//    println(s"temperture : $d ")
//  }

  // Monoid 적용
  def contest_4(p1:Player, p2:Player): IO_02 =
    PrintLine_IO_02(winnerMsg(winner(p1,p2)))

  def PrintLine_IO_02(msg:String):IO_02 = new IO_02{ def run = println(msg) }

  /* 불순함수를 최외각으로 빼낸 이후에는
     불순함수가 할일을 서술하는 함수와 서술을 해석해서 실행하는 해석기로 분류 해야 한다.
  */
  def PrintLine_IO_01(msg:String):IO_01 = new IO_01{ def run = println(msg) }

  def contest_3(p1:Player, p2:Player): IO_01 =
  PrintLine_IO_01(winnerMsg(winner(p1,p2)))


  // (결과 판단 + 출력메시지 판단+ 출력) 이 함수 하나에 섞여있음
  def contest_0(p1: Player, p2: Player) =
    if(p1.score > p2.score) println(s"${p1.name} is the winner")
    else if(p1.score < p2.score) println(s"${p2.name} is the winner")
    else println("It's a draw")

  // (결과 판단) + (출력메시지 판단+ 출력) 으로 분리
  def contest_1(p1: Player, p2: Player) = winner(p1,p2) match{
    case Some(p) => println(s"${p.name} is the winner")
    case None => println("It's a draw")
  }

  // (결과 판단) + (출력메시지 판단) + 출력) 으로 분리
  def contest_2(p1: Player, p2: Player) =
  println(winnerMsg(winner(p1,p2)))


  def winner(p1: Player, p2: Player):Option[Player] =
    if(p1.score > p2.score) Some(p1)
    else if(p1.score < p2.score) Some(p2)
    else None

  def winnerMsg(p: Option[Player]):String = p match {
    case Some(p) => s"${p.name} is the winner"
    case None => "It's a draw"
  }





}

