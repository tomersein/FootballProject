package businessLayer.userTypes.Administration;

import businessLayer.Team.Team;
import businessLayer.userTypes.Subscriber;
import serviceLayer.SystemController;

import java.util.List;

public class Player extends Subscriber implements OwnerEligible {

    private TeamOwner teamOwner;
    private String name;
    private String birthDate;
    private String fieldJob;
    private Team team;
    private boolean assign;
    /**
     * @param username
     * @param password
     * @param name
     * @param birthDate
     * @param fieldJob
     * @param team
     */
    public Player(String username, String password, String name, String birthDate, String fieldJob, Team team, SystemController systemController) {
        super(username, password,systemController);
        this.name = name;
        this.birthDate = birthDate;
        this.fieldJob = fieldJob;
        this.team = team;
        this.assign = false;
        this.teamOwner =null;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param birthDate
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @param fieldJob
     */
    public void setFieldJob(String fieldJob) {
        this.fieldJob = fieldJob;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    public void setAssign(boolean assign) {
        this.assign = assign;
    }

    /**
     * @return
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @return
     */
    public String getFieldJob() {
        return fieldJob;
    }

    /**
     * @return
     */
    public Team getTeam() {
        return team;
    }

    @Override
    public Boolean editDetails() {
        return null;
    }

    public Boolean getAssigned(){
        return assign;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * this function determine if the Player is also an Owner
     * @return true if also an owner, false if only player
     */
    @Override
    public boolean isOwner() {
        if(teamOwner ==null){
            return false;
        }
        return true;
    }

    protected TeamOwner getTeamOwner() {
        return teamOwner;
    }

    protected void setTeamOwner(TeamOwner teamOwner) {
        this.teamOwner = teamOwner;
    }
}
