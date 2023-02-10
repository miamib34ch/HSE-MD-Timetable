package org.hse.timetableforhsefe.schedule;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.hse.timetableforhsefe.R;

public class ViewHolderHeader extends RecyclerView.ViewHolder {

    private Context context;
    private TextView date;

    public ViewHolderHeader(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        date = itemView.findViewById(R.id.date);
    }

    public void bind(String date){
        this.date.setText(date);
    }
}
