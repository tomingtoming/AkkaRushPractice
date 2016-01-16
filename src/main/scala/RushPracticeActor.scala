import java.util.concurrent.TimeUnit

import akka.actor.{Actor, PoisonPill, Props}
import akka.util.Timeout

import scala.concurrent.duration.Duration
import scala.util.Random

class RushPracticeActor extends Actor {
  var counts = 0
  val limit = Int.MaxValue
  val bytes = Array.fill[Byte](1024 * 1024)(0)
  implicit val timeout = Timeout(Duration(1, TimeUnit.SECONDS))
  override def preStart() = {
    context.system.actorOf(Props[SlowReceiver], "receiver")
    self ! "go"
  }
  override def receive: Receive = {
    case "go" =>
      val receiver = context.system.actorSelection("/user/receiver")
      (1 to limit).foreach { n =>
        Random.nextBytes(bytes)
        receiver ! bytes
        System.err.println(n)
      }
    case "countup" =>
      counts += 1
      if (counts == limit) self ! "shutdown"
    case "shutdown" =>
      println("Due to shutdown...")
      self ! PoisonPill
  }
}
