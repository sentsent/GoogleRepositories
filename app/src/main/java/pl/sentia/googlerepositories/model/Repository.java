package pl.sentia.googlerepositories.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class Repository {

    @SerializedName("name")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("watchers_count")
    public Long watchersCount;

    @SerializedName("created_at")
    public Date createDate;

    @SerializedName("updated_at")
    public Date updateDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(Long watchersCount) {
        this.watchersCount = watchersCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getFormatedUpdateDate() {
        return DateFormatUtils.format(updateDate, "yyyy-MM-dd HH:mm:SS");
    }

    public String getFormatedCreateDate() {
        return DateFormatUtils.format(createDate, "yyyy-MM-dd HH:mm:SS");
    }
}
