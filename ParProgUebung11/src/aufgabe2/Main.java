package aufgabe2;

import java.io.IOException;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

	public static void main(String[] args) throws IOException {

		ActorSystem system = ActorSystem.create("CalculatorService");

		ActorRef manager = system.actorOf(
				Props.create(WorkerManager.class, 4 /* Worker */),
				"WorkerManager");

		ActorRef calculator = system.actorOf(
				Props.create(Calculator.class, manager), "Calculator");

		while (System.in.read() > 0) {
			// Der Calculator soll ein neues WorkItem schedulen
			calculator.tell(new Calculator.Calculate(), ActorRef.noSender());
		}

		system.shutdown();
	}
}
