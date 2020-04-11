package com.lcfarm.android.base.utils;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * <pre>
 *     @author 杨充
 *     blog  : https://github.com/yangchong211
 *     time  : 2017/01/30
 *     desc  : ARouter路由跳转工具类
 *     revise:
 * </pre>
 */
public class ARouterUtil {

    /**
     * 在activity中添加
     *
     * @param activity activity_test
     */
    public static void injectActivity(FragmentActivity activity) {
        if (activity == null) {
            return;
        }
        ARouter.getInstance().inject(activity);
    }

    /**
     * 在fragment中添加
     *
     * @param fragment fragment
     */
    public static void injectFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        ARouter.getInstance().inject(fragment);
    }


    /**
     * 销毁资源
     */
    public static void destroy() {
        ARouter.getInstance().destroy();
    }

    /**
     * 简单的跳转页面
     *
     * @param path string目标界面对应的路径
     */
    public static Object navigation(String path) {
        if (path == null) {
            return null;
        }
        return ARouter.getInstance()
                .build(path)
                .navigation();
    }


    /**
     * 简单的跳转页面
     *
     * @param path string目标界面对应的路径
     */
    public static Object navigationGroup(String path, String group) {
        if (path == null) {
            return null;
        }
        return ARouter.getInstance()
                .build(path, group)
                .navigation();
    }

    /**
     * 简单的跳转页面
     *
     * @param path     string目标界面对应的路径
     * @param callback 监听路由过程
     */
    public static Object navigation(String path, Context context, NavigationCallback callback) {
        if (path == null) {
            return null;
        }
        return ARouter.getInstance()
                .build(path)
                .navigation(context, callback);
    }


    /**
     * 简单的跳转页面
     *
     * @param uri uri
     */
    public static Object navigation(Uri uri) {
        if (uri == null) {
            return null;
        }
        return ARouter.getInstance()
                .build(uri)
                .navigation();
    }


    /**
     * 简单的跳转页面
     *
     * @param path      string目标界面对应的路径
     * @param bundle    bundle参数
     * @param enterAnim 进入时候动画
     * @param exitAnim  退出动画
     */
    public static Object navigation(String path, Bundle bundle, int enterAnim, int exitAnim) {
        if (path == null) {
            return null;
        }
        if (bundle == null) {
            return ARouter.getInstance()
                    .build(path)
                    .withTransition(enterAnim, exitAnim)
                    .navigation();
        } else {
            return ARouter.getInstance()
                    .build(path)
                    .with(bundle)
                    .withTransition(enterAnim, exitAnim)
                    .navigation();
        }
    }


    /**
     * 携带参数跳转页面
     *
     * @param path   path目标界面对应的路径
     * @param bundle bundle参数
     */
    public static Object navigation(String path, Bundle bundle) {
        if (path == null || bundle == null) {
            return null;
        }
        return ARouter.getInstance()
                .build(path)
                .with(bundle)
                .navigation();
    }


    /**
     * 跨模块实现ForResult返回数据（activity中使用）,在fragment中使用不起作用
     * 携带参数跳转页面
     *
     * @param path path目标界面对应的路径
     */
    public static void navigation(String path, Activity context, int code) {
        navigation(path, null, context, code);
    }

    /**
     * 跨模块实现ForResult返回数据（activity中使用）,在fragment中使用不起作用
     * 携带参数跳转页面
     *
     * @param path   path目标界面对应的路径
     * @param bundle bundle参数
     */
    public static void navigation(String path, Bundle bundle, Activity context, int code) {
        if (path == null) {
            return;
        }
        if (bundle == null) {
            ARouter.getInstance()
                    .build(path)
                    .navigation(context, code);
        } else {
            ARouter.getInstance()
                    .build(path)
                    .with(bundle)
                    .navigation(context, code);
        }
    }


    /**
     * 使用绿色通道(跳过所有的拦截器)
     *
     * @param path  path目标界面对应的路径
     * @param green 是否使用绿色通道
     */
    public static Object navigation(String path, boolean green) {
        if (path == null) {
            return null;
        }
        if (green) {
            return ARouter.getInstance()
                    .build(path)
                    .greenChannel()
                    .navigation();
        } else {
            return ARouter.getInstance()
                    .build(path)
                    .navigation();
        }
    }

//    private NavigationCallback getCallback(){
//        NavigationCallback callback = new NavCallback() {
//            @Override
//            public void onArrival(Postcard postcard) {
//                LogUtils.i("ARouterUtil"+"---跳转完了");
//            }
//
//            @Override
//            public void onFound(Postcard postcard) {
//                super.onFound(postcard);
//                LogUtils.i("ARouterUtil"+"---找到了");
//            }
//
//            @Override
//            public void onInterrupt(Postcard postcard) {
//                super.onInterrupt(postcard);
//                LogUtils.i("ARouterUtil"+"---被拦截了");
//            }
//
//            @Override
//            public void onLost(Postcard postcard) {
//                super.onLost(postcard);
//                LogUtils.i("ARouterUtil"+"---找不到了");
//                //降级处理
//                //DegradeServiceImpl degradeService = new DegradeServiceImpl();
//                //degradeService.onLost(Utils.getApp(),postcard);
//
//                //无法找到路径，作替换处理
//                PathReplaceServiceImpl pathReplaceService = new PathReplaceServiceImpl();
//                pathReplaceService.replacePath(ARouterConstant.ACTIVITY_ANDROID_ACTIVITY,ARouterConstant.ACTIVITY_DOU_MUSIC_ACTIVITY);
//            }
//        };
//        return callback;
//    }


}
