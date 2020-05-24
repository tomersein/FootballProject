/*
 * This file is generated by jOOQ.
 */
package dataLayer.Tables.tables.records;


import dataLayer.Tables.tables.SeasonTeams;

import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SeasonTeamsRecord extends TableRecordImpl<SeasonTeamsRecord> implements Record3<String, Integer, String> {

    private static final long serialVersionUID = -1273055957;

    /**
     * Setter for <code>demodb.season_teams.LeagueID</code>.
     */
    public void setLeagueid(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>demodb.season_teams.LeagueID</code>.
     */
    public String getLeagueid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>demodb.season_teams.SeasonID</code>.
     */
    public void setSeasonid(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>demodb.season_teams.SeasonID</code>.
     */
    public Integer getSeasonid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>demodb.season_teams.TeamID</code>.
     */
    public void setTeamid(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>demodb.season_teams.TeamID</code>.
     */
    public String getTeamid() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, Integer, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<String, Integer, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return SeasonTeams.SEASON_TEAMS.LEAGUEID;
    }

    @Override
    public Field<Integer> field2() {
        return SeasonTeams.SEASON_TEAMS.SEASONID;
    }

    @Override
    public Field<String> field3() {
        return SeasonTeams.SEASON_TEAMS.TEAMID;
    }

    @Override
    public String component1() {
        return getLeagueid();
    }

    @Override
    public Integer component2() {
        return getSeasonid();
    }

    @Override
    public String component3() {
        return getTeamid();
    }

    @Override
    public String value1() {
        return getLeagueid();
    }

    @Override
    public Integer value2() {
        return getSeasonid();
    }

    @Override
    public String value3() {
        return getTeamid();
    }

    @Override
    public SeasonTeamsRecord value1(String value) {
        setLeagueid(value);
        return this;
    }

    @Override
    public SeasonTeamsRecord value2(Integer value) {
        setSeasonid(value);
        return this;
    }

    @Override
    public SeasonTeamsRecord value3(String value) {
        setTeamid(value);
        return this;
    }

    @Override
    public SeasonTeamsRecord values(String value1, Integer value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SeasonTeamsRecord
     */
    public SeasonTeamsRecord() {
        super(SeasonTeams.SEASON_TEAMS);
    }

    /**
     * Create a detached, initialised SeasonTeamsRecord
     */
    public SeasonTeamsRecord(String leagueid, Integer seasonid, String teamid) {
        super(SeasonTeams.SEASON_TEAMS);

        set(0, leagueid);
        set(1, seasonid);
        set(2, teamid);
    }
}
