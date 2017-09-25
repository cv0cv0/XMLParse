package me.gr.xmlparse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.drakeet.multitype.ItemViewBinder
import me.gr.xmlparse.databinding.ItemListBinding

/**
 * Created by GR on 2017/9/25.
 */
class LanguageItem : ItemViewBinder<Language, LanguageItem.LanguageViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): LanguageViewHolder {
        val itemView = inflater.inflate(R.layout.item_list, parent, false)
        return LanguageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, item: Language) {
        holder.bind(item)
    }

    class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemListBinding.bind(itemView)

        fun bind(language: Language) {
            binding.language = language
        }
    }
}