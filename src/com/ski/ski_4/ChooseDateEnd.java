package com.ski.ski_4;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class ChooseDateEnd extends Fragment implements ICallBackFragmentAdapter {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    //TextView tvOut;

    static int pageNumber;
    int backColor;
    private TabsAdapter tabsAdapter;
    ViewPager vp;
    static DatePicker datePicker2;
    static EditText editText;

    //static DatePicker datePicker3;


    static ChooseDateEnd newInstance(int page) {
        ChooseDateEnd pageFragment = new ChooseDateEnd();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        Log.w("ChooseDateEnd", "Fragment init");
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
        View view = inflater.inflate(R.layout.activity_choose_date_end, null);
    /*view.setBackgroundColor(Color.CYAN);
    TextView tvPage = (TextView) view.findViewById(R.id.tvPage);
    tvPage.setText("Page " + pageNumber);
    tvPage.setBackgroundColor(backColor);*/
        Log.w("ChooseDateEnd", "Generated view");

        Button button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(lsn1);

        Button button2 = (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(lsn2);


        switch (Constants.ACTIVITY) {
            case 1:



                button2.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {

                        if (((Integer.parseInt(ChooseDate.getYearFromDatePicker1()) >= Integer.parseInt(getYearFromDatePicker2())) && (Integer.parseInt(ChooseDate.getMonthFromDatePicker1()) >= Integer.parseInt(getMonthFromDatePicker2())) && (Integer.parseInt(ChooseDate.getDayFromDatePicker1()) > Integer.parseInt(getDayFromDatePicker2())))|| ((Integer.parseInt(ChooseDate.getYearFromDatePicker1()) >= Integer.parseInt(getYearFromDatePicker2())) && (Integer.parseInt(ChooseDate.getMonthFromDatePicker1()) > Integer.parseInt(getMonthFromDatePicker2())))||(Integer.parseInt(ChooseDate.getYearFromDatePicker1()) > Integer.parseInt(getYearFromDatePicker2()))){

                            Toast.makeText(getActivity(), "Your End Date is before Your Start Date", Toast.LENGTH_LONG).show();
                            Log.w("Ski_c", "wrong date");

                        } else{

                            if (EnterName.getName().length() == 0 || EnterName.getPhone().length() == 0||EnterPrice.getPrice().length() == 0) {
                                Toast.makeText(getActivity(), "Your Name, Phone or Price is empty", Toast.LENGTH_LONG).show();

                            }    else{





                                Thread t = new Thread() {
                                    public void run() {
                                        new SendData(getActivity()).execute("");
                                        Log.w("Ski_c CDE", " execute senddata");
                                    }
                                };
                                t.start();
                                //this.finish();
                                /*Log.i("Ski_c CDE getActivity() is ", String.valueOf(getActivity()));*/
                                getActivity().finish();

                                Log.i("Ski_c CDE fb isLoggedIn() is ", String.valueOf(MainActivity.isLoggedIn()));
                                Log.i("Ski_c CDE Constants.FB is ", String.valueOf(Constants.FB));

                                if (MainActivity.isLoggedIn()==true||Constants.FB==1){

                                    Intent intent = new Intent(getActivity(), FbShareActivity.class);

                                    startActivity(intent);


                                }


                                /*if*/
                                /*Toast.makeText(getActivity(), "Done", Toast.LENGTH_LONG).show();
                                Log.w("Ski_c", "CDE pushed to send from ChooseDateEnd");*/
                                Log.i("Ski_c CDE  ", "here in CDE");

                                /*dialog = new ProgressDialog(getActivity());

                                Log.i("Ski_c cde ", "Dialog 2 started");

                                dialog.setTitle("YOUR DATA IS BEING PROCEED...");
                                dialog.setMessage("WAIT.");
                                dialog.setIndeterminate(true);
                                dialog.setCancelable(true);
                                dialog.show();
                                Log.i("Ski_c CDE ", "Dialog 2 is showing");*/

                            }

                        }
                    }

                });
                //tvOut.setText("Done");

                break;
            case 0:

                button2.setOnClickListener(lsn2);

                break;
        }


        //datePicker3 = (DatePicker) view.findViewById(R.id.datePicker1);
        datePicker2 = (DatePicker) view.findViewById(R.id.datePicker2);
        //datePicker2.setMinDate(datePicker3.getDayFromDatePicker1()"/"datePicker3.getMonthFromDatePicker1()"/"datePicker3.getYearFromDatePicker1());


        return view;
    }

    public static String getDayFromDatePicker2() {

        String day = Integer.toString(datePicker2.getDayOfMonth());

        return day;
    }

    public static String getMonthFromDatePicker2() {
        String month = Integer.toString(datePicker2.getMonth() + 1);

        return month;
    }

    public static String getYearFromDatePicker2() {
        String year = Integer.toString(datePicker2.getYear());

        return year;
    }


    private OnClickListener lsn1 = new OnClickListener() {
        @Override
        public void onClick(View v) {




            if (vp != null) {





                // = tabsAdapter.getViewPager();
                vp.setCurrentItem(vp.getCurrentItem() - 1, true);
            } else {
                Log.e(/*this.getClass().getName()*/"Ski_c CDE", "Error empty vp");
            }

        }
    };
    ProgressDialog dialog;

    @Override
    public void onPause() {
        super.onPause();
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    private OnClickListener lsn2 = new OnClickListener() {
        @Override
        public void onClick(View v) {

            if (((Integer.parseInt(ChooseDate.getYearFromDatePicker1()) >= Integer.parseInt(getYearFromDatePicker2())) && (Integer.parseInt(ChooseDate.getMonthFromDatePicker1()) >= Integer.parseInt(getMonthFromDatePicker2())) && (Integer.parseInt(ChooseDate.getDayFromDatePicker1()) > Integer.parseInt(getDayFromDatePicker2())))|| ((Integer.parseInt(ChooseDate.getYearFromDatePicker1()) >= Integer.parseInt(getYearFromDatePicker2())) && (Integer.parseInt(ChooseDate.getMonthFromDatePicker1()) > Integer.parseInt(getMonthFromDatePicker2())))||(Integer.parseInt(ChooseDate.getYearFromDatePicker1()) > Integer.parseInt(getYearFromDatePicker2()))){

                Toast.makeText(getActivity(), "Your End Date is before Your Start Date", Toast.LENGTH_LONG).show();
                Log.w("Ski_c", "wrong date");

            } else{

            /*if (Constants.ACTIVITY==1){

                if (EnterName.getName().length() == 0 || EnterName.getPhone().length() == 0||EnterPrice.getPrice().length() == 0) {
                    Toast.makeText(getActivity(), "Your Name, Phone or Price is empty", Toast.LENGTH_LONG).show();

                }
            } else {*/




                if (vp != null) {
                    // = tabsAdapter.getViewPager();

                    dialog = new ProgressDialog(getActivity());

                    Log.w("Ski_c cde ", "Dialog 1 started");

                    dialog.setTitle("LOADING...");
                    dialog.setMessage("WAIT.");
                    dialog.setIndeterminate(true);
                    dialog.setCancelable(true);
                    dialog.show();
                    Log.w("Ski_c CDE ", "Dialog 1 is showing");


                    vp.setCurrentItem(vp.getCurrentItem() + 1, true);

                    Thread t = new Thread() {
                        public void run() {
                            new SendData(getActivity()).execute("");
                            Log.w("Ski_c CDE ", "Ski_c execute senddata");
                        }
                    };
                    t.start();


                } else {
                /*Log.e(this.getClass().getName(),"Error empty vp");*/
                }
            }
            // }

            Log.w("Ski_c CDE ", "Ski_c show list");



                   /* Intent intent = new Intent(getActivity(),ListActiv.class);
                    startActivity(intent);*/

        }

        ;


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