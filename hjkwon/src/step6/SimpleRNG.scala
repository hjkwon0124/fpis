package step6

/**
 * Created by hjkwon on 15. 6. 1.
 */


trait RNG{
  def nextInt:(Int,RNG)

}

case class SimpleRNG(seed: Long) extends RNG{
  type Rand[+A] = RNG =>(A,RNG )

  def nextInt:(Int,RNG) ={
    val newSeed = (seed * 0x5DEECE66DL +0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>>16).toInt
    (n,nextRNG)
  }



  def ints(count: Int)(rng : RNG) : (List[Int],RNG)={
    if(count >0){
    val (x,r) = rng.nextInt
    val (xs,r1) = ints(count-1)(r)
    (x::xs,r1)
    }
    else
      (Nil,rng)
  }

  def unit[A](a:A): Rand[A]=
  rng => (a,rng)

  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] =
    fs.foldRight( unit( List[A]() ) ) ((f, acc) => map2(f, acc)(_ :: _))

  def _ints(count: Int): Rand[List[Int]] =
    sequence(List.fill(count)(rng => rng.nextInt))

  def flatMap[A,B](s:Rand[A])(f:A => Rand[B]) : Rand[B] =
    rng => {
      val (a,rng1) = s(rng)
      f(a)(rng1)
    }

  def map[A,B](s:Rand[A])(f: A=>B): Rand[B] =
  rng =>{
    val (a,rng2) = s(rng)
    (f(a),rng2)
  }

  def _map[A,B](s:Rand[A])(f:A=>B):Rand[B] =
  flatMap(s)(a=>unit(f(a)))

  def map2[A,B,C](ra:Rand[A], rb:Rand[B])(f: (A,B) => C) : Rand[C]=
    rng => {
      val (a, rng1) = ra(rng)
      val (b, rng2) = rb(rng1)
      (f(a,b), rng2)
    }

  def _map2[A,B,C](ra:Rand[A], rb:Rand[B])(f: (A,B) => C) : Rand[C]=
    flatMap(ra)( a=> map(rb)(b=> f(a,b)))







  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (i, r) = rng.nextInt
    (if (i < 0) -(i + 1) else i, r)
  }

  def nonNegativeEven: Rand[Int] =
   map(nonNegativeInt)(i=> i- i%2)
}


