package com.cricketgame.cricketgameapi.match;

import com.cricketgame.cricketgameapi.generalutil.GeneralUtil;
import com.cricketgame.cricketgameapi.player.Player;
import com.cricketgame.cricketgameapi.player.PlayerService;
import com.cricketgame.cricketgameapi.playerRecord.PlayerRecord;
import com.cricketgame.cricketgameapi.playerRecord.PlayerRecordService;
import com.cricketgame.cricketgameapi.playerRecord.UniqueId;
import com.cricketgame.cricketgameapi.scoreboard.Identifier;
import com.cricketgame.cricketgameapi.scoreboard.ScoreBoard;
import com.cricketgame.cricketgameapi.scoreboard.ScoreBoardService;
import com.cricketgame.cricketgameapi.series.Series;
import com.cricketgame.cricketgameapi.series.SeriesService;
import com.cricketgame.cricketgameapi.BatOrBall;
import com.cricketgame.cricketgameapi.team.Team;
import com.cricketgame.cricketgameapi.team.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerRecordService playerRecordService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ScoreBoardService scoreBoardService;

    static Logger logger=LoggerFactory.getLogger(MatchService.class);

    public String getWinnerOfMatch(String seriesId, int matchId) {
        String winnerOfMatch;
        try {
            Optional<Match> winner = matchRepository.findById(new UniqueMatchId(seriesId, matchId));
            System.out.println(winner);
            if (winner.isPresent()) {
                int winnerOfMatchTeamId = winner.get().getWinningTeam();
                if(winnerOfMatchTeamId==0)
                    return "Match Tie";
                winnerOfMatch = teamService.getTeamName(winnerOfMatchTeamId);
            } else
                throw new Exception("This requested data does not exist in database");
        } catch (Exception e) {
            return "Failure : " + e;
        }
        return winnerOfMatch;
    }

    public void insertMatchRecord(Match match) {
        matchRepository.save(match);
    }

    public void organizeMatch(int numberOfMatches, int numberOfOvers) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date=formatter.format(new Date());
        String seriesId=(date+"-"+GeneralUtil.seriesCount++);
        GeneralUtil generalUtil=new GeneralUtil();
        int matchId = 0;
        int winnerPoint = (numberOfMatches / 2) + 1;
        int winningPointOfTeam1=0;
        int winningPointOfTeam2=0;
        int winnerOfMatch=0;
        int winnerOfSeries=0;
        List<Team> selectedTeams = generalUtil.getSelectedTeams(teamService);
        int teamIdOfBattingTeam = selectedTeams.get(0).getTeamId();
        int teamIdOfBowlingTeam = selectedTeams.get(1).getTeamId();
        logger.info("-------------Match Started between Teams "  + selectedTeams.get(0).getTeamName() + " and " + selectedTeams.get(1).getTeamName() + "-----------------");

        while (++matchId <= numberOfMatches)
         {
             logger.info("=============Match "+matchId+" started================");
            int tossWonTeamCode = (generalUtil.getWinner() == 0) ? selectedTeams.get(0).getTeamId() : selectedTeams.get(1).getTeamId();
            logger.info("------------Team " + teamService.getTeamName(tossWonTeamCode) + " Won the Toss------------");
            BatOrBall batOrBall = BatOrBall.values()[generalUtil.getWinner()];
            logger.info("------------Team " + teamService.getTeamName(tossWonTeamCode) + " Opted for " + batOrBall.name() + "---------------");
            if (tossWonTeamCode == selectedTeams.get(1).getTeamId())
            {
                if (batOrBall==BatOrBall.BATTING) {
                    teamIdOfBattingTeam = selectedTeams.get(1).getTeamId();
                    teamIdOfBowlingTeam = selectedTeams.get(0).getTeamId();
                }
            }
            else {
                if (batOrBall==BatOrBall.BALLING) {
                    teamIdOfBattingTeam = selectedTeams.get(1).getTeamId();
                    teamIdOfBowlingTeam = selectedTeams.get(0).getTeamId();
                }
            }
            /* -------Adding Players detail Of Playing Teams in scoreBoard --------- */
            playerRecordService.addPlayerRecord(seriesId,matchId,teamIdOfBattingTeam);
            playerRecordService.addPlayerRecord(seriesId,matchId,teamIdOfBowlingTeam);

            //----------------------------Match Started-----------------
            List<Integer> scoreList1=calculateScore(seriesId,matchId,teamIdOfBattingTeam,teamIdOfBowlingTeam,numberOfOvers,1);
            List<Integer> scoreList2=calculateScore(seriesId,matchId,teamIdOfBowlingTeam,teamIdOfBattingTeam,numberOfOvers,2);
            logger.info("Total Score of "+teamService.getTeamName(teamIdOfBattingTeam)+"is :"+scoreList1.get(0));
            logger.info("Total Wickets of "+teamService.getTeamName(teamIdOfBattingTeam)+"is :"+scoreList1.get(1));
             logger.info("Total Score of "+teamService.getTeamName(teamIdOfBowlingTeam)+"is :"+scoreList2.get(0));
             logger.info("Total Wickets of "+teamService.getTeamName(teamIdOfBowlingTeam)+"is :"+scoreList2.get(1));

             if(scoreList1.get(0)>scoreList2.get(0))
                 winnerOfMatch=teamIdOfBattingTeam;
             else if(scoreList1.get(0)<scoreList2.get(0))
                 winnerOfMatch=teamIdOfBowlingTeam;
             if(winnerOfMatch==selectedTeams.get(0).getTeamId())
                 winningPointOfTeam1++;
             else if(winnerOfMatch==selectedTeams.get(1).getTeamId())
                 winningPointOfTeam2++;
            UniqueMatchId uniqueMatchId=new UniqueMatchId(seriesId,matchId);
            insertMatchRecord(new Match(uniqueMatchId,tossWonTeamCode,teamIdOfBattingTeam,scoreList1.get(0),scoreList1.get(1),scoreList2.get(0),scoreList2.get(1),winnerOfMatch));
            logger.info(teamService.getTeamName(winnerOfMatch)+"Won the game");
            if(winningPointOfTeam1==winnerPoint)
             {
                 winnerOfSeries=selectedTeams.get(0).getTeamId();
                 break;
             }
             if(winningPointOfTeam2==winnerPoint)
             {
                 winnerOfSeries=selectedTeams.get(1).getTeamId();
                 break;
             }
         }
        if(winningPointOfTeam1<winningPointOfTeam2)
            winnerOfSeries=selectedTeams.get(1).getTeamId();
        else if(winningPointOfTeam1>winningPointOfTeam2)
            winnerOfSeries=selectedTeams.get(0).getTeamId();
        Series series=new Series(seriesId,numberOfMatches,selectedTeams.get(0).getTeamId(),selectedTeams.get(1).getTeamId(),winnerOfSeries);
        seriesService.insertSeriesRecord(series);
    }

    private List<Integer> calculateScore(String seriesId,int matchId,int teamIdOfBattingTeam,int teamIdOfBowlingTeam,int numberOfOvers,int inning) {
        GeneralUtil generalUtil=new GeneralUtil();
        int score = 0;
        int sequenceNo=0;
        int totalWicketsOfTeam=0;
        int totalScoreOfPLayerOnStrike=0;
        int totalScoreOfPlayerOnPitch=0;
        int totalBallsByPlayerOnStrike=0;
        int totalBallsByPlayerOnPitch=0;

        logger.info("----------Match Started---------------");
        logger.info("---------Team " + teamService.getTeamName(teamIdOfBattingTeam) + " Ready for Batting----------------");
        List<Player> playerList = playerService.getPlayerByTeamId(teamIdOfBattingTeam);
        List<Player> bowlers=playerService.getPlayerByTeamId(teamIdOfBowlingTeam);
        int numberOfBowlers=bowlers.size();
        int wicketsData[]=new int[numberOfBowlers];
        int bowlerNumber=0;
        int currentPlayerOnStrike = playerList.get(sequenceNo++).getPlayerId();
        int currentPlayerOnPitch = playerList.get(sequenceNo).getPlayerId();
        logger.info("Bowler "+bowlers.get(bowlerNumber).getPlayerName()+" comes in to start his first over");
        for (int ball = 1; ball <=numberOfOvers * 6; ball++)
        {
            logger.info(playerService.getPlayerName(currentPlayerOnStrike) + " Player is on Strike");
            Random r = generalUtil.getRandomFunction();
            int run = r.nextInt(8);
            if(run!=7)
            {
                Identifier id=new Identifier(seriesId,matchId,inning,ball);
                scoreBoardService.updateScoreBoard(new ScoreBoard(id,run,score,currentPlayerOnStrike,currentPlayerOnPitch));
            }
            else
            {
                Identifier id=new Identifier(seriesId,matchId,inning,ball);
                scoreBoardService.updateScoreBoard(new ScoreBoard(id,-1,score,currentPlayerOnStrike,currentPlayerOnPitch));
            }
            if (run!=1 && run !=3 && run!=7)
            {
                totalScoreOfPLayerOnStrike+=run;
                totalBallsByPlayerOnStrike+=1;
                score += run;
            }
            else if(run==1 || run ==3){
                totalScoreOfPLayerOnStrike+=run;
                totalBallsByPlayerOnStrike+=1;
                score += run;

                    int temp = currentPlayerOnStrike;
                    currentPlayerOnStrike=currentPlayerOnPitch;
                    currentPlayerOnPitch= temp;

                    int tem=totalScoreOfPLayerOnStrike;
                    totalScoreOfPLayerOnStrike=totalScoreOfPlayerOnPitch;
                    totalScoreOfPlayerOnPitch=tem;

                    tem=totalBallsByPlayerOnStrike;
                    totalBallsByPlayerOnStrike=totalBallsByPlayerOnPitch;
                    totalBallsByPlayerOnPitch=tem;
            }
            else{
                if(run==7)
                {
                    logger.info("!!!!!!!!!!!!--------------Wicket---------------!!!!!!!!!!! ");
                    UniqueId player=new UniqueId(seriesId,matchId,currentPlayerOnStrike);
                    playerRecordService.updatePlayerRecord(new PlayerRecord(player,totalScoreOfPLayerOnStrike,totalBallsByPlayerOnStrike));
                    totalScoreOfPLayerOnStrike=0;
                    totalBallsByPlayerOnStrike=0;
                    wicketsData[bowlerNumber]+=1;
                    sequenceNo++;
                }
                totalWicketsOfTeam++;
                if (sequenceNo == playerList.size())
                {
                    logger.info("Inning Over!! No PLayers are left in the team "+teamService.getTeamName(teamIdOfBattingTeam));
                    break;
                }
                if (sequenceNo<playerList.size())
                    currentPlayerOnStrike = playerList.get(sequenceNo).getPlayerId();
            }
            if(ball%6==0)
            {
                logger.info("Bowler "+bowlers.get(bowlerNumber).getPlayerName()+" took "+wicketsData[bowlerNumber]+" wickets");
                if(bowlerNumber==numberOfBowlers-1)
                    bowlerNumber=0;
                else
                    bowlerNumber++;
                logger.info("Bowler "+bowlers.get(bowlerNumber).getPlayerName()+" comes in to start his over");
            }
            logger.info("Current Score : "+score);
        }
        UniqueId player1=new UniqueId(seriesId,matchId,currentPlayerOnStrike);
        UniqueId player2=new UniqueId(seriesId,matchId,currentPlayerOnPitch);
        playerRecordService.updatePlayerRecord(new PlayerRecord(player1,totalScoreOfPLayerOnStrike,totalBallsByPlayerOnStrike));
        playerRecordService.updatePlayerRecord(new PlayerRecord(player2,totalScoreOfPlayerOnPitch,totalBallsByPlayerOnPitch));
        playerRecordService.updateWicketsData(seriesId,matchId,bowlers,bowlerNumber,wicketsData);
        List<Integer> scoreList=new ArrayList<>();
        scoreList.add(score);
        scoreList.add(totalWicketsOfTeam);
        return scoreList;
    }

    public List<Match> getAllMatchRecord() {
        return matchRepository.findAll();
    }
 }

