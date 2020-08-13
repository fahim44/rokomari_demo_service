package com.fahim.android_client.view.ui.mainactivity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fahim.android_client.databinding.ActivityMainBinding;
import com.lamonjush.libui.ui.base.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ActivityMainBinding binding;

    private MainViewModel viewModel;

    private OrderListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);

        setUpRecyclerView();
        handleLiveData();
    }

    private void setUpRecyclerView() {
        adapter = new OrderListAdapter(viewModel.getOrders());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    private void handleLiveData() {
        viewModel.getNewItemAddedLiveData()
                .observe(this, aBoolean -> {
                    if (aBoolean != null && aBoolean) {
                        adapter.notifyItemInserted(0);
                    }
                });
    }
}