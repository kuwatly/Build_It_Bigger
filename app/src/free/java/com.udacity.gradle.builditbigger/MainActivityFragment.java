/*
 * Copyright (C) 2016 Iyad Kuwatly
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    @BindView(R.id.adView)
    AdView mAdView;

    @BindView(R.id.telljoke_button_view)
    Button mTellJokeButton;

    private InterstitialAd mInterstitial;
    private AdRequest adRequest;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,root);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitial = new InterstitialAd(getContext());
        mInterstitial.setAdUnitId(getString(R.string.banner_ad_unit_id));
        requestNewInterstitial();


        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                retrieveAndShowJoke();
            }
        });
        return root;
    }

    public void retrieveAndShowJoke() {
        new EndpointsAsyncTask((MainActivity) getActivity()).execute(new Pair<Context, String>(getActivity(), ""));
    }

    private void requestNewInterstitial() {
        mInterstitial.loadAd(adRequest);
    }

    @OnClick(R.id.telljoke_button_view)
    public void tellJoke(Button button) {
        if (mInterstitial.isLoaded()) {
            mInterstitial.show();
        } else {
            retrieveAndShowJoke();
        }
    }

}
