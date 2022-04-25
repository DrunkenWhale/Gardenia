package skiplist.mutable

case class SkipListNode[T](
                            key: Int,
                            data: T,
                            forwards: Array[SkipListNode[T]] = Array()
                          )
