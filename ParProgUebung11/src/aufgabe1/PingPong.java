package aufgabe1;

import java.util.concurrent.TimeUnit;

import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class PingPong {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("PingPong");

		ActorRef p1 = system.actorOf(Props.create(PingPongActor.class), "P1");
		ActorRef p2 = system.actorOf(Props.create(PingPongActor.class), "P2");
		
		/*
		 * indem wir p2 als Sender angeben kann p1 mit getSender darauf
		 * zugreifen
		 */
		p1.tell(new Start(), p2);
		
		FiniteDuration FIVE_SECONDS = Duration.create(5, TimeUnit.SECONDS);
		system.scheduler().schedule(FIVE_SECONDS, FIVE_SECONDS, p1, new Reset(), system.dispatcher(), ActorRef.noSender());
	}

	static class Start {
	}

	static class Ping {
		final int count;

		public Ping(int count) {
			this.count = count;
		}
	}
	
	static class Reset {
		boolean reset = false;
	}

	static class PingPongActor extends UntypedActor {
		boolean reset;
		
		public void onReceive(Object message) {
			if (message instanceof Start) {
				handleStart((Start) message);
			} else if (message instanceof Ping) {
				handlePing((Ping) message);
			} else if (message instanceof Reset) {
				reset = true;
			}
		}

		private void handlePing(Ping msg) {
			System.out.println(getSelf().path().name() + ": count " + msg.count);
			try {
				Thread.sleep((long) (Math.random() * 1000) + 300);
			} catch (InterruptedException e) {
			}
			if(reset) {
				getSender().tell(new Ping(0), getSelf());
				reset = false;
			}else if(msg.count < 10) {
				Ping ping = new Ping(msg.count+1);
				getSender().tell(ping, getSelf());
			} else {
				getContext().system().shutdown();
			}
		}

		private void handleStart(Start message) {
			Ping ping = new Ping(0);
			getSender().tell(ping, getSelf());
		}
	}
}