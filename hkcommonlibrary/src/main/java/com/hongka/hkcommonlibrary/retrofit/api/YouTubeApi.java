package com.hongka.hkcommonlibrary.retrofit.api;

import com.hongka.hkcommonlibrary.retrofit.model.youtube.ChannelsResponse;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.PlaylistItemsResponse;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.PlaylistsResponse;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.SearchResponse;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.VideosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jusung.kim@sk.com on 2017/05/22
 */

public interface YouTubeApi {
    public static String DOMAIN = "https://www.googleapis.com";

    /*
     * YouTube Channels 조회
     */
    @GET("/youtube/v3/channels?part=snippet,brandingSettings,statistics&field=pageInfo,nextPageToken,prevPageToken,items(id,snippet,statistics(viewCount,commentCount,subscriberCount,videoCount),brandingSettings(channel(title,description,featuredChannelsTitle,featuredChannelsUrls),image(bannerMobileImageUrl,bannerMobileExtraHdImageUrl)))")
    Call<ChannelsResponse> getChannels(@Query("key") String apiKey, @Query("id") String channelIds);

    /*
     * YouTube Search 조회
     * type : video, playlist
     */
    @GET("/youtube/v3/search?order=date&part=snippet&field=pageInfo,nextPageToken,prevPageToken,items(id(videoId,playlistId),snippet(publishedAt,title,description,thumbnails(default(url),medium(url),high(url),standard(url),maxres(url))))")
    Call<SearchResponse> getSearch(@Query("key") String apiKey, @Query("channelId") String channelId, @Query("pageToken") String pageToken, @Query("type") String type, @Query(value = "q", encoded = true) String q, @Query("maxResults") int maxResults);

    /*
     * YouTube Playlists 조회
     */
    @GET("/youtube/v3/playlists?part=snippet,contentDetails&fields=pageInfo,nextPageToken,prevPageToken,items(id,snippet(publishedAt,title,thumbnails(default(url),medium(url),high(url),standard(url),maxres(url))),contentDetails)")
    Call<PlaylistsResponse> getPlaylists(@Query("key") String apiKey, @Query("channelId") String channelId, @Query("pageToken") String pageToken, @Query("maxResults") int maxResults);

    /*
     * YouTube PlaylistItems 조회
     */
    @GET("/youtube/v3/playlistItems?part=snippet&fields=pageInfo,nextPageToken,prevPageToken,items(id,snippet(publishedAt,title,description,thumbnails(default(url),medium(url),high(url),standard(url),maxres(url)),resourceId(videoId)))")
    Call<PlaylistItemsResponse> getPlaylistItems(@Query("key") String apiKey, @Query("playlistId") String playlistId, @Query("pageToken") String pageToken, @Query("maxResults") int maxResults);

    /*
     * YouTube Video 조회, id값은 ,를 구분자로 들어올수 있음.
     */
    @GET("/youtube/v3/videos?part=id,snippet,contentDetails,statistics&fields=pageInfo,nextPageToken,prevPageToken,items(id,snippet(channelId,channelTitle,publishedAt,title,description,thumbnails(default(url),medium(url),high(url)),liveBroadcastContent),contentDetails(duration),statistics)")
    Call<VideosResponse> getVideos(@Query("key") String apiKey, @Query("id") String videoIds);
}
