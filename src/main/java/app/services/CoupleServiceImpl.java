package app.services;

import app.entities.Couple;
import app.entities.Project;
import app.entities.RecordByLine;
import app.factories.CoupleFactory;
import app.repositories.LocalRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static app.ApplicationConstants.DEFAULT_OVERLAP_ZERO_DAYS;
import static app.ApplicationConstants.INDEX_ZERO;
import static app.ApplicationConstants.ONE;

public class CoupleServiceImpl implements CoupleService {
    private LocalRepository localRepository;

    public CoupleServiceImpl(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public void addEmployeeRecords(List<RecordByLine> records) {
        this.localRepository.saveAll(records);
    }

    @Override
    public List<Couple> findAllTeamsWithOverlap() {
        List<RecordByLine> allRecords = this.localRepository.getAllRecords();
        List<Couple> teams = new ArrayList<>();
        for (int i = INDEX_ZERO; i < allRecords.size() - ONE; i++) {
            for (int j = i + ONE; j < allRecords.size(); j++) {
                RecordByLine firstEmpl = allRecords.get(i);
                RecordByLine secondEmpl = allRecords.get(j);

                if (firstEmpl.getProjectId() == secondEmpl.getProjectId()
                        && hasOverlap(firstEmpl, secondEmpl)) {

                    long overlapDays = calculateOverlap(firstEmpl, secondEmpl);
                    if (overlapDays > DEFAULT_OVERLAP_ZERO_DAYS) {
                        updateTeamCollection(teams, firstEmpl, secondEmpl, overlapDays);
                    }
                }
            }
        }
        return teams;
    }

    private void updateTeamCollection(List<Couple> teams, RecordByLine firstEmpl, RecordByLine secondEmpl, long overlapDays) {
        Project project = new Project(firstEmpl.getProjectId(),overlapDays);
        AtomicBoolean isPresent = new AtomicBoolean(false);
        teams.forEach(team -> {
            if (isTeamPresent(team, firstEmpl.getEmployeeId(), secondEmpl.getEmployeeId())) {
                team.addOverlapDuration(overlapDays);
                team.getProjects().add(project);
                isPresent.set(true);
            }
        });
        if (!isPresent.get()) {
            Couple newTeam = CoupleFactory.execute(
                    firstEmpl.getEmployeeId(),
                    secondEmpl.getEmployeeId(),
                    overlapDays);
            newTeam.getProjects().add(project);
            teams.add(newTeam);
        }
    }

    private boolean isTeamPresent(Couple team, long firstEmplId, long secondEmplId) {
        return (team.getFirstEmplId() == firstEmplId
                && team.getSecondEmplId() == secondEmplId)
                || (team.getFirstEmplId() == secondEmplId
                && team.getSecondEmplId() == firstEmplId);
    }

    private long calculateOverlap(RecordByLine firstEmpl, RecordByLine secondEmpl) {
        LocalDate periodStartDate =
                firstEmpl.getDateFrom().isBefore(secondEmpl.getDateFrom()) ?
                        secondEmpl.getDateFrom() : firstEmpl.getDateFrom();

        LocalDate periodEndDate =
                firstEmpl.getDateTo().isBefore(secondEmpl.getDateTo()) ?
                        firstEmpl.getDateTo() : secondEmpl.getDateTo();

        return Math.abs(ChronoUnit.DAYS.between(periodStartDate, periodEndDate));
    }

    private boolean hasOverlap(RecordByLine firstEmpl, RecordByLine secondEmpl) {
        return (firstEmpl.getDateFrom().isBefore(secondEmpl.getDateTo())
                || firstEmpl.getDateFrom().isEqual(secondEmpl.getDateTo()))
                && (firstEmpl.getDateTo().isAfter(secondEmpl.getDateFrom())
                || firstEmpl.getDateTo().isEqual(secondEmpl.getDateFrom()));
    }
}
