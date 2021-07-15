package app.entities;

import java.time.LocalDate;

public class RecordByLine {
    private long employeeId;
    private long projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public RecordByLine(long employeeId, long projectId,
                        LocalDate dateFrom, LocalDate dateTo) {
        this.setEmployeeId(employeeId);
        this.setProjectId(projectId);
        this.setDateFrom(dateFrom);
        this.setDateTo(dateTo);
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public long getProjectId() {
        return this.projectId;
    }

    public LocalDate getDateFrom() {
        return this.dateFrom;
    }

    public LocalDate getDateTo() {
        return this.dateTo;
    }

    private void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    private void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    private void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    private void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
