package br.com.interaje.easytrade.adapter;

/**
 * Created by charles on 10/12/15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.interaje.easytrade.fragments.ListEstoqueProdutos;
import br.com.interaje.easytrade.fragments.ListWebProdutos;

public class ProdutoFragmentAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ProdutoFragmentAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ListEstoqueProdutos tab1 = new ListEstoqueProdutos();
                return tab1;
            case 1:
                ListWebProdutos tab2 = new ListWebProdutos();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
