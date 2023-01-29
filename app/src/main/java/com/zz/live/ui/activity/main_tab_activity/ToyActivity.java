package com.zz.live.ui.activity.main_tab_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.lovense.sdklibrary.Lovense;
import com.lovense.sdklibrary.LovenseToy;
import com.lovense.sdklibrary.callBack.LovenseError;
import com.lovense.sdklibrary.callBack.OnConnectListener;
import com.lovense.sdklibrary.callBack.OnErrorListener;
import com.lovense.sdklibrary.callBack.OnSearchToyListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.ToyConnectEvent;
import com.zz.live.bean.ToyEntity;
import com.zz.live.ui.adapter.ToyAdapter;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ToyActivity extends BaseActivity {
    @BindView(R.id.stop_search_tv)
    TextView stop_search_tv;
    @BindView(R.id.search_toy_tv)
    TextView search_toy_tv;
    @BindView(R.id.bluetooth_status_tv)
    TextView bluetooth_status_tv;
    @BindView(R.id.searching_tv)
    TextView searching_tv;
    @BindView(R.id.toy_recycler)
    RecyclerView toy_recycler;
    private ToyAdapter toyAdapter;
    private ArrayList<LovenseToy>toyEntityArrayList = new ArrayList<>();
    private RxPermissions rxPermissions;
    private BluetoothAdapter blueadapter;
    private OnConnectListener onConnectListener;
    @Override
    public int getLayoutId() {
        return R.layout.activity_toy;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        CommonToolbarUtil.initToolbar(this,"跳蛋玩具");
        rxPermissions = new RxPermissions(this);
        initBluetoothStatus();
        initRecycler();
    }

    private void initRecycler() {
        toyAdapter = new ToyAdapter(R.layout.toy_recycler_item,toyEntityArrayList);
        toy_recycler.setLayoutManager(new LinearLayoutManager(this));
        toy_recycler.setAdapter(toyAdapter);
        toyAdapter.addChildClickViewIds(R.id.toy_item_wrap_linear);
        toyAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                LovenseToy lovenseToy = toyEntityArrayList.get(position);
                String toyId = lovenseToy.getToyId();
                if(!Lovense.getInstance(getApplication()).isConnected(toyId)){
                    onConnectListener = new OnConnectListener() {
                        @Override
                        public void onConnect(String toyId, String status) {
                            switch (status) {
                                case LovenseToy.STATE_CONNECTING:
                                    toyAdapter.notifyDataSetChanged();
                                    break;
                                case LovenseToy.STATE_CONNECTED:
                                    EventBus.getDefault().post(new ToyConnectEvent(1, toyId));
                                    toyAdapter.notifyDataSetChanged();
                                    break;
                                case LovenseToy.STATE_FAILED:
                                    EventBus.getDefault().post(new ToyConnectEvent(-1, toyId));
                                    toyAdapter.notifyDataSetChanged();
                                    break;
                                case LovenseToy.SERVICE_DISCOVERED:
                                    Lovense.getInstance(getApplication()).sendCommand(toyId, LovenseToy.COMMAND_GET_DEVICE_TYPE);
                                    Lovense.getInstance(getApplication()).sendCommand(toyId, LovenseToy.COMMAND_GET_BATTERY);
                                    toyAdapter.notifyDataSetChanged();
                                    break;
                            }

                        }

                        @Override
                        public void onError(LovenseError error) {
                           showtoast("设备连接失败, 请重试");
                        }

                    };
                    Lovense.getInstance(getApplication()).connectToy(toyId, onConnectListener);
                }else {
                    showtoast("设备已连接");
                }
            }
        });
    }

    private void initBluetoothStatus() {
        blueadapter= BluetoothAdapter.getDefaultAdapter();
        if(blueadapter!=null){
            if(blueadapter.isEnabled()){
                bluetooth_status_tv.setText("蓝牙已开启");
            }else {
                bluetooth_status_tv.setText("蓝牙未开启, 请先开启蓝牙!");
            }
        }
    }

    @OnClick({R.id.search_toy_tv,R.id.stop_search_tv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.search_toy_tv:
                if(blueadapter!=null){
                    if(blueadapter.isEnabled()){
                        bluetooth_status_tv.setText("蓝牙已开启");
                        rx();
                    }else {
                        bluetooth_status_tv.setText("蓝牙未开启, 请先开启蓝牙!");
                        showtoast("蓝牙未开启, 请先开启蓝牙!");
                    }
                }

            break;
            case R.id.stop_search_tv:
                try {
                    Lovense.getInstance(getApplication()).stopSearching();
                    searching_tv.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void onNetChange(boolean netWorkState) {

    }
    private void scanDev() {
        toyEntityArrayList.clear();
        toyAdapter.notifyDataSetChanged();
        searching_tv.setVisibility(View.VISIBLE);
        Lovense.getInstance(getApplication()).searchToys(new OnSearchToyListener() {
            @Override
            public void onSearchToy(LovenseToy lovenseToy) {
                addDevice(lovenseToy);
            }

            @Override
            public void finishSearch() {
                Lovense.getInstance(getApplication()).saveToys(toyEntityArrayList, new OnErrorListener() {
                    @Override
                    public void onError(LovenseError error) {
                        showtoast(error.getMessage());
                    }
                });
                if(searching_tv!=null){
                    searching_tv.setVisibility(View.GONE);
                }
                if(toyEntityArrayList.size()==0){
                    showtoast("未搜索到设备, 请检查设备是否开启!");
                }
            }

            @Override
            public void onError(LovenseError msg) {
                Toast.makeText(ToyActivity.this, msg.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addDevice(LovenseToy lovenseToy) {
        if (lovenseToy != null) {
            if (!isAdded(lovenseToy)) {
                toyEntityArrayList.add(lovenseToy);
                toyAdapter.notifyDataSetChanged();
            }
        }
    }

    protected boolean isAdded(LovenseToy lovenseToy) {
        for (LovenseToy t: toyEntityArrayList) {
            String  id = t.getToyId();
            String toyId = lovenseToy.getToyId();
            if (!TextUtils.isEmpty(id) && id.equals(toyId)) {
                return true;
            }
        }
        return false;
    }

    private void rx(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check
            if (ActivityCompat.checkSelfPermission(ToyActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(ToyActivity.this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
                rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.BLUETOOTH)
                        .subscribe(new io.reactivex.functions.Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean b) throws Exception {
                                if (b) {
                                    scanDev();
                                } else {
                                    showtoast("如果您使用的是Android 6.0+，则必须启用GPS才能连接到蓝牙设备。");
                                }
                            }
                        });
            } else {
                scanDev();
            }
        } else {
            scanDev();
        }
    }
}