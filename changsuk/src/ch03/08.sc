import ch03._

List.foldRight(List(1, 2, 3, 4, 5), Nil:List[Int])(Cons(_,_))

// res0: ch03.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Nil)))))
//  - 리스트가 복사된다.
//  - apply() 메소드와 유사한 동작을 수행한다.