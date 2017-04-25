package com.example.android.binge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dwilder1181 on 4/19/2017.
 */

public class SeriesInfoFragment extends Fragment {
    public static String KEY_NUMBER_OF_EPISODES = "number_of_episodes";
    public static String KEY_FREQUENCY = "frequency";
    private TextView mTitleTextView;
    private TextView mEpisodeTextView;
    private TextView mRuntimeTextView;
    private TextView mTotalBingeTimeTextView;
    private TextView mCompletionDateTextView;
    private Spinner mFrequencySpinner;
    private EditText mNumberOfEpisodesEditText;
    private Button mCalculateButton;
    private Button mStartDateButton;
    private int mNumberOfEpisodes=1;
    private String mFrequency;
    private Date mStartDate = new Date();
    private Date mEndDate=null;
    private Series mSeries;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_series_info, container, false);
        mSeries=SavedSeries.getInstance().getSeries();

        if (savedInstanceState != null) {
            mNumberOfEpisodes = savedInstanceState.getInt(KEY_NUMBER_OF_EPISODES);
            mFrequency = savedInstanceState.getString(KEY_FREQUENCY);
        }

        mStartDateButton = (Button) v.findViewById(R.id.series_info_start_date_button);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        mStartDateButton.setText(dateFormat.format(mStartDate));


        mFrequencySpinner = (Spinner) v.findViewById(R.id.series_info_frequency_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.time_frequency, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFrequencySpinner.setAdapter(adapter);
        mFrequencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String frequency = (String) adapterView.getItemAtPosition(i);
                mFrequency=frequency;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mEpisodeTextView = (TextView) v.findViewById(R.id.series_info_episodes_text_view);
        mEpisodeTextView.setText(new Integer(mSeries.getNumberOfEpisodes()).toString());

        mRuntimeTextView = (TextView) v.findViewById(R.id.series_info_runtime_text_view);
        mRuntimeTextView.setText(new Integer(mSeries.getTotalTime()).toString() + " mins");

        mTitleTextView = (TextView) v.findViewById(R.id.series_info_title_text_view);
        mTitleTextView.setText(mSeries.getTitle());

        mNumberOfEpisodesEditText = (EditText) v.findViewById(R.id.series_info_number_episodes_edit_text);

        mTotalBingeTimeTextView = (TextView) v.findViewById(R.id.series_info_total_binge_time_text_view);
        mCompletionDateTextView = (TextView) v.findViewById(R.id.series_info_completion_date_text_view);
        mFrequencySpinner = (Spinner) v.findViewById(R.id.series_info_frequency_spinner);
        mNumberOfEpisodesEditText = (EditText) v.findViewById(R.id.series_info_number_episodes_edit_text);

        mCalculateButton = (Button) v.findViewById(R.id.series_info_calculate_button);
        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mNumberOfEpisodesEditText.getText().length()==0) {
                    Toast.makeText(getActivity(), R.string.no_episodes_alert, Toast.LENGTH_SHORT).show();
                    return;
                }
                    mTotalBingeTimeTextView.setText(TimeUtility.minsToBiggestUnitString(mSeries.getTotalTime()));

                    int numberOfEpisodes= Integer.valueOf(mNumberOfEpisodesEditText.getText().toString());
                    mEndDate=TimeScheduler.getFrequencyEndDate(mSeries,mStartDate,mFrequency,numberOfEpisodes, getActivity());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

                    mCompletionDateTextView.setText(dateFormat.format(mEndDate));

            }
        });



        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_NUMBER_OF_EPISODES, mNumberOfEpisodes);
        outState.putString(KEY_FREQUENCY,mFrequency);
    }
}
