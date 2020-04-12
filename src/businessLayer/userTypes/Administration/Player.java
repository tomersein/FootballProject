package businessLayer.userTypes.Administration;

import businessLayer.Team.Team;
import businessLayer.userTypes.Subscriber;
import serviceLayer.SystemController;

import java.util.List;

public class Player extends Subscriber {
    private String name;
    private String birthDate;
    private String fieldJob;
    private List<Team> teams;

    /**
     * @param username
     * @param password
     * @param name
     * @param birthDate
     * @param fieldJob
     * @param teams
     */
    public Player(String username, String password, String name, String birthDate, String fieldJob, List<Team> teams, SystemController systemController) {
        super(username, password,systemController);
        this.name = name;
        this.birthDate = birthDate;
        this.fieldJob = fieldJob;
        this.teams = teams;
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
    public List<Team> getTeams() {
        return teams;
    }

    @Override
    public Boolean editDetails() {
        return null;
    }
}