import ch05._
val a = Stream(1, 2, 3, 4, 5)
val b = Stream(6, 7, 8, 9, 10)
val c = Stream(Stream(1, 2, 3), Stream(4, 5, 6), Stream(7, 8, 9))
// 1
a.toList
// 2-1
a.take(2).toList
// 2-2
a.drop(2).toList
// 3, 5
a.takeWhile(x => x < 3).toList
// 4
a.forAll(_ < 3)
a.forAll(_ < 10)
a.forAll(_ <= 5)
a.forAll(_ < 5)
a.forAll(_ > 4)
// 6
a.headOption
// 7-3
a.append(b).toList
// 7-4
a.flatMap(a => Stream(a + 10, a + 20, a + 30)).toList
//14
Stream(1, 2, 3) startsWith Stream(1, 2)
Stream(1, 2, 3) startsWith Stream(2, 3)
//15
Stream(1, 2, 3).tails
Stream(1, 2, 3).hasSubsequence(Stream(1, 2))



