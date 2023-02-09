package org.hse.timetableforhsefe.schedule;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.hse.timetableforhsefe.R;

public class ViewHolderHeader extends RecyclerView.ViewHolder {

    private Context context;
    private TextView title;

    public ViewHolderHeader(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        title = itemView.findViewById(R.id.date);
    }

    public void bind(String date){
        title.setText(date);
    }
}
