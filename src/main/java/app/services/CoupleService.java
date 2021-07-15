package app.services;

import app.entities.Couple;
import app.entities.RecordByLine;

import java.util.List;

public interface CoupleService {
    void addEmployeeRecords(List<RecordByLine> records);

    List<Couple> findAllTeamsWithOverlap();
}
