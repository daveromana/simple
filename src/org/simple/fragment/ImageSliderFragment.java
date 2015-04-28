package org.simple.fragment;

import java.util.HashMap;

import org.spring.simple.R;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ImageSliderFragment extends Fragment implements BaseSliderView.OnSliderClickListener{
	private SliderLayout sliderLayout;
	private PagerIndicator custom_indicator2,custom_indicator;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.imageslider_layout, container, false);
		this.sliderLayout=(SliderLayout) view.findViewById(R.id.slider);
		this.custom_indicator=(PagerIndicator) view.findViewById(R.id.custom_indicator);
		this.custom_indicator2=(PagerIndicator) view.findViewById(R.id.custom_indicator2);
		addSlideView();
		sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
		sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
	    sliderLayout.setCustomAnimation(new DescriptionAnimation());
		sliderLayout.setDuration(4000);
	//	sliderLayout.setCustomIndicator(custom_indicator2);
		return view;
	}
	private HashMap< String, String> getURL_Maps(){
		HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
	    return url_maps;
	}
	private void addSlideView(){
		HashMap<String,String> urls=getURL_Maps();
		for(String name : urls.keySet()){
            TextSliderView textSliderView = new TextSliderView(this.getActivity());
           
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(urls.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.getBundle()
                    .putString("extra",name);

           sliderLayout.addSlider(textSliderView);
        }
	}
	@Override
	public void onSliderClick(BaseSliderView slider) {
		// TODO Auto-generated method stub
		Toast.makeText(this.getActivity(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
	}

}
