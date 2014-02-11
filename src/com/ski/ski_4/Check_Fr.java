package com.ski.ski_4;

/**
 * Created with IntelliJ IDEA.
 * User: Alyosha
 * Date: 21.11.13
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Check_Fr extends Fragment implements ICallBackFragmentAdapter {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    private TabsAdapter tabsAdapter;
    ViewPager vp;
    static int pageNumber;
    int backColor;
    static EditText editTextN;

    static EnterPrice newInstance(int page) {
        EnterPrice pageFragment = new EnterPrice();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        Log.w("EnterPrice", "Fragment init");
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

        //Random rnd = new Random();
        //backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_check, null);
    /*view.setBackgroundColor(Color.CYAN);
    TextView tvPage = (TextView) view.findViewById(R.id.tvPage);
    tvPage.setText("Page " + pageNumber);
    tvPage.setBackgroundColor(backColor);*/

        Log.w("EnterPrice", "Generated view");

        Button button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(lsn1);

        Button button2 = (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(lsn2);

        editTextN = (EditText) view.findViewById(R.id.editTextN);


        return view;
    }

    /*public static String getNumber() {
        String number = editTextN.getText().toString();
        return number;
    }*/

    private View.OnClickListener lsn1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (vp != null) {
                // = tabsAdapter.getViewPager();
                vp.setCurrentItem(vp.getCurrentItem() - 1, true);
            } else {
                /*Log.e(this.getClass().getName(),"Error empty vp");*/
            }
        }
    };

    private View.OnClickListener lsn2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if (vp != null) {
                // = tabsAdapter.getViewPager();
                vp.setCurrentItem(vp.getCurrentItem() + 1, true);
            } else {
                /*Log.e(this.getClass().getName(),"Error empty vp");*/
            }
        }
    };

    @Override
    public TabsAdapter getTabsAdapter() {
        return tabsAdapter;
    }

    @Override
    public void setTabsAdapter(TabsAdapter adapter) {
        this.tabsAdapter = adapter;
        this.vp = tabsAdapter.getViewPager();
    }
}