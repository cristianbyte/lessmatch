package com.uni_verso.uni_verso.util;

import java.util.List;

/**
 * Utility class to calculate various metrics for song verse matching between users
 */
public class PairingScoreCalculator {

    /**
     * Calculates how many unique verses were selected by either user
     * 
     * @param user1Selections List of boolean flags for first user's selections
     * @param user2Selections List of boolean flags for second user's selections
     * @return Total count of verses selected by either user
     */
    public static int calculateSelectedVerses(List<Boolean> user1Selections, List<Boolean> user2Selections) {
        int count = 0;
        
        // Ensure both lists have values and are of same length
        if (user1Selections == null || user2Selections == null ||
            user1Selections.size() != user2Selections.size()) {
            return 0;
        }
        
        // Count verses that were selected by either user
        for (int i = 0; i < user1Selections.size(); i++) {
            if (Boolean.TRUE.equals(user1Selections.get(i)) || Boolean.TRUE.equals(user2Selections.get(i))) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * Calculates how many verses both users selected
     * 
     * @param user1Selections List of boolean flags for first user's selections
     * @param user2Selections List of boolean flags for second user's selections
     * @return Count of verses selected by both users
     */
    public static int calculateMatchedVerses(List<Boolean> user1Selections, List<Boolean> user2Selections) {
        int count = 0;
        
        // Ensure both lists have values and are of same length
        if (user1Selections == null || user2Selections == null ||
            user1Selections.size() != user2Selections.size()) {
            return 0;
        }
        
        // Count verses that were selected by both users
        for (int i = 0; i < user1Selections.size(); i++) {
            if (Boolean.TRUE.equals(user1Selections.get(i)) && Boolean.TRUE.equals(user2Selections.get(i))) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * Calculates the percentage of matching verses compared to total selected verses
     * 
     * @param selectedVerses Total count of verses selected by either user
     * @param matchedVerses Count of verses selected by both users
     * @return Percentage of matching verses (0-100)
     */
    public static double calculateMatchPercentage(int selectedVerses, int matchedVerses) {
        if (selectedVerses == 0) {
            return 0.0;
        }
        
        return (double) matchedVerses / selectedVerses * 100;
    }
    
    /**
     * Calculates the overall connection score based on matches and total verses
     * 
     * @param user1Selections List of boolean flags for first user's selections
     * @param user2Selections List of boolean flags for second user's selections
     * @param matchedVerses Count of verses selected by both users
     * @param totalVerses Total number of verses in the song
     * @return Connection score (0-100)
     */
    public static double calculateConnectionScore(List<Boolean> user1Selections, 
                                               List<Boolean> user2Selections,
                                               int matchedVerses,
                                               int totalVerses) {
        // Count individual selections
        int user1Count = countSelections(user1Selections);
        int user2Count = countSelections(user2Selections);
        
        // If nobody selected anything, connection is zero
        if (user1Count == 0 || user2Count == 0) {
            return 0.0;
        }
        
        // Calculate matching factor (how much they agree on what they selected)
        double matchingFactor = (double) matchedVerses / Math.max(user1Count, user2Count);
        
        // Calculate song coverage factor (how much of the song resonated with them)
        double coverageFactor = (double) matchedVerses / totalVerses;
        
        // Weighted formula for connection score
        // 70% based on how much they matched on what they selected
        // 30% based on how much of the song resonated with both of them
        return (matchingFactor * 0.7 + coverageFactor * 0.3) * 100;
    }
    
    /**
     * Helper method to count selections in a list
     */
    private static int countSelections(List<Boolean> selections) {
        if (selections == null) {
            return 0;
        }
        
        int count = 0;
        for (Boolean selection : selections) {
            if (Boolean.TRUE.equals(selection)) {
                count++;
            }
        }
        
        return count;
    }
}