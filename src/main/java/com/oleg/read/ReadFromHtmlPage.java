package com.oleg.read;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ReadFromHtmlPage {

    //This is class in Development

    public static void main(String[] args) throws IOException {

        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = "https://studfiles.net/preview/2438081/";

        Document doc = Jsoup.connect(url).get();

        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        for (Element href : links) {
            if (doc.tagName().equals("href")) ;

        }
    }
}

