package br.com.rogersdk.calculadorajni;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.rogersdk.calculadorajni.databinding.NumberBinding;

public class NumberAdapter extends BaseAdapter {

    private List<String> numbers = new ArrayList<>();

    public NumberAdapter(List<String> numbers) {
        this.numbers = numbers;
    }

    private NumberBinding binding;

    @Override
    public int getCount() {
        return numbers.size();
    }

    @Override
    public Object getItem(int i) {
        return numbers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        binding = NumberBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        binding.gridNumberText.setText(numbers.get(i));
        return binding.getRoot();
    }
}
