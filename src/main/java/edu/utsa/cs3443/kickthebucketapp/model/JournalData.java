package edu.utsa.cs3443.kickthebucketapp.model;

import java.time.LocalDate;
import java.util.ArrayList;


public class JournalData {

    public static ArrayList<JournalEntry> entries = new ArrayList<>();

    public static void syncFromGoals() {
        for (String goal : GoalData.completedGoals) {
            if (!isAlreadyLogged(goal)) {
                entries.add(new JournalEntry(goal, LocalDate.now()));
            }
        }
    }

    private static boolean isAlreadyLogged(String goal) {
        for (JournalEntry entry : entries) {
            if (entry.getGoalText().equals(goal)) {
                return true;
            }
        }
        return false;
    }

    public static void removeEntry(JournalEntry entry) {
        entries.remove(entry);
        GoalData.completedGoals.remove(entry.getGoalText());
    }
}
