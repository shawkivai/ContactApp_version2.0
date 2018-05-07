package com.example.user04.contactapp;

import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link contactlist.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link contactlist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class contactlist extends Fragment implements SearchView.OnQueryTextListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private Myadapter adapter;
    private List<listitem> listitems;
//    private ArrayList<listitem> listitems;

    private Myadapter madapter;
    private DatabaseHelper database;
    MenuItem search;
//     private Myadapter recycler;

    private OnFragmentInteractionListener mListener;

    public contactlist() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment contactlist.
     */
    // TODO: Rename and change types and number of parameters
    public static contactlist newInstance(String param1, String param2) {
        contactlist fragment = new contactlist();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_contactlist, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listitems = new ArrayList<>();

//         for (int i=0; i<10; i++){
//             listitem item = new listitem("item"+i,"des"+i);
//             listitems.add(item);
//         }


        database = new DatabaseHelper(getActivity());
        listitems = database.getdata();
        //recycler =new Myadapter(listitems);

//
//        listitems.add(new listitem("shawki", "Intern", "w3" , "01839308129"));
//        listitems.add(new listitem("Shiffu", "Intern","W3" ,"017777777"));


        adapter = new Myadapter(listitems, getContext());
        recyclerView.setAdapter(adapter);
        return view;


    }


//    Menu For Searching//

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_contact_scrolling, menu);
//
//        final SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        search.setIconified(false);
//        search.setQueryHint("search contact...");
//        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                List<listitem> searchContactList = new ArrayList<>();
//                for (listitem contact : listitems){
//                    if (contact.getName().contains(s.toString())){
//                        searchContactList.add(contact);
//                    }
//                }
//                madapter.setFilter(searchContactList);
//                return true;
//            }
//        });
////    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_search:
//
////                SearchView searchView =(SearchView) MenuItemCompat.getActionView(search);
////                searchView.setOnQueryTextListener(this);
//
//
//                return true;
//
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_contact_scrolling, menu);
        super.onCreateOptionsMenu(menu,inflater);

        final SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
        search.setIconified(false);
        search.setQueryHint("search contact...");
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<listitem> searchContactList = new ArrayList<>();
                for (listitem contact : listitems){
                    if (contact.getName().contains(s.toString())){
                        searchContactList.add(contact);
                    }
                }
                adapter.setFilter(searchContactList);
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:

                break;
        }

        return super.onOptionsItemSelected(item);
    }
//    private void filter(String text) {
//        //new array list that will hold the filtered data
//        ArrayList<String> filterdNames = new ArrayList<>();
//
//        //looping through existing elements
//        for (String s : name) {
//            //if the existing elements contains the search input
//            if (s.toLowerCase().contains(text.toLowerCase())) {
//                //adding the element to filtered list
//                filterdNames.add(s);
//            }
//        }
//
//        //calling a method of the adapter class and passing the filtered list
//        adapter.filterList(filterdNames);
//    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;


    }

    @Override
    public boolean onQueryTextChange(String newtext) {
        newtext = newtext.toLowerCase();
        ArrayList<listitem> newList = new ArrayList<>();
        for (listitem listitems : listitems) {

            String name = listitems.getName().toLowerCase();
            if (name.contains(newtext)) {
                newList.add(listitems);
            }


        }

        madapter.setFilter(newList);
        return true;


    }


    /**
     * This method is to fetch all user records from SQLite
     */



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

            Toast.makeText(context, "contact list", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
