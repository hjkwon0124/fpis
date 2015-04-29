import ch03._

def reverse[A](x: List[A]) = {
  List.foldLeft(x, List[A]())((b, a) => Cons(a, b))
}

// 1. 일단 빈 리스트를 만들고
// 2. 가장 왼쪽 항목부터 차례로 앞에 붙여 나간다.
// 3. 결국 가장 오른쪽의 항목이 가장 앞에 붙게 된다.
reverse(List(1, 2, 3, 4, 5))