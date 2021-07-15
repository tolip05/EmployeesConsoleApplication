package app.io;

public class ConsoleOutputWriter implements Writer {
    @Override
    public void write(String output) {
        System.out.print(output);
    }

    @Override
    public void writeLine(String output) {
        System.out.println(output);
    }

    @Override
    public void writeLine(String format, Object... params) {
        System.out.println(String.format(format, params));
    }

    @Override
    public void writeLine() {
        System.out.println();
    }
}
