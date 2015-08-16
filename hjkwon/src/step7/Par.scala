package step7

import java.util.concurrent._

/**
 * Created by hjkwon on 15. 8. 15.
 */


object Par {
  type Par[A] = ExecutorService => Future[A]


  def unit[A](a:A):Par[A] = (es:ExecutorService) => UnitFuture(a)
  def lazyUnit[A](a : =>A):Par[A] = fork(unit(a))

  private case class UnitFuture[A](get: A) extends Future[A] {
    def isDone = true
    def get(timeout: Long, units: TimeUnit) = get
    def isCancelled = false
    def cancel(evenIfRunning: Boolean): Boolean = false
  }

  def map2[A,B,C](af:Par[A])(bf:Par[B])(f:(A,B)=>C):Par[C]=
    (es:ExecutorService) =>{
    val result_af = af(es)
    val result_bf = bf(es)
    UnitFuture(f(result_af.get,result_bf.get))
  }

  def fork[A]( a : =>Par[A]):Par[A] =
    (es:ExecutorService) => es.submit(new Callable[A] {
      override def call(): A = a(es).get
    })

  def asyncF[A,B](f:A =>B): A => Par[B] = (a:A) => lazyUnit(f(a))

  def map[A,B](pa: Par[A])(f:A =>B): Par[B] = map2(pa)( unit(()) )( (a,_) => f(a) )

  def parMap[A,B](ps:List[A])(f : A => B):Par[List[B]] = fork {
    val ls = ps.map(asyncF(f(_)))
    sequence_simple(ls)
  }

  def sequence_simple[A](l: List[Par[A]]): Par[List[A]] =
    l.foldRight[Par[List[A]]](unit(List()))((h,t) => map2(h)(t)(_::_))

//  def parFilter[A](as: List[A])(f : A => Boolean): Par[List[A]] =
//  as match {
//    case Nil => unit(Nil)
//    case h::t =>
//
//  }

}
