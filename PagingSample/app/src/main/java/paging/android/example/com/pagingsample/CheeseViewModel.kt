/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package paging.android.example.com.pagingsample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData

/**
 * 简单的ViewModel提供美味奶酪的分页列表
 */
class CheeseViewModel(app: Application) : AndroidViewModel(app) {
    val dao = CheeseDb.get(app).cheeseDao()

    /**
     * 在我在这里使用-ktx Kotlin扩展方法，另一方面你需要使用LivePagedListBuilder()，和PagedList.Config.Builder()
     */
    val allCheeses = dao.allCheesesByName().toLiveData(Config(

            /**
             * 好的页面大小值，它在大型设备上至少填充一个屏幕的内容，因此用户不太可能看见null项。
             * 你可以使用这个常量来观察分页行为。
             * 可能根据设备的大小调整，但是通常没有必要，除非用户在大型设备上滑动时，滑动项的速度会比小型设备快，
             * 比如大型设备使用项目的网格布局的时候。
             */
            pageSize = 20,

            /**
             * 如果启用了placeholder，PageList将报告完整的大小，但是一些item可能在onBind方法中为null（PageListAdapter
             * 在数据加载时触发重新绑定）
             *
             * 如果关闭了placeholder，onBind将不会获取null，但是会加载更多的页面，当加载新页面的时候滚动条可能会抖动。
             * 如果你关闭了placeholder，你可能应该关闭滚动条。
             */
            enablePlaceholders = true,

            /**
             * PageList在内从中一次性保存的最大数量item。
             * 这个数触发了PageList在加载更多页面时开始删除远程页面。
             * //FIXME 难道是滑动很多页面，人后内存回收最前面item的内存？？如何实现的？
             */
            maxSize = 200))

    fun insert(text: CharSequence) = ioThread {
        dao.insert(Cheese(id = 0, name = text.toString()))
    }

    fun remove(cheese: Cheese) = ioThread {
        dao.delete(cheese)
    }
}
