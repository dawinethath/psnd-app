package core.lib.network.base;

public class YouTubeLinkAPI {
    public static final String URL_REQUEST_LINK = "https://uploadbeta.com/api/video/?cached&lang=en&page=youtube&hash=##HASH##&video=https://www.youtube.com/watch?v=";
    public static final String URL_HASH         = "https://weibomiaopai.com/online-video-downloader/youtube";

//    public static YouTubeVideo getYouTubeVideoURL(YouTubeConfigure configure, String youtubeId) {
//        String requestUrl = configure.getYoutubeRequestLink() + youtubeId;
//        requestUrl = requestUrl.replace("##HASH##", getHash(configure.getUrlHash()));
//        String json = API.getHtml(requestUrl);
//        if ((BuildConfig.DEBUG_MODE)) {
//            DebugUtil.logDebug(new Exception(), json);
//        }
//        YouTubeVideo video = new Gson().fromJson(json, YouTubeVideo.class);
//        if (video != null) {
//            DebugUtil.logInfo(new Exception(), video.toJson());
//            video.setId(youtubeId);
//            return video;
//        }
//        return null;
//    }
//
//    private static String getHash(String urlHash) {
//        String html = API.getHtml(urlHash);
//        String hash = html.split("hash =")[1].split(Pattern.quote("$.ajax"))[0].trim().replaceAll("\"", "").replace(";", "");
//        return hash;
//    }
}
