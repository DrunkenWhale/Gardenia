package skiplist

import scala.math.random

object SkipListUtil {

  /**
   *
   * @note
   * this method will have
   * 50% probability return 1
   * 25% probability return 2
   * 12.5% probability return 3
   * ...
   * the sum of probability geometric series will convergence to 1
   *
   * */
  def randomLevel(p: Double = 0.5, maxLevel: Int): Int = {
    var level = 1
    while (p < random() && level < maxLevel) {
      level += 1
    }
    level
  }

}
