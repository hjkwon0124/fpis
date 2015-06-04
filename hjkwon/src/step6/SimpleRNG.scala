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

  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (i, r) = rng.nextInt
    (if (i < 0) -(i + 1) else i, r)
  }

  def map[A,B](s:Rand[A])(f: A=>B): Rand[B] =
  rng =>{
    val (a,rng2) = s(rng)
    (f(a),rng2)
  }

  def nonNegativeEven: Rand[Int] =
   map(nonNegativeInt)(i=> i- i%2)
}


