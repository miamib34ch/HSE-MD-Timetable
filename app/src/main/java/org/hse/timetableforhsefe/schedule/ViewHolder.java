package org.hse.timetableforhsefe.schedule;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.hse.timetableforhsefe.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView start;
    private TextView end;
    private TextView type;
    private TextView name;
    private TextView place;
    private TextView teacher;

    public ViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        start = itemView.findViewById(R.id.start_time);
        end = itemView.findViewById(R.id.finish_time);
        type = itemView.findViewById(R.id.lesson_type);
        name = itemView.findViewById(R.id.lesson_name);
        place = itemView.findViewById(R.id.lesson_classroom);
        teacher = itemView.findViewById(R.id.lesson_teacher);
    }

    public void bind(final ScheduleItem data) {
        start.setText(data.getLessonStart());
        end.setText(data.getLessonEnd());
        type.setText(data.getLessonType());
        name.setText(data.getLessonName());
        place.setText(data.getLessonPlace());
        teacher.setText(data.getTeacher());
    }
}