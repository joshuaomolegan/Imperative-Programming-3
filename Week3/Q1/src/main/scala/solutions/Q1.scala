package solutions

import scala.swing._
import scala.swing.event._

// GUI for WordPaths.scala
object WordPathApp extends SimpleSwingApplication{
    def top = new MainFrame {
        val source = new TextField{columns = 5}
        val target = new TextField{columns = 5}
        val searchBtn = new Button("Search")
        var solution = new TextArea(rows = 10, columns = 20){
            text = "No Solution"
            lineWrap = true
            editable = false
            listenTo(searchBtn)
            reactions += {
                case ButtonClicked(searchBtn) => (new Worker).start
            }
        }

        contents = new BoxPanel(Orientation.Vertical){
            contents += new Label("Source: ")
            contents += source 
            contents += new Label("Target: ")
            contents += target
            contents += searchBtn
            contents += solution
        }

        // Object for the class to find the path
        val pathFinder = new WordPaths("src/main/scala/solutions/knuth_words.txt")
        
        // Thread to run path finding code
        class Worker extends Thread{
            override def run{
                val ans = pathFinder.findPath(source.text, target.text)
                Swing.onEDT{
                    ans match {
                        case Some(path) => {
                            solution.text = path.head
                            for(w <- path.tail) solution.text += ", "+w
                        }
                        case _ => solution.text = "No Solution"
                    }
                }
            }
        }
    }
}