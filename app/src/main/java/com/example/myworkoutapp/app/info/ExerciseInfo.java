package com.example.myworkoutapp.app.info;

public class ExerciseInfo extends ActivityInfo implements Comparable <ExerciseInfo>{
    private String maxWeight;
    private String recommendedReps;
    private String recommendedSets;

    //Exercise Info
    public static String exId = "exerciseID";
    public static String exDescription = "exerciseDescription";
    public static String exName = "exerciseName";
    public static String exMaxWeight = "maxWeight";
    public static String exRecReps = "recommendedReps";
    public static String exRecSets = "recommendedSets";

    public ExerciseInfo(String exerciseID, String name, String description,
                        String recommendedReps, String recommendedSets)
    {
        super(exerciseID, name, description);
        this.maxWeight = "0";
        this.recommendedReps = recommendedReps;
        this.recommendedSets = recommendedSets;
    }

    public ExerciseInfo(String exerciseID, String name, String description,
                        String recommendedReps, String recommendedSets, String maxWeight)
    {
        super(exerciseID, name, description);
        this.maxWeight = maxWeight;
        this.recommendedReps = recommendedReps;
        this.recommendedSets = recommendedSets;
    }

    public String getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getRecommendedReps() {
        return recommendedReps;
    }

    public void setRecommendedReps(String recommendedReps) {
        this.recommendedReps = recommendedReps;
    }

    public String getRecommendedSets() {
        return recommendedSets;
    }

    public void setRecommendedSets(String recommendedSets) {
        this.recommendedSets = recommendedSets;
    }

    @Override
    public int compareTo(ExerciseInfo o) {
        return super.name.compareTo(o.name);
    }
}
