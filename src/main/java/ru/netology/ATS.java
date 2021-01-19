package ru.netology;

import java.util.Queue;

import static ru.netology.CallCentre.*;

public class ATS implements Runnable {

    private Queue<Call> callQueue;
    private boolean cycle;

    public ATS(Queue<Call> callQueue, boolean cycle) {
        this.callQueue = callQueue;
        this.cycle = cycle;
    }

    @Override
    public void run() {
        System.out.println("ATS started.");
        while (true) {

            try {
                if (callQueue.size() < QUEUE_LIMIT_SIZE) {
                    for (int i = 0; i < NUMBER_OF_CALLS; i++) {
                        Call call = new Call(1);
                        callQueue.add(call);
                    }
                    System.out.println(">>>>>>>>>>>>>>> Added " + NUMBER_OF_CALLS + " new calls. Calls in the queue: " + callQueue.size());
                    cycle = false;
                } else {
                    System.out.println("<<<<<<<<<<<<<<< Limit is reached. Calls in the queue: " + callQueue.size());
                }
                Thread.sleep(ATC_DELAY);
            } catch (InterruptedException exception) {
                System.out.println("ATS INTERRUPTED");
            }
        }
    }
}
