package org.hse.timetableforhsefe.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.hse.timetableforhsefe.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int TYPE_ITEM = 0;
    private final static int TYPE_HEADER = 1;

    private List<ScheduleItem> dataList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == TYPE_ITEM){
            View contractView = inflater.inflate(R.layout.schedule_item, parent, false);
            return new ViewHolder(contractView, context);
        }
        else if (viewType == TYPE_HEADER){
            View contractView = inflater.inflate(R.layout.schedule_header, parent, false);
            return new ViewHolderHeader(contractView, context);
        }

        throw new IllegalArgumentException("Invalid view type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ScheduleItem item = dataList.get(position);
        if (holder instanceof ViewHolder)
            ((ViewHolder) holder).bind(item);
        else if (holder instanceof ViewHolderHeader)
            ((ViewHolderHeader) holder).bind(((ScheduleItemHeader)item).getDateString());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public int getItemViewType(int position) {
        ScheduleItem item = dataList.get(position);
        if (item instanceof ScheduleItemHeader)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    public void setData(List<ScheduleItem> dataList){
        this.dataList = dataList;
    }
}
