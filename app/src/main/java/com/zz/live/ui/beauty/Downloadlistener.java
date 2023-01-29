package com.zz.live.ui.beauty;

public interface Downloadlistener {
    void onDownloadFail(String errorMsg);

    void onDownloadProgress(final int progress);

    void onDownloadSuccess(String filePath);
}
