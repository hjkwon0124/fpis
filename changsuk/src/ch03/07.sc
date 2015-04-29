import ch03._

def shortProduct(l: List[Double]): Double = {
  List.foldRight(l, 1.0)((x, y) => x * y)
  // not
  // 가변 가능한 부분은 predicate인데, foldRight는 predicate을 평가하기에 앞서
  // 리스트의 가장 마지막 항목까지 foldRight의 재귀적 호출을 먼저 수행한다.
  // 따라서 predicate을 통해 실행 흐름을 단축 시킬 기회가 없다.
}