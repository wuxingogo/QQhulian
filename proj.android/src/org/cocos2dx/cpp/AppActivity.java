/****************************************************************************
Copyright (c) 2008-2010 Ricardo Quesada
Copyright (c) 2010-2012 cocos2d-x.org
Copyright (c) 2011      Zynga Inc.
Copyright (c) 2013-2014 Chukong Technologies Inc.
 
http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
****************************************************************************/
package org.cocos2dx.cpp;

import java.util.ArrayList;

import org.cocos2dx.lib.Cocos2dxActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.Tencent;

public class AppActivity extends Cocos2dxActivity {


	String APP_ID = "1103576258";
	String AppKey="MzDg35AqotNtGtGO";
	static Tencent mTencent;
	static Activity instance;
	static BaseUiListener qqShareListener;
	
	//QZone分享， SHARE_TO_QQ_TYPE_DEFAULT 图文，SHARE_TO_QQ_TYPE_IMAGE 纯图
    private static int shareType = QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mTencent = Tencent.createInstance(APP_ID, this.getApplicationContext());
		qqShareListener = new BaseUiListener(this);
		instance = this;

	}
	public static void shareQQMessage(){
		Log.e("share qq message", "share qq message");
		final Bundle params = new Bundle();
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "shared sdk in qq by ly");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://blog.sina.com.cn/s/blog_cc3752f40102v5je.html");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "this is only a message with test my game.by ly");


        mTencent.shareToQQ(instance, params, qqShareListener);
        
	}
	public static void shareQQZone(){
		Log.e("share qq zone", "share qq zone");
		final Bundle params = new Bundle();
		params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, shareType);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "http://blog.sina.com.cn/s/blog_cc3752f40102v5je.html");
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "this is only a message with test my game.by ly");
//        TextView imageUrl = new TextView(instance);
//        imageUrl
//        .setText("http://y.qq.com/i/song.html?songid=XXX&source=mobileQQ#wechat_redirect");
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://y.qq.com/i/song.html?songid=XXX&source=mobileQQ#wechat_redirect");
        ArrayList<String> imageUrls = new ArrayList<String>();
        imageUrls.add("http://y.qq.com/i/song.html?songid=XXX&source=mobileQQ#wechat_redirect");
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
            	mTencent.shareToQzone(instance, params, qqShareListener);
//            }
//        }).start();
	}
	public static void callMsg(int i){
		Log.e("log", "java call msg");
		shareQQMessage();
	}
	public static void callFriend(int i){
		Log.e("log", "java call friend");
		shareQQZone();
	}
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		mTencent.onActivityResult(requestCode, resultCode, data);
		Log.e("", "result");
	 }
	
}
