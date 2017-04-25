package com.example.android.binge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by dwilder1181 on 4/18/2017.
 */

public class SearchFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;
    private FrameLayout mRecyclerViewFrame;
    private SearchAdapter mSearchAdapter;
    private ImageView mLogoImage;
    private List<Series> mSearchList;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        mSearchList = new ArrayList<>();
        mSearchList.add( new Series("Bleach", "Ichigo saves rukia", 5, null, 24, 1, Time.getTotalTime(5,24), Series.Status.ONGOING, new GregorianCalendar(2011, 1, 15).getTime(), new GregorianCalendar(2017, 4, 11).getTime()));
        mSearchList.add( new Series("Naruto", "Ichigo saves rukia", 5, null, 24, 1, Time.getTotalTime(5,24), Series.Status.ONGOING, new GregorianCalendar(2011, 1, 15).getTime(), new GregorianCalendar(2017, 4, 11).getTime()));
        mSearchList.add( new Series("Samurai Jack", "Ichigo saves rukia", 5, null, 24, 1, Time.getTotalTime(5,24), Series.Status.ONGOING, new GregorianCalendar(2011, 1, 15).getTime(), new GregorianCalendar(2017, 4, 11).getTime()));
        mSearchList.add( new Series("Pokemon", "Ichigo saves rukia", 5, null, 24, 1, Time.getTotalTime(5,24), Series.Status.ONGOING, new GregorianCalendar(2011, 1, 15).getTime(), new GregorianCalendar(2017, 4, 11).getTime()));

        mSearchView = (SearchView) v.findViewById(R.id.series_selection_search_view);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.series_selection_recycler_view);
        mRecyclerViewFrame = (FrameLayout) v.findViewById(R.id.series_selection_frame_layout);
        mLogoImage = new ImageView(getActivity());
        mLogoImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_logo));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateSearchResults();

        return v;
    }

    public void updateSearchResults() {
        if (mSearchList == null || mSearchList.size() == 0) {
            mRecyclerViewFrame.removeAllViews();
            mRecyclerViewFrame.addView(mLogoImage);
        }else {
            mRecyclerViewFrame.removeAllViews();
            mSearchAdapter = new SearchAdapter(mSearchList);
            mRecyclerView.setAdapter(mSearchAdapter);
            mRecyclerViewFrame.addView(mRecyclerView);
        }
    }

    public class SearchListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImageView;
        public TextView mTitleTextView;
        public TextView mEpisodeTextView;
        public TextView mRuntimeTextView;
        public Series mSeries;


        public SearchListViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.selection_view_image_view);
            mTitleTextView = (TextView) itemView.findViewById(R.id.selection_view_title_text_view);
            mEpisodeTextView = (TextView) itemView.findViewById(R.id.selection_view_episodes_text_view);
            mRuntimeTextView = (TextView) itemView.findViewById(R.id.selection_view_runtime_text_view);
            itemView.setOnClickListener(this);

        }
            public void setSeries(Series series) {
                mSeries=series;
            }
        @Override
        public void onClick(View view) {
            SavedSeries.getInstance().setSeries(mSeries);
            Intent intent = new Intent(getActivity(), SeriesInfoActivity.class);
            startActivity(intent);
        }
    }


    public class SearchAdapter extends RecyclerView.Adapter<SearchListViewHolder> {
        private List<Series> mSeriesList;

        public SearchAdapter(List<Series> series) {
            mSeriesList = series;
        }

        @Override

        public SearchListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.series_selection_view, parent, false);
            return new SearchListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SearchListViewHolder holder, int position) {
            Series series = mSeriesList.get(position);
            holder.setSeries(series);
            holder.mTitleTextView.setText(series.getTitle());
            holder.mEpisodeTextView.setText(String.valueOf(series.getNumberOfEpisodes()));
            holder.mRuntimeTextView.setText(String.valueOf(series.getTotalTime()));
            holder.mImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_logo));

        }

        @Override
        public int getItemCount() {
            return mSeriesList.size();
        }
    }


}
