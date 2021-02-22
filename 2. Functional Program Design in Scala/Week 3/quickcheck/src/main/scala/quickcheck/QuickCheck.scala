package quickcheck

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    e <- arbitrary[A]
    h <- oneOf(Gen.const(empty), genHeap)
  } yield insert(e, h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("minimum of one") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("minimum of two") = forAll { (a: Int, b: Int) =>
    val h1 = insert(a, empty)
    val h2 = insert(b, h1)
    findMin(h2) == Math.min(a, b)
  }

  property("delete one element makes it empty") = forAll { a: Int =>
    val h = insert(a, empty)
    deleteMin(h) == empty
  }

  property("sorted heap") = forAll { h: H =>
    def isSorted(h: H): Boolean =
      if (isEmpty(h)) true
      else {
        val min = findMin(h)
        val newH = deleteMin(h)
        isEmpty(newH) || (min <= findMin(newH) && isSorted(newH))
      }
    isSorted(h)
  }

  property("mnimum of two heapps") = forAll { (h1: H, h2: H) =>
    val meldH = meld(h1, h2)
    findMin(meldH) == Math.min(findMin(h1), findMin(h2))
  }

  property("meld") = forAll { (h1: H, h2: H) =>
    def heapEqual(h1: H, h2: H): Boolean =
      if (isEmpty(h1) && isEmpty(h2)) true
      else {
        val min1 = findMin(h1)
        val min2 = findMin(h2)
        min1 == min2 && heapEqual(deleteMin(h1), deleteMin(h2))
      }
    val meld1 = meld(h1, h2)
    val minH1 = findMin(h1)
    val meld2 = meld(deleteMin(h1), insert(minH1, h2))
    heapEqual(meld1, meld2)
  }
  
}
