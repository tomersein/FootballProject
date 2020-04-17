package businessLayer.userTypes.Administration;

import businessLayer.Team.Team;
import dataLayer.DataBaseValues;
import dataLayer.DemoDB;
import org.junit.BeforeClass;
import org.junit.Test;
import serviceLayer.TeamService;

import static org.junit.Assert.*;

public class TestTeamOwner {


    static TeamOwner Barkat;
    static TeamOwner Nissanov;
    static TeamOwner Jacob;
    static TeamOwner Glazers;
    static TeamOwner Inon;
    static TeamOwner Alex;
    static TeamOwner piqueF;

    static TeamManager klopp;
    static TeamManager pep;


    static Player Buzaglo;
    static Player Tamash;
    static Player Roso; //This Player will not be in the DB
    static Player pique;

    static Referee Alon;

    static Team HTA;
    static Team BeerSheva;
    static Team Barca; //This Team will not be in the DB
    static Team LeedsUnited; //This Team will not be in the DB



    //private SystemController systemController;
    static DemoDB DB;
    static DataBaseValues tDB;


    static TeamService teamService;

    @BeforeClass
    public static void createTestValues() {
       // systemController = SystemController.SystemController();
        tDB = new DataBaseValues();
        DB = tDB.getDB();
        Barkat = (TeamOwner) DB.selectSubscriberFromDB("AlonaBarkat");
        Nissanov = (TeamOwner) DB.selectSubscriberFromDB("Nissanov");
        Jacob = (TeamOwner) DB.selectSubscriberFromDB("JabobS");
        Glazers = (TeamOwner) DB.selectSubscriberFromDB("Glazers");
        Inon = (TeamOwner) DB.selectSubscriberFromDB("Inon");
        piqueF = (TeamOwner) DB.selectSubscriberFromDB("piqueF");
        Buzaglo = (Player) DB.selectSubscriberFromDB("Buzaglo");
        Tamash = (Player) DB.selectSubscriberFromDB("Tamash");
        pique = (Player) DB.selectSubscriberFromDB("pique");
        Alon = (Referee) DB.selectSubscriberFromDB("Alon");

        pep = (TeamManager) DB.selectSubscriberFromDB("pepG");
        LeedsUnited = DB.selectTeamFromDB("LeedsUnited");
        Alex = (TeamOwner) DB.selectSubscriberFromDB("Alex");

        BeerSheva = DB.selectTeamFromDB("Beer Sheva");
        HTA = DB.selectTeamFromDB("HTA");

        klopp= (TeamManager)DB.selectSubscriberFromDB("kloppJ");

        teamService = new TeamService();

    }

    @Test
    public void UC6_1_1() {
        //check if add asset works correctly 6.1.1
        //new player that was not assign to a team

        assertTrue(Barkat.addAsset(123, "Player", "Buzaglo"));

        //a player that was already assign to a team
        assertFalse(Barkat.addAsset(123, "Player", "Buzaglo"));

        assertFalse(Nissanov.addAsset(456, "Player", "Buzaglo"));

        assertTrue(Barkat.addAsset(123, "Coach", "efronio"));

        assertFalse(Nissanov.addAsset(123, "Coach", "efronio"));

        assertTrue(Barkat.addAsset(123, "TeamManager", "itayK"));

        assertFalse(Nissanov.addAsset(123, "TeamManager", "itayK"));

      //  assertTrue(Barkat.deleteAsset(123, "TeamManager", "itayK"));

        assertTrue(Jacob.addAsset(789,"Stadium","samiOfer"));

        assertFalse(Jacob.addAsset(789,"Stadium","samiOfer"));

    }

    @Test
    public void UC6_1_2() {
        //check if remove asset works correctly 6.1.2
        //new player that was not assign to a team
        Barkat.addAsset(123, "Player", "Buzaglo");
        assertTrue(Barkat.deleteAsset(123, "Player", "Buzaglo"));
        assertFalse(Barkat.deleteAsset(123, "Player", "Buzaglo"));
        assertFalse(Nissanov.deleteAsset(456, "Player", "Buzaglo"));


        Barkat.addAsset(123, "Coach", "efronio");
        assertTrue(Barkat.deleteAsset(123, "Coach", "efronio"));
        assertFalse(Nissanov.deleteAsset(123, "Coach", "efronio"));

        Barkat.addAsset(123, "TeamManager", "itayK");
        assertTrue(Barkat.deleteAsset(123, "TeamManager", "itayK"));
        assertFalse(Barkat.deleteAsset(123, "TeamManager", "itayK"));
        assertFalse(Jacob.deleteAsset(789, "TeamManager", "itayK"));

        Jacob.addAsset(789,"Stadium","samiOfer");

        assertTrue(Jacob.deleteAsset(789,"Stadium","samiOfer"));
        assertFalse(Jacob.deleteAsset(789,"Stadium","samiOfer"));
        assertFalse(Barkat.deleteAsset(123, "Stadium", "samiOfer"));

    }

    @Test
    public void UC6_1_3() {
        Barkat.addAsset(123, "Player", "Buzaglo");
        Barkat.addAsset(123, "Coach", "efronio");
        Jacob.addAsset(789, "TeamManager", "itayK");
        Jacob.addAsset(789,"Stadium","samiOfer");

        assertTrue(Barkat.editPlayer(123,"Buzaglo","birthDate","9/11"));
        assertTrue(Barkat.editPlayer(123,"Buzaglo","fieldJob","attacker"));
        Barkat.deleteAsset(123, "Player", "Buzaglo");
        assertFalse(Barkat.editPlayer(123,"Buzaglo","birthDate","9/20"));
        assertFalse(Barkat.editPlayer(123,"ido","birthDate","9/20"));

        assertTrue(Barkat.editCoach(123,"efronio","training","attacker"));
        assertTrue(Barkat.editCoach(123,"efronio","teamJob","mainCoach"));
        Barkat.deleteAsset(123, "Coach", "efronio");
        assertFalse(Barkat.editCoach(123,"efronio","teamJob","SubCoach"));

        assertTrue(Jacob.editTeamManager(789,"itayK","salary",100000));
        Jacob.deleteAsset(789,"TeamManager","itayK");
        assertFalse(Jacob.editTeamManager(789,"itayK","salary",20));

        assertTrue(Jacob.editStadium(789,"samiOfer","numberOfSeats",50));

        assertFalse(Jacob.editStadium(789,"natania","numberOfSeats",50));
    }
    @Test
    public void UC6_1() {
        teamService.addAsset("AlonaBarkat",123, "Player", "Buzaglo");

    }

    @Test
    public void UC6_4(){
        //all good
        assertTrue(teamService.addManager("Inon","kloppJ","GENERAL","HTA","100"));
        //add same manager again and adding manager to occupied team
        assertFalse(teamService.addManager("Inon","kloppJ","GENERAL","HTA","100"));
        //wrong username
        assertFalse(teamService.addManager("Inon","kloppJU","GENERAL","HTA","100"));
        //try assign a teamOwner
        assertFalse(teamService.addManager("Inon","AlonaBarkat","GENERAL","HTA","100"));


    }

    @Test
    public void UC6_5(){
        //all good
        assertTrue(teamService.fireManager("Inon","kloppJ","HTA"));
        //fire teamowner
        assertFalse(teamService.fireManager("Inon","AlonaBarkat","HTA"));
        //wrong username
        assertFalse(teamService.fireManager("Inon","kloppJU","HTA"));
        //try fire the same manager again
        assertFalse(teamService.fireManager("Inon","kloppJ","HTA"));
    }


    @Test
    public void UC8_2(){
        //1 - UNIT
        //check if Alona who has 2 teams is exclusive

        assertFalse(Barkat.isExclusiveTeamOwner());

        //2
        //check if Alona is now Exclusive
        BeerSheva.getTeamOwners().remove(Nissanov);
        Nissanov.getTeams().remove(BeerSheva);

        assertTrue(Barkat.isExclusiveTeamOwner());

        //3
        //check what happens without any teams
        BeerSheva.getTeamOwners().remove(Nissanov);
        Nissanov.getTeams().remove(BeerSheva);
        assertFalse(Nissanov.isExclusiveTeamOwner());
    }

    /*
    @Test
    public void isFictive() {

        assertFalse(Nissanov.isFictive());
        Nissanov.setOriginalObject(Buzaglo);
        assertTrue(Nissanov.isFictive());
    }

    @Test
    public void checkTeamRequest() {
        //1
        //check if we get true on a normal request
        assertTrue(Barkat.sendRequestForTeam("TheSharks", "2003"));

        //2
        //check if we get a false on a not valid year
        assertFalse(Barkat.sendRequestForTeam("TheSharks", "0"));

        //3
        //check if we get a false on not valid name
        assertFalse(Barkat.sendRequestForTeam("", "2004"));

    }

*/

    /**
     * Unit Test - enterMember(String userName))
     */
    @Test
    public void UT_enterMember() {
        assertEquals(Barkat.enterMember("Glazers"), Glazers); //Try to search a subscriber
        assertNull(Barkat.enterMember("Itay")); //Search a team member which in not exist in the system.

    }

    /**
     * Unit Test - enterMember(String teamName))
     */
    @Test
    public void UT_appointToOwner() {
        assertFalse(Barkat.appointToOwner(Buzaglo, "Manchester")); //Try and Fail to add to a team which you don't own.
        assertTrue(Barkat.appointToOwner(Buzaglo, "Beer Sheva")); //Try to add successfully.
        assertFalse(Barkat.appointToOwner(Glazers,"Beer Sheva")); //Try and Fail to add someone which is already a team owner.
        assertFalse(Barkat.appointToOwner(Alon,"Beer Sheva")); //Try and Fail to add someone, when you are not a Player, a Coach or a Team Manager.
    }

    @Test
    public void UC_6_2() {
        //Test 1 - add Successfully
        assertTrue(Barkat.appointToOwner(Tamash, "Beer Sheva"));

        //Test - 2 - Try to add a Player which does not exists in the DB
        assertFalse(Barkat.appointToOwner(Roso, "Beer Sheva"));

        //Test - 3 -Try and Fail to add someone which is already a team owner.
        assertFalse(Barkat.appointToOwner(Glazers,"Beer Sheva"));

    }

    /**
     * Unit Test - getTeam(String teamName)
     */
    @Test
    public void UT_getTeam() {
        assertEquals(Inon.getTeam("Beer Sheva"),BeerSheva);
        assertNull(Inon.getTeam("NAS"));
        assertEquals(Inon.getTeam("HTA"),HTA);
        assertNotEquals(Inon.getTeam("HTA"),BeerSheva);
    }

    /**
     * Unit Test - changeStatus(Team team)
     */
    @Test
    public void UT_changeStatus() {
        //enabled to disabled
        Inon.disableStatus(BeerSheva);
        assertFalse(BeerSheva.getActive());

        //disabled to enabled
        Inon.enableStatus(BeerSheva);
        assertTrue(BeerSheva.getActive());
    }

    @Test
    public void UC6_6_1_a(){
        //Test - 1 - Disable successfully
        assertTrue(teamService.disableTeamStatus("ManchesterUnited","Glazers"));
    }

    @Test
    public void UC6_6_1_b(){
        //Test - 2 - Try to disable a Team status which does not exists in the DB
        assertFalse(teamService.disableTeamStatus("Barca","Glazers"));
    }

    @Test
    public void UC6_6_1_c() {
        //Test - 3 - Try to disable an already disabled team
        assertFalse(teamService.disableTeamStatus("ManchesterUnited","Glazers"));
    }

    @Test
    public void UC6_6_2_a() {
        //Test - 4 - Enable successfully
        assertTrue(teamService.enableTeamStatus("ManchesterUnited","Glazers"));
    }

    @Test
    public void UC6_6_2_b(){
        //Test - 5 - Try to enable a Team status which does not exists in the DB
        assertFalse(teamService.enableTeamStatus("Barca","Glazers"));
    }

    @Test
    public void UC6_6_2_c(){
        //Test - 6 - Try to enable an already disabled team
        assertFalse(teamService.enableTeamStatus("ManchesterUnited","Glazers"));
    }

    @Test
    public void addManager() {
        //try assign manager to a team that not belong to me
        assertFalse(Alex.addManager("pepG",Permissions.GENERAL,BeerSheva,200));
        //manager already has team
        assertFalse(Alex.addManager("itayK",Permissions.GENERAL,LeedsUnited,200));
        //all good
        assertTrue(Alex.addManager("pepG",Permissions.GENERAL,LeedsUnited,1000));
        //add again same manager
        assertFalse(Alex.addManager("pepG",Permissions.GENERAL,LeedsUnited,1000));

    }

    @Test
    public void fireManager() {
        //try fire manager from team that not belong to me
        assertFalse(Alex.fireManager("itayK",BeerSheva));
        //not my manager
        assertFalse(Alex.fireManager("itayK",LeedsUnited));
        //all good
        assertTrue(Alex.fireManager("pepG",LeedsUnited));
        //manager has no team
        assertFalse(Alex.fireManager("pepG",LeedsUnited));
        //add again same manager

    }

    @Test
    public void getOriginalObject() {
        assertEquals(piqueF.getOriginalObject(),DB.selectSubscriberFromDB("pique"));
    }

    @Test
    public void setOriginalObject() {
        piqueF.setOriginalObject(null);
        assertEquals(piqueF.getOriginalObject(),null);
    }

    @Test
    public void equals() {
        assertTrue(piqueF.equals(DB.selectSubscriberFromDB("piqueF")));
        assertTrue(piqueF.equals((TeamOwner)DB.selectSubscriberFromDB("piqueF")));
        assertFalse(piqueF.equals(DB.selectSubscriberFromDB("pepG")));
        assertFalse(piqueF.equals(DB.selectSubscriberFromDB("Alex")));
        assertFalse(piqueF.equals(null));

    }
}
