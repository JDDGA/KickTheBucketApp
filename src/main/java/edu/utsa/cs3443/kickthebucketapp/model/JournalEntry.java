package edu.utsa.cs3443.kickthebucketapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * stores all info for a completed goal
*/
public class JournalEntry {

    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d, yyyy");

    private String goalText;
    private LocalDate dateCompleted;
    private String reflection;

    /**
     * constructor
     * creates a new journal entry
    */
    public JournalEntry(String goalText, LocalDate dateCompleted) {
        this.goalText = goalText;
        this.dateCompleted = dateCompleted;
        this.reflection = "";
    }

    /**
     * GETTER
    */
    public String getGoalText() {
        return goalText;
    }

    /**
     * SETTER
     */
    public void setGoalText(String goalText) {
        this.goalText = goalText;
    }

    /**
     * GETTER
     */
    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    /**
     * SETTER
     */
    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    /**
     * GETTER
     */
    public String getReflection() {
        return reflection;
    }

    /**
     * SETTER
     */
    public void setReflection(String reflection) {
        this.reflection = (reflection == null) ? "" : reflection;
    }

    /**
     * CHECKER
     */
    public boolean hasReflection() {
        return reflection != null && !reflection.trim().isEmpty();
    }

    /**
     * meant to format the date it was completed
     */
    public String getFormattedDate() {
        return dateCompleted.format(DISPLAY_FORMAT);
    }
}
