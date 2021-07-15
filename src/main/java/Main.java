import app.core.Engine;
import app.io.ConsoleOutputWriter;
import app.io.FileIO;
import app.io.FileIOImpl;
import app.io.Writer;
import app.repositories.LocalRepository;
import app.repositories.LocalRepositoryImpl;
import app.services.CoupleService;
import app.services.CoupleServiceImpl;

public class Main {
    public static void main(String[] args) {
        FileIO fileIO = new FileIOImpl();
        Writer writer = new ConsoleOutputWriter();
        LocalRepository localRepository = new LocalRepositoryImpl();
        CoupleService coupleService = new CoupleServiceImpl(localRepository);
        Engine engine = new Engine(fileIO,writer,coupleService);
        engine.run();
    }
}
