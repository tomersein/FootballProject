package dataLayer;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static dataLayer.Tables.Tables.*;

public class DBSeasons implements DB_Inter {
    String username = "root";
    String password = "Messi1Ronaldo2";
    String myDriver = "org.mariadb.jdbc.Driver";
    //String myUrl = "jdbc:mysql://132.72.65.33:3306/SoccerProject";
    String myUrl = "jdbc:mysql://localhost:3306/localsoccer";
    Connection connection = null;



    public DBSeasons(){
        //connect to DB and save to field in class.
        try {
            Class.forName(myDriver);
            connection = DriverManager.getConnection(myUrl,username,password);
            System.out.println("Successful connection to server db ");
        } catch (SQLException e) {
            System.out.println("error connecting to server. connection is now null");
        } catch (ClassNotFoundException e) {
            System.out.println("error connecting to driver");
        }
    }

    @Override
    public boolean containInDB(String objectName) {
        return false;
    }

    public boolean containsInDB(String leagueID, int seasonID){
        DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
        Result<?> result = create.select().from(SEASONS).where(SEASONS.LEAGUEID.eq(leagueID).
                and(SEASONS.SEASONID.eq(seasonID))).fetch();
        return (!result.isEmpty());
    }


    @Override
    public Object selectFromDB(String objectName) {
        return null;
    }

    @Override
    public boolean removeFromDB(String objectName) {
        return false;
    }

    public HashMap<String, LinkedList<String>> selectFromDB (String leagueID, int seasonID){
        HashMap<String,LinkedList<String>> allDetails = new HashMap<>();
        if(containsInDB(leagueID,seasonID)){
            LinkedList<String> matches = new LinkedList<>();
            LinkedList<String> referee = new LinkedList<>();
            LinkedList<String> teams = new LinkedList<>();
            DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
            Result<?> result = create.select().from(SEASON_MATCHES).where(SEASONS.LEAGUEID.eq(leagueID).
                    and(SEASONS.SEASONID.eq(seasonID))).fetch();
            for (Record r : result){
                matches.add(r.get(SEASON_MATCHES.MATCHID).toString());
            }
            allDetails.put("matches",matches);
            result = create.select().from(SEASON_REFEREE).where(SEASONS.LEAGUEID.eq(leagueID).
                    and(SEASONS.SEASONID.eq(seasonID))).fetch();
            for (Record r : result){
                referee.add(r.get(SEASON_REFEREE.REFEREEID));
            }
            allDetails.put("referees",referee);
            result = create.select().from(SEASON_TEAMS).where(SEASONS.LEAGUEID.eq(leagueID).
                    and(SEASONS.SEASONID.eq(seasonID))).fetch();
            for (Record r : result){
                teams.add(r.get(SEASON_TEAMS.TEAMID));
            }
            allDetails.put("teams",teams);
            result = create.select().from(SEASON_TABLELEAGUE).where(SEASONS.LEAGUEID.eq(leagueID).
                    and(SEASONS.SEASONID.eq(seasonID))).fetch();
            LinkedList <String> table = new LinkedList<>();
            for (Record r : result){
                table.add(r.get(SEASON_TABLELEAGUE.TEAMID));
                table.add(r.get(SEASON_TABLELEAGUE.NUMOFGAMES).toString());
                table.add(r.get(SEASON_TABLELEAGUE.GOALSFOR).toString());
                table.add(r.get(SEASON_TABLELEAGUE.GOALSAGAINST).toString());
                table.add(r.get(SEASON_TABLELEAGUE.POINTS).toString());
            }
            allDetails.put("table",table);
            LinkedList<String> rankingPolicy = new LinkedList<>();
            result = create.select().from(RANKINGPOLICY).where(SEASONS.LEAGUEID.eq(leagueID).
                    and(SEASONS.SEASONID.eq(seasonID))).fetch();
            for (Record r : result){
                rankingPolicy.add(r.get(RANKINGPOLICY.LEAGUEID));
                rankingPolicy.add(r.get(RANKINGPOLICY.SEASONID).toString());
                rankingPolicy.add(r.get(RANKINGPOLICY.WIN).toString());
                rankingPolicy.add(r.get(RANKINGPOLICY.LOSE).toString());
                rankingPolicy.add(r.get(RANKINGPOLICY.TIE).toString());
            }
            allDetails.put("rankingPolicy",rankingPolicy);
            result = create.select().from(MATCHING_POLICY).where(SEASONS.LEAGUEID.eq(leagueID).
                    and(SEASONS.SEASONID.eq(seasonID))).fetch();
            LinkedList <String> matchingPolicy = new LinkedList<>();
            for (Record r : result){
                matchingPolicy.add(r.get(MATCHING_POLICY.TYPE));
            }
            allDetails.put("matchingPolicy",matchingPolicy);
        }
        return allDetails;
    }


    public boolean removeFromDB(String leagueID, int seasonID) {
        if(containsInDB(leagueID,seasonID)){
            DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
            create.delete(SEASONS)
                    .where(SEASONS.LEAGUEID.eq(leagueID)).and(SEASONS.SEASONID.eq(seasonID)).execute();
            return true;
        }
        return false;
    }

    @Override
    public boolean addToDb(String username, String password, String name, Map<String, ArrayList<String>> objDetails) {
        return false;
    }

    public boolean addToDb (String leagueID,int seasonID, HashMap <String,LinkedList<String>> details, Date start, Date end){
        if(!containsInDB(leagueID,seasonID)){
            DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
            create.insertInto(SEASONS,SEASONS.LEAGUEID,SEASONS.SEASONID).values(leagueID,seasonID).execute();
            //create.insertInto(SEASONS,SEASONS.SEASONID).values(seasonID).execute();
            create.insertInto(SEASONS,SEASONS.STARTDATE).execute();
            create.insertInto(SEASONS,SEASONS.ENDDATE).execute();
            LinkedList<String> teams = details.get("teams");
            if(teams!=null) {
                for (String team : teams) {
                    create.insertInto(SEASON_TEAMS, SEASON_TEAMS.LEAGUEID, SEASON_TEAMS.SEASONID, SEASON_TEAMS.TEAMID).values(leagueID, seasonID, team).execute();
                }
            }
            LinkedList<String> referees = details.get("referees");
            if(referees!=null) {
                for (String referee : referees) {
                    create.insertInto(SEASON_REFEREE, SEASON_REFEREE.LEAGUEID, SEASON_REFEREE.SEASONID, SEASON_REFEREE.REFEREEID).values(leagueID, seasonID, referee).execute();
                }
            }
            LinkedList<String> matches = details.get("matches");
            if(matches!=null) {
                for (String match : matches) {
                    int matchInt = Integer.parseInt(match);
                    create.insertInto(SEASON_MATCHES, SEASON_MATCHES.LEAGUEID, SEASON_MATCHES.SEASONID, SEASON_MATCHES.MATCHID).values(leagueID, seasonID, matchInt).execute();
                }
            }
            LinkedList<String> tables = details.get("table");
            if(tables!=null){
            for(int i=0;i<tables.size();i=i+5) {
                String team = tables.get(i);
                int numOFGames = Integer.parseInt(tables.get(i + 1));
                int goalsFor = Integer.parseInt(tables.get(i + 2));
                int goalsAgainst = Integer.parseInt(tables.get(i + 3));
                int points = Integer.parseInt(tables.get(i + 4));
                create.insertInto(SEASON_TABLELEAGUE, SEASON_TABLELEAGUE.LEAGUEID, SEASON_TABLELEAGUE.SEASONID, SEASON_TABLELEAGUE.TEAMID,
                        SEASON_TABLELEAGUE.NUMOFGAMES, SEASON_TABLELEAGUE.GOALSFOR, SEASON_TABLELEAGUE.GOALSAGAINST, SEASON_TABLELEAGUE.POINTS)
                        .values(leagueID, seasonID, team, numOFGames, goalsFor, goalsAgainst, points).execute();
            }
            }
            LinkedList<String> rankingPolicy = details.get("rankingPolicy");
            int win = Integer.parseInt(rankingPolicy.get(0));
            int lose = Integer.parseInt(rankingPolicy.get(1));
            int tie = Integer.parseInt(rankingPolicy.get(2));
            create.insertInto(RANKINGPOLICY,RANKINGPOLICY.LEAGUEID,RANKINGPOLICY.SEASONID,RANKINGPOLICY.WIN,
                    RANKINGPOLICY.LOSE,RANKINGPOLICY.TIE).values(leagueID,seasonID,win,lose,tie).execute();

            LinkedList<String> matchingPolicy = details.get("matchingPolicy");
            if(matchingPolicy!=null) {
                String type = matchingPolicy.get(0);
                create.insertInto(MATCHING_POLICY, MATCHING_POLICY.LEAGUEID, MATCHING_POLICY.SEASONID, MATCHING_POLICY.TYPE).values(leagueID, seasonID, type).execute();
            }
            return true;
        }
        return false;
    }

    public boolean addRefereeInSeason(String leagueID, int seasonID, String referee){
        if(containsInDB(leagueID,seasonID)){
            DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
            create.insertInto(SEASON_REFEREE, SEASON_REFEREE.LEAGUEID,SEASON_REFEREE.SEASONID,
                    SEASON_REFEREE.REFEREEID).values(leagueID,seasonID,referee).execute();
            return true;
        }
        return false;
    }

    public boolean addTeamInSeason(String leagueID, int seasonID, String teamID){
        if(containsInDB(leagueID,seasonID)){
            DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
            create.insertInto(SEASON_TEAMS, SEASON_TEAMS.LEAGUEID,SEASON_TEAMS.SEASONID,
                    SEASON_TEAMS.TEAMID).values(leagueID,seasonID,teamID).execute();
            create.insertInto(SEASON_TABLELEAGUE,SEASON_TABLELEAGUE.LEAGUEID,SEASON_TABLELEAGUE.SEASONID,SEASON_TABLELEAGUE.TEAMID,SEASON_TABLELEAGUE.NUMOFGAMES,
                    SEASON_TABLELEAGUE.GOALSFOR,SEASON_TABLELEAGUE.GOALSAGAINST,SEASON_TABLELEAGUE.POINTS).values(leagueID,seasonID,teamID,0,0,0,0).execute();
            return true;
        }
        return false;
    }

    public boolean addMatchTableToSeason(String leagueID, int seasonID, LinkedList <Integer> matchNum){
        if(containsInDB(leagueID,seasonID)) {
            DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
            if(matchNum!=null) {
                for(int i=0;i<matchNum.size();i++) {
                    create.insertInto(SEASON_MATCHES, SEASON_MATCHES.LEAGUEID, SEASON_MATCHES.SEASONID,
                            SEASON_MATCHES.MATCHID).values(leagueID,seasonID,matchNum.get(i)).execute();
                }
                return true;
            }
        }
        return false;
    }

    public boolean updateSeasonTable(String leagueID, int seasonID, String teamID, LinkedList<Integer> info){
        if(containsInDB(leagueID,seasonID)) {
            DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
            create.update(SEASON_TABLELEAGUE).set(SEASON_TABLELEAGUE.NUMOFGAMES,info.get(0)).
                    set(SEASON_TABLELEAGUE.GOALSFOR,info.get(1))
                    .set(SEASON_TABLELEAGUE.GOALSAGAINST,info.get(2)).set(SEASON_TABLELEAGUE.POINTS,info.get(3))
                    .where(SEASON_TABLELEAGUE.LEAGUEID.eq(leagueID).and(SEASON_TABLELEAGUE.SEASONID.eq(seasonID).and
                                    (SEASON_TABLELEAGUE.TEAMID.eq(teamID)))).execute();
           return true;
        }
        return false;
    }



    private LocalDate convertToDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}
