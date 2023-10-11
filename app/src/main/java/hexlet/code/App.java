package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

public class App implements Callable<Integer> {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Differ()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
