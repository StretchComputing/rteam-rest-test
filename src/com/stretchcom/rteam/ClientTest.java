package com.stretchcom.rteam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.google.appengine.repackaged.com.google.common.util.Base64;
import com.stretchcom.rteam.server.GMT;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ClientTest {
	
	//private static final String HTTP_BASE_URL = "http://6.latest.rteamtest.appspot.com/";
	// http://rteamtest.appspot.com
	
	//::TODO::  **************** HTTPS TURNED OFF RIGHT NOW *************************
	//private static final String HTTPS_BASE_URL = "https://14.latest.rteamtest.appspot.com:8443/"; // tried this but it didn't work
	//private static final String HTTPS_BASE_URL = "http://v2-2.latest.rteamtest.appspot.com/";
	//private static final String HTTPS_BASE_URL = "http://rteamtest.appspot.com/";
	//private static final String HTTPS_BASE_URL = "http://localhost:8888/v1/";  //development server.  Run->Run As->Web Application
	private static final String HTTPS_BASE_URL = "http://localhost:8888/";  //development server.  Run->Run As->Web Application
	
	private static final String USERS_RESOURCE_URI = "users";
	private static final String USER_RESOURCE_URI = "user";
	private static final String TEAMS_RESOURCE_URI = "teams";
	private static final String TEAM_RESOURCE_URI = "team";
	private static final String MEMBERS_RESOURCE_URI = "members";
	private static final String MEMBER_RESOURCE_URI = "member";
	private static final String GAMES_RESOURCE_URI = "games";
	private static final String GAME_RESOURCE_URI = "game";
	private static final String PRACTICES_RESOURCE_URI = "practices";
	private static final String PRACTICE_RESOURCE_URI = "practice";
	private static final String ATTENDEES_RESOURCE_URI = "attendees";
	private static final String MESSAGE_THREADS_RESOURCE_URI = "messageThreads";
	private static final String MESSAGE_THREAD_RESOURCE_URI = "messageThread";
	private static final String ACTIVITIES_RESOURCE_URI = "activities";
	private static final String ACTIVITY_RESOURCE_URI = "activity";
	private static final String MOBILE_CARRIERS_RESOURCE_URI = "mobileCarriers";
	
	private static int totalAssertCount = 0;
	private static int passingAssertCount = 0;
	private static int failingAssertCount = 0;
	private static Boolean isLoggingEnabled = true;
	
	// HTTP Methods
	private static final String HTTP_PUT = "PUT";
	private static final String HTTP_POST = "POST";
	private static final String HTTP_GET = "GET";
	private static final String HTTP_DELETE = "DELETE";
	
	private static final int TEN_SECONDS_IN_MILLIS = 10000;

	
	public static void main(String[] args) throws Exception {
		totalAssertCount = 0;
		passingAssertCount = 0;
		failingAssertCount = 0;
		isLoggingEnabled = true;
		
		//=====================================================================================================================
		//=====================================================================================================================
		// GAE ==> Entity Keys
		//=====================================================================================================================
		//
		// USER TOKEN
//		String token1 = "jha31i9e14k0rf92daqvb8epg2"; // 'User One' - IDs below do NOT belong to this user!!!!
//		String token2 = "8if3v0u0h3spn867mk830ach5r"; // joepwro on GAE
//		String token3 = "8ehecce6lqjegntlksm7f83t7u"; // Mike's user that is having Get Activity for All Teams API errors
//		
//		// TEAM #1
//		String teamId1 = "aglydGVhbXRlc3RyDAsSBFRlYW0YwpM4DA"; // thunder
//		String memberId1_1 = "aglydGVhbXRlc3RyGQsSBFRlYW0YwpM4DAsSBk1lbWJlchjRDww"; //fan
//		String memberId1_2 = "";
//		String creatorMemberId1 = "";
//		String gameId1_1 = "";
//		String gameId1_2 = "";
//		String practiceId1_1 = "";
//		String messageThreadId1_1 = "";
//		String messageThreadId1_2 = "";
//		
//		// TEAM #2
//		String teamId2 = "aglydGVhbXRlc3RyDAsSBFRlYW0Y6alIDA"; // Beta Testers
//		String memberId2_1 = "";
//		String memberId2_2 = "";
//		String gameId2_1 = "";
//		String practiceId2_1 = "";
//		
//		String teamId3 = "aglydGVhbXRlc3RyDQsSBFRlYW0YqYyTAQw"; // Beta Testers
		//
		//=====================================================================================================================
		
		
		//=====================================================================================================================
		//=====================================================================================================================
		// DEVELOPMENT SERVER(Localhost:8888) ==> Entity Keys
		//=====================================================================================================================
		//
		// USER TOKEN
		String token1 = "3plhg1smhtr5jalfei6hk8aut9";
		String token2 = "";
		String token3 = "";
		String token4 = ""; 
		
		// TEAM #1
		String teamId1 = "aglydGVhbXRlc3RyCgsSBFRlYW0YBAw"; 
		String memberId1_1 = "aglydGVhbXRlc3RyFgsSBFRlYW0YBAwLEgZNZW1iZXIYBQw"; // creator
		String memberId1_2 = "";
		String creatorMemberId1 = "";
		String gameId1_1 = "aglydGVhbXRlc3RyCgsSBEdhbWUYCww";
		String gameId1_2 = "";
		String practiceId1_1 = "abc";
		String practiceId1_2 = "";
		String messageThreadId1_1 = "aglydGVhbXRlc3RyEwsSDU1lc3NhZ2VUaHJlYWQYIAw";
		String messageThreadId1_2 = "";
		
		// TEAM #2
		String teamId2 = "abc"; // for testing new guardian code
		String memberId2_1 = ""; 
		String memberId2_2 = "";
		String gameId2_1 = "";
		String practiceId2_1 = "";
		
		String teamId3 = "";
		//=====================================================================================================================

		
		String superUser = "joepwro@gmail.com";
		String superUserToken = "7gnu0oj9fppi76f8158sa6aegi";
		

		//====================================================================================================
		// USER #1 INFO ======================================================================================
		String userEmailAddress1 = "rteamtest1@gmail.com";
		String userFirstName1 = "User";
		String userLastName1 = "One";
		String userPassword1 = "t5ns6qn";
		String userPhoneNumber1 = "7082461681";
		String passwordResetQuestion1 = "Grandpa's nick name";
		String passwordResetAnswer1 = "stretch";
		String userTimeZone1 = "America/Chicago";  //America/Los_Angeles   //America/Chicago //America/New_York
		//====================================================================================================
		

		//====================================================================================================
		// USER #2 INFO ======================================================================================
		String userEmailAddress2 = "joepwro@rteam.com"; // for testing user that should be associated with member 1
		String userFirstName2 = "Joe";
		String userLastName2 = "Wro";
		String userPassword2 = "u22r8h5";
		String userPhoneNumber2 = "7082461681";
		String passwordResetQuestion2 = "Favorite small town";
		String passwordResetAnswer2 = "Peotone";
		String userTimeZone2 = "America/Chicago";  //America/Los_Angeles   //America/Chicago //America/New_York
		//====================================================================================================
		

		//====================================================================================================
		// USER #3 INFO ======================================================================================
		String userEmailAddress3 = "rteamtest9@gmail.com";
		String userFirstName3 = "User";
		String userLastName3 = "Three";
		String userPassword3 = "u22r8h5";
		String userPhoneNumber3 = "3124680892";
		String passwordResetQuestion3 = "lake";
		String passwordResetAnswer3 = "redstone";
		String userTimeZone3 = "America/Chicago";  //America/Los_Angeles   //America/Chicago //America/New_York
		//====================================================================================================
		
		
		//====================================================================================================
		// USER #4 INFO ======================================================================================
		String userEmailAddress4 = "rteamtest4@gmail.com";
		String userFirstName4 = "FourFirstName";
		String userLastName4 = "FourLastName";
		String userPassword4 = "u22r8h5";
		String userPhoneNumber4 = "3124680892";
		String passwordResetQuestion4 = "lake";
		String passwordResetAnswer4 = "redstone";
		String userTimeZone4 = "America/Chicago";  //America/Los_Angeles   //America/Chicago //America/New_York
		//====================================================================================================

		
		//====================================================================================================
		// TEAM #1 INFO ======================================================================================
		String teamName1 = "Running Raspberries1";
		String teamDescription1 = "Steph's old team!1";
		String leagueName1 = "AYSO1";
		String twitterUserName1 = "fakeTwitterName1";
		String twitterPassword1 = "fakeTwitterPassword1";
		String teamSport1 = "baseball1";
		String teamSiteUrl1 = "ws_angels1.com";
		String teamGender1 = "male";
		String teamCity1 = "Western Springs";
		String teamState1 = "Illinois";
		String teamLatitude1 = "41.809";
		String teamLongitude1 = "-87.9";
		
		// Team #1, Member #1
		String memberFirstName1_1 = "TwoFirstName";
		String memberLastName1_1 = "TwoLastName";
		String memberEmailAddress1_1 = "rteamtest2@gmail.com";
		String memberParticipantRole1_1 = "coordinator";
		String memberPhoneNumber1_1 = "7085882301";
		String jerseyNumber1_1 = "12";
		List<String> guardianEmailAddresses1_1 = new ArrayList<String>();
		guardianEmailAddresses1_1.add("rteamtest4@gmail.com");
		guardianEmailAddresses1_1.add("rteamtest5@gmail.com");
		List<String> guardianFirstNames1_1 = new ArrayList<String>();
		guardianFirstNames1_1.add("FourFirstName");
		guardianFirstNames1_1.add("Steve");
		List<String> guardianLastNames1_1 = new ArrayList<String>();
		guardianLastNames1_1.add("ForuLastName");
		guardianLastNames1_1.add("Jobs");
		List<String> roles1_1 = new ArrayList<String>();
		roles1_1.add("player");
		String memberGender1_1 = "male";
		String memberAge1_1 = "51";
		String memberAddress1_1 = "3944 Johnson";
		String memberCity1_1 = "LaGrange";
		String memberState1_1 = "Illinois";
		String memberZipCode1_1 = "60558";
		
		// Team #1, Member #2
		String memberFirstName1_2 = "TeamOne";
		String memberLastName1_2 = "MemberTwo";
		String memberEmailAddress1_2 = "rteamtest4@gmail.com";
		String memberParticipantRole1_2 = "fan";
		String memberPhoneNumber1_2 = "588.2300";
		String jerseyNumber1_2 = "12";
		List<String> guardianEmailAddresses1_2 = null;
		List<String> guardianFirstNames1_2 = null;
		List<String> guardianLastNames1_2 = null;
		List<String> roles1_2 = new ArrayList<String>();
		roles1_2.add("player");
		String memberGender1_2 = "male";
		String memberAge1_2 = "22";
		String memberAddress1_2 = "235 W. Van Buren";
		String memberCity1_2 = "Chicago";
		String memberState1_2 = "Illinois";
		String memberZipCode1_2 = "60558";
		
		// Team #1, Game #1
		String gameDescription1_1 = "Battle of the Group Apps";
		String gameStartDate1_1 = "2011-10-17 05:55";
		String gameEndDate1_1 = "2011-10-17 10:55";
		String gameTimeZone1_1 = "America/Chicago";  //America/Los_Angeles   //America/Chicago //America/New_York
		Double gameLatitude1_1 = 41.8238889;	//Brookfield
		Double gameLongitude1_1 = -87.8516667;
		String gameOpponent1_1 = "High Society";
		
		// Team #1, Game #2
		String gameDescription1_2 = "Team #1, Game #2";
		String gameStartDate1_2 = "2010-09-15 03:05";
		String gameEndDate1_2 = "2010-09-15 5:45";
		String gameTimeZone1_2 = "America/Chicago";  //America/Los_Angeles   //America/Chicago //America/New_York
		Double gameLatitude1_2 = 41.8238889;	//Brookfield
		Double gameLongitude1_2 = -87.8516667;
		String gameOpponent1_2 = "Dashing Doves";
		
		// Team #1, Practice #1
		String practiceDescription1_1 = "Strong and harder";
		String practiceStartDate1_1 = "2011-1-22 07:00";
		String practiceEndDate1_1 = "2011-1-22 10:00";
		String practiceTimeZone1_1 = "America/Chicago";  //America/Los_Angeles   //America/Chicago //America/New_York
		Double practiceLatitude1_1 = 41.8238889;	//Brookfield
		Double practiceLongitude1_1 = -87.8516667;
		String practiceOpponent1_1 = "Blue Devils";
		String practiceEventType1_1 = "generic";
		String practiceEventName1_1 = "Ledo's Party";
		
		// Team #1, Game #1 Attendees
		List<String> attendeeIds1_1 = new ArrayList<String>();
		attendeeIds1_1.add(memberId1_1);
		attendeeIds1_1.add(creatorMemberId1);
		List<String> attendance1_1 = new ArrayList<String>();
		attendance1_1.add("yes");
		attendance1_1.add("yes");
		//====================================================================================================
		//====================================================================================================
		
		
		
		//====================================================================================================
		// TEAM #2 INFO ======================================================================================
		String teamName2 = "Beta Testers";
		String teamDescription2 = "A not so motivated group";
		String leagueName2 = "";
		String twitterUserName2 = "fakeTwitterName2";
		String twitterPassword2 = "fakeTwitterPassword2";
		String teamSport2 = "baseball1";
		String teamSiteUrl2 = "barelytesters.com";
		String teamGender2 = "female";
		String teamCity2 = "Western Springs";
		String teamState2 = "Illinois";
		String teamLatitude2 = "41.809";
		String teamLongitude2 = "-87.9";
		
		// Team #2, Member #1
		String memberFirstName2_1 = "TeamTwo";
		String memberLastName2_1 = "MemberOne";
		String memberEmailAddress2_1 = "rteamtest4@gmail.com";
		String memberParticipantRole2_1 = "member";
		String memberPhoneNumber2_1 = "7085882302";
		String jerseyNumber2_1 = "55";
		List<String> guardianEmailAddresses2_1 = new ArrayList<String>();
		guardianEmailAddresses2_1.add("rteamtest3@gmail.com");
		List<String> roles2_1 = new ArrayList<String>();
		roles2_1.add("player");
		String memberGender2_1 = "male";
		String memberAge2_1 = "51";
		String memberAddress2_1 = "3944 Johnson";
		String memberCity2_1 = "LaGrange";
		String memberState2_1 = "Illinois";
		String memberZipCode2_1 = "60558";
		
		// Team #2, Game #1
		String gameDescription2_1 = "Team #2, Game #1";
		String gameStartDate2_1 = "2010-09-22 12:15";
		String gameEndDate2_1 = "2010-09-22 1:45";
		String gameTimeZone2_1 = "America/Chicago";  //America/Los_Angeles   //America/Chicago //America/New_York
		Double gameLatitude2_1 = 41.8238889;	//Brookfield
		Double gameLongitude2_1 = -87.8516667;
		String gameOpponent2_1 = "Terrible Tigers";
		//====================================================================================================
		//====================================================================================================
		
		
		//====================================================================================================
		// MESSAGE THREAD #1 INFO ===========================================================================
		String subject1 = "subject for message #1";
		String body1 = "This is the body of message 1.";	
		String type1 = "confirm";
		String eventType1 = "practice";
		String isAlert1 = "true";
		String includeFans1 = "true";
		List<String> pollChoices1 = new ArrayList<String>();
		pollChoices1.add("Win");
		pollChoices1.add("Lose");
		pollChoices1.add("Draw");
		List<String> theReminderRecipients1 = new ArrayList();
		List<String> recipients1 = new ArrayList();
		recipients1.add(memberId1_1);
		//====================================================================================================
		
		
		//====================================================================================================
		// MESSAGE THREAD #2 INFO ===========================================================================
		String subject2 = "Let's have fun...";
		String body2 = "What is your child's favorite position in the field?";	
		String type2 = "poll";
		String eventType2 = "game";
		String isAlert2 = "true";
		String includeFans2 = "false";
		List<String> pollChoices2 = new ArrayList<String>();
		pollChoices2.add("Pitcher");
		pollChoices2.add("First Base");
		pollChoices2.add("Short Stop");
		pollChoices2.add("Center Field");
		pollChoices2.add("Catcher");
		List<String> theReminderRecipients2 = null;
		List<String> recipients2 = null;
		//====================================================================================================
		
		
		// ===========
		// CREATE USER
		// ===========
		// PARAMS:: verifyCreateUser(String theFirstName, String theLastName, String thePassword, String theEmailAddress,
		//                           String thePhoneNumber, Boolean theAlreadyMember, String passwordResetQuestion,
		//                           String passwordResetAnswer, String theLatitude, String theLongitude)
		//token1 = verifyCreateUser(userFirstName1, userLastName1, userPassword1, userEmailAddress1, userPhoneNumber1, false, passwordResetQuestion1, passwordResetAnswer1, null, null);
		//token2 = verifyCreateUser(userFirstName2, userLastName2, userPassword2, userEmailAddress2, userPhoneNumber2, false, passwordResetQuestion2, passwordResetAnswer2, null, null);
		//token3 = verifyCreateUser(userFirstName3, userLastName3, userPassword3, userEmailAddress3, userPhoneNumber3, false, passwordResetQuestion3, passwordResetAnswer3, null, null);
		//token4 = verifyCreateUser(userFirstName4, userLastName4, userPassword4, userEmailAddress4, userPhoneNumber4, false, passwordResetQuestion4, passwordResetAnswer4, null, null);
		//token3 = verifyCreateUser("Joe", "Wroblewski", "joepwro", "joe@gmail.com", "6302156979", false, passwordResetQuestion3, passwordResetAnswer3, "41.809", "-87.9");
		
		// =======================
		// GET USER INFO and TOKEN
		// =======================
		//verifyGetUserInfo(token1);
		//verifyGetUserInfo("8if3v0u0h3spn867mk830ach5r");
		//verifyGetUserInfo(token3);
		//verifyGetUserInfo(token4);
		//verifyGetUserToken(userEmailAddress1, userPassword1);
		//verifyGetUserToken("rteamtest9@gmail.com", "redst0ne");
		//verifyGetUserToken("joepwro@gmail.com", "uto123");
		//verifyGetUserToken(userEmailAddress1, "bad_user_password");
		//verifyGetUserToken("invalid_email_address", "bad_user_password");
	    //verifyGetUserConfirmationInfo("gsk9uhpdigheionqpr9ng0mek3");  // copy-and-paste oneUseToken (from user welcome msg recipient entity) param from local GAE data viewer
		//verifyGetUserPasswordResetQuestion(userEmailAddress1);

		
		// ===========
		// DELETE USER
		// ===========
		//verifyDeleteUser(token2, true);  // delete NOW
		//verifyDeleteUser(token2, false); // deactivate
		//verifyDeleteAllUsers(superUserToken);
		
		
		// ===========
		// UPDATE USER
		// ===========
		//PARAMS:: verifyUpdateUser(String theFirstName, String theLastName, String thePassword, String thePhoneNumber,
		//                          String theIsNetworkAuthenticated, String passwordResetQuestion, String passwordResetAnswer,
		//                          String theUserIconOneId, String theUserIconOneAlias, String theUserIconOneImage,
		//                          String theUserIconTwoId, String theUserIconTwoAlias, String theUserIconTwoImage,
		//                          Integer theAutoArchiveDayCount, String theFakePhoto, String theLatitude,
		//                          String theLongitudeString theToken)
		//verifyUpdateUser("Joseph", "Wroblewski", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, token1);
		//verifyUpdateUser(null, null, "redst0ne", null, null, null, null, null, null, null, null, null, null, null, null, null, null, token3); // update password
		//verifyUpdateUser(null, null, null, null, null, null, null, null, null, null, null, null, null, null, "this is fake photo data", null, null, token1); // photo
		//verifyUpdateUser(null, null, null, null, "true", null, null, null, null, null, null, null, null, null, null, null, null, token1); // network authenticate user
		//verifyUpdateUser(null, null, null, null, "false", null, null, null, null, null, null, null, null, null, null, null, null, token1); // remove network authentication from user
		//verifyUpdateUser(userFirstName1, userLastName1, null, null, null, "newQuestion", "newAnswer", null, null, null, null, null, null, null, null, null, null, token1); // change password question and answer
		//verifyUpdateUser(null, null, null, null, null, null, null, "IconOneId", "IconOneAlias", "IconOneImage", "IconTwoId", "IconTwoAlias", "IconTwoImage", null, null, null, null, token1); // set user icon fields
		//verifyUpdateUser(null, null, null, null, null, null, null, null, null, null, null, null, null, 10, null, null, null, token1); // update autoArchiveDayCount
		//verifyUpdateUser(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "41.809", "-87.9", token1); // latitude, longitude
		//verifyResetUserPassword(userEmailAddress1, passwordResetAnswer1);
		//verifyResetUserPassword(userEmailAddress1, "badResetPasswordAnswer");
		
		
		// ===========
		// CREATE TEAM
		// ===========
		//PARAMS:verifyCreateTeam(String theTeamName, String theTeamDescription, String theLeagueName, String theSport,
		//		                  String theTeamSiteUrl, String theGender, String theCity, String theState, String theLatitude,
		//		                  String theLongitude, Boolean theUseTwitter, String theToken)
		//verifyCreateTeam("Cubs", "Joe's first team", leagueName1, teamSport1, teamSiteUrl1, teamGender1, teamCity1, teamState1, teamLatitude1, teamLongitude1, false, token1);
		//verifyCreateTeam(teamName1, teamDescription1, leagueName1, teamSport1, teamSiteUrl1, teamGender1, teamCity1, teamState1, teamLatitude1, teamLongitude1, true, token1); //use Twitter
		//verifyCreateTeam(teamName2, "a team description", leagueName2, teamSport2, teamSiteUrl2, teamGender2, teamCity2, teamState2, teamLatitude2, teamLongitude2, false, token2);
		//verifyCreateTeam(teamName2, teamDescription2, leagueName2, teamSport2, teamSiteUrl2, teamGender2, teamCity2, teamState2, teamLatitude2, teamLongitude2, true, token1); //use Twitter
		
		
		// ===========
		// UPDATE TEAM
		// ===========
		//PARAMS:	verifyUpdateTeam(String theTeamId, String theTeamName, String theTeamDescription, String theLeagueName,
		//             String theTwitterUserName, String theTwitterPassword, String theTeamSiteUrl, String theSport,
		//             String theTwitterAccessToken, String theTwitterAccessTokenSecret,
		//             String theNewestTwitterId, Long theNewestCacheId, Boolean theUseTwitter, String theToken)
		// NOTE: to activate Twitter, use calls below to set access token and secret from values obtained from production GAE and
		//       also set useTwitter to true.
		//verifyUpdateTeam(teamId1, teamName1, null, null, "followTheYellowBrickRoad", twitterPassword1, null, null, null, null, null, null, null, token1); // new twitter user name
		//verifyUpdateTeam(teamId1, null, null, null, null, null, null, "speed golf", null, null, null, null, null, token1); // new sport
		//verifyUpdateTeam(teamId1, teamName1, null, null, null, null, null, null, "207215589-2kKNbfLkK5tT7Ph0AQrPcxhenvnWY03aojbNB2xu", "qWZaBQLGJA2NMtT4gJp8JQ6vv1LWBRghzstVNlVV4", null, null, null, token1); // twitter access token and secret. Values taken from GAE
		//verifyUpdateTeam(teamId2, teamName1, null, null, null, null, null, null, "207215589-2kKNbfLkK5tT7Ph0AQrPcxhenvnWY03aojbNB2xu", "qWZaBQLGJA2NMtT4gJp8JQ6vv1LWBRghzstVNlVV4", null, null, null, token1); // twitter access token and secret. Values taken from GAE
		//verifyUpdateTeam(teamId1, teamName1, null, null, null, null, null, null, null, null, 28955536676L, 3L, null, token1); // newestTwitterId, newestCacheId
		//verifyUpdateTeam(teamId2, null, null, null, null, null, null, null, null, null, null, null, true, token1); // useTwitter=true, change json key in routine to useTwitterForced temporarily
		//verifyUpdateTeam(teamId1, null, null, null, null, null, null, null, null, null, null, null, false, token1); // useTwitter=false (for testing useTwitter=true to useTwitter=false)
		//verifyUpdateTeam("aglydGVhbXRlc3RyDQsSBFRlYW0YiZ6AAQw", null, null, null, null, null, null, null, null, null, null, null, false, token2); // useTwitter=false (for testing useTwitter=true to useTwitter=false)
		
		
		
		//========================
		// GET TEAMS AND TEAM INFO
		//========================
		//verifyGetTeamInfo(teamId1, token1);
		//verifyGetTeamInfo(teamId2, token1);
		//verifyGetTeamInfo("aglydGVhbXRlc3RyDQsSBFRlYW0Y5eGbAQw", "embaf11058fikl5nki4r0o7vk0");
		//verifyGetTeams(token1);
		//verifyGetTeams("8if3v0u0h3spn867mk830ach5r");
		//verifyGetTeams(token4);
		
		
		// ===========
		// DELETE TEAM
		// ===========
		//verifyDeleteTeam(teamId1, token1);
		//verifyDeleteTeam(teamId2, token1);
		
		
		// =========================================
		// TEAM: TWITTER CALLBACK CONFIRMATION (GWT)
		// =========================================
		// copy-and-paste team oneUseToken param from local GAE data viewer
		// NOTE: only used for partial testing - cannot be used to activate Twitter for a team because Twitter modifies the callback
		//       URLplacing an OAuth verifier at the end. Only twitter knows that oauth_verifier and only sent after user enters  
		//       Twitter credentials.
		// URL for testing in browser:  http://localhost:8888/Rteam.html?twittertoken=sbfe0hcc781oia1ehf130p4p5b&oauth_verifier=wroblewski

		
		// =============
		// CREATE MEMBER
		// =============
		//PARAMS: 	verifyCreateMember(String theTeamId, String theFirstName, String theLastName, List<String> theRoles,
		//             String theEmailAddress, String theJerseyNumber, String thePhoneNumber, List<String> theGuardianEmailAddresses,
		//             List<String> theGuardianFirstNames, List<String> theGuardianLastNames, String theParticipantRole, String theGender,
		//             String theAge, String theStreetAddress, String theCity, String theState, String theZipcode, String theToken)
//		verifyCreateMember(teamId1, memberFirstName1_1, memberLastName1_1, roles1_1, memberEmailAddress1_1, jerseyNumber1_1,
//		        memberPhoneNumber1_1, guardianEmailAddresses1_1, guardianFirstNames1_1, guardianLastNames1_1, memberParticipantRole1_1,
//		        memberGender1_1, memberAge1_1, memberAddress1_1, memberCity1_1, memberState1_1, memberZipCode1_1, token1);

//		verifyCreateMember(teamId1, memberFirstName1_2, memberLastName1_2, roles1_2, memberEmailAddress1_2, jerseyNumber1_2,
//        memberPhoneNumber1_2, guardianEmailAddresses1_2, guardianFirstNames1_2, guardianLastNames1_2, memberParticipantRole1_2,
//        memberGender1_2, memberAge1_2, memberAddress1_2, memberCity1_2, memberState1_2, memberZipCode1_2, token1); // a fan team #1
		
//		verifyCreateMember(teamId1, memberFirstName1_1, memberLastName1_1, roles1_1, memberEmailAddress1_1, jerseyNumber1_1,
//        memberPhoneNumber1_1, null, null, null, memberParticipantRole1_1,
//        memberGender1_1, memberAge1_1, memberAddress1_1, memberCity1_1, memberState1_1, memberZipCode1_1, token1); // no guardian
		
//		verifyCreateMember(teamId2, memberFirstName2_1, memberLastName2_1, roles2_1, memberEmailAddress2_1, jerseyNumber2_1,
//        memberPhoneNumber2_1, guardianEmailAddresses2_1, null, null, memberParticipantRole2_1,
//        memberGender2_1, memberAge2_1, memberAddress2_1, memberCity2_1, memberState2_1, memberZipCode2_1, token1);
		
//		verifyCreateMember(teamId2, "Poor", "Orphan", null, "orphan@lost.com", null,
//        null, null, null, null, "member",
//        null, null, null, null, null, null, token1); // no guardians
		
//		List<String> guardianEmailAddresses = new ArrayList<String>();
//		guardianEmailAddresses.add("mom@justMom.com");
//		List<String> guardianFirstNames = new ArrayList<String>();
//		guardianFirstNames.add("Gale");
//		List<String> guardianLastNames = new ArrayList<String>();
//		guardianLastNames.add("Wroblewski");
//		verifyCreateMember(teamId2, "Just", "Mom", null, "kid@justMom.com", null,
//				null, guardianEmailAddresses, guardianFirstNames, guardianLastNames, "member",
//				null, null, null, null, null, null, token1); // just a mom
		
		
		// =======================
		// CREATE MULTIPLE MEMBERS
		// =======================
		//PARAMS: 	verifyCreateMultipleMembers(String theTeamId, String theFirstName, String theLastName, String theEmailAddress,
		//              String thePhoneNumber, String theParticipantRole, String theToken)
//		String[] firstNames = {"Lindsay", "Lindsay2"};
//		String[] lastNames = {"Green", "Green2"};
//		String[] emailAddresses = {"lindsay@clinton.com", "lindsay2@clinton.com"};
//		String[] phoneNumbers = {"", ""};
//		verifyCreateMultipleMembers(teamId1, firstNames, lastNames, emailAddresses, phoneNumbers, "member", token1);
		
		
		// =============
		// UPDATE MEMBER
		// =============
		//PARAMS:verifyUpdateMember(String theTeamId, String theMemberId, String theFirstName, String theLastName, List<String> theRoles,
		//            String theEmailAddress, String theJerseyNumber, String thePhoneNumber, String theThumbNail, 
		//            List<String> theGuardianKeys, List<String> theGuardianEmailAddresses, List<String> theGuardianFirstNames, 
		//            List<String> theGuardianLastNames, List<String> theGuardianPhoneNumbers, String theParticipantRole, String theToken)
//		List<String> guardianKeys = new ArrayList<String>();
//		guardianKeys.add("rridmir");
//		guardianKeys.add("d0ju4uj");
//		List<String> guardianEmailAddresses = new ArrayList<String>();
//		guardianEmailAddresses.add("mommy1@justMom.com");
//		guardianEmailAddresses.add("daddy1@justMom.com");
//		List<String> guardianFirstNames = new ArrayList<String>();
//		guardianFirstNames.add("Gale");
//		guardianFirstNames.add("Joe");
//		List<String> guardianLastNames = new ArrayList<String>();
//		guardianLastNames.add("Wroblewski");
//		guardianLastNames.add("Wroblewski");
//		List<String> guardianPhoneNumbers = new ArrayList<String>();
//		guardianPhoneNumbers.add("7084204130");
//		guardianPhoneNumbers.add("6302156979");
//		verifyUpdateMember(teamId2, "aglydGVhbXRlc3RyGAsSBFRlYW0Y_gkMCxIGTWVtYmVyGIMKDA", null, null, null, null, null, "3125551212", null, guardianKeys, guardianEmailAddresses, guardianFirstNames, guardianLastNames, guardianPhoneNumbers, null, token1);
		//verifyUpdateMember(teamId1, memberId1_1, null, null, null, null, null, null, null, null, null, null, null, null, "coordinator", token1); // make a coordinator
		//verifyUpdateMember(teamId3, memberId1_1, null, null, null, "light2@tunnel.com", null, null, null, null, null, null, null, null, null, token3); // 
		//verifyUpdateMember(teamId1, memberId1_1, null, null, null, null, null, "3124680892", null, null, null, null, null, null, null, token1); // change the phone number

		
		// ==========================
		// GET MEMBER and MEMBER INFO
		// ==========================
		//verifyGetMembers(teamId1, false, token1);  // do not include fans
		//verifyGetMembers("aglydGVhbXRlc3RyDQsSBFRlYW0YnaObAQw", false, token1);
		//verifyGetMembers("aglydGVhbXRlc3RyDQsSBFRlYW0Y5LKjAQw", true, "8if3v0u0h3spn867mk830ach5r"); //2-2 test
		
		//verifyGetMembers(teamId3, true, token3);  // include fans
		//verifyGetMemberInfo(teamId1, memberId1_1, token1);
		//verifyGetMemberInfo(teamId1, memberId1_2, token1);
		//verifyGetMemberInfo(teamId2, "aglydGVhbXRlc3RyGAsSBFRlYW0Y_gkMCxIGTWVtYmVyGIMKDA", token1);
		//verifyGetMemberInfo(teamId1, creatorMemberId1, token1);
		//verifyGetMembershipStatus(userEmailAddress1);
		//verifyGetMembershipStatus("rteamtest1@gmail.com");
		
		
		// =============
		// DELETE MEMBER
		// =============
		//verifyDeleteMember(teamId1, memberId1_1, token1);
		//verifyDeleteMember(teamId1, "aglydGVhbXRlc3RyGAsSBFRlYW0YpQoMCxIGTWVtYmVyGLkKDA", token1);
		
		
		// =============
		// CREATE GAME
		// =============
		// PARAMS: verifyCreateGame(String theTeamId, String theStartDate, String theTimeZone, String theDescription, String theEndDate,
		//                          Double theLatitude, Double theLongitude, String theOpponent, String theLocation, String theToken)
		// TEAM #1, GAME #1
//		verifyCreateGame(teamId1, gameStartDate1_1, gameTimeZone1_1, gameDescription1_1, gameEndDate1_1, gameLatitude1_1,
//				         gameLongitude1_1, gameOpponent1_1, "Chicago Burbs", token1);
		// TEAM #1, GAME #2
//		verifyCreateGame(teamId1, gameStartDate1_2, gameTimeZone1_2, gameDescription1_2, gameEndDate1_2, gameLatitude1_2,
//                       gameLongitude1_2, gameOpponent1_2, "Spring Rock Park", token1);
		// TEAM #2, GAME #1
//		verifyCreateGame(teamId2, gameStartDate2_1, gameTimeZone2_1, gameDescription2_1, gameEndDate2_1, gameLatitude2_1,
//                         gameLongitude2_1, gameOpponent2_1, "Spring Rock Park", token1);
		
//		verifyCreateGame("aglydGVhbXRlc3RyDQsSBFRlYW0Y5LKjAQw", gameStartDate2_1, gameTimeZone2_1, gameDescription2_1, gameEndDate2_1, gameLatitude2_1,
//             gameLongitude2_1, "Deer in Headlights", "Head Quarters", "8if3v0u0h3spn867mk830ach5r"); //2-2 test
		
		// =====================
		// CREATE MULTIPLE GAMES
		// =====================
		// PARAMS: verifyCreateMultipleGames(String theTeamId, String theStartDate, String theTimeZone, String theDescription,
		//		                    String theEndDate, Double theLatitude, Double theLongitude, String theOpponent, String theLocation,
		//		                    Integer theNumberOfGames, String theNotificationType, String theToken)
//		verifyCreateMultipleGames(teamId1, gameStartDate1_1, gameTimeZone1_1, gameDescription1_1, gameEndDate1_1, gameLatitude1_1,
//                                  gameLongitude1_1, gameOpponent1_1, "Friendly Confines", 2, "plain", token1);
		
		
		// =============
		// UPDATE GAME
		// =============
		// PARAMS::verifyUpdateGame(String theTeamId, String theGameId, String theStartDate, String theTimeZone, String theDescription,
		//                         String theEndDate, Double theLatitude, Double theLongitude, String theOpponent, String theScoreUs, 
		//                         String theScoreThem, String theInterval, String theNotificationType, String theLocation,
		//                         String thePollStatus, Boolean theUpdateAll, String theToken)
		//verifyUpdateGame(teamId1, gameId1_1, null, null, "turkey Bowl", null, null, null, null, null, null, null, null, null, null, null, token1); // update desc
		//verifyUpdateGame(teamId1, gameId1_1, null, null, "no notification test", null, null, null, null, null, null, null, "none", null, null, null, token1); // update desc, no notification
		//verifyUpdateGame(teamId1, gameId1_1, null, null, null, null, null, null, null, null, null, null, null, "Wrigley Field", null, null, token1); // update location
		//verifyUpdateGame(teamId1, gameId1_1, null, null, null, null, 41.1111111, -87.7777777, null, null, null, null, "plain", "Quad Cities", null, true, token1); // update location for all games, w/ notification
		//verifyUpdateGame(teamId1, gameId1_1, null, null, null, null, null, null, null, "4", "4", "-4", null, null, null, null, token1); // update scores and interval
		//verifyUpdateGame(teamId1, gameId1_1, null, null, null, null, null, null, null, "2", "10", "4.4", null, null, null, null, token1); // invalid score/interval
		//verifyUpdateGame(teamId1, gameId1_1, "2011-1-25 11:11", userTimeZone1, null, null, null, null, null, null, null, null, null, null, null, null, token1); // update start time
		//verifyUpdateGame(teamId1, gameId1_1, null, null, null, null, null, null, null, null, null, null, null, null, "closed", null, token1); // close the game poll
		//verifyUpdateGame(teamId1, gameId1_1, null, null, null, null, null, null, null, null, null, null, null, null, "open", null, token1); // open the game poll
		
		
		// ==============
		// CAST GAME VOTE
		// ==============
		// PARAMS::verifyCastGameVote(String theTeamId, String theGameId, String theMemberId, String theVoteType, String theToken)
		//verifyCastGameVote(teamId1, gameId1_1, "aglydGVhbXRlc3RyGAsSBFRlYW0YpQoMCxIGTWVtYmVyGKYKDA", "mvp", token1); // vote member1
		//verifyCastGameVote(teamId1, gameId1_1, "aglydGVhbXRlc3RyGAsSBFRlYW0YsAYMCxIGTWVtYmVyGNUGDA", "mvp", token1); // vote member2
		
		
		// =====================
		// Get Game Vote Tallies
		// =====================
		// PARAMS::verifyGetGameVoteTallies(String theTeamId, String theGameId, String theVoteType, String theToken)
		//verifyGetGameVoteTallies(teamId1, gameId1_1, "mvp", token1); // get game tallies generic
		
		
		// =======================================
		// GET GAME INFO, TEAM GAMES AND ALL GAMES
		// =======================================
		//verifyGetTeamGames(teamId1, gameTimeZone1_1, token1);
		//verifyGetTeamGames(teamId2, gameTimeZone2_1, token1);
		//verifyGetAllGames(gameTimeZone1_1, token1);
		//verifyGetGameInfo(teamId1, gameId1_1, gameTimeZone1_1, token1);
		//verifyGetGameInfo("aglydGVhbXRlc3RyDQsSBFRlYW0Y5LKjAQw", "aglydGVhbXRlc3RyDQsSBEdhbWUY3IK4AQw", gameTimeZone1_1, "8if3v0u0h3spn867mk830ach5r"); //2-2 test
		
		
		
		// =============
		// DELETE GAME
		// =============
		//verifyDeleteGame(teamId1, gameId1_1, token1);
		//verifyDeleteGame(teamId1, "aglydGVhbXRlc3RyCwsSBEdhbWUYyAoM", token1);
		
		
		// ===============
		// CREATE PRACTICE
		// ===============
		// PARAMS::verifyCreatePractice(String theTeamId, String theStartDate, String theTimeZone, String theDescription,
		//                              String theEndDate, Double theLatitude, Double theLongitude, String theOpponent,
		//                              String theEventType, String theEventName, String theToken)
//		verifyCreatePractice(teamId1, practiceStartDate1_1, practiceTimeZone1_1, practiceDescription1_1, practiceEndDate1_1, 
//		   practiceLatitude1_1, practiceLongitude1_1, practiceOpponent1_1, practiceEventType1_1, practiceEventName1_1, token1);
//		verifyCreatePractice(teamId1, practiceStartDate1_1, practiceTimeZone1_1, "joe literal practice", practiceEndDate1_1, 
//		   null, null, null, "practice", practiceEventName1_1, token1);
//		verifyCreatePractice(teamId1, practiceStartDate1_1, practiceTimeZone1_1, practiceDescription1_1, practiceEndDate1_1, 
//		   practiceLatitude1_1, practiceLongitude1_1, practiceOpponent1_1, practiceEventType1_1, practiceEventName1_1, token1);
		
		
		// =========================
		// CREATE MULTIPLE PRACTICES
		// =========================
		// PARAMS: verifyCreateMultiplePractices(String theTeamId, String theStartDate, String theTimeZone, String theDescription,
		//		                    String theEndDate, Double theLatitude, Double theLongitude, String theOpponent, String theEventType,
		//		                    String theEventName, Integer theNumberOfPractices, String theNotificationType, String theToken)
//		verifyCreateMultiplePractices(teamId1, gameStartDate1_1, gameTimeZone1_1, gameDescription1_1, gameEndDate1_1, gameLatitude1_1,
//                                  gameLongitude1_1, gameOpponent1_1, "practice", "dog training", 2, "plain", token1);
		
		
		// ===============
		// UPDATE PRACTICE
		// ===============
		// PARAMS:: verifyUpdatePractice(String theTeamId, String thePracticeId, String theStartDate, String theTimeZone,
		//                               String theDescription, String theEndDate, Double theLatitude, Double theLongitude,
		//                               String theOpponent, String theEventName, Boolean theUpdateAll, String theNotificationType,
		//                               Boolean theIsCanceled, String theToken)
		//verifyUpdatePractice(teamId1, practiceId1_1, null, null, "Team #1, Practice #1 UPDATED", null, null, null, null, null, null, "plain", null, token1); // update desc
		//verifyUpdatePractice(teamId1, practiceId1_1, null, null, null, null, null, null, null, "Team Picnic", null, "plain", null, token1); // update eventName
		//verifyUpdatePractice(teamId1, practiceId1_1, null, null, null, null, 41.1111111, -87.7777777, "Clinton", null, true, "plain", null, token1); // update all locations
		//verifyUpdatePractice(teamId1, practiceId1_1, null, null, null, null, null, null, "brand new Opponent", null, null, "plain", null, token1); // update eventName
		//verifyUpdatePractice(teamId1, practiceId1_1, "2011-02-19 07:00", userTimeZone1, null, null, null, null, null, null, null, "plain", null, token1); // update start time
		//verifyUpdatePractice(teamId1, practiceId1_2, "2010-10-19 11:11", userTimeZone1, null, null, null, null, null, null, null, "plain", null, token1); // update start time
		//verifyUpdatePractice(teamId1, practiceId1_1, null, null, null, null, null, null, null, null, null, "plain", true, token1); // update isCanceled
		
		
		// ===================================================
		// GET PRACTICE INFO, TEAM PRACTICES AND ALL PRACTICES
		// ===================================================
		//verifyGetTeamPractices(teamId1, practiceTimeZone1_1, "all", token1);
		//verifyGetAllPractices(practiceTimeZone1_1, "all", null, token1); // eventType of 'all'
		//verifyGetAllPractices(practiceTimeZone1_1, null, "now", token1);   // happening of 'now'
		//verifyGetPracticeInfo(teamId1, practiceId1_1, practiceTimeZone1_1, token1);
		//verifyGetPracticeInfo(teamId1, practiceId1_2, practiceTimeZone1_1, token1);
		//verifyGetPracticeInfo(teamId1, "aglydGVhbXRlc3RyDwsSCFByYWN0aWNlGIIGDA", practiceTimeZone1_1, token1);
		
		
		// ===============
		// DELETE PRACTICE
		// ===============
		//verifyDeletePractice(teamId1, practiceId1_1, token1);
		//verifyDeletePractice(teamId1, "aglydGVhbXRlc3RyDwsSCFByYWN0aWNlGNEKDA", token1);
		
		
		// ================
		// UPDATE ATTENDEES
		// ================
		// PARAMS:: verifyUpdateAttendees(String theTeamId, String theEventId, String theEventType, List<String> theMembers,
		//                                List<String> theAttendance, String theToken)
		//verifyUpdateAttendees(teamId1, gameId1_1, "game", attendeeIds1_1, attendance1_1, token1);
//		List<String> theseAttendeeIds = new ArrayList<String>();
//		theseAttendeeIds.add(memberId1_1);
//		List<String> theseAttendances = new ArrayList<String>();
//		theseAttendances.add("yes");
//		List<String> thesePreGameStatuses = new ArrayList<String>();
//		thesePreGameStatuses.add("maybe");
//		verifyUpdateAttendees(teamId1, gameId1_1, "game", theseAttendeeIds, theseAttendances, thesePreGameStatuses, token1);
		
		
		// ================
		// GET ATTENDEES
		// ================
		// PARAMS:: verifyGetAttendees(teamId, gameId, eventType, memberId, timezone, startDate, endDate, token)
		verifyGetAttendees(teamId1, gameId1_1, "game", null, null, null, null, token1); //eventId based
		//verifyGetAttendees(teamId1, null, null, memberId1_1, gameTimeZone1_1, "2010-11-07 09:55", null, token1); //memberId based, start date
		//verifyGetAttendees(teamId1, null, "game", memberId1_1, gameTimeZone1_1, "2010-11-07 09:55", null, token1); //memberId based, start date, games only
		//verifyGetAttendees(teamId1, null, null, memberId1_1, gameTimeZone1_1, null, "2010-11-09 09:55", token1); //memberId based, end date
		//verifyGetAttendees(teamId1, null, null, memberId1_1, gameTimeZone1_1, "2010-11-09 09:55", "2010-11-11 09:55", token1); //memberId based, both dates
		
		
		
		// =====================
		// CREATE MESSAGE THREAD
		// =====================
		// PARAMS:: verifyCreateMessageThread(String theTeamId, String theSubject, String theBody, String theType, String theEventId,
		//                                    String theEventType, String theIsAlert, String theIncludeFans, List<String> thePollChoices,
		//                                    List<String> theRecipients, String theIsPublic, Boolean theCoordinatorsOnly, String theToken)
		//verifyCreateMessageThread(teamId1, "Coolness", "Joe, Tuesday Morning", type1, null, eventType1, isAlert1, includeFans1, pollChoices1, null, null, null, token1); // confirmation msg
		//verifyCreateMessageThread(teamId1, "Dev test 1", "Joe, Sunday Night", type1, null, eventType1, isAlert1, includeFans1, pollChoices1, null, null, true, token1); // confirmation msg to coordinators only
		//verifyCreateMessageThread(teamId1, subject2, body2, type2, null, eventType2, isAlert2, includeFans2, pollChoices2, null, null, null, token1);  // poll msg
		//verifyCreateMessageThread(teamId1, subject2, body2, type2, null, eventType2, isAlert2, includeFans2, pollChoices2, null, "false", null, token1);  // private poll msg
		//verifyCreateMessageThread(teamId1, subject1, body1, type1, "aglydGVhbXRlc3RyCwsSBEdhbWUYsgYM", "game", isAlert1, includeFans1, pollChoices1, null, null, null, token1); // message associated with a game
		
		
		
		// =======================
		// GET MESSAGE THREAD INFO
		// =======================
		// PARAMS:: verifyGetMessageThreadInfo(String theTeamId, String theMessageThreadId, String theTimeZone,
		//                                     Boolean includeMemberInfo, String theToken)
		//verifyGetMessageThreadInfo(teamId1, messageThreadId1_1, userTimeZone1, true, token1);
		//verifyGetMessageThreadInfo(teamId1, messageThreadId1_1, userTimeZone1, true, token2);
		//verifyGetMessageThreadInfo(teamId1, "aglydGVhbXRlc3RyFAsSDU1lc3NhZ2VUaHJlYWQYoAcM", userTimeZone1, true, token1);
		
		
		
		// ==============================
		// GET MESSAGE THREADS FOR A TEAM
		// ==============================
		// PARAMS: verifyGetMessageThreads(String theTeamId, String theTimeZone, String theMessageGroup, String theEventId,
        // ------                          String theEventType, String theStatus, String theWasViewed, String theIncludeBodyAndChoices,
		//                                 Boolean theUseThreads, String theToken)
		//verifyGetMessageThreads(teamId1, userTimeZone1, null, null, null, null, null, null, null, token1); //all groups, all messages, active - by default
		//verifyGetMessageThreads(teamId1, userTimeZone1, null, null, null, "all", null, null, null, token1); //all groups, all messages, all statuses
		//verifyGetMessageThreads(teamId1, userTimeZone1, "inbox", null, null, null, null, null, null, token2); //inbox only
		//verifyGetMessageThreads(teamId1, userTimeZone1, "inbox", gameId1_1, "game", null, null, null, null, token1); //inbox, eventId, eventType
		//verifyGetMessageThreads(teamId1, userTimeZone1, "inbox", gameId1_1, "game", "active", null, null, null, token1); //inbox, eventId, eventType, status
		//verifyGetMessageThreads(teamId1, userTimeZone1, "inbox", gameId1_1, "game", "all", null, null, null, token1); //inbox, eventId, eventType, all statuses
		//verifyGetMessageThreads(teamId1, userTimeZone1, "inbox", gameId1_1, "game", null, "false", null, null, token1); //inbox, eventId, eventType, wasViewed
		//verifyGetMessageThreads(teamId1, userTimeZone1, null, gameId1_1, "game", "all", null, null, null, token1); //eventId, eventType, 
		//verifyGetMessageThreads(teamId1, userTimeZone1, null, null, null, null, "false", null, null, token1); //wasViewed
		//verifyGetMessageThreads(teamId1, userTimeZone1, null, null, null, "all", "false", null, null, token1); //all status, wasViewed
		//verifyGetMessageThreads(teamId1, userTimeZone1, "outbox", null, null, null, null, null, null, token1); //outbox only
		//verifyGetMessageThreads(teamId1, userTimeZone1, "outbox", gameId1_1, "game", null, null, null, null, token1); //outbox, eventId, eventType
		//verifyGetMessageThreads(teamId1, userTimeZone1, "outbox", gameId1_1, "game", "active", null, null, null, token1); //outbox, eventId, eventType, status
		//verifyGetMessageThreads(teamId1, userTimeZone1, "outbox", gameId1_1, "game", "all", null, null, null, token1); //outbox, eventId, eventType, status
		//verifyGetMessageThreads(teamId1, userTimeZone1, "outbox", gameId1_1, "game", null, "false", null, null, token1); //outbox, eventId, eventType, wasViewed
		//verifyGetMessageThreads(teamId1, userTimeZone1, null, null, null, null, null, null, true, token2); //all groups with useThreads
		
		
		// =================================
		// GET MESSAGE THREADS FOR ALL TEAMS
		// =================================
		// PARAMS: verifyGetMessageThreadsForAllTeams(String theTimeZone, String theMessageGroup, String theEventId,
        // ------                          String theEventType, String theStatus, String theWasViewed, String theIncludeBodyAndChoices,
		//                                 Boolean theUseThreads, String theToken)
		//verifyGetMessageThreadsForAllTeams(userTimeZone1, null, null, null, null, null, null, null, token1); //all groups, all messages, active - by default
		//verifyGetMessageThreadsForAllTeams(userTimeZone1, null, null, null, null, null, null, true, token1); //all groups with useThreads

		
		// ======================
		// UPDATE MESSAGE THREAD
		// ======================
		// PARAMS: 	verifyUpdateMessageThread(String theTeamId, String theMessageThreadId, List<String> theReminderRecipients,
        // ------                             String theReply, String theFollowupMessage, String theWasViewed, String theStatus, String theToken)
		//verifyUpdateMessageThread(teamId1, messageThreadId1_1, null, "User two replying to second message from user one", null, "true", null, token1); //reply
		//verifyUpdateMessageThread(teamId1, messageThreadId1_1, theReminderRecipients1, null, null, null, null, token1); //reminder
		//verifyUpdateMessageThread(teamId1, messageThreadId1_1, null, null, "Second Follow-up Message", null, null, token1); //follow-up
		//verifyUpdateMessageThread(teamId1, messageThreadId1_1, null, null, null, "true", null, token1); //wasViwed only
		//verifyUpdateMessageThread(teamId1, messageThreadId1_1, null, null, null, null, "archived", token1); //archive
		//verifyUpdateMessageThread(teamId1, messageThreadId1_1, null, "Catcher", null, null, null, token2); //poll reply
		
		
		// ======================
		// UPDATE MESSAGE THREADS
		// ======================
		// PARAMS: 	verifyUpdateMessageThreads(String theMessageLocation, List<String> theMessageThreadIds, String theStatus, String theToken)
//		List<String> messageThreadIds = new ArrayList<String>();
//		messageThreadIds.add(messageThreadId1_1);
//		verifyUpdateMessageThreads("outbox", messageThreadIds, "archived", token1); //archive messageThreadIds
		
		
		// ===============================================
		// MESSAGE THREADS CONFIRMATIONS AND REPLIES (GWT)
		// ===============================================
		// copy-and-paste oneUseToken param from local GAE data viewer
		// URL for testing in browser:  http://localhost:8888/Rteam.html?polltoken=q896muj9mi751l5f9f95kf2p85&pollResponse=Catcher
	    //verifyMessageThreadResponseInfo("mk48ak8l5hqi70ft0n1fkpn4ig", null);       // confirmation
		//verifyMessageThreadResponseInfo("ouhh0v16elikfdmd075dqkk6ag", "Catcher");  // poll response

		
		// ========================
		// NEW MESSAGE THREAD COUNT
		// ========================
		//verifyGetNewMessageThreadCount(null, null, null, null, null, token1); // all active messages for this user
		//verifyGetNewMessageThreadCount(null, null, null, null, "true", token1); // all active messages for this user + includeNewActivity
		//verifyGetNewMessageThreadCount(null, null, null, null, "true", token2); // all active messages for this user + includeNewActivity
		//verifyGetNewMessageThreadCount(null, null, null, "true", null, token1); // all active messages and resynch counter
		//verifyGetNewMessageThreadCount(null, null, null, "badvalue", null, token1); //bad value for resynch counter
		//verifyGetNewMessageThreadCount(teamId1, gameId1_1, "game", null, null, token2); // for a specific game
		//verifyGetNewMessageThreadCount(teamId1, gameId1_1, null, null, null, token2);  // eventType missing
		//verifyGetNewMessageThreadCount(teamId1, gameId1_1, "invalidType", null, null, token2);  // invalid eventType
		//verifyGetNewMessageThreadCount("1234567890", gameId1_1, "game", null, null, token2);  // invalid teamId
		
		
		// ==============
		// CREATE ACTIVTY
		// ==============
		//verifyCreateActivity(teamId1, "3.0 is coming!", null, token1); // simple status update
		//verifyCreateActivity(teamId1, "Fake photo message", "this is not a jpeg", token1); // try to send a fake photo - didn't work in Dev - said missing plugin
		//verifyCreateActivity(teamId2, "Peace, Love, Joy", null, token1); // simple status update
//		String bigStatus = "this is the frst part of the message and the length of this is seventy" +
//		                   " this is the second part of the message also has length of yep seventy" +
//		                   "exceeds 140 char limt!!!!!";
//		verifyCreateActivity(teamId2, bigStatus, null, token1); // status update size exceeds Twitter 140 char limit
//		String maxStatus = "this is the frst part of the message and the length of this is seventy" +
//        			       "this is the second part of the message also has length of xxxx seventy";
//		verifyCreateActivity(teamId2, maxStatus, null, token1); // status update size exactly 140 char limit
//		verifyCreateActivity(teamId2, "Peace, Love, Joy", null, token1); // status update size exactly 140 char limit
		
		
		// =========================
		// GET ACTIVITIES FOR A TEAM
		// =========================
		// PARAMS:   verifyGetActivities(String theTeamId, String theMaxCount, String theRefreshFirst, String theNewOnly,
        //                               String theMaxCacheId, String theTimeZone, String theToken)
		//verifyGetActivities(teamId1, "20", "false", null, null, userTimeZone1, token1); // get from cache like first call from screen
		//verifyGetActivities(teamId1, null, null, null, null, userTimeZone1, token1);    // get from cache -- using all defaults
		//verifyGetActivities(teamId1, "20", "true", "true", null, userTimeZone1, token1);  // refresh first, new only
		//verifyGetActivities(teamId1, "20", "true", "true", null, userTimeZone1, token2);  // refresh first, new only
		
		
		// ============================
		// GET ACTIVITIES FOR ALL TEAMS
		// ============================
		// PARAMS: verifyGetActivitiesForAllTeams(String theMaxCount, String theRefreshFirst, String theNewOnly,
        //                                        String theMostCurrentDateStr, String theTotalNumberOfDaysStr,
		//                                        String theTimeZone, String theToken)
		//verifyGetActivitiesForAllTeams("20", "false", null, "2011-10-20", "30", userTimeZone1, token1);  // get last week of activity from cache
		//verifyGetActivitiesForAllTeams("20", "true", "true", null, null, userTimeZone1, token1); // refresh & newOnly
		//verifyGetActivitiesForAllTeams("20", null, "true", null, null, userTimeZone1, token3); // refresh & newOnly
		
		
		// =================================
		// GET STATUS OF ACTIVITIES FOR USER
		// =================================
		// PARAMS: verifyGetStatusOfActivitiesForUser(String theTeamId, String theActivityIds, String theToken)
//		List<String> activityIds = new ArrayList<String>();
//		activityIds.add("aglydGVhbXRlc3RyDwsSCEFjdGl2aXR5GNgKDA");
//		verifyGetStatusOfActivitiesForUser(activityIds, token1); // for one activity ID
		
		
		// ==================
		// GET ACTIVITY PHOTO
		// ==================
		// PARAMS: verifyGetActivityPhoto(String theTeamId, String theActivityId, String theToken)
		//verifyGetActivityPhoto(teamId1, "aglydGVhbXRlc3RyDwsSCEFjdGl2aXR5GNgKDA", token1);
		//verifyGetActivityPhoto(teamId1, "555", token1);  // bad activity ID
		
		
		// ===============
		// UPDATE ACTIVITY
		// ===============
		// PARAMS:  verifyUpdateActivity(String theTeamId, String theActivityId, String theVote, String theToken)
		//verifyUpdateActivity(teamId1, "aglydGVhbXRlc3RyDwsSCEFjdGl2aXR5GNgKDA", "like", token1);  //LIKE vote
		//verifyUpdateActivity(teamId1, "aglydGVhbXRlc3RyDwsSCEFjdGl2aXR5GNgKDA", "dislike", token1);  //DISLIKE vote
		
		
		// =======================
		// GET MOBILE CARRIER LIST
		// =======================
		//verifyGetMobileCarriers();

		
		// =========
		// CRON JOBS
		// =========
		//verifyGetMessageArchiver();
		//verifyEmailSmsIsAlive();
		
		
		// ===============
		// DATA MIGRATIONS
		// ===============
		//verifyUserMigration("createUserCacheIds", null); // migration to add newestCacheIds list to users. Needed for Activity Badge
		//verifyUserMigration("encryptPasswords", "joepwro@gmail.com");   // run migration for specified email address only
		//verifyUserMigration("encryptPasswords", null);   // run migration for all email addresses
		//verifyUserMigration("bindMembersToUser", "amazingrace1998@gmail.com");   // bind memberships of user with given email address
		
		// ***** Following 4 migrations are for the Message Auto Archive Feature
		//verifyUserMigration("addAutoArchiveDayCountToUsers", null); // migration to add default AutoArchiveDayCount to all users
		//verifyUserMigration("addAutoArchiveDayCountToNaMembers", null); // migration to add default AutoArchiveDayCount to all NA members
		//verifyUserMigration("addActiveThruGmtDateToMessageThreads", null); // migration to add ActiveThruGmtDate to ACTIVE messageThreads
		//verifyUserMigration("addActiveThruGmtDateToRecipients", null); // migration to add ActiveThruGmtDate to Non-ARCHIVED Recipients
		//verifyUserMigration("normalizeGuardianLists", null); // migration to normalize Guardian lists in Member entity
		//verifyUserMigration("normalizeEmailAddressesTask", null); // convert ALL stored email addresses to lower case
		//verifyUserMigration("guardianSmsEmailAddressesTask", null); // make guardianSmsEmailAddress list same size as other guardian lists
		//verifyUserMigration("normalizePhoneNumbersTask", null); // migration to normalize member phone numbers to all digits
		//verifyUserMigration("normalizeGuardianListsTask", null); // for each member, ensure all guardian lists are the same size
		//verifyUserMigration("defaultMemberAccessPreferencesTask", null); // for each member, default new access preference fields
	}
	
	private static String verifyUserApis() {
		String emailAddress = "769843230@unique.com";
		//String token = verifyCreateUser(emailAddress);
		return "PASS";
	}
	
//	private static String verifyCreateUser(String theFirstName, String theLastName, String thePassword, String theEmailAddress,
//			String thePhoneNumber, Boolean theAlreadyMember, String thePasswordResetQuestion, String thePasswordResetAnswer,
//			String theLatitude, String theLongitude) {
//		if(isLoggingEnabled) System.out.println("\n\nverifyCreateUser() starting .....\n");
//		ClientResource usersRoot = new ClientResource(HTTPS_BASE_URL + USERS_RESOURCE_URI);
//		JSONObject json = new JSONObject();
//		String token = "";
//		try {
//			// required fields
//			json.put("password", thePassword);
//			json.put("emailAddress", theEmailAddress);
//			
//			// optional fields
//			if(theFirstName != null) json.put("firstName", theFirstName);
//			if(theLastName != null) json.put("lastName", theLastName);
//			if(theAlreadyMember) json.put("alreadyMember", "true");
//			if(thePhoneNumber != null) json.put("phoneNumber", thePhoneNumber);
//			if(thePasswordResetQuestion != null) json.put("passwordResetQuestion", thePasswordResetQuestion);
//			if(thePasswordResetAnswer != null) json.put("passwordResetAnswer", thePasswordResetAnswer);
//			if(theLatitude != null) json.put("latitude", theLatitude);
//			if(theLongitude != null) json.put("longitude", theLongitude);
//			
//			System.out.println(json.toString());
//			JsonRepresentation jsonPost = new JsonRepresentation(json);
//			
//			//usersRoot.setRequestEntityBuffering(true);
//			String repStr = usersRoot.post(jsonPost).getText();
//			if(isLoggingEnabled) System.out.println("repStr = " + repStr);
//			JSONObject jsonReturn = new JSONObject(repStr);
//		} catch (ResourceException e) {
//			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			System.out.println("verifyCreateUser() complete");
//		}
//		return token;
//	}
	
	private static String verifyCreateUser(String theFirstName, String theLastName, String thePassword, String theEmailAddress,
			String thePhoneNumber, Boolean theAlreadyMember, String thePasswordResetQuestion, String thePasswordResetAnswer,
			String theLatitude, String theLongitude) {
		if(isLoggingEnabled) System.out.println("\n\nverifyCreateUser() starting .....\n");
		String urlStr = HTTPS_BASE_URL + USERS_RESOURCE_URI;
		JSONObject json = new JSONObject();
		String token = "";
		try {
			// required fields
			json.put("password", thePassword);
			json.put("emailAddress", theEmailAddress);
			
			// optional fields
			if(theFirstName != null) json.put("firstName", theFirstName);
			if(theLastName != null) json.put("lastName", theLastName);
			if(theAlreadyMember) json.put("alreadyMember", "true");
			if(thePhoneNumber != null) json.put("phoneNumber", thePhoneNumber);
			if(thePasswordResetQuestion != null) json.put("passwordResetQuestion", thePasswordResetQuestion);
			if(thePasswordResetAnswer != null) json.put("passwordResetAnswer", thePasswordResetAnswer);
			if(theLatitude != null) json.put("latitude", theLatitude);
			if(theLongitude != null) json.put("longitude", theLongitude);
			System.out.println(json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_POST, json.toString(), null, null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			JSONObject jsonReturn = new JSONObject(response);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyCreateUser() complete");
		}
		return token;
	}
	
	
	private static String verifyUpdateUser(String theFirstName, String theLastName, String thePassword, String thePhoneNumber,
			  String theIsNetworkAuthenticated, String thePasswordResetQuestion, String thePasswordResetAnswer, 
			  String theUserIconOneId, String theUserIconOneAlias, String theUserIconOneImage, String theUserIconTwoId,
			  String theUserIconTwoAlias, String theUserIconTwoImage, Integer theAutoArchiveDayCount, String theFakePhoto,
			  String theLatitude, String theLongitude, String theToken) {
		if(isLoggingEnabled) System.out.println("\n\nverifyUpdateUser() starting .....\n");
		String urlStr = HTTPS_BASE_URL + USER_RESOURCE_URI;
		System.out.println("urlStr = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		String token = "";
		try {
			if(theFirstName != null) json.put("firstName", theFirstName);
			if(theLastName != null) json.put("lastName", theLastName);
			if(thePassword != null) json.put("password", thePassword);
			if(theIsNetworkAuthenticated != null) json.put("isNetworkAuthenticated", theIsNetworkAuthenticated);
			if(thePhoneNumber != null) json.put("phoneNumber", thePhoneNumber);
			if(thePasswordResetQuestion != null) json.put("passwordResetQuestion", thePasswordResetQuestion);
			if(thePasswordResetAnswer != null) json.put("passwordResetAnswer", thePasswordResetAnswer);
			if(theUserIconOneId != null) json.put("userIconOneId", theUserIconOneId);
			if(theUserIconOneAlias != null) json.put("userIconOneAlias", theUserIconOneAlias);
			if(theUserIconOneImage != null) json.put("userIconOneImage", theUserIconOneImage);
			if(theUserIconTwoId != null) json.put("userIconTwoId", theUserIconTwoId);
			if(theUserIconTwoAlias != null) json.put("userIconTwoAlias", theUserIconTwoAlias);
			if(theUserIconTwoImage != null) json.put("userIconTwoImage", theUserIconTwoImage);
			if(theAutoArchiveDayCount != null) json.put("autoArchiveDayCount", theAutoArchiveDayCount);
			if(theLatitude != null) json.put("latitude", theLatitude);
			if(theLongitude != null) json.put("longitude", theLongitude);
			
			if(theFakePhoto != null) {
				String photoBase64 = Base64.encode(theFakePhoto.getBytes());
				json.put("photo", photoBase64);
			}
			System.out.println(json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), "login", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyUpdateUser() complete");
		}
		return token;
	}
	
	
	private static String verifyUserMigration(String theMigrationName, String theParameterOne) {
		if(isLoggingEnabled) System.out.println("\n\n verifyUserMigration() starting .....\n");
		String urlStr = HTTPS_BASE_URL + USERS_RESOURCE_URI + "/migration";
		JSONObject json = new JSONObject();
		String token = "";
		try {
			json.put("secret", "ae53b1f9");
			json.put("migrationName", theMigrationName);
			if(theParameterOne != null) json.put("parameterOne", theParameterOne);
			System.out.println(json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), null, null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyUserMigration() complete");
		}
		return token;
	}

	private static String verifyResetUserPassword(String theEmailAddress, String thePasswordResetAnswer) {
		if(isLoggingEnabled) System.out.println("\n\n verifyResetUserPassword() starting .....\n");
		String encodedEmail = ClientTest.encode(theEmailAddress);
		String urlStr = HTTPS_BASE_URL + USER_RESOURCE_URI;
		urlStr = urlStr + "?" + "emailAddress=" + encodedEmail;
		JSONObject json = new JSONObject();
		String token = "";
		try {
			json.put("isPasswordReset", "any_thing_here_works");
			if(thePasswordResetAnswer != null) {json.put("passwordResetAnswer", thePasswordResetAnswer);}
			System.out.println(json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), null, null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyResetUserPassword() complete");
		}
		return token;
	}
	
	private static void verifyDeleteAllUsers(String theSuperUserToken) {
		System.out.println("\n\n verifyDeleteAllUsers() starting .....\n");
		String urlStr = HTTPS_BASE_URL + USERS_RESOURCE_URI + "/deleteAll";
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_DELETE, null, "rTeamLogin", theSuperUserToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyDeleteAllUsers() complete");
		}
	}
	
//	private static void verifyGetUserInfo(String theToken) {
//		System.out.println("\n\n verifyGetUserInfo() starting .....\n");
//		String url = HTTPS_BASE_URL + USER_RESOURCE_URI;
//		System.out.println("url = " + url + "\n");
//		ClientResource usersRoot = new ClientResource(url);
//		try {
//			usersRoot.setChallengeResponse(ChallengeScheme.HTTP_BASIC, "login", theToken);
//			usersRoot.get().write(System.out);
//			System.out.println("\n");
//		} catch (ResourceException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			System.out.println("verifyGetUserInfo() complete\n");
//		}
//	}
	
	private static void verifyGetUserInfo(String theToken) {
		System.out.println("\n\n verifyGetUserInfo() starting .....\n");
		String urlStr = HTTPS_BASE_URL + USER_RESOURCE_URI;
		System.out.println("urlStr = " + urlStr + "\n");
		
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "login", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetUserInfo() complete\n");
		}
	}
	
	private static void verifyGetUserConfirmationInfo(String theOneUseToken) {
		System.out.println("\n\n verifyGetUserConfirmationInfo() starting .....\n");
		String encodedOneUseToken = ClientTest.encode(theOneUseToken);
		String urlStr = HTTPS_BASE_URL + USER_RESOURCE_URI;
		urlStr = urlStr + "?" + "oneUseToken=" + encodedOneUseToken;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, null, null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetUserConfirmationInfo() complete\n");
		}
	}
	
	private static void verifyGetUserToken(String theEmailAddress, String thePassword) {
		System.out.println("\n\nverifyGetUserToken() starting .....\n");
		String encodedEmail = ClientTest.encode(theEmailAddress);
		String encodedPassword = ClientTest.encode(thePassword);
		String urlStr = HTTPS_BASE_URL + USER_RESOURCE_URI;
		urlStr = urlStr + "?" + "emailAddress=" + encodedEmail + "&" + "password=" + encodedPassword;
		System.out.println("urlStr = " + urlStr + "\n");
		
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, null, null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetUserToken() complete\n");
		}
	}
	
	private static void verifyGetUserPasswordResetQuestion(String theEmailAddress) {
		System.out.println("\n\n verifyGetUserPasswordResetQuestion() starting .....\n");
		String encodedEmail = ClientTest.encode(theEmailAddress);
		String urlStr = HTTPS_BASE_URL + USER_RESOURCE_URI + "/passwordResetQuestion";
		urlStr = urlStr + "?" + "emailAddress=" + encodedEmail;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, null, null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetUserPasswordResetQuestion() complete\n");
		}
	}
	
	private static void verifyDeleteUser(String theToken, Boolean deleteNow) {
		System.out.println("\n\n verifyDeleteUser() starting .....\n");
		String urlStr = HTTPS_BASE_URL + USER_RESOURCE_URI;
		if(deleteNow != null && deleteNow) {
			urlStr = urlStr + "?" + "deleteNow=true";
		}
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_DELETE, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyDeleteUser() complete\n");
		}
	}
	
	private static void verifyCreateTeam(String theTeamName, String theTeamDescription, String theLeagueName, String theSport,
			   String theTeamSiteUrl, String theGender, String theCity, String theState, String theLatitude,
			    String theLongitude, Boolean theUseTwitter, String theToken) {
		System.out.println("\n\nverifyCreateTeam() starting .....\n");
		String urlStr = HTTPS_BASE_URL + TEAMS_RESOURCE_URI;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			json.put("teamName", theTeamName);
			json.put("description", theTeamDescription);
			if(theLeagueName != null) json.put("leagueName", theLeagueName);
			if(theSport != null) json.put("sport", theLeagueName);
			if(theTeamSiteUrl != null) json.put("teamSiteUrl", theTeamSiteUrl);
			if(theGender != null) json.put("gender", theGender);
			if(theCity != null) json.put("city", theCity);
			if(theState != null) json.put("state", theState);
			if(theLatitude != null) json.put("latitude", theLatitude);
			if(theLongitude != null) json.put("longitude", theLongitude);
			if(theUseTwitter != null) json.put("useTwitter", theUseTwitter.booleanValue());
			System.out.println(json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_POST, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			JSONObject jsonReturn = new JSONObject(response);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyCreateTeam() complete\n");
		}
	}
	
	private static void verifyUpdateTeam(String theTeamId, String theTeamName, String theTeamDescription, String theLeagueName,
			String theTwitterUserName, String theTwitterPassword, String theTeamSiteUrl, String theSport, 
			String theTwitterAccessToken, String theTwitterAccessTokenSecret,
			Long theNewestTwitterId, Long theNewestCacheId, Boolean theUseTwitter, String theToken) {
		System.out.println("\n\nverifyUpdateTeam() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			if(theTeamName != null) json.put("teamName", theTeamName);
			if(theTeamDescription != null) json.put("description", theTeamDescription);
			if(theLeagueName != null) json.put("leagueName", theLeagueName);
			if(theTwitterUserName != null) json.put("twitterUserName", theTwitterUserName);
			if(theTwitterPassword != null) json.put("twitterPassword", theTwitterPassword);
			if(theTeamSiteUrl != null) json.put("teamSiteUrl", theTeamSiteUrl);
			if(theSport != null) json.put("sport", theSport);
			if(theTwitterAccessToken != null) json.put("twitterAccessToken", theTwitterAccessToken);
			if(theTwitterAccessTokenSecret != null) json.put("twitterAccessTokenSecret", theTwitterAccessTokenSecret);
			if(theNewestTwitterId != null) json.put("newestTwitterId", theNewestTwitterId);
			if(theNewestCacheId != null) json.put("newestCacheId", theNewestCacheId);
			if(theUseTwitter != null) json.put("useTwitterForced", theUseTwitter);
			//if(theUseTwitter != null) json.put("useTwitter", theUseTwitter);
			System.out.println(json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyUpdateTeam() complete\n");
		}
	}

	private static void verifyGetTeamInfo(String theTeamId, String theToken) {
		System.out.println("\n\nverifyGetTeamInfo() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetTeamInfo() complete\n");
		}
	}
	
	private static void verifyGetTeams(String theToken) {
		System.out.println("\n\nverifyGetTeams() starting .....\n");
		String urlStr = HTTPS_BASE_URL + TEAMS_RESOURCE_URI;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetTeams() complete\n");
		}
	}

	private static void verifyDeleteTeam(String theTeamName, String theToken) {
		System.out.println("\n\n verifyDeleteTeam() starting .....\n");
		String encodedTeamName = ClientTest.encode(theTeamName);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamName;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_DELETE, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyDeleteTeam() complete\n");
		}
	}
	

	private static void verifyCreateMember(String theTeamId, String theFirstName, String theLastName, List<String> theRoles,
			String theEmailAddress, String theJerseyNumber, String thePhoneNumber, List<String> theGuardianEmailAddresses,
			List<String> theGuardianFirstNames, List<String> theGuardianLastNames, String theParticipantRole, String theGender, 
			String theAge, String theStreetAddress, String theCity, String theState, String theZipcode, String theToken) {
		System.out.println("\n\nverifyCreateMember() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + MEMBERS_RESOURCE_URI;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			json.put("firstName", theFirstName);
			json.put("lastName", theLastName);
			if(theEmailAddress != null) json.put("emailAddress", theEmailAddress);
			if(theJerseyNumber != null) json.put("jerseyNumber", theJerseyNumber);
			if(theParticipantRole != null) json.put("participantRole", theParticipantRole);
			if(thePhoneNumber != null) json.put("phoneNumber", thePhoneNumber);
			if(theGender != null) json.put("gender", theGender);
			if(theAge != null) json.put("age", theAge);
			if(theStreetAddress != null) json.put("streetAddress", theStreetAddress);
			if(theCity != null) json.put("city", theCity);
			if(theState != null) json.put("state", theState);
			if(theZipcode != null) json.put("zipcode", theZipcode);
			
			if(theGuardianEmailAddresses != null) {
				JSONArray guardianJsonArray = new JSONArray();
				int index = 0;
				for(String ea : theGuardianEmailAddresses) {
					JSONObject guardianJsonObj = new JSONObject();
					guardianJsonObj.put("emailAddress", ea);
					if(theGuardianFirstNames != null) guardianJsonObj.put("firstName", theGuardianFirstNames.get(index));
					if(theGuardianLastNames != null) guardianJsonObj.put("lastName", theGuardianLastNames.get(index));
					index++;
					guardianJsonArray.put(guardianJsonObj);
				}
				if(guardianJsonArray.length() > 0) json.put("guardians", guardianJsonArray);
			}
			
			if(theRoles != null) {
				JSONArray roleJsonArray = new JSONArray();
				for(String r : theRoles) {
					roleJsonArray.put(r);
				}
				json.put("roles", roleJsonArray);
			}
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_POST, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			JSONObject jsonReturn = new JSONObject(response);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyCreateMember() complete\n");
		}
	}
	

	// All String arrays passed in must be non-null and exactly the same size!!!!!
	private static void verifyCreateMultipleMembers(String theTeamId, String[] theFirstNames, String[] theLastNames,
			                  String[] theEmailAddresses, String[] thePhoneNumbers, String theParticipantRole, String theToken) {
		System.out.println("\n\n verifyCreateMultipleMembers() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + MEMBERS_RESOURCE_URI + "/multiple";
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			JSONArray membersJsonArray = new JSONArray();
			for(int index=0; index<theFirstNames.length; index++) {
				JSONObject memberJsonObj = new JSONObject();
				memberJsonObj.put("firstName", theFirstNames[index]);
				memberJsonObj.put("lastName", theLastNames[index]);
				memberJsonObj.put("emailAddress", theEmailAddresses[index]);
				if(thePhoneNumbers[index].length() > 0) memberJsonObj.put("phoneNumber", thePhoneNumbers[index]);
				memberJsonObj.put("participantRole", theParticipantRole);
				membersJsonArray.put(memberJsonObj);
			}
			json.put("members", membersJsonArray);
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_POST, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			JSONObject jsonReturn = new JSONObject(response);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyCreateMultipleMembers() complete\n");
		}
	}

	private static void verifyGetMembers(String theTeamId, Boolean theIncludeFans, String theToken) {
		System.out.println("\n\nverifyGetMembers() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + MEMBERS_RESOURCE_URI;
		if(theIncludeFans != null && theIncludeFans.booleanValue()) {
			urlStr = urlStr + "?" + "includeFans=true";
		}
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetMembers() complete\n");
		}
	}

	private static void verifyGetMemberInfo(String theTeamId, String theMemberId, String theToken) {
		System.out.println("\n\nverifyGetMemberInfo() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedMemberId = ClientTest.encode(theMemberId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + MEMBER_RESOURCE_URI + "/" + encodedMemberId;
    	System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetMemberInfo() complete\n");
		}
	}

	private static void verifyGetMembershipStatus(String theEmailAddress) {
		System.out.println("\n\n verifyGetMembershipStatus() starting .....\n");
		String encodedEmailAddress = ClientTest.encode(theEmailAddress);
		String urlStr = HTTPS_BASE_URL + MEMBER_RESOURCE_URI;
		urlStr = urlStr + "?" + "emailAddress=" + encodedEmailAddress;
    	System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, null, null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetMembershipStatus() complete\n");
		}
	}

	private static void verifyDeleteMember(String theTeamId, String theMemberId, String theToken) {
		System.out.println("\n\n verifyDeleteMember() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedMemberId = ClientTest.encode(theMemberId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + MEMBER_RESOURCE_URI + "/" + encodedMemberId;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_DELETE, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyDeleteMember() complete\n");
		}
	}

	private static void verifyUpdateMember(String theTeamId, String theMemberId, String theFirstName, String theLastName,
			List<String> theRoles, String theEmailAddress, String theJerseyNumber, String thePhoneNumber, String theThumbNail,
			List<String> theGuardianKeys, List<String> theGuardianEmailAddresses, List<String> theGuardianFirstNames,
			List<String> theGuardianLastNames, List<String> theGuardianPhoneNumbers, String theParticipantRole, String theToken) {
		System.out.println("\n\n verifyUpdateMember() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedMemberId = ClientTest.encode(theMemberId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + MEMBER_RESOURCE_URI + "/" + encodedMemberId;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			if(theFirstName != null) json.put("firstName", theFirstName);
			if(theLastName != null) json.put("lastName", theLastName);
			if(theEmailAddress != null) json.put("emailAddress", theEmailAddress);
			if(theJerseyNumber != null) json.put("jerseyNumber", theJerseyNumber);
			if(theThumbNail != null) json.put("thumbNail", theThumbNail);
			if(theParticipantRole != null) json.put("participantRole", theParticipantRole);
			if(thePhoneNumber != null) json.put("phoneNumber", thePhoneNumber);
			
			if(theGuardianKeys != null) {
				JSONArray guardianJsonArray = new JSONArray();
				int index = 0;
				for(String key : theGuardianKeys) {
					JSONObject guardianJsonObj = new JSONObject();
					guardianJsonObj.put("key", key);
					if(theGuardianEmailAddresses != null) guardianJsonObj.put("emailAddress", theGuardianEmailAddresses.get(index));
					if(theGuardianFirstNames != null) guardianJsonObj.put("firstName", theGuardianFirstNames.get(index));
					if(theGuardianLastNames != null) guardianJsonObj.put("lastName", theGuardianLastNames.get(index));
					if(theGuardianPhoneNumbers != null) guardianJsonObj.put("phoneNumber", theGuardianPhoneNumbers.get(index));
					index++;
					guardianJsonArray.put(guardianJsonObj);
				}
				if(guardianJsonArray.length() > 0) json.put("guardians", guardianJsonArray);
			}
			
			if(theRoles != null) {
				JSONArray roleJsonArray = new JSONArray();
				for(String r : theRoles) {
					roleJsonArray.put(r);
				}
				json.put("roles", roleJsonArray);
			}
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyUpdateMember() complete\n");
		}
	}
	
	private static void verifyCreatePractice(String theTeamId, String theStartDate, String theTimeZone, String theDescription,
			String theEndDate, Double theLatitude, Double theLongitude, String theOpponent, 
			String theEventType, String theEventName, String theToken) {
		System.out.println("\n\n verifyCreatePractice() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + PRACTICES_RESOURCE_URI;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();		try {
			json.put("startDate", theStartDate);
			json.put("timeZone", theTimeZone);
			
			if(theDescription != null) json.put("description", theDescription);
			if(theEndDate != null) json.put("endDate", theEndDate);
			if(theLatitude != null) json.put("latitude", theLatitude);
			if(theLongitude != null) json.put("longitude", theLongitude);
			if(theOpponent != null) json.put("opponent", theOpponent);
			if(theEventType != null) json.put("eventType", theEventType);
			if(theEventName != null) json.put("eventName", theEventName);
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_POST, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyCreatePractice() complete\n");
		}
	}
	
	// Creates multiple weekly practices (7 days apart from each other).
	// theNumberOfPractices: specifies the number of practices to create.  If null, defaults to 2.
	// theNotificationType: defaults to "plain"
	private static void verifyCreateMultiplePractices(String theTeamId, String theStartDate, String theTimeZone, String theDescription,
			String theEndDate, Double theLatitude, Double theLongitude, String theOpponent, String theEventType, String theEventName,
			Integer theNumberOfPractices, String theNotificationType, String theToken) {
		System.out.println("\n\n verifyCreateMultiplePractices() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + PRACTICES_RESOURCE_URI + "/recurring/multiple";
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			/////////////////////
			// Parameter handling
			/////////////////////
			if(theStartDate == null || theTimeZone == null || theEventType == null) {
				System.out.println("StartDate, TimeZone and EventType are all required");
				return;
			}
			
			TimeZone tz = GMT.getTimeZone(theTimeZone);
			if(tz == null) {
				System.out.println("TimeZone not recognized");
				return;
			}
			
			Date baseStartDate = null;
			Date baseEndDate = null;
			try {
				baseStartDate = GMT.stringToDate(theStartDate, tz);
				if(theEndDate != null) {baseEndDate = GMT.stringToDate(theEndDate, tz);}
			} catch (ParseException e) {
				System.out.println("invalid Start/End Date format");
				return;
			}
			
			// default parameters if necessary
			if(theNumberOfPractices == null)    {theNumberOfPractices = 2;}
			if(theNotificationType == null) {theNotificationType = "plain";}
			
			////////////////////////////
			// Create multiple practices
			////////////////////////////
			json.put("timeZone", theTimeZone);
			json.put("notificationType", theNotificationType);
			JSONArray practicesJsonArray = new JSONArray();
			for(int i=0; i<theNumberOfPractices; i++) {
				JSONObject practiceJsonObject = new JSONObject();
				
				// add a multiple of 7 days to base date
				Date newStartDate = GMT.addDaysToDate(baseStartDate, i*7);
				String newStartDateStr = GMT.dateToString(newStartDate);
				practiceJsonObject.put("startDate", newStartDateStr);
				if(theEndDate != null) {
					Date newEndDate = GMT.addDaysToDate(baseEndDate, i*7);
					String newEndDateStr = GMT.dateToString(newEndDate);
					practiceJsonObject.put("endDate", newEndDateStr);
				}
				
				if(theDescription != null) practiceJsonObject.put("description", theDescription);
				if(theLatitude != null) practiceJsonObject.put("latitude", theLatitude);
				if(theLongitude != null) practiceJsonObject.put("longitude", theLongitude);
				if(theEventType != null) practiceJsonObject.put("eventType", theEventType);
				if(theEventName != null) practiceJsonObject.put("eventName", theEventName);
				if(theOpponent != null) practiceJsonObject.put("opponent", theOpponent);
				practicesJsonArray.put(practiceJsonObject);
			}
			json.put("practices", practicesJsonArray);
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_POST, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyCreateMultiplePractices() complete\n");
		}
	}
	
	private static void verifyGetTeamPractices(String theTeamId, String theTimeZone, String theEventType, String theToken) {
		System.out.println("\n\nverifyGetPractices() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedTimeZone = ClientTest.encode(theTimeZone);
		String encodedEventType = null;
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + PRACTICES_RESOURCE_URI + "/" + encodedTimeZone;
		if(theEventType != null) {
			encodedEventType = ClientTest.encode(theEventType);
			urlStr = urlStr + "?" + "eventType=" + encodedEventType;
		}
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetTeamPractices() complete\n");
		}
	}
	
	private static void verifyGetAllPractices(String theTimeZone, String theEventType, String theHappening, String theToken) {
		System.out.println("\n\n verifyGetAllPractices() starting .....\n");
		String encodedTimeZone = ClientTest.encode(theTimeZone);
		String encodedEventType = null;
		String urlStr = HTTPS_BASE_URL + PRACTICES_RESOURCE_URI + "/" + encodedTimeZone;
		if(theHappening != null) {
			encodedEventType = ClientTest.encode(theHappening);
			urlStr = urlStr + "?" + "happening=" + encodedEventType;
		} else if(theEventType != null) {
			encodedEventType = ClientTest.encode(theEventType);
			urlStr = urlStr + "?" + "eventType=" + encodedEventType;
		}

		System.out.println("url with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetAllPractices() complete\n");
		}
	}
	
	private static void verifyGetPracticeInfo(String theTeamId, String thePracticeId, String theTimeZone, String theToken) {
		System.out.println("\n\nverifyGetPracticeInfo() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedPracticeId = ClientTest.encode(thePracticeId);
		String encodedTimeZone = ClientTest.encode(theTimeZone);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + PRACTICE_RESOURCE_URI + "/" + encodedPracticeId + "/" + encodedTimeZone;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetPracticeInfo() complete\n");
		}
	}

	private static void verifyDeletePractice(String theTeamId, String thePracticeId, String theToken) {
		System.out.println("\n\n verifyDeletePractice() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedPracticeId = ClientTest.encode(thePracticeId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + PRACTICE_RESOURCE_URI + "/" + encodedPracticeId;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_DELETE, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyDeletePractice() complete\n");
		}
	}

	private static void verifyUpdatePractice(String theTeamId, String thePracticeId, String theStartDate, String theTimeZone,
			         String theDescription, String theEndDate, Double theLatitude, Double theLongitude,
			         String theOpponent, String theEventName, Boolean theUpdateAll, String theNotificationType,
			         Boolean theIsCanceled, String theToken) {
		System.out.println("\n\n verifyUpdatePractice() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedPracticeId = ClientTest.encode(thePracticeId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + PRACTICE_RESOURCE_URI + "/" + encodedPracticeId;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			if(theStartDate != null) json.put("startDate", theStartDate);
			if(theTimeZone != null) json.put("timeZone", theTimeZone);
			if(theDescription != null) json.put("description", theDescription);
			if(theEndDate != null) json.put("endDate", theEndDate);
			if(theLatitude != null) json.put("latitude", theLatitude);
			if(theLongitude != null) json.put("longitude", theLongitude);
			if(theOpponent != null) json.put("opponent", theOpponent);
			if(theEventName != null) json.put("eventName", theEventName);
			if(theUpdateAll != null) json.put("updateAll", theUpdateAll.booleanValue());
			if(theNotificationType != null) json.put("notificationType", theNotificationType);
			if(theIsCanceled != null) json.put("isCanceled", theIsCanceled);
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyUpdatePractice() complete\n");
		}
	}
	
	private static void verifyCreateGame(String theTeamId, String theStartDate, String theTimeZone, String theDescription, String theEndDate,
			Double theLatitude, Double theLongitude, String theOpponent, String theLocation, String theToken) {
		System.out.println("\n\n verifyCreateGame() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + GAMES_RESOURCE_URI;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			json.put("startDate", theStartDate);
			json.put("timeZone", theTimeZone);
			json.put("description", theDescription);

			if(theEndDate != null) json.put("endDate", theEndDate);
			if(theLatitude != null) json.put("latitude", theLatitude);
			if(theLongitude != null) json.put("longitude", theLongitude);
			if(theLocation != null) json.put("location", theLocation);
			if(theOpponent != null) json.put("opponent", theOpponent);
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_POST, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			JSONObject jsonReturn = new JSONObject(response);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyCreateGame() complete\n");
		}
	}
	
	// Creates multiple weekly games (7 days apart from each other).
	// theNumberOfGames: specifies the number of games to create.  If null, defaults to 2.
	// theNotificationType: defaults to "plain"
	private static void verifyCreateMultipleGames(String theTeamId, String theStartDate, String theTimeZone, String theDescription,
			String theEndDate, Double theLatitude, Double theLongitude, String theOpponent, String theLocation,
			Integer theNumberOfGames, String theNotificationType, String theToken) {
		System.out.println("\n\n verifyCreateMultipleGame() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + GAMES_RESOURCE_URI + "/recurring/multiple";
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			/////////////////////
			// Parameter handling
			/////////////////////
			if(theStartDate == null || theTimeZone == null) {
				System.out.println("StartDate or TimeZone is NULL and they are both required");
				return;
			}
			
			TimeZone tz = GMT.getTimeZone(theTimeZone);
			if(tz == null) {
				System.out.println("TimeZone not recognized");
				return;
			}
			
			Date baseStartDate = null;
			Date baseEndDate = null;
			try {
				baseStartDate = GMT.stringToDate(theStartDate, tz);
				if(theEndDate != null) {baseEndDate = GMT.stringToDate(theEndDate, tz);}
			} catch (ParseException e) {
				System.out.println("invalid Start/End Date format");
				return;
			}
			
			// default parameters if necessary
			if(theNumberOfGames == null)    {theNumberOfGames = 2;}
			if(theNotificationType == null) {theNotificationType = "plain";}
			
			////////////////////////
			// Create multiple games
			////////////////////////
			json.put("timeZone", theTimeZone);
			json.put("notificationType", theNotificationType);
			JSONArray gamesJsonArray = new JSONArray();
			for(int i=0; i<theNumberOfGames; i++) {
				JSONObject gameJsonObj = new JSONObject();
				
				// add a multiple of 7 days to base date
				Date newStartDate = GMT.addDaysToDate(baseStartDate, i*7);
				String newStartDateStr = GMT.dateToString(newStartDate);
				gameJsonObj.put("startDate", newStartDateStr);
				if(theEndDate != null) {
					Date newEndDate = GMT.addDaysToDate(baseEndDate, i*7);
					String newEndDateStr = GMT.dateToString(newEndDate);
					gameJsonObj.put("endDate", newEndDateStr);
				}
				
				if(theDescription != null) gameJsonObj.put("description", theDescription);
				if(theLatitude != null) gameJsonObj.put("latitude", theLatitude);
				if(theLongitude != null) gameJsonObj.put("longitude", theLongitude);
				if(theLocation != null) gameJsonObj.put("location", theLocation);
				if(theOpponent != null) gameJsonObj.put("opponent", theOpponent);
				gamesJsonArray.put(gameJsonObj);
			}
			json.put("games", gamesJsonArray);
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_POST, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			JSONObject jsonReturn = new JSONObject(response);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyCreateMultipleGame() complete\n");
		}
	}
	
	private static void verifyGetTeamGames(String theTeamId, String theTimeZone, String theToken) {
		System.out.println("\n\n verifyGetTeamGames() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedTimeZone = ClientTest.encode(theTimeZone);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + GAMES_RESOURCE_URI + "/" + encodedTimeZone;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetTeamGames() complete\n");
		}
	}
	
	private static void verifyGetAllGames(String theTimeZone, String theToken) {
		System.out.println("\n\n verifyGetAllGames() starting .....\n");
		String encodedTimeZone = ClientTest.encode(theTimeZone);
		String urlStr = HTTPS_BASE_URL + GAMES_RESOURCE_URI + "/" + encodedTimeZone;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetAllGames() complete\n");
		}
	}
	
	private static void verifyGetGameInfo(String theTeamId, String theGameId, String theTimeZone, String theToken) {
		System.out.println("\n\nverifyGetGameInfo() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedGameId = ClientTest.encode(theGameId);
		String encodedTimeZone = ClientTest.encode(theTimeZone);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + GAME_RESOURCE_URI + "/" + encodedGameId + "/" + encodedTimeZone;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetGameInfo() complete\n");
		}
	}

	private static void verifyDeleteGame(String theTeamId, String theGameId, String theToken) {
		System.out.println("\n\n verifyDeleteGame() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedGameId = ClientTest.encode(theGameId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + GAME_RESOURCE_URI + "/" + encodedGameId;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_DELETE, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyDeleteGame() complete\n");
		}
	}

	private static void verifyUpdateGame(String theTeamId, String theGameId, String theStartDate, String theTimeZone, String theDescription,
			          String theEndDate, Double theLatitude, Double theLongitude, String theOpponent, String theScoreUs,
			          String theScoreThem, String theInterval, String theNotificationType, String theLocation,
			          String thePollStatus, Boolean theUpdateAll, String theToken) {
		System.out.println("\n\n verifyUpdateGame() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedGameId = ClientTest.encode(theGameId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + GAME_RESOURCE_URI + "/" + encodedGameId;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			if(theStartDate != null) json.put("startDate", theStartDate);
			if(theTimeZone != null) json.put("timeZone", theTimeZone);
			if(theDescription != null) json.put("description", theDescription);
			if(theEndDate != null) json.put("endDate", theEndDate);
			if(theLatitude != null) json.put("latitude", theLatitude);
			if(theLongitude != null) json.put("longitude", theLongitude);
			if(theLocation != null) json.put("location", theLocation);
			if(theOpponent != null) json.put("opponent", theOpponent);
			if(theScoreUs != null) json.put("scoreUs", theScoreUs);
			if(theScoreThem != null) json.put("scoreThem", theScoreThem);
			if(theInterval != null) json.put("interval", theInterval);
			if(theNotificationType != null) json.put("notificationType", theNotificationType);
			if(thePollStatus != null) json.put("pollStatus", thePollStatus);
			if(theUpdateAll != null) json.put("updateAll", theUpdateAll.booleanValue());
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyUpdateGame() complete\n");
		}
	}

	private static void verifyCastGameVote(String theTeamId, String theGameId, String theMemberId, String theVoteType, String theToken) {
		System.out.println("\n\n verifyCastGameVote() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedGameId = ClientTest.encode(theGameId);
		String encodedVoteType = ClientTest.encode(theVoteType);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + GAME_RESOURCE_URI + "/" + encodedGameId + "/vote/" + encodedVoteType;
			
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			if(theMemberId != null) json.put("memberId", theMemberId);
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyCastGameVote() complete\n");
		}
	}

	private static void verifyGetGameVoteTallies(String theTeamId, String theGameId, String theVoteType, String theToken) {
		System.out.println("\n\n verifyGetGameVoteTallies() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedGameId = ClientTest.encode(theGameId);
		String encodedVoteType = ClientTest.encode(theVoteType);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + GAME_RESOURCE_URI + "/" + encodedGameId + "/vote/" + encodedVoteType + "/tallies";
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetGameVoteTallies() complete\n");
		}
	}
	
	private static void verifyUpdateAttendees(String theTeamId, String theEventId, String theEventType, List<String> theMembers, List<String> theAttendance, List<String> thePreGameStatus, String theToken) {
		System.out.println("\n\n verifyUpdateAttendees() starting .....\n");
		String urlStr = HTTPS_BASE_URL + ATTENDEES_RESOURCE_URI;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			json.put("teamId", theTeamId);
			json.put("eventId", theEventId);
			json.put("eventType", theEventType);

			JSONArray attendeesJsonArray = new JSONArray();
			for(int i=0; i<theMembers.size(); i++) {
				JSONObject attendeeJsonObj = new JSONObject();
				attendeeJsonObj.put("memberId", theMembers.get(i));
				
				if(theAttendance != null && theAttendance.size() == theMembers.size()) {
					attendeeJsonObj.put("present", theAttendance.get(i));
				}
				if(thePreGameStatus != null && thePreGameStatus.size() == theMembers.size()) {
					attendeeJsonObj.put("preGameStatus", thePreGameStatus.get(i));
				}
				
				attendeesJsonArray.put(attendeeJsonObj);
			}
			json.put("attendees", attendeesJsonArray);
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyUpdateAttendees() complete\n");
		}
	}
	
	//verifyUpdateAttendees(teamId, gameId, "game", members, attendance, token);
	private static void verifyGetAttendees(String theTeamId, String theEventId, String theEventType, String theMemberId, String theTimeZone,
			                               String theStartDate, String theEndDate, String theToken) {
		System.out.println("\n\n verifyGetAttendees() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		
		String urlStr = HTTPS_BASE_URL + ATTENDEES_RESOURCE_URI + "?" + "teamId=" + encodedTeamId;
		if(theEventId != null) {
			String encodedEventId = ClientTest.encode(theEventId);
			urlStr = urlStr + "&" + "eventId=" + encodedEventId;
		}
		if(theEventType != null) {
			String encodedEventType = ClientTest.encode(theEventType);
			urlStr = urlStr + "&" + "eventType=" + encodedEventType;
		}
		if(theMemberId != null) {
			String encodedMemberId = ClientTest.encode(theMemberId);
			urlStr = urlStr + "&" + "memberId=" + encodedMemberId;
		}
		if(theTimeZone != null) {
			String encodedTimeZone = ClientTest.encode(theTimeZone);
			urlStr = urlStr + "&" + "timeZone=" + encodedTimeZone;
		}
		if(theStartDate != null) {
			String encodedStartDate = ClientTest.encode(theStartDate);
			urlStr = urlStr + "&" + "startDate=" + encodedStartDate;
		}
		if(theEndDate != null) {
			String encodedEndDate = ClientTest.encode(theEndDate);
			urlStr = urlStr + "&" + "endDate=" + encodedEndDate;
		}
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetAttendees() complete\n");
		}
	}		
	
	private static void verifyCreateMessageThread(String theTeamId, String theSubject, String theBody, String theType, String theEventId,
			String theEventType, String theIsAlert, String theIncludeFans, List<String> thePollChoices, List<String> theRecipients,
			String theIsPublic, Boolean theCoordinatorsOnly, String theToken) {
		System.out.println("\n\n verifyCreateMessageThread() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + MESSAGE_THREADS_RESOURCE_URI;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			json.put("subject", theSubject);
			json.put("body", theBody);
			json.put("type", theType);
			
			if(theEventId != null) json.put("eventId", theEventId);
			if(theEventType != null) json.put("eventType", theEventType);
			if(theIsAlert != null) json.put("isAlert", theIsAlert);
			if(theIncludeFans != null) json.put("includeFans", theIsAlert);
			if(theIsPublic != null) json.put("isPublic", theIsPublic);
			if(theCoordinatorsOnly != null) json.put("coordinatorsOnly", theCoordinatorsOnly);

			if(thePollChoices != null) {
				JSONArray emailJsonArray = new JSONArray();
				for(String s : thePollChoices) {
					emailJsonArray.put(s);
				}
				json.put("pollChoices", emailJsonArray);
			}
			

			if(theRecipients != null) {
				JSONArray recipientsJsonArray = new JSONArray();
				for(String s : theRecipients) {
					recipientsJsonArray.put(s);
				}
				json.put("recipients", recipientsJsonArray);
			}
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_POST, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
			JSONObject jsonReturn = new JSONObject(response);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyCreateMessageThread() complete\n");
		}
	}
	
	private static void verifyGetMessageThreadInfo(String theTeamId, String theMessageThreadId, String theTimeZone, Boolean includeMemberInfo, String theToken) {
		System.out.println("\n\n verifyGetMessageThreadInfo() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedMessageThreadId = ClientTest.encode(theMessageThreadId);
		String encodedTimeZone = ClientTest.encode(theTimeZone);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + MESSAGE_THREAD_RESOURCE_URI + "/" + encodedMessageThreadId + "/" + encodedTimeZone;
		if(includeMemberInfo) {
			String encodedIncludeMemberInfo = ClientTest.encode("true");
			urlStr = urlStr + "?" + "includeMemberInfo=" + encodedIncludeMemberInfo;
		}
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetMessageThreadInfo() complete\n");
		}
	}
	
	private static void verifyGetMessageThreads(String theTeamId, String theTimeZone, String theMessageGroup,
			        String theEventId, String theEventType, String theStatus, String theWasViewed, String theIncludeBodyAndChoices,
			        Boolean theUseThreads, String theToken) {
		System.out.println("\n\nverifyGetMessageThreads() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedTimeZone = ClientTest.encode(theTimeZone);
		
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + MESSAGE_THREADS_RESOURCE_URI + "/" + encodedTimeZone;
		urlStr = urlStr + "?" + "oneUseToken=xyz";
		
		if(theMessageGroup != null) {
			String encodedMessageGroup = ClientTest.encode(theMessageGroup);
			urlStr = urlStr + "&" + "messageGroup=" + encodedMessageGroup;
		}
		if(theEventId != null) {
			String encodedEventId = ClientTest.encode(theEventId);
			urlStr = urlStr + "&" + "eventId=" + encodedEventId;
		}
		if(theEventType != null) {
			String encodedEventType = ClientTest.encode(theEventType);
			urlStr = urlStr + "&" + "eventType=" + encodedEventType;
		}
		if(theStatus != null) {
			String encodedStatus = ClientTest.encode(theStatus);
			urlStr = urlStr + "&" + "status=" + encodedStatus;
		}
		if(theWasViewed != null) {
			String encodedWasViewed = ClientTest.encode(theWasViewed);
			urlStr = urlStr + "&" + "wasViewed=" + encodedWasViewed;
		}
		if(theIncludeBodyAndChoices != null) {
			String encodedIncludeBodyAndChoices = ClientTest.encode(theIncludeBodyAndChoices);
			urlStr = urlStr + "&" + "includeBodyAndChoices=" + encodedIncludeBodyAndChoices;
		}
		if(theUseThreads != null && theUseThreads) {
			String useThreads = ClientTest.encode(theIncludeBodyAndChoices);
			urlStr = urlStr + "&" + "useThreads=true";
		}
		System.out.println("url with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetMessageThreads() complete\n");
		}
	}
	
	
	private static void verifyGetMessageThreadsForAllTeams(String theTimeZone, String theMessageGroup, String theEventId,
			              String theEventType, String theStatus, String theWasViewed, String theIncludeBodyAndChoices,
			              Boolean theUseThreads, String theToken) {
		System.out.println("\n\n verifyGetMessageThreadsForAllTeams() starting .....\n");
		String encodedTimeZone = ClientTest.encode(theTimeZone);
		
		String urlStr = HTTPS_BASE_URL + MESSAGE_THREADS_RESOURCE_URI + "/" + encodedTimeZone;
		urlStr = urlStr + "?" + "oneUseToken=xyz";
		
		if(theMessageGroup != null) {
			String encodedMessageGroup = ClientTest.encode(theMessageGroup);
			urlStr = urlStr + "&" + "messageGroup=" + encodedMessageGroup;
		}
		if(theEventId != null) {
			String encodedEventId = ClientTest.encode(theEventId);
			urlStr = urlStr + "&" + "eventId=" + encodedEventId;
		}
		if(theEventType != null) {
			String encodedEventType = ClientTest.encode(theEventType);
			urlStr = urlStr + "&" + "eventType=" + encodedEventType;
		}
		if(theStatus != null) {
			String encodedStatus = ClientTest.encode(theStatus);
			urlStr = urlStr + "&" + "status=" + encodedStatus;
		}
		if(theWasViewed != null) {
			String encodedWasViewed = ClientTest.encode(theWasViewed);
			urlStr = urlStr + "&" + "wasViewed=" + encodedWasViewed;
		}
		if(theIncludeBodyAndChoices != null) {
			String encodedIncludeBodyAndChoices = ClientTest.encode(theIncludeBodyAndChoices);
			urlStr = urlStr + "&" + "includeBodyAndChoices=" + encodedIncludeBodyAndChoices;
		}
		if(theUseThreads != null && theUseThreads) {
			String useThreads = ClientTest.encode(theIncludeBodyAndChoices);
			urlStr = urlStr + "&" + "useThreads=true";
		}
		System.out.println("url with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetMessageThreadsForAllTeams() complete\n");
		}
	}
	
	private static void verifyGetNewMessageThreadCount(String theTeamId, String theEventId, String theEventType, String theResynchCounter,
			String theIncludeNewActivity, String theToken) {
		System.out.println("\n\n verifyGetNewMessageThreadCount() starting .....\n");
		String urlStr = HTTPS_BASE_URL + MESSAGE_THREADS_RESOURCE_URI + "/count/new";
		// add dummy query parameter so all the conditional parameters below can be appended with "&"
		urlStr = urlStr + "?" + "ignore=na";
		
		if(theTeamId != null) {
			String encodedTeamId = ClientTest.encode(theTeamId);
			urlStr = urlStr + "&" + "teamId=" + encodedTeamId;
		}
		if(theEventId != null) {
			String encodedEventId = ClientTest.encode(theEventId);
			urlStr = urlStr + "&" + "eventId=" + encodedEventId;
		}
		if(theEventType != null) {
			String encodedEventType = ClientTest.encode(theEventType);
			urlStr = urlStr + "&" + "eventType=" + encodedEventType;
		}
		if(theResynchCounter != null) {
			String encodedResynchCounter = ClientTest.encode(theResynchCounter);
			urlStr = urlStr + "&" + "resynchCounter=" + encodedResynchCounter;
		}
		if(theIncludeNewActivity != null) {
			String encodedIncludeNewActivity = ClientTest.encode(theIncludeNewActivity);
			urlStr = urlStr + "&" + "includeNewActivity=" + encodedIncludeNewActivity;
		}
		System.out.println("url with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetNewMessageThreadCount() complete\n");
		}
	}

	private static void verifyUpdateMessageThread(String theTeamId, String theMessageThreadId, List<String> theReminderRecipients,
			                                      String theReply, String theFollowupMessage, String theWasViewed, 
			                                      String theStatus, String theToken) {
		System.out.println("\n\n verifyUpdateMessageThread() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedMessageThreadId = ClientTest.encode(theMessageThreadId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + MESSAGE_THREAD_RESOURCE_URI + "/" + encodedMessageThreadId;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			if(theReply != null) {
				json.put("reply", theReply);
			} else if(theReminderRecipients != null) {
				JSONArray memberIdJsonArray = new JSONArray();
				for(String memId : theReminderRecipients) {
					memberIdJsonArray.put(memId);
				}
				json.put("sendReminder", memberIdJsonArray);
			} else if(theFollowupMessage != null) {
				json.put("followupMessage", theFollowupMessage);
			} 
			
			if(theWasViewed != null) {
				json.put("wasViewed", theWasViewed);
			}
			
			if(theStatus != null) {
				json.put("status", theStatus);
			}
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyUpdateMessageThread() complete\n");
		}
	}

	private static void verifyUpdateMessageThreads(String theMessageLocation, List<String> theMessageThreadIds, String theStatus, String theToken) {
		System.out.println("\n\n verifyUpdateMessageThreads() starting .....\n");
		String urlStr = HTTPS_BASE_URL + MESSAGE_THREADS_RESOURCE_URI;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			json.put("messageLocation", theMessageLocation);
			JSONArray messageThreadsIdJsonArray = new JSONArray();
			for(String mtId : theMessageThreadIds) {
				messageThreadsIdJsonArray.put(mtId);
			}
			json.put("messageThreadIds", messageThreadsIdJsonArray);
			json.put("status", theStatus);
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyUpdateMessageThreads() complete\n");
		}
	}
	
	private static void verifyMessageThreadResponseInfo(String theOneUseToken, String theReply) {
		System.out.println("\n\n verifyMessageThreadResponseInfo() starting .....\n");
		String encodedOneUseToken = ClientTest.encode(theOneUseToken);
		String urlStr = HTTPS_BASE_URL + MESSAGE_THREAD_RESOURCE_URI;
		urlStr = urlStr + "?" + "oneUseToken=" + encodedOneUseToken;
		if(theReply != null) {
			String encodedReply = ClientTest.encode(theReply);
			urlStr = urlStr + "&pollResponse=" + encodedReply;
		}
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			JSONObject json = new JSONObject();
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), null, null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyMessageThreadResponseInfo() complete\n");
		}
	}
	
	private static void verifyCreateActivity(String theTeamId, String theStatusUpdate, String theFakePhoto, String theToken) {
		System.out.println("\n\n verifyCreateActivity() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + ACTIVITIES_RESOURCE_URI;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			if(theStatusUpdate != null) json.put("statusUpdate", theStatusUpdate);
			if(theFakePhoto != null) {
				String photoBase64 = Base64.encode(theFakePhoto.getBytes());
				json.put("photo", photoBase64);
			}
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_POST, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
			JSONObject jsonReturn = new JSONObject(response);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyCreateActivity() complete\n");
		}
	}
	
	
	private static void verifyGetActivities(String theTeamId, String theMaxCount, String theRefreshFirst, String theNewOnly,
			                                String theMaxCacheId, String theTimeZone, String theToken) {
		System.out.println("\n\n verifyGetActivities() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedTimeZone = ClientTest.encode(theTimeZone);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + ACTIVITIES_RESOURCE_URI + "/" + encodedTimeZone;
		urlStr = urlStr + "?" + "ignore=na";
		
		if(theMaxCount != null) {
			String encodedMaxCount = ClientTest.encode(theMaxCount);
			urlStr = urlStr + "&" + "maxCount=" + encodedMaxCount;
		}
		if(theRefreshFirst != null) {
			String encodedRefreshFirst = ClientTest.encode(theRefreshFirst);
			urlStr = urlStr + "&" + "refreshFirst=" + encodedRefreshFirst;
		}
		if(theNewOnly != null) {
			String encodedNewOnly = ClientTest.encode(theNewOnly);
			urlStr = urlStr + "&" + "newOnly=" + encodedNewOnly;
		}
		if(theMaxCacheId != null) {
			String encodedMaxCacheId = ClientTest.encode(theMaxCacheId);
			urlStr = urlStr + "&" + "maxCacheId=" + encodedMaxCacheId;
		}

		System.out.println("url with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetActivities() complete\n");
		}
	}

	
	private static void verifyGetActivitiesForAllTeams(String theMaxCount, String theRefreshFirst, String theNewOnly,
			                                           String theMostCurrentDateStr, String theTotalNumberOfDaysStr,
			                                           String theTimeZone, String theToken) {
		System.out.println("\n\n verifyGetActivitiesForAllTeams() starting .....\n");
		String encodedTimeZone = ClientTest.encode(theTimeZone);
		String urlStr = HTTPS_BASE_URL + ACTIVITIES_RESOURCE_URI + "/" + encodedTimeZone;
		urlStr = urlStr + "?" + "ignore=na";
		
		if(theMaxCount != null) {
			String encodedMaxCount = ClientTest.encode(theMaxCount);
			urlStr = urlStr + "&" + "maxCount=" + encodedMaxCount;
		}
		if(theRefreshFirst != null) {
			String encodedRefreshFirst = ClientTest.encode(theRefreshFirst);
			urlStr = urlStr + "&" + "refreshFirst=" + encodedRefreshFirst;
		}
		if(theNewOnly != null) {
			String encodedNewOnly = ClientTest.encode(theNewOnly);
			urlStr = urlStr + "&" + "newOnly=" + encodedNewOnly;
		}
		if(theMostCurrentDateStr != null) {
			String encodedMostCurrentDateStr = ClientTest.encode(theMostCurrentDateStr);
			urlStr = urlStr + "&" + "mostCurrentDate=" + encodedMostCurrentDateStr;
		}
		if(theTotalNumberOfDaysStr != null) {
			String encodedTotalNumberOfDaysStr = ClientTest.encode(theTotalNumberOfDaysStr);
			urlStr = urlStr + "&" + "totalNumberOfDays=" + encodedTotalNumberOfDaysStr;
		}

		System.out.println("url with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetActivitiesForAllTeams() complete\n");
		}
	}
	
	private static void verifyGetStatusOfActivitiesForUser(List<String> theActivityIds, String theToken) {
		System.out.println("\n\n verifyGetStatusOfActivitiesForUser() starting .....\n");
		String urlStr = HTTPS_BASE_URL + ACTIVITIES_RESOURCE_URI + "/status/userVote";
		urlStr = urlStr + "?" + "ignore=na";
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			if(theActivityIds != null) {
				JSONArray activityIdsJsonArray = new JSONArray();
				for(String aid : theActivityIds) {
					JSONObject activityIdJsonObj = new JSONObject();
					activityIdJsonObj.put("activityId", aid);
					activityIdsJsonArray.put(activityIdJsonObj);
				}
				if(activityIdsJsonArray.length() > 0) json.put("activities", activityIdsJsonArray);
			}
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_POST, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetStatusOfActivitiesForUser() complete\n");
		}
	}
	
	private static void verifyGetActivityPhoto(String theTeamId, String theActivityId, String theToken) {
		System.out.println("\n\n verifyGetActivityPhoto() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedActivityId = ClientTest.encode(theActivityId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + ACTIVITY_RESOURCE_URI + "/" + encodedActivityId + "/photo";
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetActivityPhoto() complete\n");
		}
	}

	private static void verifyUpdateActivity(String theTeamId, String theActivityId, String theVote, String theToken) {
		System.out.println("\n\n verifyUpdateActivityString() starting .....\n");
		String encodedTeamId = ClientTest.encode(theTeamId);
		String encodedActivityId = ClientTest.encode(theActivityId);
		String urlStr = HTTPS_BASE_URL + TEAM_RESOURCE_URI + "/" + encodedTeamId + "/" + ACTIVITY_RESOURCE_URI + "/" + encodedActivityId;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		try {
			if(theVote != null) json.put("vote", theVote);
			System.out.println("json object = " + json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), "rTeamLogin", theToken);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyUpdateActivityString() complete\n");
		}
	}
	
	private static void verifyGetMessageArchiver() {
		System.out.println("\n\n verifyGetMessageArchiver() starting .....\n");
		String urlStr = HTTPS_BASE_URL + "cron/messageArchiver";
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, null, null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetMessageArchiver() complete\n");
		}
	}
	
	private static void verifyEmailSmsIsAlive() {
		System.out.println("\n\n verifyEmailSmsIsAlive() starting .....\n");
		String urlStr = HTTPS_BASE_URL + "cron/emailSmsIsAlive";
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, null, null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyEmailSmsIsAlive() complete\n");
		}
	}
	
	private static void verifyGetMobileCarriers() {
		System.out.println("\n\n verifyGetMobileCarriers() starting .....\n");
		String urlStr = HTTPS_BASE_URL + MOBILE_CARRIERS_RESOURCE_URI;
		System.out.println("urlStr with encoding = " + urlStr + "\n");
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, null, null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetMobileCarriers() complete\n");
		}
	}

	private static void rAssert(Boolean hasTestPassed, String theOutputMessage) {
		totalAssertCount++;
		if(hasTestPassed) {
			passingAssertCount++;
		} else {
			failingAssertCount++;
			System.out.println(theOutputMessage + "\n");
		}
	}

	private static void justEncode() {
		String emailAddress = "joepwro@gmail.com";
		System.out.println("emailAddress = " + emailAddress + "\n");
		String encodedEmail = ClientTest.encode(emailAddress);
		System.out.println("encodedEmail = " + encodedEmail + "\n");
	}
	
	// theUrl: complete url
	// thePayload: the JSON payload to send, if any.  Can be null.
	// theHttpMethod: one of GET POST HEAD OPTIONS PUT DELETE TRACE
	static private String send(URL theUrl, String theHttpMethod, String theJsonPayload, 
			                   String theBasicAuthUserName, String theBasicAuthPassword) {
		System.out.println("ClientTest::send theUrl = " + theUrl.toString());
		System.out.println("ClientTest::send theJsonPayload = " + theJsonPayload);
		System.out.println("ClientTest::send theHttpMethod = " + theHttpMethod);

		String response = "";
		HttpURLConnection connection = null;
		OutputStreamWriter writer = null;
		InputStreamReader reader = null;
		try {
			/////////////////////
			// Prepare connection
			/////////////////////
			connection = (HttpURLConnection)theUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setAllowUserInteraction(false);
			connection.setRequestMethod(theHttpMethod);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(TEN_SECONDS_IN_MILLIS);
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("accept-encoding", "*/*");
			
			
			///////////////////////
			// Basic Authentication
			///////////////////////
			if(theBasicAuthUserName != null && theBasicAuthPassword != null) {
				StringBuilder buf = new StringBuilder(theBasicAuthUserName);
				buf.append(':');
				buf.append(theBasicAuthPassword);
				byte[] bytes = null;
				try {
					bytes = buf.toString().getBytes("ISO-8859-1");
				} catch (java.io.UnsupportedEncodingException uee) {
					System.out.println("base64 encoding failed: " + uee.getMessage());
				}

				String header = "Basic " + Base64.encode(bytes);
				connection.setRequestProperty("Authorization", header);
			}

			////////////////////
			// Send HTTP Request
			////////////////////
			connection.connect();
			
			if(theJsonPayload == null) {
				theJsonPayload = "{}";
			}
			if(!theHttpMethod.equalsIgnoreCase(HTTP_GET) && !theHttpMethod.equalsIgnoreCase(HTTP_DELETE)) {
				writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
				writer.write(theJsonPayload);
				writer.flush();
			}

			////////////////////
			// Get HTTP response
			////////////////////
			int responseCode = connection.getResponseCode();
			System.out.println("responseCode = " + responseCode);
			
			if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
				// read-back the response
				reader = new InputStreamReader(connection.getInputStream());
				BufferedReader in = new BufferedReader(reader);
				StringBuffer responseBuffer = new StringBuffer();
				while (true) {
					String inputLine = in.readLine();
					if(inputLine == null) {break;}
					responseBuffer.append(inputLine);
				}
				in.close();
				response = responseBuffer.toString();
			} else // Server returned HTTP error code.
			{
				System.out.println("ClientTest::send() server returned error code: " + responseCode);
			}

		} catch (UnsupportedEncodingException ex) {
			System.out.println("ClientTest::send() UnsupportedEncodingException: " + ex);
		} catch (MalformedURLException ex) {
			System.out.println("ClientTest::send() MalformedURLException: " + ex);
		} catch (IOException ex) {
			System.out.println("ClientTest::send() IOException: " + ex);
		} finally {
			try {
				if (writer != null) {writer.close();}
			} catch (Exception ex) {
				System.out.println("ClientTest::send() Exception closing writer: " + ex);
			}
			try {
				if (reader != null) {reader.close();}
			} catch (Exception ex) {
				System.out.println("ClientTest::send() Exception closing reader: " + ex);
			}
			if (connection != null) {connection.disconnect();}
		}

		return response;
	}
	
	private static String encode(String theInput) {
		String output = "";
		try {
			output = URLEncoder.encode(theInput, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("encode exception = " + e.getMessage());
		}
		return output;
	}
	
	
}
