package com.wkz.bannerlayout.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.wkz.bannerlayout.annotation.TipsDotsSiteMode;
import com.wkz.bannerlayout.annotation.TipsLayoutSiteMode;
import com.wkz.bannerlayout.annotation.TipsPageNumSiteMode;
import com.wkz.bannerlayout.annotation.TipsProgressesSiteMode;
import com.wkz.bannerlayout.annotation.TipsTitleSiteMode;

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
    public static final Drawable GLIDE_ERROR_DRAWABLE = new ColorDrawable(Color.DKGRAY);
    /**
     * The Glide default placeholder for the load
     */
    public static final Drawable GLIDE_PLACEHOLDER_DRAWABLE = new ColorDrawable(Color.DKGRAY);


    /**
     * this is BannerTipsLayout default setting
     */
    public static final int TIPS_LAYOUT_BACKGROUND = Color.BLACK;
    public static final int TIPS_LAYOUT_WIDTH = BannerLayout.MATCH_PARENT;
    public static final int TIPS_LAYOUT_HEIGHT = BannerLayout.WRAP_CONTENT;
    public static final boolean IS_TIPS_LAYOUT_BACKGROUND = false;
    public static final int TIPS_SITE = TipsLayoutSiteMode.BOTTOM;


    /**
     * this is dots default setting
     */
    public static final boolean IS_VISIBLE_DOTS = true;
    public static final float DOTS_LEFT_MARGIN = 2F;
    public static final float DOTS_TOP_MARGIN = 0F;
    public static final float DOTS_RIGHT_MARGIN = 2F;
    public static final float DOTS_BOTTOM_MARGIN = 20F;
    public static final float DOTS_WIDth = 5F;
    public static final float DOTS_HEIGHT = 5F;
    public static final float DOTS_ENABLED_RADIUS = 20.0F;
    public static final float DOTS_NORMAL_RADIUS = 20.0F;
    public static final int DOTS_ENABLED_COLOR = Color.RED;
    public static final int DOTS_NORMAL_COLOR = Color.WHITE;
    public static final int DOTS_SELECTOR = 0;
    public static final int[] DOTS_SITE = {TipsDotsSiteMode.BOTTOM, TipsDotsSiteMode.CENTER_HORIZONTAL};


    /**
     * this is title default setting
     */
    public static final boolean IS_VISIBLE_TITLE = false;
    public static final float TITLE_SIZE = 13.0F;
    public static final int TITLE_COLOR = Color.BLACK;
    public static final float TITLE_LEFT_MARGIN = 10;
    public static final float TITLE_TOP_MARGIN = 8F;
    public static final float TITLE_RIGHT_MARGIN = 10;
    public static final float TITLE_BOTTOM_MARGIN = 8F;
    public static final float TITLE_WIDTH = BannerLayout.MATCH_PARENT;
    public static final float TITLE_HEIGHT = BannerLayout.WRAP_CONTENT;
    public static final int TITLE_BACKGROUND_COLOR = 0x50000000;
    public static final int[] TITLE_SITE = {TipsTitleSiteMode.LEFT, TipsTitleSiteMode.BOTTOM};


    /**
     * this is pageNumberTextView default setting
     */
    public static final float PAGE_NUM_VIEW_RADIUS = 25.0F;
    public static final float PAGE_NUM_VIEW_LEFT_MARGIN = 0F;
    public static final float PAGE_NUM_VIEW_TOP_MARGIN = 0F;
    public static final float PAGE_NUM_VIEW_RIGHT_MARGIN = 15F;
    public static final float PAGE_NUM_VIEW_BOTTOM_MARGIN = 0F;
    public static final float PAGE_NUM_VIEW_SIZE = 10.0F;
    public static final float PAGE_NUM_VIEW_PADDING_LEFT = 5F;
    public static final float PAGE_NUM_VIEW_PADDING_TOP = 0F;
    public static final float PAGE_NUM_VIEW_PADDING_RIGHT = 5F;
    public static final float PAGE_NUM_VIEW_PADDING_BOTTOM = 0F;
    public static final int PAGE_NUM_VIEW_BACKGROUND = Color.BLACK;
    public static final int PAGE_NUL_VIEW_TEXT_COLOR = Color.WHITE;
    public static final String PAGE_NUM_VIEW_MARK = " / ";
    public static final int PAGE_NUM_VIEW_SITE = TipsPageNumSiteMode.TOP_RIGHT;


    /**
     * this is progressDrawable default setting
     */
    public static final boolean IS_VISIBLE_PROGRESSES = false;
    public static final float PROGRESSES_LEFT_MARGIN = 2.5F;
    public static final float PROGRESSES_TOP_MARGIN = 0F;
    public static final float PROGRESSES_RIGHT_MARGIN = 2.5F;
    public static final float PROGRESSES_BOTTOM_MARGIN = 0F;
    public static final int[] PROGRESSES_SITE = {TipsProgressesSiteMode.BOTTOM, TipsProgressesSiteMode.CENTER_HORIZONTAL};
}
