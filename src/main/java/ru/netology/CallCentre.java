package ru.netology;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCentre {
    public static final long ATC_DELAY = 5000;
    public static final int NUMBER_OF_CALLS = 40;

    public static final long OPERATOR_DELAY = 700;
    public static final int NUMBER_OF_OPERATORS = 4;
    public static final int QUEUE_LIMIT_SIZE = 100;

    private Queue<Call> callQueue;

    private volatile boolean cycle = true;


    CallCentre() {
        ThreadGroup operators = new ThreadGroup("Operators");
        callQueue = new ConcurrentLinkedQueue<>();

        Thread ats = new Thread(new ATS(callQueue, cycle));


        for (int i = 0; i < NUMBER_OF_OPERATORS; i++) {
            Operator operator = new Operator(callQueue, cycle);
            Thread thread = new Thread(operators, operator);
            thread.setName("Operator № " + i);
            System.out.println(thread.getName() + " begin working.");
            thread.start();

        }

        ats.start();

    }
}
