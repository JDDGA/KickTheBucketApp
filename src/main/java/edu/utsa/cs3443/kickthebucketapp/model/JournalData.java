package edu.utsa.cs3443.kickthebucketapp.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * meant to store and manage all journal entries passed over from created goals
*/
public class JournalData {

    public static ArrayList<JournalEntry> entries = new ArrayList<>();

    /**
     * Adds completed goals into the journal if they haven't been added already
    */
    public static void syncFromGoals() {
        for (String goal : GoalData.completedGoals) {
            if (!isAlreadyLogged(goal)) {
                entries.add(new JournalEntry(goal, LocalDate.now()));
            }
        }
    }

    /**
     * checks if a goal already exists
    */
    private static boolean isAlreadyLogged(String goal) {
        for (JournalEntry entry : entries) {
            if (entry.getGoalText().equals(goal)) {
                return true;
            }
        }
        return false;
    }

    /**
     * removes a journal entry along with its goal
    */
    public static void removeEntry(JournalEntry entry) {
        entries.remove(entry);
        GoalData.completedGoals.remove(entry.getGoalText());
    }
}
