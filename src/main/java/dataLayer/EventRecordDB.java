package dataLayer;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jooq.Record;

import static dataLayer.Tables.Tables.EVENTSRECORDER_EVENTS;
import static dataLayer.Tables.Tables.SEASON_MATCHES;

public class EventRecordDB implements DB_Inter {
    String username = "root";
    String password = "Messi1Ronaldo2";
    String myDriver = "org.mariadb.jdbc.Driver";
    //String myUrl = "jdbc:mysql://132.72.65.33:3306/SoccerProject";
    String myUrl = "jdbc:mysql://localhost:3306/localsoccer";
    Connection connection = null;


    @Override
    public boolean containInDB(String objectName, String arg2, String arg3) {
        return false;
    }

    @Override
    public Map<String, ArrayList<String>> selectFromDB(String matchID, String arg2, String arg3) {
        DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
        Result<?> result = create.select().from(EVENTSRECORDER_EVENTS).where(EVENTSRECORDER_EVENTS.MATCHID.eq(Integer.parseInt(matchID))).fetch();
        HashMap<String,ArrayList<String>> details = new HashMap<>();
        int counter=0;
        for (Record r :result){
            ArrayList<String> eventDetails = new ArrayList<>();
            eventDetails.add(r.get(EVENTSRECORDER_EVENTS.MATCHID).toString());
            eventDetails.add(r.get(EVENTSRECORDER_EVENTS.TIME));
            eventDetails.add(r.get(EVENTSRECORDER_EVENTS.EVENTID).toString());
            details.put(String.valueOf(counter),eventDetails);
            counter++;
        }
        return details;
    }

    @Override
    public boolean removeFromDB(String objectName, String arg2, String arg3) {
        return false;
    }

    @Override
    public boolean addToDB(String str1, String str2, String str3, String str4, Map<String, ArrayList<String>> objDetails) {
        return false;
    }

    @Override
    public int countRecords() {
        return 0;
    }

    @Override
    public ArrayList<Map<String, ArrayList<String>>> selectAllRecords(Enum<?> e) {
        return null;
    }

    @Override
    public boolean update(Enum<?> e, Map<String, String> arguments) {
        return false;
    }

    @Override
    public boolean TerminateDB() {
        return false;
    }
}