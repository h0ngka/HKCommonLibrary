package com.hongka.hkcommonlibrary.utils;

import android.content.UriMatcher;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by jusung.kim@sk.com on 2017. 6. 7..
 */
public class YouTubeLink {
    private static final int YOUTUBE_SHORT_CODE = 1;
    private static final int NOT_PREFIX_YOUTUBE_CODE = 2;
    private static final int YOUTUBE_WEB_CODE = 3;
    private static final int YOUTUBE_MOBILE_CODE = 4;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI("youtu.be", "*", YOUTUBE_SHORT_CODE);
        sUriMatcher.addURI("youtube.com", "watch", NOT_PREFIX_YOUTUBE_CODE);
        sUriMatcher.addURI("www.youtube.com", "watch", YOUTUBE_WEB_CODE);
        sUriMatcher.addURI("m.youtube.com", "watch", YOUTUBE_MOBILE_CODE);
    }

    public static boolean isYoutubeLinkUrl(String url) {
        int match = getType(url);
        if (match > 0) {
            return true;
        }
        return false;
    }

    public static String getYoutubeVideoId(String url) {
        if (getType(url) == YOUTUBE_SHORT_CODE) {
            Uri uri = Uri.parse(url);
            String id = uri.getLastPathSegment().trim();
            return id;
        } else {
            Uri uri = Uri.parse(url);
            String id = uri.getQueryParameter("v");
            return id;
        }
    }

    private static int getType(String url) {
        if (!TextUtils.isEmpty(url)) {
            Uri uri = Uri.parse(url);
            if (uri != null) {
                return sUriMatcher.match(uri);
            }
        }
        return UriMatcher.NO_MATCH;
    }
}
