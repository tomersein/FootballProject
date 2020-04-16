package businessLayer.userTypes.Administration;

import businessLayer.Team.Team;
import businessLayer.userTypes.Subscriber;
import businessLayer.userTypes.SystemController;

public class Player extends Subscriber implements OwnerEligible {

    private TeamOwner teamOwner;
    private String birthDate;
    private String fieldJob;
    private Team team;
    /**
     * @param username
     * @param password
     * @param name
     * @param birthDate
     * @param fieldJob
     * @param team
     */
    public Player(String username, String password, String name, String birthDate, String fieldJob, Team team, SystemController systemController) {
        super(username, password,name,systemController);
        this.birthDate = birthDate;
        this.fieldJob = fieldJob;
        this.team = team;
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
