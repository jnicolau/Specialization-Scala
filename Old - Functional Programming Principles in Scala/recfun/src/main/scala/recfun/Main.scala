package recfun

import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    if (c < 0 || r < 0 || c > r) throw new IllegalArgumentException("c and r must not be a negative integer and c can't be higher than r.")
    else if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balance(chars: List[Char], opened: Int): Boolean =
      if (chars.isEmpty) opened == 0
      else if (chars.head == ')')
        if (opened == 0) false
        else balance(chars.tail, opened - 1)
      else if (chars.head == '(') balance(chars.tail, opened + 1)
      else balance(chars.tail, opened)
    balance(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if (money == 0 || coins.isEmpty) 0
    else
      (if (money == coins.head) 1
      else if (money > coins.head) countChange(money - coins.head, coins)
      else 0
        ) + countChange(money, coins.tail)
}
