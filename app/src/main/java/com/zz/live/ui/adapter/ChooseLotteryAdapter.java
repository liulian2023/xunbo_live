package com.zz.live.ui.adapter;
import android.app.Activity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.LotteryEntitiy;
import com.zz.live.utils.GlideLoadViewUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
public class ChooseLotteryAdapter extends BaseQuickAdapter<LotteryEntitiy.GameClasslistBean, BaseViewHolder> {

    Activity activity;
    public ChooseLotteryAdapter(int layoutResId, @Nullable List<LotteryEntitiy.GameClasslistBean> data,Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, LotteryEntitiy.GameClasslistBean dataBean) {
        GlideLoadViewUtil.cpLoadNormalView(activity,dataBean.getImage(),baseViewHolder.getView(R.id.lottery_litile_iv));
        baseViewHolder.setText(R.id.lottery_name_tv,dataBean.getTypename());
    }
}
