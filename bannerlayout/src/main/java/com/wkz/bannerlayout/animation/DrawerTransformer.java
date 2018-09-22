package com.wkz.bannerlayout.animation;

import android.view.View;

public class DrawerTransformer extends ABaseTransformer {
    @Override
    protected void onTransform(View view, float position) {
        if (position <= 0) {
            view.setTranslationX(0);
        } else if (position > 0 && position <= 1) {
            view.setTranslationX(-view.getWidth() / 2 * position);
        }
    }
}
