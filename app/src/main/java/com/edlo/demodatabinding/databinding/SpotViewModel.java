package com.edlo.demodatabinding.databinding;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.edlo.demodatabinding.BR;
import com.edlo.demodatabinding.model.ItemSpot;
import com.edlo.network.QuickReq;

/**
 * Author: moi0312
 * Created by: ModelGenerator
 */
public class SpotViewModel extends BaseObservable {
	private String id;
	private String parkName;
	private String name;
	private String yearBuilt;
	private String openTime;
	private String image;
	private String desc;

	public SpotViewModel(ItemSpot item) {
		setItemPark(item);
	}

	public void setItemPark(ItemSpot item) {
		this.id = item.getId();
		this.parkName = item.getParkName();
		this.name = item.getName();
		this.yearBuilt = item.getYearBuilt();
		this.openTime = item.getOpenTime();
		this.image = item.getImage();
		this.desc = item.getIntroduction();
		notifyPropertyChanged(BR._all);
	}

	@Bindable
	public String getId() {
		return id;
	}
	@Bindable
	public String getParkName() {
		return parkName;
	}
	@Bindable
	public String getName() {
		return name;
	}
	@Bindable
	public String getYearBuilt() {
		return yearBuilt;
	}
	@Bindable
	public String getOpenTime() {
		return openTime;
	}
	@Bindable
	public String getImage() {
		return image;
	}
	@Bindable
	public String getDesc() {
		return desc;
	}

	@BindingAdapter(value = {"android:src"})
	public static void setImageUrl(final ImageView view, String url) {
		Glide.with(view.getContext()).load(url).into(view);
	}
}