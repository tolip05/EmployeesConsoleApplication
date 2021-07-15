package app.entities;

public class Project {
    private long projectId;
    private long workedDays;

    public Project(long projectId, long workedDays) {
        this.setProjectId(projectId);
        this.setWorkedDays(workedDays);
    }

    public long getProjectId() {
        return this.projectId;
    }

    public long getWorkedDays() {
        return this.workedDays;
    }

    private void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    private void setWorkedDays(long workedDays) {
        this.workedDays = workedDays;
    }
}
