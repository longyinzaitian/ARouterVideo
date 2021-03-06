package video.cn.app.ui.launch;

import android.content.Context;

import com.husy.network.AbstractCallListener;
import com.husy.network.RetrofitClient;
import com.husy.network.bingimage.LaunchImageRequest;
import com.husy.network.bingimage.LaunchResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import video.cn.base.base.BasePresenter;
import video.cn.base.utils.SpUtil;

/**
 * @author husy
 * @date 2019/8/31
 */
public class LaunchPresenter extends BasePresenter<LaunchContract.LaunchView> implements LaunchContract.LaunchPresenter {

    public LaunchPresenter(LaunchContract.LaunchView iView) {
        super(iView);
    }

    @Override
    public void getImage() {
        RetrofitClient.getInstance().create(LaunchImageRequest.class)
            .getImage(1)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new AbstractCallListener<LaunchResponse>() {
                @Override
                public void onResponse(LaunchResponse launchResponse) {
                    if (iView == null || launchResponse.getImages() == null
                            || launchResponse.getImages().isEmpty()) {
                        return;
                    }

                    String pic = launchResponse.getImages().get(0).getUrl();
                    iView.showFlash(pic);
                    SpUtil.saveLaunchPic((Context) iView, pic);
                }

                @Override
                public void onFail(Throwable e) {
                    if (iView != null) {
                        iView.loadLaunchFail();
                    }
                }
            });
    }

}
