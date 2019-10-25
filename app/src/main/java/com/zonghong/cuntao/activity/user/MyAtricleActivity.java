package com.zonghong.cuntao.activity.user;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.http.HttpClient;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.ToastUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.ArticleIndexBean;
import com.waw.hr.mutils.bean.BankListBean;
import com.zonghong.cuntao.MAPP;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.adapter.CommunityAdapter;
import com.zonghong.cuntao.adapter.MyArticleAdapter;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityMyArticleBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyAtricleActivity extends BaseActivity<ActivityMyArticleBinding> {

    private MyArticleAdapter myArticleAdapter;

    private QMUIDialog qmuiDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_article;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("我的社区");
        binding.rvList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false)); loadingDialog = DialogUtils.getLoadingDialog(this, "", false);
    }

    @Override
    public void initData() {
        myAtricle();
    }

    @Override
    public void initListener() {

    }


    public void showConfirmDialog(String id) {
        if (qmuiDialog == null) {
            qmuiDialog = new QMUIDialog.MessageDialogBuilder(this)
                    .setMessage("确定要删除这条文章吗")
                    .addAction("取消", new QMUIDialogAction.ActionListener() {
                        @Override
                        public void onClick(QMUIDialog dialog, int index) {
                            qmuiDialog.dismiss();
                        }
                    })
                    .addAction("确定", new QMUIDialogAction.ActionListener() {
                        @Override
                        public void onClick(QMUIDialog dialog, int index) {
                            qmuiDialog.dismiss();
                            delArticle(id);
                        }
                    })
                    .create();
        }
        qmuiDialog.show();
    }

    public void delArticle(String id){
        loadingDialog.show();
        params = new HashMap<>();
        params.put("article_id", id);
        HttpClient.Builder.getServer().articleDel(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getSuclDialog(MyAtricleActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
                myAtricle();
            }

            @Override
            public void onError(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getFailDialog(MyAtricleActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

    private void myAtricle() {
        params.put("page", page);
        HttpClient.Builder.getServer().articleMy(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<ArticleIndexBean>() {
            @Override
            public void onSuccess(BaseBean<ArticleIndexBean> baseBean) {

                total = baseBean.getData().getTotalPage();
                if (myArticleAdapter == null) {
                    myArticleAdapter = new MyArticleAdapter(baseBean.getData().getList(),new WeakReference<>(MyAtricleActivity.this));
                    binding.rvList.setAdapter(myArticleAdapter);
                    myArticleAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            if (page >= total) {
                                ToastUtils.show(MAPP.mapp, "到底啦");
                                myArticleAdapter.loadMoreEnd();
                                return;
                            }
                            page = page + 1;
                            myAtricle();
                        }
                    }, binding.rvList);
                } else {
                    if (page == 1) {
                        myArticleAdapter.setNewData(baseBean.getData().getList());
                    } else {
                        myArticleAdapter.addData(baseBean.getData().getList());
                    }
                    myArticleAdapter.loadMoreComplete();
                }
            }

            @Override
            public void onError(BaseBean<ArticleIndexBean> baseBean) {

            }
        });
    }
}
