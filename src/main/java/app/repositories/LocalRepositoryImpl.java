package app.repositories;

import app.entities.RecordByLine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LocalRepositoryImpl implements LocalRepository {
    private List<RecordByLine> database;

    public LocalRepositoryImpl() {
        this.database = new ArrayList<>();
    }

    @Override
    public void save(RecordByLine record) {
       this.database.add(record);
    }

    @Override
    public void saveAll(Collection<RecordByLine> records) {
       this.database.addAll(records);
    }

    @Override
    public List<RecordByLine> getAllRecords() {
       return Collections.unmodifiableList(this.database);
    }
}
