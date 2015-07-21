package etcThread

import java.io.{InputStreamReader, BufferedReader, InputStream}
import java.net.Socket

/**
 * Created by hjkwon on 15. 7. 21.
 */
object NetworkClient extends App{
  val soc = new Socket("127.0.0.1",2020)

  val is:InputStream = soc.getInputStream();
  val br = new BufferedReader(new InputStreamReader(is));
  val str = br.readLine()
  println(str)

}
