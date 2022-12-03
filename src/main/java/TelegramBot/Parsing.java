package TelegramBot;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;

public class Parsing
{
    public String getVideoLink(String request) throws IOException
    {
        String url = "https://www.youtube.com/results?search_query="+ URLEncoder.encode(request, StandardCharsets.UTF_8);
        String page = String.valueOf(Jsoup.parse(new URL(url),3000));
        int count=page.indexOf("\"url\":\"/watch?v=")+16;
        StringBuilder linkToVideo = new StringBuilder("https://www.youtube.com/watch?v=");

        while (page.charAt(count)!='\"')
        {
            linkToVideo.append(page.charAt(count));
            count++;
        }
        return linkToVideo.toString();
    }
}