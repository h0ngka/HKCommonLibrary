package com.hongka.hkcommonlibrary.retrofit.model.youtube;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jusung.kim@sk.com on 2017/05/23
 */

public class ChannelsResponse implements Serializable {
    public PageInfo pageInfo;
    public List<Channel> items;
}
