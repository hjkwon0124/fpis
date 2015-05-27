package step5

/**
 * Created by hjkwon on 15. 5. 26.
 */
object main extends App{
  val testStream = Stream(2,5,3,4,2,6,78,7,1,10)
  val ones:Stream[Int] = Stream.cons(1,ones)
//  val evaltest1 = Stream(eval(1),eval(2),eval(3))
//  val evaltest2:Stream[eval] = Stream.cons(eval(2),evaltest2)

  val test = Stream.unfold(10)( x => { if(x>0) Option(x,x-2) else None})
  println("5) unfold + take \n" + test.take(10).toList + "\n")
  println("5.3) takeWhile\n" + testStream.takeWhile(x => x >1).toList + "\n")
  println("5.4) forAll\n" + testStream.forAll(x => x >1) + "\n")

  val test_tails = Stream.unfold(1)(p => if(p<6) Some((p,p+1)) else None)


  println(test_tails.tails.toList)

}


case class eval(a:Int){
  println("*) EVAL"+a)
}
