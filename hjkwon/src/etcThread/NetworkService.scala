package etcThread

/**
 * Created by hjkwon on 15. 7. 21.
 */

import java.lang.invoke.CallSite
import java.net.{Socket, ServerSocket}
import java.util.concurrent.{Callable, Executors, ExecutorService}
import java.util.Date

class NetworkService(port: Int, poolSize: Int) extends Runnable {
  val serverSocket = new ServerSocket(port)
  val pool: ExecutorService = Executors.newFixedThreadPool(poolSize)

  def run() {
    try {
      while (true) {
        // This will block until a connection comes in.
        val socket = serverSocket.accept()
//        pool.execute(new Handler(socket))
//        pool.submit(new Handler(socket))
          println(pool.submit(new Handler2(socket)))
      }
    } finally {
      pool.shutdown()
    }
  }
}

class Handler(socket: Socket) extends Runnable {
  def message = (Thread.currentThread.getName() + "\n").getBytes

  def run() {
    socket.getOutputStream.write(message)
    socket.getOutputStream.close()
  }
}



class Handler2(socket: Socket) extends Callable[String]{
  def message = (Thread.currentThread.getName() + "\n").getBytes

  def call: String = {
    socket.getOutputStream.write(message)
    socket.getOutputStream.close()
    "hello"
  }
}