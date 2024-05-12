package com.example.myapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> students;

    public StudentAdapter(List<Student>students) {
        this.students=students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new StudentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_student, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int i) {
        studentViewHolder.bind(students.get(i));

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{

        private TextView fullNameTv;
        private TextView courseTitleTv;
        private TextView scoreTv;
        private TextView firstCharacterTv;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            fullNameTv=itemView.findViewById(R.id.tv_student_fullName);
            courseTitleTv=itemView.findViewById(R.id.tv_student_course);
            scoreTv=itemView.findViewById(R.id.tv_student_score);
            firstCharacterTv=itemView.findViewById(R.id.tv_student_firstCharacter);

        }
        public void bind(Student student){
            fullNameTv.setText(student.getFirstName()+" "+student.getLastName());
            courseTitleTv.setText(student.getCourse());
            scoreTv.setText(String.valueOf(student.getScore()));
            firstCharacterTv.setText(student.getFirstName().substring(0,1));

        }

    }

}