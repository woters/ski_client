package com.example.ski_4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created with IntelliJ IDEA.
 * User: Alyosha
 * Date: 03.12.13
 * Time: 19:55
 * To change this template use File | Settings | File Templates.
 */
public class ListActivity extends Activity {

    String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей" };

    String[] names2 = { "aaa", "vvv", "bbb", "vvv", "bbb", "qqq",
            "oooo", "kkkk", "jjj", "hhh", "gggg" };

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        // находим список
        ListView l1 = (ListView) findViewById(R.id.listView1);

        // создаем адаптер
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);

        // присваиваем адаптер списку
        l1.setAdapter(adapter1);

        // находим список
        ListView l2 = (ListView) findViewById(R.id.listView2);

        // создаем адаптер
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names2);

        // присваиваем адаптер списку
        l2.setAdapter(adapter2);

    }
}
