package com.wkz.bannerlayout.widget;

import android.graphics.Color;
import android.widget.RelativeLayout;

import com.wkz.bannerlayout.R;

public final class BannerDefaults {

    /**
     * By default,  isGuide
     */
    public static final boolean IS_GUIDE = false;
    /**
     * Whether the vertical sliding ,The default is not
     */
    public static final boolean IS_VERTICAL = false;
    /**
     * Auto rotation is not turned on by default
     */
    public static final boolean IS_START_ROTATION = false;
    /**
     * Default rotation time
     */
    public static final int DELAY_TIME = 2000;
    /**
     * The default viewpager can be manually swiped
     */
    public static final boolean VIEW_PAGER_TOUCH_MODE = false;
    /**
     * Default viewpager switching speed
     */
    public static final int BANNER_DURATION = 800;
    /**
     * The Glide default error placeholder
     */
    public static final int GLIDE_ERROR_IMAGE = R.drawable.bl_drawable_holder;
    /**
     * The Glide default placeholder for the load
     */
    public static final int GLIDE_PLACE_IMAGE = R.drawable.bl_drawable_holder;



    /**
     * this is BannerTipsLayout default setting
     */
    public static final int TIPS_LAYOUT_BACKGROUND = Color.BLACK;
    public static final int TIPS_LAYOUT_WIDTH = BannerLayout.MATCH_PARENT;
    public static final int TIPS_LAYOUT_HEIGHT = 50;
    public static final boolean IS_TIPS_LAYOUT_BACKGROUND = false;


    /**
     * this is dots default setting
     */
    public static final boolean IS_VISIBLE_DOTS = true;
    public static final int DOTS_LEFT_MARGIN = 2;
    public static final int DOTS_RIGHT_MARGIN = 2;
    public static final int DOTS_WIDth = 5;
    public static final int DOTS_HEIGHT = 5;
    public static final int DOTS_SELECTOR = R.drawable.bl_selector_dots;
    public static final float DOTS_ENABLED_RADIUS = 20.0F;
    public static final float DOTS_NORMAL_RADIUS = 20.0F;
    public static final int DOTS_ENABLED_COLOR = Color.BLUE;
    public static final int DOTS_NORMAL_COLOR = Color.WHITE;
    public static final int DOTS_SITE = RelativeLayout.CENTER_IN_PARENT;


    /**
     * this is title default setting
     */
    public static final boolean IS_VISIBLE_TITLE = false;
    public static final float TITLE_SIZE = 12.0F;
    public static final int TITLE_COLOR = Color.YELLOW;
    public static final int TITLE_LEFT_MARGIN = 10;
    public static final int TITLE_RIGHT_MARGIN = 10;
    public static final int TITLE_WIDTH = BannerLayout.WRAP_CONTENT;
    public static final int TITLE_HEIGHT = BannerLayout.WRAP_CONTENT;
    public static final int TITLE_SITE = RelativeLayout.ALIGN_PARENT_LEFT;
    public static final int TIPS_SITE = RelativeLayout.ALIGN_PARENT_BOTTOM;


    /**
     * this is pageNumberTextView default setting
     */
    public static final int PAGE_NUM_VIEW_SITE = RelativeLayout.RIGHT_OF;
    public static final float PAGE_NUM_VIEW_RADIUS = 25.0F;
    public static final int PAGE_NUM_VIEW_RIGHT_MARGIN = 15;
    public static final int PAGE_NUM_VIEW_TOP_MARGIN = 0;
    public static final int PAGE_NUM_VIEW_LEFT_MARGIN = 0;
    public static final int PAGE_NUM_VIEW_BOTTOM_MARGIN = 0;
    public static final float PAGE_NUM_VIEW_SIZE = 10.0F;
    public static final int PAGE_NUM_VIEW_PADDING_LEFT = 20;
    public static final int PAGE_NUM_VIEW_PADDING_TOP = 5;
    public static final int PAGE_NUM_VIEW_PADDING_RIGHT = 20;
    public static final int PAGE_NUM_VIEW_PADDING_BOTTOM = 5;
    public static final int PAGE_NUM_VIEW_BACKGROUND = Color.BLACK;
    public static final int PAGE_NUL_VIEW_TEXT_COLOR = Color.WHITE;
    public static final String PAGE_NUM_VIEW_MARK = " / ";


    /**
     * this is progressDrawable default setting
     */
    public static final float PROGRESSES_LEFT_MARGIN = 2.5F;
    public static final float PROGRESSES_RIGHT_MARGIN = 2.5F;
    public static final int PROGRESSES_SITE = RelativeLayout.CENTER_IN_PARENT;
}
