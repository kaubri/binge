package com.example.android.binge;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dwilder1181 on 4/18/2017.
 */

public class SearchFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;
    private FrameLayout mRecyclerViewFrame;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        return v;
    }

    public class SearchList extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTitleTextView;
        public TextView mEpisodeTextView;
        public TextView mRuntimeTextView;


        public SearchList(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.selection_view_image_view);
            mTitleTextView = (TextView) itemView.findViewById(R.id.selection_view_title_text_view);
            mEpisodeTextView = (TextView) itemView.findViewById(R.id.selection_view_episode_text_view);
            mRuntimeTextView = (TextView) itemView.findViewById(R.id.selection_view_runtime_text_view);

        }
    }

    public class SearchAdapter extends RecyclerView.Adapter<SearchList> {
        private List<Series> mSeriesList;

        public SearchAdapter(List<Series> series) {
            mSeriesList=series;
        }

        @Override

        public SearchList onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.series_selection_view, parent, false);
            return new SearchList(view);
        }

        @Override
        public void onBindViewHolder(SearchList holder, int position) {
            Series series = mSeriesList.get(position);
            holder.mTitleTextView.setText(series.getTitle());
            holder.mEpisodeTextView.setText(series.getNumberOfEpisodes());
            holder.mRuntimeTextView.setText(series.getTotalTime());

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }


}
