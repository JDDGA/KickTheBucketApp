package edu.utsa.cs3443.kickthebucketapp.model;

import java.util.ArrayList;

public class GoalData {
    /**
     * for goals screen, finished goals get sent to journal / GoalData.completedGoals
     */
    public static ArrayList<String>
            activeGoals = new ArrayList<>();

    /**
     * for journal screen
     */
    public static ArrayList<String>
            completedGoals = new ArrayList<>();
}
