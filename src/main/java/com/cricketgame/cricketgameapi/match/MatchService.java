package com.cricketgame.cricketgameapi.match;

import com.cricketgame.cricketgameapi.Constants;
import com.cricketgame.cricketgameapi.generalutil.GeneralUtilService;
import com.cricketgame.cricketgameapi.player.Player;
import com.cricketgame.cricketgameapi.player.PlayerId;
import com.cricketgame.cricketgameapi.player.PlayerService;
import com.cricketgame.cricketgameapi.playerRecord.PlayerRecordService;
import com.cricketgame.cricketgameapi.scoreboard.Identifier;
import com.cricketgame.cricketgameapi.scoreboard.ScoreBoard;
import com.cricketgame.cricketgameapi.scoreboard.ScoreBoardService;
import com.cricketgame.cricketgameapi.series.Series;
import com.cricketgame.cricketgameapi.series.SeriesService;
import com.cricketgame.cricketgameapi.team.Team;
import com.cricketgame.cricketgameapi.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private GeneralUtilService generalUtilService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerRecordService playerRecordService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ScoreBoardService scoreBoardService;

    public List<Match> getAllMatchRecord() {
        List<Match> matchList = new ArrayList<>();
        for (Match match : matchRepository.findAll())
            matchList.add(match);
        return matchList;
    }

    public String getWinnerOfMatch(int seriesId, int matchId) {
        String winnerOfMatch = null;
        try {
            Optional<Match> winner = matchRepository.findById(new UniqueMatchId(seriesId, matchId));
            System.out.println(winner);
            if (winner.isPresent()) {
                int winnerOfMatchTeamId = winner.get().getWinningTeam();
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

    public void organizeMatch(int numberOfMatches, int numberOfOvers)
    {
        int seriesId=1;
        int matchId = 0;
        int winnerPoint = (numberOfMatches / 2) + 1;
        int winningPointOfTeam1=0;
        int winningPointOfTeam2=0;
        int winnerOfMatch=0;
        int winnerOfSeries=0;
        List<Team> selectedTeams = generalUtilService.getSelectedTeams();
        int teamIdOfBattingTeam = selectedTeams.get(0).getTeam_id();
        int teamIdOfBowlingTeam = selectedTeams.get(1).getTeam_id();

         while (++matchId <= numberOfMatches)
         {
            int tossWonTeamCode = (generalUtilService.getWinner() == 0) ? selectedTeams.get(0).getTeam_id() : selectedTeams.get(1).getTeam_id();
            String batOrBowl = (generalUtilService.getWinner() == 0) ? Constants.BATTING : Constants.BOWLING;
            if (tossWonTeamCode == selectedTeams.get(1).getTeam_id())
            {
                if (Constants.BATTING.equalsIgnoreCase(batOrBowl)) {
                    teamIdOfBattingTeam = selectedTeams.get(1).getTeam_id();
                    teamIdOfBowlingTeam = selectedTeams.get(0).getTeam_id();
                }
            }
            else {
                if (Constants.BOWLING.equalsIgnoreCase(batOrBowl)) {
                    teamIdOfBattingTeam = selectedTeams.get(1).getTeam_id();
                    teamIdOfBowlingTeam = selectedTeams.get(0).getTeam_id();
                }
            }
            /* -------Adding Players detail Of Playing Teams in scoreBoard --------- */
            playerRecordService.addPlayerRecord(teamIdOfBattingTeam);
            playerRecordService.addPlayerRecord(teamIdOfBowlingTeam);

            //----------------------------Match Started-----------------
            List<Integer> scoreList1=calculateScore(matchId,teamIdOfBattingTeam,numberOfOvers,1);
            List<Integer> scoreList2=calculateScore(matchId,teamIdOfBowlingTeam,numberOfOvers,2);
            if(winningPointOfTeam1==winnerPoint)
            {
                winnerOfSeries=selectedTeams.get(0).getTeam_id();
                break;
            }
            if(winningPointOfTeam2==winnerPoint)
            {
                winnerOfSeries=selectedTeams.get(1).getTeam_id();
                break;
            }
            if(scoreList1.get(0)>scoreList2.get(0))
                winnerOfMatch=teamIdOfBattingTeam;
            else
                winnerOfMatch=teamIdOfBowlingTeam;
            insertMatchRecord(new Match(new UniqueMatchId(seriesId,matchId),teamIdOfBattingTeam,scoreList1.get(0),scoreList1.get(1),scoreList2.get(0),scoreList2.get(1),winnerOfMatch));
        }
        if(winningPointOfTeam1<winningPointOfTeam2)
            winnerOfSeries=selectedTeams.get(1).getTeam_id();
        else if(winningPointOfTeam1>winningPointOfTeam2)
            winnerOfSeries=selectedTeams.get(0).getTeam_id();
        seriesService.insertSeriesRecord(new Series(seriesId,numberOfMatches,selectedTeams.get(0).getTeam_id(),selectedTeams.get(1).getTeam_id(),winnerOfSeries));
    }

    private List<Integer> calculateScore(int matchId,int teamIdOfBattingTeam,int numberOfOvers,int inning) {
        int seriesId=1;
        int score = 0;
        int sequenceNo=0;
        int totalWicketsOfTeam=0;
        int totalScoreOfPLayerOnStrike=0;
        int totalScoreOfPlayerOnPitch=0;
        int totalBallsByPlayerOnStrike=0;
        int totalBallsByPlayerOnPitch=0;

        List<Player> playerList = playerService.getPlayerByTeamId(teamIdOfBattingTeam);
        PlayerId currentPlayerOnStrike = playerList.get(sequenceNo++).getPlayerId();
        PlayerId currentPlayerOnPitch = playerList.get(sequenceNo).getPlayerId();

        for (int ball = 1; ball <=numberOfOvers * 6; ball++)
        {
            Random r = generalUtilService.getRandomFunction();
            int run = r.nextInt(8);
            if(run!=7)
                scoreBoardService.updateScoreBoard(new ScoreBoard(new Identifier(seriesId,matchId,inning,ball),run,score,currentPlayerOnStrike,currentPlayerOnPitch));
            else
                scoreBoardService.updateScoreBoard(new ScoreBoard(new Identifier(seriesId,matchId,inning,ball),-1,score,currentPlayerOnStrike,currentPlayerOnPitch));
            if (run!=1 && run !=3 && run!=7) {
                totalScoreOfPLayerOnStrike+=run;
                totalBallsByPlayerOnStrike+=1;
                score += run;
            }
            else if(run==1 || run ==3){
                totalScoreOfPLayerOnStrike+=run;
                totalBallsByPlayerOnStrike+=1;
                score += run;
                {
                    PlayerId temp = currentPlayerOnStrike;
                    currentPlayerOnStrike=currentPlayerOnPitch;
                    currentPlayerOnPitch= temp;

                    int tem=totalScoreOfPLayerOnStrike;
                    totalScoreOfPLayerOnStrike=totalScoreOfPlayerOnPitch;
                    totalScoreOfPlayerOnPitch=tem;

                    tem=totalBallsByPlayerOnStrike;
                    totalBallsByPlayerOnStrike=totalBallsByPlayerOnPitch;
                    totalBallsByPlayerOnPitch=tem;
                }
            }
            else{
                if(run==7)
                {
                    playerRecordService.updatePlayerRecord(currentPlayerOnStrike,totalScoreOfPLayerOnStrike,0,totalBallsByPlayerOnStrike);
                    totalScoreOfPLayerOnStrike=0;
                    totalBallsByPlayerOnStrike=0;
                    sequenceNo++;
                }
                totalWicketsOfTeam++;
                if (sequenceNo == playerList.size())
                    break;
                if (sequenceNo<playerList.size())
                    currentPlayerOnStrike = playerList.get(sequenceNo).getPlayerId();
            }
        }
        playerRecordService.updatePlayerRecord(currentPlayerOnStrike,totalScoreOfPLayerOnStrike,0,totalBallsByPlayerOnStrike);
        playerRecordService.updatePlayerRecord(currentPlayerOnPitch,totalScoreOfPlayerOnPitch,0,totalBallsByPlayerOnPitch);
        List<Integer> scoreList=new ArrayList<>();
        scoreList.add(score);
        scoreList.add(totalWicketsOfTeam);
        return scoreList;
    }
 }

