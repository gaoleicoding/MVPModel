package com.gaolei.retrofitdemo;

import java.io.Serializable;

/**
 * Created by caiwancheng on 2017/8/29.
 */

public class FeedArticleData  {

    //    {
//    "apkLink": "",
//        "author": "LiangLuDev",
//        "chapterId": 294,
//        "chapterName": "完整项目",
//        "collect": false,
//        "courseId": 13,
//        "desc": "注册登录、用户信息、用户密码、用户图像修改、书籍分类、本地书籍扫描、书架、书籍搜索（作者名或书籍名）、书籍阅读（仅txt格式，暂不支持PDF等其他格式）、阅读字体、背景颜色、翻页效果等设置、意见反馈（反馈信息发送到我的邮箱）、应用版本更新",
//        "envelopePic": "http://www.wanandroid.com/blogimgs/fab6fb8b-c3aa-495f-b6a9-c007d78751c0.gif",
//        "fresh": false,
//        "id": 2836,
//        "link": "http://www.wanandroid.com/blog/show/2116",
//        "niceDate": "2018-04-22",
//        "origin": "",
//        "projectLink": "https://github.com/LiangLuDev/WeYueReader",
//        "publishTime": 1524376619000,
//        "superChapterId": 0,
//        "superChapterName": "",
//        "tags": [ ],
//        "title": "微Yue电子书阅读 WeYueReader",
//        "type": 0,
//        "visible": 1,
//        "zan": 0
//},
//        {
//        "apkLink": "",
//        "author": "kangdongpu",
//        "chapterId": 294,
//        "chapterName": "完整项目",
//        "collect": false,
//        "courseId": 13,
//        "desc": "玩安卓客户端(Java版)\r\n",
//        "envelopePic": "http://www.wanandroid.com/blogimgs/f3be8cc0-6b4d-4ee2-8f45-fe132949d640.png",
//        "fresh": false,
//        "id": 2797,
//        "link": "http://www.wanandroid.com/blog/show/2104",
//        "niceDate": "2018-04-08",
//        "origin": "",
//        "projectLink": "https://github.com/kangdongpu/WanAndroid",
//        "publishTime": 1523187585000,
//        "superChapterId": 0,
//        "superChapterName": "",
//        "tags": [ ],
//        "title": "玩安卓客户端(Java版) WanAndroid",
//        "type": 0,
//        "visible": 1,
//        "zan": 0
//}
    private String apkLink;
    private String author;
    private int chapterId;
    private String chapterName;
    private boolean collect;
    private int courseId;
    private String desc;
    private String envelopePic;
    private int id;
    private String link;
    private String niceDate;
    private String origin;
    private String projectLink;
    private int superChapterId;
    private String superChapterName;
    private long publishTime;
    private String title;
    private int visible;
    private int zan;

    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

}
