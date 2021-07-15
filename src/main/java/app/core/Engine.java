package app.core;

import app.entities.Couple;
import app.entities.Project;
import app.entities.RecordByLine;
import app.factories.RecordByLineFactory;
import app.io.FileIO;
import app.io.Writer;
import app.services.CoupleService;

import java.util.List;
import java.util.stream.Collectors;

import static app.ApplicationConstants.*;

public class Engine implements Runnable {
    private FileIO fileIO;
    private Writer writer;
    private CoupleService coupleService;

    public Engine(FileIO fileIO, Writer writer,
                  CoupleService coupleService) {
        this.fileIO = fileIO;
        this.writer = writer;
        this.coupleService = coupleService;
    }

    @Override
    public void run() {
        List<RecordByLine> records = this.fileIO.read(FILE_PATH)
                .stream().map(RecordByLineFactory::execute)
                .collect(Collectors.toList());
        this.coupleService.addEmployeeRecords(records);
        List<Couple> couples = this.coupleService.findAllTeamsWithOverlap();
        printResult(couples);
    }

    private void printResult(List<Couple> couples) {
        if (couples.size() != 0) {
            couples.sort((firstCouple, secondCouple) ->
                    (int) (secondCouple.getTotalDuration() - firstCouple.getTotalDuration()));
            Couple bestCouple = couples.get(0);
            this.writer.write(
                    String.format(BEST_TEAM_PATTERN,
                            bestCouple.getFirstEmplId(),
                            bestCouple.getSecondEmplId(),
                            bestCouple.getTotalDuration()));
            this.writer.writeLine();
            for (Project project : bestCouple.getProjects()) {
                this.writer.write(String.format(BEST_RESULT, bestCouple.getFirstEmplId()
                        , bestCouple.getSecondEmplId(), project.getProjectId(), project.getWorkedDays()));
                this.writer.writeLine();
            }
        } else {
            this.writer.write(NO_TEAMS_MSG);
        }
    }
}

