package com.eajy.materialdesign2.view;

/**
 * Created by zhang on 2016.08.21.
 */
public interface onMoveAndSwipedListener {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

}
