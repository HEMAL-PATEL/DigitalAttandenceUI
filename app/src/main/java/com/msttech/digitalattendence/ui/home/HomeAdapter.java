package com.msttech.digitalattendence.ui.home;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msttech.digitalattendence.R;
import com.msttech.digitalattendence.data.model.TeacherModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mahim on 4/3/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeVH> {

    private Context mContext;
    private List<TeacherModel> mTeacherModels;
    private OnItemClick mOnItemClick;

    public HomeAdapter(Context mContext, List<TeacherModel> mTeacherModels, OnItemClick mOnItemClick) {
        this.mContext = mContext;
        this.mTeacherModels = mTeacherModels;
        this.mOnItemClick = mOnItemClick;
    }

    @Override
    public HomeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher_list, parent, false);
        return new HomeVH(view);
    }

    @Override
    public void onBindViewHolder(HomeVH holder, int position) {

        TeacherModel model = mTeacherModels.get(position);

        holder.teacherName.setText(model.getTeacherName());
        holder.teacherDesignation.setText(model.getTeacherDesignation() + ", " + model.getDepartment());

        Glide.with(mContext).load(Uri.parse(model.getImageUrl())).into(holder.teacherImage);
    }

    @Override
    public int getItemCount() {
        return mTeacherModels.size();
    }

    interface OnItemClick {
        void onGetItemClick(int position, String id, String name);
    }

    class HomeVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.teacher_image)
        CircleImageView teacherImage;

        @BindView(R.id.teacher_name)
        TextView teacherName;

        @BindView(R.id.teacher_designation)
        TextView teacherDesignation;

        public HomeVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            TeacherModel model = mTeacherModels.get(position);

            mOnItemClick.onGetItemClick(position, model.getId(), model.getTeacherName());
        }
    }


}
