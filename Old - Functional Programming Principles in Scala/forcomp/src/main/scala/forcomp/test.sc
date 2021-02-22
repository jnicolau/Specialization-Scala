import forcomp.Anagrams._

val occurrences = List(('a', 2), ('b', 2))

//*    List(
//  *      List(),
//  *      List(('a', 1)),
//  *      List(('a', 2)),
//  *      List(('b', 1)),
//  *      List(('a', 1), ('b', 1)),
//  *      List(('a', 2), ('b', 1)),
//  *      List(('b', 2)),
//  *      List(('a', 1), ('b', 2)),
//  *      List(('a', 2), ('b', 2))
//  *    )
combinations(occurrences)


(1 to 3) zip List(4,5,6)