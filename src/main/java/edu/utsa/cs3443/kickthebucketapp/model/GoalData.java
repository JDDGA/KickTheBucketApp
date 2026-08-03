package edu.utsa.cs3443.kickthebucketapp.model;

import java.util.ArrayList;

/**
 * stores the app's active and completed goals, later used to create journal entries
*/
public class GoalData {
    public static ArrayList<String>
            activeGoals = new ArrayList<>();

    public static ArrayList<String>
            completedGoals = new ArrayList<>();
}
