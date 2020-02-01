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

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

/**
 * 一个简单的ViewHolder，它绑定一个奶酪item。它也接受null item，因为数据可能在它绑定的时候还没有返回。
 */
class CheeseViewHolder(parent :ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.cheese_item, parent, false)) {

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    var cheese : Cheese? = null

    /**
     * Item可能为null，如果它们没有分页。在Item加载后，PagedListAdapter将会重新绑定这个ViewHodler。
     */
    fun bindTo(cheese : Cheese?) {
        this.cheese = cheese
        nameView.text = cheese?.name
    }
}