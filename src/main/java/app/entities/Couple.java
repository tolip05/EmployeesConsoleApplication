package app.entities;

import java.util.ArrayList;
import java.util.List;

public class Couple {
    private long firstEmplId;
    private long secondEmplId;
    private long totalDuration;
    private List<Project>projects;

    public Couple(long firstEmplId, long secondEmplId, long totalDuration) {
        this.setFirstEmplId(firstEmplId);
        this.setSecondEmplId(secondEmplId);
        this.setTotalDuration(totalDuration);
        this.projects = new ArrayList<>();
    }

    public long getFirstEmplId() {
        return this.firstEmplId;
    }

    public long getSecondEmplId() {
        return this.secondEmplId;
    }

    public long getTotalDuration() {
        return this.totalDuration;
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    private void setFirstEmplId(long firstEmplId) {
        this.firstEmplId = firstEmplId;
    }

    private void setSecondEmplId(long secondEmplId) {
        this.secondEmplId = secondEmplId;
    }

    private void setTotalDuration(long totalDuration) {
        this.totalDuration = totalDuration;
    }

    private void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    public void addOverlapDuration(long overlap) {
        this.totalDuration += overlap;
    }
}
