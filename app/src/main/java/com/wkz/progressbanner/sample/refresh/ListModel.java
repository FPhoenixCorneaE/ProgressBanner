package com.wkz.progressbanner.sample.refresh;

import android.support.annotation.Nullable;

public final class ListModel {
    @Nullable
    private String title;
    @Nullable
    private String titleImage;
    private int slug;
    @Nullable
    private ListModel.Author author;

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(@Nullable String var1) {
        this.title = var1;
    }

    @Nullable
    public final String getTitleImage() {
        return this.titleImage;
    }

    public final void setTitleImage(@Nullable String var1) {
        this.titleImage = var1;
    }

    public final int getSlug() {
        return this.slug;
    }

    public final void setSlug(int var1) {
        this.slug = var1;
    }

    @Nullable
    public final ListModel.Author getAuthor() {
        return this.author;
    }

    public final void setAuthor(@Nullable ListModel.Author var1) {
        this.author = var1;
    }

    public static final class Author {
        @Nullable
        private String profileUrl;
        @Nullable
        private String bio;
        @Nullable
        private String name;

        @Nullable
        public final String getProfileUrl() {
            return this.profileUrl;
        }

        public final void setProfileUrl(@Nullable String var1) {
            this.profileUrl = var1;
        }

        @Nullable
        public final String getBio() {
            return this.bio;
        }

        public final void setBio(@Nullable String var1) {
            this.bio = var1;
        }

        @Nullable
        public final String getName() {
            return this.name;
        }

        public final void setName(@Nullable String var1) {
            this.name = var1;
        }
    }
}
