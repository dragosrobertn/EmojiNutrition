package com.dragosneagu.emojinutrition;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class LessonListFragment extends Fragment {

    ArrayList<Lesson> lessons = new ArrayList<>();

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LessonListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static LessonListFragment newInstance(int columnCount) {
        LessonListFragment fragment = new LessonListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lessonlist_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyLessonListAdapter(lessons));


        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Lesson lesson);

    }

    public void setLessonList(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }
}


class MyLessonListAdapter extends RecyclerView.Adapter {
    ArrayList<Lesson> lessons;

    public MyLessonListAdapter(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_list_view, parent, false);
        MyViewHolder vh = new MyViewHolder((TextView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder vh=(MyViewHolder)holder;
        vh.mTextView.setId(lessons.get(position).getLessonID());
        vh.mTextView.setText(lessons.get(position).getLessonTitle());
        vh.mTextView.setOnClickListener(lessonListener);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public MyViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }

    }

    View.OnClickListener lessonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            new LessonActivity().showLessonByID(String.format("%1$s", view.getId()));
        }
    };

}
