package com.edlo.demodatabinding.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edlo.demodatabinding.databinding.ListItemSpotBinding;
import com.edlo.demodatabinding.databinding.SpotViewModel;
import com.edlo.demodatabinding.model.ItemSpot;

import java.util.ArrayList;

/**
 * Created by moi0312 on 2018/2/20.
 */

public class SpotListAdapter extends RecyclerView.Adapter<SpotListAdapter.ParkViewHolder> {
	private static final String TAG = SpotListAdapter.class.getSimpleName();

	ArrayList<ItemSpot> mDataSet;

	public SpotListAdapter(ArrayList<ItemSpot> mDataSet) {
		this.mDataSet = mDataSet;
	}

	@Override
	public ParkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		ParkViewHolder viewHolder = ParkViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ParkViewHolder holder, int position) {
		Log.v(TAG, "onBindViewHolder");
		ItemSpot item = mDataSet.get(position);
		holder.bindTo(item);
	}

	@Override
	public int getItemCount() {
		Log.v(TAG, "SpotListAdapter getItemCount:"+mDataSet.size());
		return mDataSet.size();
	}

	public static class ParkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

		private ListItemSpotBinding mBinding;
		private ItemSpot itemData;

		static ParkViewHolder create(LayoutInflater layoutInflater, ViewGroup parent) {
			ListItemSpotBinding binding = ListItemSpotBinding.inflate(layoutInflater, parent, false);
			ParkViewHolder viewHolder = new ParkViewHolder(binding);
			binding.getRoot().setOnClickListener(viewHolder);

			return viewHolder;
		}

		public ParkViewHolder(ListItemSpotBinding binding) {
			super(binding.getRoot());
			mBinding = binding;
		}

		public ListItemSpotBinding getBinding() {
			return mBinding;
		}

		public void bindTo(ItemSpot item){
			itemData = item;
			SpotViewModel viewModel = new SpotViewModel(item);
			mBinding.setViewModel(viewModel);
		}

		@Override
		public void onClick(View v) {
			Log.v(TAG, "onClick: "+itemData.getName());
			//TODO handler click event
		}
	}
}
