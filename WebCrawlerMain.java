

public class WebCrawlerMain
{

    public static void main(String[] args)
    {
        WebCrawler crawl = new WebCrawler();
        crawl.webPageLinks("https://en.wikipedia.org/wiki/Main_Page");
        System.out.println("Title Pages: ");
        System.out.println();
        crawl.PrintWebPageTitles();
        System.out.println();
        System.out.println("Words Found: ");
        System.out.println();
        crawl.PrintWordsandCount();

    }
}
