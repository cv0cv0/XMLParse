package me.gr.xmlparse

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter
import org.xmlpull.v1.XmlPullParser

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView:RecyclerView
    private val adapter = MultiTypeAdapter()
    private val items = Items()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter.register(Language::class.java, LanguageItem())
        recyclerView=findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        initData()
    }

    private fun initData() {
        val parser = resources.getXml(R.xml.subject)
        var eventType = parser.eventType
        var language: Language? = null
        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    when (parser.name) {
                        "language" -> language = Language()
                        "name" -> language?.name=parser.nextText()
                        "usage" -> language?.usage=parser.nextText()
                    }
                }
                XmlPullParser.END_TAG -> {
                    if (parser.name == "language") {
                        items.add(language)
                    }
                }
            }
            eventType = parser.next()
        }
        adapter.items = items
        adapter.notifyDataSetChanged()
    }
}
