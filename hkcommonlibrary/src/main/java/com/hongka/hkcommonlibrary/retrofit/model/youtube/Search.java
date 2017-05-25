package com.hongka.hkcommonlibrary.retrofit.model.youtube;

/**
 * Created by jusung.kim@sk.com on 2017/05/24
 */

public class Search {
    public Id id;
    public Snippet snippet;

    public static class Id {
        public String videoId;
        public String playlistId;
    }
}
