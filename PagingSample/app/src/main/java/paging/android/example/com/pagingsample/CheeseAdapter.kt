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

import android.util.Log
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import android.view.ViewGroup

/**
 * 简单的PageListAdapter，向CardView绑定奶酪item
 * PageListAdapter是RecyclerView.Adapter的子类，它可以在RecyclerView中展示PagedList的内容。当用户滑动的时候
 * 他请求新的页面，并在后台线程计算列表差异来处理新的PagedList，并将最小的，最有效的更新分发到RecyclerView来确保
 * 最小UI线程的工作。
 * 如果你想用你自己的Adapter子类，尝试在你的Adapter中使用PagedListAdapterHelder
 */
class CheeseAdapter : PagedListAdapter<Cheese, CheeseViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        Log.d("MainActivity", "onBindViewHolder position : ${position}")
        Log.d("MainActivity", "onBindViewHolder Item : ${getItem(position)?.name}")
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder {
        Log.d("MainActivity", "onCreateViewHolder viewType : ${viewType}")
        return CheeseViewHolder(parent)
    }


    companion object {
        /**
         * 在新的PagedList更新时，这个Diff callback通知PagedListAdapter如何计算列表差异。
         * 当你使用Add按钮添加一个奶酪的时候，PagedListAdapter使用diffCallback来检测和以前相比有只有一个item不同，
         * 因此它只需要动画和重渲染一个view
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Cheese>() {
            override fun areItemsTheSame(oldItem: Cheese, newItem: Cheese): Boolean {
                //在数据有变化，渲染之前进行Diff对比
                Log.d("MainActivity", "areItemsTheSame")
                Log.d("MainActivity", "oldItem.id : ${oldItem.name}")
                Log.d("MainActivity", "newItem.id : ${newItem.name}")
                return oldItem.id == newItem.id
            }

            /**
             * 在Kotlin中注意，==检查数据类比较所有内容，但是在Java中，通常你需要实现对象的equals，并使用它来比较
             * 对象的内容。
             */
            override fun areContentsTheSame(oldItem: Cheese, newItem: Cheese): Boolean{
                Log.d("MainActivity", "areContentsTheSame")
                Log.d("MainActivity", "oldItem.id : ${oldItem.name}")
                Log.d("MainActivity", "newItem.id : ${newItem.name}")
                return  oldItem == newItem
            }

        }
    }
}
