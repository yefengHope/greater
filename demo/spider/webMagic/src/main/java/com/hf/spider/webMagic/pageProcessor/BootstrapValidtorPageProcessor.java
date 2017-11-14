package com.hf.spider.webMagic.pageProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

/**
 * Created by HF on 2017/11/12.
 */
public class BootstrapValidtorPageProcessor implements PageProcessor {

    private static Logger logger;
    static {
        logger = LoggerFactory.getLogger(BootstrapValidtorPageProcessor.class);
    }

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        System.out.println(page.getHtml().toString());

        // 部分二：定义如何抽取页面信息，并保存下来
        page.putField("html", page.toString());
        page.putField("name", page.getHtml().$("#main .doc-heading h1").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        // page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(page.getHtml().links().regex("(http://bootstrapvalidator\\.votintsev\\.ru/validators/(\\w+)/)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new GithubRepoPageProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("http://bootstrapvalidator.votintsev.ru/validators/")
                // 保存结果到D盘的webmagic目录中
                .addPipeline(new FilePipeline("D:\\webmagic\\BootstrapValidator\\"))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}