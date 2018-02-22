package com.edlo.demodatabinding;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edlo.demodatabinding.adapter.SpotListAdapter;
import com.edlo.demodatabinding.databinding.FragParkListBinding;
import com.edlo.demodatabinding.model.ItemSpot;
import com.edlo.demodatabinding.network.ServerAPI;
import com.edlo.network.ResponseCallback;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class SpotListFrag extends Fragment {
	public static final String TAG = SpotListFrag.class.getSimpleName();
	private static final String LIST_DATA = "listData";

	private ArrayList<ItemSpot> listData;
	private FragParkListBinding binding;

	public static SpotListFrag newInstance(){
		SpotListFrag frag = new SpotListFrag();
		return frag;
	}

	public SpotListFrag() {
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(savedInstanceState!= null && savedInstanceState.containsKey(LIST_DATA)){
			listData = (ArrayList<ItemSpot>) savedInstanceState.getSerializable(LIST_DATA);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		binding = FragParkListBinding.inflate(inflater, container, false);
		return binding.getRoot();
	}

	private void initViews(Context context) {
		binding.list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Activity activity = getActivity();
		initViews(activity);
		if(listData != null) {
			Log.v(TAG, "onActivityCreated listData != null");
			setData();
		} else {
			Log.v(TAG, "onActivityCreated listData == null");
			ServerAPI.req_SpotInParks(activity, callback);
		}
	}

	private void setData() {
		SpotListAdapter adapter = new SpotListAdapter(listData);
		binding.list.setAdapter(adapter);
	}

	private ResponseCallback<JSONObject> callback = new ResponseCallback<JSONObject>() {
		@Override
		public void onResponse(JSONObject response) {
			listData = ServerAPI.result_SpotInParks(response);
			setData();
		}

		@Override
		public void onError(String message) { }
	};

	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.v(TAG, "onSaveInstanceState");
		outState.putSerializable(LIST_DATA, listData);
		super.onSaveInstanceState(outState);
	}

}
