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
        start = itemView.findViewById(R.id.start);
        end = itemView.findViewById(R.id.finish);
        type = itemView.findViewById(R.id.type);
        name = itemView.findViewById(R.id.lesson_name);
        place = itemView.findViewById(R.id.place);
        teacher = itemView.findViewById(R.id.lesson_teacher);
    }

    public void bind(final ScheduleItem data) {
        start.setText(data.getStart());
        end.setText(data.getEnd());
        type.setText(data.getType());
        name.setText(data.getName());
        place.setText(data.getPlace());
        teacher.setText(data.getTeacher());
    }
}