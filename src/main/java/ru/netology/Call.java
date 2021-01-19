package ru.netology;

public class Call {
    private static int id;

    public int getId() {
        return id;
    }

    public Call(int inc) {
        this.id += inc;
    }

    @Override
    public String toString() {
        return "Call number: " + getId();
    }
}
