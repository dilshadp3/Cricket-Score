package com.example.cricketscore;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cricketscore.fragment.CommanteryFragment;
import com.example.cricketscore.fragment.ScoreCardFragment;
import com.example.cricketscore.fragment.SummaryFragment;
import com.example.cricketscore.models.CricketDataModel;
import com.example.cricketscore.models.MatchDetailsModel;
import com.example.cricketscore.models.PlayerModel;
import com.example.cricketscore.models.TeamModel;
import com.example.cricketscore.retrofit.RetrofitService;
import com.example.cricketscore.utils.ApiInterface;

import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    TextView teamOne,teamTwo,teamOneScore,teamTwoScore,matchType,matchToss,teamWin;
    CardView scoreCard,summaryCard,commentaryCard;

    public int totalPublicScore;
    Fragment fragment = null;
    MatchDetailsModel matchDetailsModel=new MatchDetailsModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiCall();
        setUi();

    }

    private void setDflt() {
        fragment = new SummaryFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("model", matchDetailsModel);
        fragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mainFragment, fragment);
        transaction.commit();
    }

    private void setUi() {
        teamOne=(TextView) findViewById(R.id.teamOneName);
        teamTwo=(TextView) findViewById(R.id.teamTwoName);
        teamOneScore=(TextView) findViewById(R.id.teamOneScore);
        teamTwoScore=(TextView) findViewById(R.id.teamTwoScore);
        matchType=(TextView) findViewById(R.id.matchType);
        matchToss=(TextView) findViewById(R.id.tossChoose);
        teamWin=(TextView) findViewById(R.id.teamWin);
        scoreCard=(CardView) findViewById(R.id.scoreCard);
        summaryCard=(CardView) findViewById(R.id.summaryCard);
        commentaryCard=(CardView) findViewById(R.id.commentaryCard);
        summaryCard.setCardBackgroundColor(Color.parseColor("#C4B9FC"));
        scoreCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreCard.setCardBackgroundColor(Color.parseColor("#C4B9FC"));
                commentaryCard.setCardBackgroundColor(Color.WHITE);
                summaryCard.setCardBackgroundColor(Color.WHITE);
                Bundle bundle = new Bundle();

                bundle.putParcelable("model", matchDetailsModel);
                fragment = new ScoreCardFragment();
                fragment.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainFragment, fragment);
                transaction.commit();
            }
        });

        summaryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                summaryCard.setCardBackgroundColor(Color.parseColor("#C4B9FC"));
                commentaryCard.setCardBackgroundColor(Color.WHITE);
                scoreCard.setCardBackgroundColor(Color.WHITE);
                fragment = new SummaryFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("model", matchDetailsModel);
                fragment.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainFragment, fragment);
                transaction.commit();
            }
        });

        commentaryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentaryCard.setCardBackgroundColor(Color.parseColor("#C4B9FC"));
                summaryCard.setCardBackgroundColor(Color.WHITE);
                scoreCard.setCardBackgroundColor(Color.WHITE);
                fragment = new CommanteryFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainFragment, fragment);
                transaction.commit();
            }
        });
    }

    private void apiCall() {
            final SweetAlertDialog sweetAlertDialog=new SweetAlertDialog(
                    MainActivity.this,SweetAlertDialog.PROGRESS_TYPE);
            sweetAlertDialog.setTitle("Please waite..");
            sweetAlertDialog.show();
            apiInterface = RetrofitService.getClient(getApplicationContext()).create(ApiInterface.class);
            Call<CricketDataModel> call = apiInterface.getMatchData();
            call.enqueue(new Callback<CricketDataModel>() {
                @Override
                public void onResponse(Call<CricketDataModel> call, Response<CricketDataModel> response) {
                    sweetAlertDialog.dismiss();
                    CricketDataModel cricketDataModelResponse = response.body();
                    if (cricketDataModelResponse!=null) {
                        matchDetailsModel=cricketDataModelResponse.getMatchDetails();
                        Log.d(TAG, "onResponse: "+cricketDataModelResponse);
                        matchType.setText(cricketDataModelResponse.getMatchDetails().getFormat()+ " cricket series");
                        matchToss.setText(cricketDataModelResponse.getMatchDetails().getToss() +" choos to "+cricketDataModelResponse.getMatchDetails().getTossDecision());
                        setTeamData(cricketDataModelResponse.getMatchDetails().getTeams(),cricketDataModelResponse.getMatchDetails().getToss());
                        setDflt();
                    }

                }

                @Override
                public void onFailure(Call<CricketDataModel> call, Throwable t) {
                    sweetAlertDialog.dismiss();
                    SweetAlertDialog sDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
                    sDialog.setTitleText("Error..!!");
                    sDialog.setContentText("check your network connection");
                    sDialog.show();
                    Button btn = (Button) sDialog.findViewById(cn.pedant.SweetAlert.R.id.neutral_button);
                    btn.setBackgroundColor(ContextCompat.getColor(MainActivity.this, cn.pedant.SweetAlert.R.color.red_btn_bg_color));
                }
            });
        }

    private void setTeamData(List<TeamModel> teams,String tossTeam) {

        teamOne.setText( teams.get(0).getName());
        teamTwo.setText( teams.get(1).getName());
        List<PlayerModel> teamOnePlayer=teams.get(0).getPlayers();
        List<PlayerModel> teamTwoPlayer=teams.get(1).getPlayers();

        int teamOneTotalScore=0;
        int teamOneTotalWicket=0;
        for (PlayerModel temp:teamOnePlayer){
            teamOneTotalScore=teamOneTotalScore+temp.getRuns();
            if (temp.getDismissal()!=null){
                teamOneTotalWicket=teamOneTotalWicket+1;
            }
        }
        teamOneScore.setText(teamOneTotalScore+"/"+teamOneTotalWicket);

        int teamTwoTotalScore=0;
        int teamTwoTotalWicket=0;
        for (PlayerModel temp:teamTwoPlayer){
            teamTwoTotalScore=teamTwoTotalScore+temp.getRuns();
            if (temp.getDismissal()!=null){
                teamTwoTotalWicket=teamTwoTotalWicket+1;
            }
        }
        teamTwoScore.setText(teamTwoTotalScore+"/"+teamTwoTotalWicket);

        totalPublicScore=teamTwoTotalScore;
        if (teamOneTotalScore>teamTwoTotalScore){
            if (teams.get(0).getName().equals(tossTeam.trim())){
                teamWin.setText(teams.get(0).getName()+" win by "+(teamOneTotalScore-teamTwoTotalScore)+" runs");
            }else {
                teamWin.setText(teams.get(0).getName()+" win by "+(10-teams.get(0).getPlayers().size())+" wickets");
            }

        }else {
            if (teams.get(1).getName().equals(tossTeam.trim())){
                teamWin.setText(teams.get(1).getName()+" won by "+(teamOneTotalScore-teamTwoTotalScore)+" runs");
            }else {
                teamWin.setText(teams.get(1).getName()+" won by "+(10-teams.get(1).getPlayers().size())+" wickets");
            }
        }

    }
}