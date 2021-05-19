package solutions

class WordPaths(dictFile: String){
  /** The dictionary */
  var dict : Dictionary = new Dictionary(dictFile)

  /** A type representing paths through the graph of words */
  type Path = List[String]

  /** Print the Path path, separating entries with commas.
    * Pre: path is non-empty. */
  def printPath(path: Path) = {
    print(path.head)
    for(w <- path.tail) print(", "+w)
    println()
  }

  /** Find all neighbours of w
    * @return valid neighbouring words in a List (ordering unimportant) */
  def neighbours(w: String) : List[String] = {
    var result = List[String]() // build up the result
    for(i <- 0 until w.length; c <- 'a' to 'z')
      if(c != w(i)){
        val w1 = w.patch(i,List(c),1) // replace ith character 
                                      // of w with c
        if(dict.isWord(w1)) result = w1 :: result
      }
    result
  }

  /** Find a minimum length path from start to target.
    * @return Some(p) for some shortest Path p if one exists;
    * otherwise None. */
  def findPath(start: String, target: String) : Option[Path] = {
    // We'll perform a breadth-first search.  Each node of the search graph
    // will be a list of words, consecutive words differing in one letter, and
    // ending with start, thereby representing a path (in reverse order)
    val queue = scala.collection.mutable.Queue(List(start))
    // Keep track of the words we've already considered
    val seen = new scala.collection.mutable.HashSet[String]
    seen += start

    while(!queue.isEmpty){
      val path = queue.dequeue(); val w = path.head
      for(w1 <- neighbours(w)){
        if(w1==target) return Some((target::path).reverse)
        else if(!seen.contains(w1)){seen += w1; queue += w1::path}
      } // end of for
    } // end of while
    None // no solutions found
  } // end of findPath
}
