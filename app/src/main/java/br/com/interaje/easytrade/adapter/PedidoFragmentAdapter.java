package br.com.interaje.easytrade.adapter;

/**
 * Created by charles on 10/12/15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.interaje.easytrade.fragments.ListTodosProdutos;
import br.com.interaje.easytrade.fragments.ListMaisVendidosProdutos;
import br.com.interaje.easytrade.fragments.ListOutrosProdutos;

public class PedidoFragmentAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PedidoFragmentAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ListTodosProdutos tab1 = new ListTodosProdutos();
                return tab1;
            case 1:
                ListMaisVendidosProdutos tab2 = new ListMaisVendidosProdutos();
                return tab2;
            case 2:
                ListOutrosProdutos tab3 = new ListOutrosProdutos();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
