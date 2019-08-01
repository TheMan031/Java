import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.HashSet;
import java.io.IOException;


public class WebCrawler
{
    private static final int MAX_PAGES = 5;
    private HashSet<String> webPageTitle = new HashSet<>();
    private HashSet<String> webPageURL = new HashSet<>();
    private HashMap<String, Integer> map = new HashMap<>();

    public void webPageLinks(String firstURL)
    {
        if ((webPageTitle.size() < MAX_PAGES) && !webPageURL.contains(firstURL))
        {
            webPageURL.add(firstURL);
            System.out.println(firstURL);

            try
            {
                Document log = Jsoup.connect(firstURL).get();
                Elements linksOnWebPage = log.select("a[href*=/wiki/]");
                String title = log.select("title").first().text();
                webPageTitle.add(title);
                String text = log.body().text();
                wordCount(text);

                for (Element link : linksOnWebPage)
                {
                    if (webPageTitle.size() <= MAX_PAGES)
                    {
                        Thread.sleep(1000);
                        webPageLinks(link.attr("abs:href"));
                    }

                    else
                    {
                        System.out.println("Could not visit the URL");
                        System.out.println(firstURL + ", " + webPageURL);
                    }
                }
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }

            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void PrintWebPageTitles()
    {

        for (String wPT : webPageTitle)
        {
            System.out.println(wPT);
        }
    }

    public void PrintWordsandCount()
    {

        for (String key : map.keySet())
        {
            System.out.println(key + " : " + map.get(key));
        }
    }

    private void wordCount(String text)
    {
        String[] sentence = text.split(" ");

        for (String word : sentence)
        {

            if (map.containsKey(word))
            {
                int value = map.get(word);
                value += 1;
                map.remove(word);
                map.put(word,value);
            }

            else
            {
                map.put(word, 1);
            }
        }
    }
}
