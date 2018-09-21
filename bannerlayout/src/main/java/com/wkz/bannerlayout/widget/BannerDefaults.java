package com.wkz.bannerlayout.widget;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.RelativeLayout;

import com.wkz.bannerlayout.R;

/**
 * by y on 2016/10/25
 */
public final class BannerDefaults {

    /**
     * By default,  isGuide
     */
    public static final boolean IS_GUIDE = false;
    /**
     * customize  dots State Selector
     */
    public static final int DOTS_SELECTOR = 0;
    /**
     * Default BannerTipsLayout background color
     */
    public static final int TIPS_LAYOUT_BACKGROUND = Color.BLACK;
    /**
     * The default dots control width
     */
    public static final int TIPS_LAYOUT_WIDTH = BannerLayout.MATCH_PARENT;
    /**
     * The default dots control height
     */
    public static final int TIPS_LAYOUT_HEIGHT = 50;
    /**
     * Default rotation time
     */
    public static final int DELAY_TIME = 2000;
    /**
     * The dots are displayed by default
     */
    public static final boolean IS_VISIBLE_DOTS = true;
    /**
     * Auto rotation is not turned on by default
     */
    public static final boolean IS_START_ROTATION = false;
    /**
     * By default,  dots backgrounds are not displayed
     */
    public static final boolean IS_TIPS_LAYOUT_BACKGROUND = false;
    /**
     * The default dots are marginLeft
     */
    public static final int DOTS_LEFT_MARGIN = 10;
    /**
     * The default dots are marginRight
     */
    public static final int DOTS_RIGHT_MARGIN = 10;
    /**
     * The default dotS width
     */
    public static final int DOTS_WIDth = 15;
    /**
     * The default dotS height
     */
    public static final int DOTS_HEIGHT = 15;
    /**
     * The default viewpager can be manually swiped
     */
    public static final boolean VIEW_PAGER_TOUCH_MODE = false;
    /**
     * Glide default error placeholder
     */
    public static final int GLIDE_ERROR_IMAGE = R.drawable.bl_drawable_holder;
    /**
     * Glide The default placeholder for the load
     */
    public static final int GLIDE_PLACE_IMAGE = R.drawable.bl_drawable_holder;
    /**
     * The title is not displayed by default
     */
    public static final boolean TITLE_VISIBLE = false;
    /**
     * The default title font size
     */
    public static final float TITLE_SIZE = 12.0F;
    /**
     * The default title font color
     */
    public static final int TITLE_COLOR = Color.YELLOW;
    /**
     * The default title of marginLeft
     */
    public static final int TITLE_LEFT_MARGIN = 10;
    /**
     * The default title of marginRight
     */
    public static final int TITLE_RIGHT_MARGIN = 10;
    /**
     * The width of the default title
     */
    public static final int TITLE_WIDTH = BannerLayout.WRAP_CONTENT;
    /**
     * The height of the default title
     */
    public static final int TITLE_HEIGHT = BannerLayout.WRAP_CONTENT;
    /**
     * The site of the default title
     */
    public static final int TITLE_SITE = RelativeLayout.ALIGN_PARENT_LEFT;
    /**
     * The site of the default tips
     */
    public static final int TIPS_SITE = RelativeLayout.ALIGN_PARENT_BOTTOM;
    /**
     * The site of the default dots
     */
    public static final int DOTS_SITE = RelativeLayout.ALIGN_PARENT_RIGHT;
    /**
     * Default viewpager switching speed
     */
    public static final int BANNER_DURATION = 800;
    /**
     * Whether the vertical sliding ,The default is not
     */
    public static final boolean IS_VERTICAL = false;

    public static final float ENABLED_RADIUS = 20.0F;
    public static final float NORMAL_RADIUS = 20.0F;
    public static final int ENABLED_COLOR = Color.BLUE;
    public static final int NORMAL_COLOR = Color.WHITE;
    /**
     * this is pageNumberTextView setting
     */
    public static final int PAGE_NUM_VIEW_SITE = RelativeLayout.RIGHT_OF;
    public static final float PAGE_NUM_VIEW_RADIUS = 25.0F;
    public static final int PAGE_NUM_VIEW_RIGHT_MARGIN = 0;
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
}
