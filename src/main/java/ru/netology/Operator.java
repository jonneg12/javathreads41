package ru.netology;

import java.util.Queue;

import static ru.netology.CallCentre.OPERATOR_DELAY;

public class Operator implements Runnable {

    private volatile Queue<Call> callQueue;
    private boolean cycle;

    public Operator(Queue<Call> callQueue, boolean cycle) {
        this.callQueue = callQueue;
        this.cycle = cycle;
    }

    @Override
    public void run() {
        while (cycle || callQueue.size() > 0) {
            try {
                Thread.sleep(OPERATOR_DELAY);
                if (callQueue.poll() != null) {
                    System.out.println(Thread.currentThread().getName() + " answered call. Calls remain : " + callQueue.size());
                }
            } catch (InterruptedException exception) {
                System.out.println(Thread.currentThread().getName() + " INTERRUPTED");
            }
        }

    }
}
