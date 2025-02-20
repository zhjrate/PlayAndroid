package com.zj.play.home

import android.content.BroadcastReceiver
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.zj.core.Play
import com.zj.core.view.base.BaseActivity
import com.zj.play.article.ArticleBroadCast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * 版权：Zhujiang 个人版权
 *
 * @author zhujiang
 * 创建日期：2020/9/15
 * 描述：文章收藏 BaseActivity，注册文章收藏状态改变的广播
 *
 */
abstract class ArticleCollectBaseActivity : BaseActivity() {

    private var articleReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            Play.isLogin().collectLatest {
                initData()
            }
        }
        articleReceiver =
            ArticleBroadCast.setArticleChangesReceiver(this) { initData() }
    }

    override fun onDestroy() {
        super.onDestroy()
        ArticleBroadCast.clearArticleChangesReceiver(this, articleReceiver)
    }

}