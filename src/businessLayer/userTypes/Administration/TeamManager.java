package businessLayer.userTypes.Administration;

import businessLayer.Team.Team;
import businessLayer.userTypes.Subscriber;
import serviceLayer.SystemController;

import java.util.List;

public class TeamManager extends Subscriber implements OwnerEligible {

    private TeamOwner teamOwner;
    private String name;
    private List<Team> teams;

    /**
     * @param username
     * @param password
     * @param name
     * @param teams
     */
    public TeamManager(String username, String password, String name, List<Team> teams, SystemController systemController) {
        super(username, password, systemController);
        this.name = name;
        this.teams = teams;
        this.teamOwner =null;
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
    public List<Team> getTeams() {
        return teams;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param teams
     */
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }


    @Override
    public Boolean editDetails() {
        return null;
    }


    /**
     * this function determine if the coach is also an Owner
     * @return true if also an owner, false if only coach
     */
    @Override
    public boolean isOwner() {
        if(teamOwner ==null){
            return false;
        }
        return true;
    }

    public TeamOwner getTeamOwner() {
        return teamOwner;
    }

    public void setTeamOwner(TeamOwner teamOwner) {
        this.teamOwner = teamOwner;
    }
}
