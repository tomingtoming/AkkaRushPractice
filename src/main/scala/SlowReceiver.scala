import akka.actor.Actor
import java.security.MessageDigest

class SlowReceiver extends Actor {
  var counts = 0
  val digest = MessageDigest.getInstance("MD5")
  override def preStart() = {
    println("preStart called!!: " + this)
  }
  override def receive = {
    case bytes: Array[Byte] =>
      digest.reset()
      counts += 1
      System.out.println(digest.digest(bytes).map("%02x".format(_)).mkString + ":" + counts)
      Thread.sleep(100)
      sender() ! "countup"
  }
  override def postStop() = {
    println("postStop called!!: " + this)
  }
}
