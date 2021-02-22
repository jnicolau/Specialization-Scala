package recfun

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
    def pascal(c: Int, r: Int): Int = {
      require(c >= 0, "Invalid column.")
      require(r >= 0, "Invalid row.")
      require(c <= r, "Invalid column amd/or row.")
      if (c == 0 || r == c)
        1
      else
        pascal(c-1, r-1) + pascal(c, r-1)
    }
    
  
  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balanceAcc(chars: List[Char], acc: Int): Boolean = 
      if (acc < 0)
        false
      else if (chars.isEmpty) 
        acc == 0
      else if (chars.head == '(')
        balanceAcc(chars.tail, acc+1)
      else if (chars.head == ')')
        balanceAcc(chars.tail, acc-1)
      else
        balanceAcc(chars.tail, acc)

    balanceAcc(chars, 0)
  }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      if (money <= 0 || coins.isEmpty)
        0
      else if (money == coins.head)
        1 + countChange(money, coins.tail)
      else if (money < coins.head)
        countChange(money, coins.tail)
      else
        countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
  }
