package businessLayer.Tournament;

import businessLayer.Tournament.Match.Stadium;
import businessLayer.Utilities.alertSystem.AlertSystem;
import businessLayer.Utilities.logSystem.LoggingSystem;
import businessLayer.userTypes.Administration.AssociationRepresentative;
import businessLayer.userTypes.Administration.Referee;
import businessLayer.userTypes.Subscriber;
import businessLayer.userTypes.SystemController;
import dataLayer.DemoDB;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LeagueController {

    //----------------OLD DATA STRUCTURES THAT ARE LOCATED IN THE DB-----------------------//
    //private HashMap<String, League> leagues;
    //private businessLayer.Tournament.RankingPolicy rankingPolicy; //might be list as well, define later
    //private businessLayer.Tournament.MatchingPolicy matchingPolicy; //might be list as well, define later
    //private List<AssociationRepresentative> associationRepresentatives;
    //private HashMap<String, Referee> referees;

    private LoggingSystem loggingSystem;
    private AlertSystem alertSystem;
    private SystemController systemController;
    //private DemoDB DB;

    public LeagueController() {

    }

    public void setSystemController(SystemController systemController) {
        this.systemController = systemController;
    }

    /**
     * get a random stadium from the DB
     *
     * @return
     */
    public Stadium getRandomStadium() {
        return systemController.findDefaultStadium();
    }

    /**
     * @param alerts
     * @return
     */
    /*
    public boolean sendAlerts(List<String> alerts) {

        return true;
    }
    */

    /**
     * @param logs
     * @return
     */
    public boolean sendLogs(List<String> logs) {

        return true;
    }


/**
 *
 * @return
 *//*

    public businessLayer.Tournament.RankingPolicy getRankingPolicy() {
        return rankingPolicy;
    }

    */
/**
 *
 * @param rankingPolicy
 *//*

    public void setRankingPolicy(businessLayer.Tournament.RankingPolicy rankingPolicy) {
        this.rankingPolicy = rankingPolicy;
    }

    */
/**
 *
 * @return
 *//*

    public businessLayer.Tournament.MatchingPolicy getMatchingPolicy() {
        return matchingPolicy;
    }

    */
/**
 *
 * @param matchingPolicy
 *//*

    public void setMatchingPolicy(businessLayer.Tournament.MatchingPolicy matchingPolicy) {
        this.matchingPolicy = matchingPolicy;
    }
*/

    /**
     * @return
     */
    public LoggingSystem getLoggingSystem() {
        return loggingSystem;
    }

    /**
     * @param loggingSystem
     */
    public void setLoggingSystem(LoggingSystem loggingSystem) {
        this.loggingSystem = loggingSystem;
    }


    /**
     * @return
     */
    public AlertSystem getAlertSystem() {
        return alertSystem;
    }

    /**
     * @param alertSystem
     */
    public void setAlertSystem(AlertSystem alertSystem) {
        this.alertSystem = alertSystem;
    }

    /**
     * The function returns whether a league with the same ID exists
     *
     * @param leagueID
     * @return true/false
     */
    public boolean doesLeagueExist(String leagueID) {
        return systemController.containsLeague(leagueID);
    }

    /**
     * The function creates a league and returns whether the league was created successfully or not
     *
     * @param leagueID
     * @return true/false
     */
    public boolean createLeague(String leagueID) {

        if (leagueID == null) {
            return false;
        }
        League newLeague = new League(leagueID);
        systemController.addLeagueToDB(leagueID, newLeague);
        if (!systemController.containsLeague(leagueID)) {
            return false;
        }
        return true;
    }

    /**
     * The function creates a season within league and returns whether the season was created successfully or not
     *
     * @param leagueID
     * @param seasonID
     * @param startingDate
     * @return
     */
    public boolean addSeasonToLeague(String leagueID, int seasonID, Date startingDate, Date endingDate) {

        League leagueToAdd = systemController.getLeagueFromDB(leagueID);
        if (leagueToAdd == null) {
            return false;
        }
        return leagueToAdd.addSeasonToLeague(seasonID, startingDate, endingDate);
        //todo: check if when you pull out a complex object from a hashmap, the changes you do to it are registered in the hashmap
    }

    /**
     * The function receives referee from the system controller, removes it from its data structures and returns whether the removal was successful or not
     *
     * @param referee
     * @return true/false
     */
    public boolean removeReferee(Subscriber referee) {
        if (!(referee instanceof Referee)) {
            return false;
        }
        String refName = referee.getUsername();
        if (systemController.containsReferee(refName)) {
            systemController.removeReferee(refName);
            return true;
        }
        return false;
    }


    /**
     * The function assigns a referee from the system to a season within a specific league, returns whether the assignment was successful or not
     *
     * @param refUserName
     * @param leagueName
     * @param seasonID
     * @return true/false
     */
    public boolean addRefereeToSeasonInLeague(String refUserName, String leagueName, int seasonID) {

        if (refUserName == null || leagueName == null) {
            return false;
        }
        if (systemController.containsLeague(leagueName) && systemController.containsReferee(refUserName)) {
            League addingToLeague = systemController.getLeagueFromDB(leagueName);
            Referee refToAssign = systemController.getRefereeFromDB(refUserName);
            return addingToLeague.addRefereeToSeason(refToAssign, seasonID);
        }
        return false;
    }


    /**
     * The function adds an association representative to the data structure in the league controller
     *
     * @param associationRep
     */
    public void addAssociationRepToController(AssociationRepresentative associationRep) {
        if (associationRep != null) {
            if (!systemController.containsInSystemAssociationRepresentative(associationRep.getUsername())) {
                systemController.addSubscriberToDB(associationRep.getUsername(), associationRep);
            }
        }
    }


    /**
     * The function adds referee to the league controller data structures by receiving it from the system controller
     *
     * @param referee
     */
    public void addRefereeToDataFromSystemController(Referee referee) {

        if (referee != null && !systemController.containsReferee(referee.getUsername())) {
            systemController.addSubscriberToDB(referee.getUsername(), referee);
        }
    }


    //-----------------------------AssociationRepresentative: Link to Service Layer------------------------

    /**
     * The function receives username and leagueID from the interface layer and calls the creation function in the business layer
     *
     * @param leagueID
     * @param username
     * @return
     */
    public boolean addLeagueThroughRepresentative(String leagueID, String username) {

        if (leagueID != null && username != null) {
            Subscriber user = systemController.getSubscriberByUserName(username);
            if (user instanceof AssociationRepresentative) {
                AssociationRepresentative userRep = (AssociationRepresentative) user;
                return userRep.createLeague(leagueID);
            }
        }
        return false;
    }

    /**
     * The function receives username, leagueID, seasonID and dates from the interface layer and calls the creation function in the business layer
     *
     * @param leagueID
     * @param seasonID
     * @param startingDate
     * @param endingDate
     * @param username
     * @return
     */
    public boolean addSeasonThroughRepresentative(String leagueID, int seasonID, Date startingDate, Date endingDate, String username) {

        if (leagueID != null && username != null) {
            Subscriber user = systemController.getSubscriberByUserName(username);
            if (user instanceof AssociationRepresentative) {
                AssociationRepresentative userRep = (AssociationRepresentative) user;
                return userRep.createSeason(leagueID, seasonID, startingDate, endingDate);
            }
        }
        return false;
    }

    /**
     * The function receives a referee's username and the representative's username from the interface layer and calls the creation function in the business layer
     *
     * @param refUsername
     * @param username
     * @return
     */
    public boolean createRefereeThroughRepresentative(String refUsername, String username) {

        if (refUsername != null && username != null) {
            Subscriber user = systemController.getSubscriberByUserName(username);
            if (user instanceof AssociationRepresentative) {
                AssociationRepresentative userRep = (AssociationRepresentative) user;
                return userRep.createReferee(refUsername);
            }
        }
        return false;
    }

    /**
     * The function receives a referee's username and the representative's username from the interface layer and calls the removal function in the business layer
     *
     * @param refUsername
     * @param username
     * @return
     */
    public boolean removeRefereeThroughRepresentative(String refUsername, String username) {

        if (refUsername != null && username != null) {
            Subscriber user = systemController.getSubscriberByUserName(username);
            if (user instanceof AssociationRepresentative) {
                AssociationRepresentative userRep = (AssociationRepresentative) user;
                return userRep.removeRefree(refUsername);
            }
        }
        return false;
    }

    /**
     * The function receives a referee's username, leagueID, seasonID and the representative's username from the interface layer and calls the assigning function in the business layer
     *
     * @param refUsername
     * @param leagueName
     * @param seasonID
     * @param username
     * @return
     */
    public boolean assignRefereeThroughRepresentative(String refUsername, String leagueName, int seasonID, String username) {

        if (refUsername != null && leagueName != null && username != null) {
            Subscriber user = systemController.getSubscriberByUserName(username);
            if (user instanceof AssociationRepresentative) {
                AssociationRepresentative userRep = (AssociationRepresentative) user;
                return userRep.assignRefereeToSeason(refUsername, leagueName, seasonID);
            }
        }
        return false;
    }


    /**
     * The function receives a team's name and the representative's username from the interface layer and calls the assigning function in the business layer
     *
     * @param teamName
     * @param username
     * @return
     */
    public boolean confirmTeamRequestThroughRepresentative(String teamName, String username) {

        if (teamName != null && username != null) {
            Subscriber user = systemController.getSubscriberByUserName(username);
            if (user instanceof AssociationRepresentative) {
                AssociationRepresentative userRep = (AssociationRepresentative) user;
                return userRep.confirmTeamRequest(teamName);
            }
        }
        return false;
    }


    /**
     * The function receives a stadium's identifier, number of seats and the representative's username from the interface layer and calls the creating function in the business layer
     * @param nameStadium
     * @param numberOfSeats
     * @param username
     * @return
     */
    public boolean createNewStadiumThroughRepresentative(String nameStadium, String numberOfSeats, String username) {

        if (nameStadium != null && numberOfSeats != null && username != null) {
            Subscriber user = systemController.getSubscriberByUserName(username);
            if (user instanceof AssociationRepresentative) {
                AssociationRepresentative userRep = (AssociationRepresentative) user;
                return userRep.createNewStadium(nameStadium, numberOfSeats);
            }
        }
        return false;
    }
}