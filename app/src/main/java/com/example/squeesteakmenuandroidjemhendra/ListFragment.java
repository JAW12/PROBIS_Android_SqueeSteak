package com.example.squeesteakmenuandroidjemhendra;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.squeesteakmenuandroidjemhendra.databinding.FragmentListBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListFragment extends Fragment {

    FragmentListBinding binding;
    int kategori;
    private ArrayList<Menu> arrMenu = new ArrayList<>();


    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(int kategori) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt("kategori", kategori);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            kategori = getArguments().getInt("kategori");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    MenuAdapter menuAdapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        menuAdapter = new MenuAdapter(arrMenu);
        menuAdapter.setItemClick(new MenuAdapter.onItemClickListener() {
            @Override
            public void itemClicked(View v, int i) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("menu", arrMenu.get(i));
                startActivity(intent);
            }
        });
        binding.recyclerView.setAdapter(menuAdapter);

        GetProduct();
    }

    private void GetProduct(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.url) +"getMenu",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");
                            String message = jsonObject.getString("message");
                            arrMenu.clear();
                            if(code == 1){
                                JSONArray jsonArray = jsonObject.getJSONArray("menu");
                                for(int i = 0; i < jsonArray.length(); i++){
                                    JSONObject menuObj = jsonArray.getJSONObject(i);
                                    Menu menu = new Menu(
                                            menuObj.getInt("id"),
                                            menuObj.getString("nama"),
                                            menuObj.getString("nama_kategori"),
                                            menuObj.getInt("harga"),
                                            menuObj.getString("url"),
                                            menuObj.getString("deskripsi")
                                    );
                                    arrMenu.add(menu);
                                }
                            }
                            menuAdapter.notifyDataSetChanged();
//                            Toast.makeText(getActivity(), String.valueOf(arrMenu.size()), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("function", "getMenu");
                params.put("kategori", String.valueOf(kategori));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}