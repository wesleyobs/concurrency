package br.com.concurrency.executortask;

public interface Task {
    boolean execute();

    default void log(final String message, Object... args){
        System.out.println(String.format(message, args));
    }
}
