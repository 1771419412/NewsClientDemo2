package com.example.newsclientdemo.util;

import com.example.newsclientdemo.data.NovelContentBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 雪无痕 on 2017/5/20.
 */

public class ParserWeb {
    private static final String TAG = ParserWeb.class.getSimpleName();
    private static NovelContentBean sNovelContentBean;

    //解析书籍开始阅读地址
    public static String parser_web(String url) {
        String attr = "";
        try {

            Document document = Jsoup.connect(url).get();
            Elements book_btn = document.select("div.book_btn");
            attr = book_btn.first().getElementsByTag("a").attr("href");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attr;
    }

    //解析文章内容,返回完整的文章对象
    public static NovelContentBean parser_nol(String url) {
        NovelContentBean novelContentBean = new NovelContentBean();
        List<String> con = new ArrayList<>();
        try {
            Document novel = Jsoup.connect(url).get();
            Elements read_con = novel.select("div.pane");
            String title = read_con.first().getElementsByTag("h1").text();
            novelContentBean.setTitle(title);

            Elements select = read_con.select("div.bread_crumb");
            String novel_name = select.first().getElementsByTag("a").get(3).text();
            novelContentBean.setNovel_name(novel_name);

            String author = read_con.select("span.author").text();
            novelContentBean.setAuthor(author);

            String time = read_con.select("span.number").text();
            String[] strs = time.split("字");
            novelContentBean.setTime(strs[0]);
            novelContentBean.setWdnumber("字" + strs[1]);

            Elements select1 = read_con.select("div.content");
            for (Element ele : select1) {
                String p = ele.getElementsByTag("p").text();
                con.add(p);
            }
            novelContentBean.setNv_content(con);

            Elements select2 = read_con.select("div.tc");
            Elements elements = select2.get(3).getElementsByTag("a");

            Elements select3 = elements.select("a.marr");
            String pre = "";
            String next = "";
            //判断是否存在上一章
            if (select3.size() == 2) {
                pre = select3.first().attr("href");
            }
            //判断是否存在下一章
            Elements select4 = elements.select("a.next");
            if (select4 != null) {
                next = select4.first().attr("href");
            }
            novelContentBean.setPre_link(pre);
            novelContentBean.setNext_link(next);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return novelContentBean;
    }
}
