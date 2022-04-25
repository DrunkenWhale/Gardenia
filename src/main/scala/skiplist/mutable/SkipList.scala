package skiplist.mutable

import skiplist.SkipListUtil.randomLevel

class SkipList[T] {

  private var level = 0

  def put(key: Int, value: T): Boolean = {
    val level = randomLevel(maxLevel = level)
    true
  }

}
