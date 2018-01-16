package com.fred_w.demo.codercommunity.mvp.model.entity;

import java.io.Serializable;

/**
 * 动态信息 Entity
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2018-1-15
 * @update
 */
public class Active implements Serializable {

    private int id;
    private String portrait;
    private String author;
    private long authorid;
    private int catalog;
    private int appClient;
    private long objectId;
    private int objectType;
    private int objectCatalog;
    private String objectTitle;
    private String url;
    private String message;
    private String tweetImage;
    private int commentCount;
    private String pubDate;
    private ObjectReply objectReply;

    @Override
    public String toString() {
        return "Active{" +
                "id=" + id +
                ", portrait='" + portrait + '\'' +
                ", author='" + author + '\'' +
                ", authorid=" + authorid +
                ", catalog=" + catalog +
                ", appClient=" + appClient +
                ", objectId=" + objectId +
                ", objectType=" + objectType +
                ", objectCatalog=" + objectCatalog +
                ", objectTitle='" + objectTitle + '\'' +
                ", url='" + url + '\'' +
                ", message='" + message + '\'' +
                ", tweetImage='" + tweetImage + '\'' +
                ", commentCount=" + commentCount +
                ", pubDate='" + pubDate + '\'' +
                ", objectReply=" + objectReply +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public int getCatalog() {
        return catalog;
    }

    public void setCatalog(int catalog) {
        this.catalog = catalog;
    }

    public int getAppClient() {
        return appClient;
    }

    public void setAppClient(int appClient) {
        this.appClient = appClient;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public int getObjectCatalog() {
        return objectCatalog;
    }

    public void setObjectCatalog(int objectCatalog) {
        this.objectCatalog = objectCatalog;
    }

    public String getObjectTitle() {
        return objectTitle;
    }

    public void setObjectTitle(String objectTitle) {
        this.objectTitle = objectTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTweetImage() {
        return tweetImage;
    }

    public void setTweetImage(String tweetImage) {
        this.tweetImage = tweetImage;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public ObjectReply getObjectReply() {
        return objectReply;
    }

    public void setObjectReply(ObjectReply objectReply) {
        this.objectReply = objectReply;
    }

    class ObjectReply implements Serializable {
        private String objectName;
        private String objectBody;

        @Override
        public String toString() {
            return "ObjectReply{" +
                    "objectName='" + objectName + '\'' +
                    ", objectBody='" + objectBody + '\'' +
                    '}';
        }

        public String getObjectName() {
            return objectName;
        }

        public void setObjectName(String objectName) {
            this.objectName = objectName;
        }

        public String getObjectBody() {
            return objectBody;
        }

        public void setObjectBody(String objectBody) {
            this.objectBody = objectBody;
        }
    }

}
