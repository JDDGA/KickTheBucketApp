package edu.utsa.cs3443.kickthebucketapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JournalEntry {

    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d, yyyy");

    private String goalText;
    private LocalDate dateCompleted;
    private String reflection;

    public JournalEntry(String goalText, LocalDate dateCompleted) {
        this.goalText = goalText;
        this.dateCompleted = dateCompleted;
        this.reflection = "";
    }

    public String getGoalText() {
        return goalText;
    }

    public void setGoalText(String goalText) {
        this.goalText = goalText;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getReflection() {
        return reflection;
    }

    public void setReflection(String reflection) {
        this.reflection = (reflection == null) ? "" : reflection;
    }

    public boolean hasReflection() {
        return reflection != null && !reflection.trim().isEmpty();
    }

    public String getFormattedDate() {
        return dateCompleted.format(DISPLAY_FORMAT);
    }
}
