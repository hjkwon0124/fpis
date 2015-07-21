package etcThread

import java.util.concurrent.FutureTask

/**
 * Created by hjkwon on 15. 7. 21.
 */
object main extends App{
  val ns = new NetworkService(2020,2)

  ns.run()

}
