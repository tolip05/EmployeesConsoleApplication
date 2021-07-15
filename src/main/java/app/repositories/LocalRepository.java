package app.repositories;

import app.entities.RecordByLine;

import java.util.Collection;
import java.util.List;

public interface LocalRepository {
    void save(RecordByLine record);

    void saveAll(Collection<RecordByLine> records);

    List<RecordByLine> getAllRecords();
}
