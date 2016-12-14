package com.dragosneagu.emojinutrition;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LessonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LessonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LessonFragment extends Fragment {

    private Lesson lesson;

    private OnFragmentInteractionListener mListener;

    public LessonFragment() {
        // Required empty public constructor
    }

    public static LessonFragment newInstance(String param1, String param2) {
        LessonFragment fragment = new LessonFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

class MyLessonAdapter extends RecyclerView.Adapter {
    Lesson lesson;

    public MyLessonAdapter(Lesson lesson) {
        this.lesson = lesson;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lesson, parent, false);
        MyViewHolder vh = new MyViewHolder((RelativeLayout) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder vh=(MyViewHolder)holder;
        vh.mlessonTitle.setText(lesson.getLessonTitle());
        vh.mlessonContent.setText(lesson.getLessonContent());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mlessonContent;
        public TextView mlessonTitle;
        public MyViewHolder(RelativeLayout v) {
            super(v);
            mlessonContent = (TextView) v.findViewById(R.id.lessoncontent);
            mlessonTitle = (TextView) v.findViewById(R.id.lessonTitleFragment);
        }

    }

}
