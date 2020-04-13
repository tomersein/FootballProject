package businessLayer.Tournament.Match;

import businessLayer.Team.Team;

import java.util.HashMap;

public class Stadium {
    private String name;
    private Match currentMatch;
    private HashMap <Integer,Match> previousMatches;
    private int numberOfSeats;
    private HashMap <String, Team> owners;

    //private Team currentPlayingTeam1;
    //private Team currentPlayingTeam2;


    /**
     * the constructor of stadium
     * @param name
     * @param currentMatch
     * @param previousMatches
     * @param numberOfSeats
     * @param owners
     */
    public Stadium(String name, Match currentMatch, HashMap <Integer,Match> previousMatches, int numberOfSeats, HashMap <String, Team> owners ) {
        this.name = name;
        this.currentMatch = currentMatch;
        this.previousMatches=previousMatches;
        this.numberOfSeats=numberOfSeats;
        this.owners=owners;
    }

    /**
     * the function creates a new stadium in the system
     * @param name the name of the stadium
     * @param numberOfSeats the number of seats the stadium has
     */
    public Stadium (String name, int numberOfSeats){
        this.name = name;
        currentMatch = null;
        previousMatches = new HashMap<>();
        this.numberOfSeats = numberOfSeats;
        this.owners = new HashMap<>();
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public Match getCurrentMatch() {
        return currentMatch;
    }


    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param currentMatch
     */
    public void setCurrentMatch(Match currentMatch) {
        this.currentMatch = currentMatch;
    }

    public HashMap<Integer, Match> getPreviousMatches() {
        return previousMatches;
    }

    public void setPreviousMatches(HashMap<Integer, Match> previousMatches) {
        this.previousMatches = previousMatches;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public HashMap<String, Team> getOwners() {
        return owners;
    }

    public void setOwners(HashMap<String, Team> owners) {
        this.owners = owners;
    }


    public boolean containTeam(Team team) {
        if(owners.containsKey(team.getTeamId())){
            return true;
        }
        return false;
    }

    public void removeTeam(Team team) {
        this.owners.remove(team.getTeamName());
    }

    public void addTeam(Team team) {
        this.owners.put(team.getTeamName(),team);
    }
}
