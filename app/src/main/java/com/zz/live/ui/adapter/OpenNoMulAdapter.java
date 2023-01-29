package com.zz.live.ui.adapter;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.OpenNoMulBean;
import com.zz.live.myView.RoundTextView;
import com.zz.live.utils.LotteryNumColorUtil;

import java.util.List;

public class OpenNoMulAdapter extends BaseMultiItemQuickAdapter<OpenNoMulBean, BaseViewHolder> {

    public OpenNoMulAdapter(List<OpenNoMulBean> data) {
        super(data);
        //图片设置类型  1快三 2赛车 3六合彩 4其他普通蓝色圆圈背景
        addItemType(1, R.layout.item_openno_kuaisan);
        addItemType(2, R.layout.item_openno_race);
        addItemType(3, R.layout.item_openno_marksix);
        addItemType(4, R.layout.item_openno_normal);
        addItemType(5, R.layout.item_openno_normal);
    }

    @Override
    protected void convert(BaseViewHolder helper, OpenNoMulBean item) {

        switch (helper.getItemViewType()) {
            case 1:
                ImageView imageView = helper.getView(R.id.iv_openno_kuaisan);
                imageView.setImageResource(Integer.parseInt(item.getName()));
                break;
            case 2:
               RoundTextView tv= helper.getView(R.id.tv_openno_race);
                tv.setText("" + item.getName());
                LotteryNumColorUtil.initLotteryNumColor(tv);
                break;
            case 3:
                TextView tv1 = helper.getView(R.id.tv_openno_marksix);
                LinearLayout wrap_linear = helper.getView(R.id.six_wrap_linear);
                if (!item.getName().equals("+")) {
                    switch (item.getColor()) {
                        case "red":
                            tv1.setBackground(getContext().getResources().getDrawable(R.drawable.shape_circle_red));
                            break;
                        case "green":
                            tv1.setBackground(getContext().getResources().getDrawable(R.drawable.shape_circle_green));
                            break;
                        case "blue":
                            tv1.setBackground(getContext().getResources().getDrawable(R.drawable.shape_circle_blue));
                            break;
                    }
                    helper.setText(R.id.tv_animal_marksix, item.getAnimal());
                    wrap_linear.setPadding(5,5,5,5);
                }else {
                    wrap_linear.setPadding(2,2,2,2);
                }
                tv1.setText(item.getName());
                break;
            case 4:
                TextView tv4 = helper.getView(R.id.tv_openno_normal);
                if (item.getGame() == 2 ||
                        item.getGame() == 6 ||
                        item.getGame() == 7 ||
                        item.getGame() == 8 ||
                        item.getGame() == 9) {
                    tv4.setText("" + item.getName());
                    tv4.setBackground(getContext().getResources().getDrawable(R.drawable.shape_circle_blue));
                }
                break;
            case 5:
                TextView tv5 = helper.getView(R.id.tv_openno_normal);
                LinearLayout dandan_wrap_linear = helper.getView(R.id.dandan_wrap_linear);
                if (!item.getName().equals("+")&&!item.getName().equals("=")) {
                    tv5.setBackground(getContext().getResources().getDrawable(R.drawable.shape_circle_blue));
                    dandan_wrap_linear.setPadding(3,3,3,3);
                }else {
                    tv5.setBackgroundColor(getContext().getColor(R.color.transparent));
                    dandan_wrap_linear.setPadding(0,0,0,0);
                }
                tv5.setText("" + item.getName());
                break;
                default:
                    break;
        }

    }
}
