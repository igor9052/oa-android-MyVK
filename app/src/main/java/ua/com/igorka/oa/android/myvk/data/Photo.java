package ua.com.igorka.oa.android.myvk.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Igor Kuzmenko on 04.03.2015.
 *
 */
public class Photo implements Parcelable {

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
    @SerializedName("pid")
    private int pid;
    @SerializedName("aid")
    private int aid;
    @SerializedName("owner_id")
    private int ownerId;
    @SerializedName("src")
    private String src;
    @SerializedName("src_big")
    private String srcBig;
    @SerializedName("src_small")
    private String srcSmall;
    @SerializedName("src_xbig")
    private String srcXBig;
    @SerializedName("src_xxbig")
    private String srcXxBig;
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;
    @SerializedName("text")
    private String text;
    @SerializedName("created")
    private String created;
    @SerializedName("post_id")
    private String postId;

    public Photo() {
    }

    private Photo(Parcel in) {
        this.pid = in.readInt();
        this.aid = in.readInt();
        this.ownerId = in.readInt();
        this.src = in.readString();
        this.srcBig = in.readString();
        this.srcSmall = in.readString();
        this.srcXBig = in.readString();
        this.srcXxBig = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
        this.text = in.readString();
        this.created = in.readString();
        this.postId = in.readString();
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrcBig() {
        return srcBig;
    }

    public void setSrcBig(String srcBig) {
        this.srcBig = srcBig;
    }

    public String getSrcSmall() {
        return srcSmall;
    }

    public void setSrcSmall(String srcSmall) {
        this.srcSmall = srcSmall;
    }

    public String getSrcXBig() {
        return srcXBig;
    }

    public void setSrcXBig(String srcXBig) {
        this.srcXBig = srcXBig;
    }

    public String getSrcXxBig() {
        return srcXxBig;
    }

    public void setSrcXxBig(String srcXxBig) {
        this.srcXxBig = srcXxBig;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "pid=" + pid +
                ", aid=" + aid +
                ", ownerId=" + ownerId +
                ", src='" + src + '\'' +
                ", srcBig='" + srcBig + '\'' +
                ", srcSmall='" + srcSmall + '\'' +
                ", srcXBig='" + srcXBig + '\'' +
                ", srcXxBig='" + srcXxBig + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", text='" + text + '\'' +
                ", created='" + created + '\'' +
                ", postId='" + postId + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pid);
        dest.writeInt(this.aid);
        dest.writeInt(this.ownerId);
        dest.writeString(this.src);
        dest.writeString(this.srcBig);
        dest.writeString(this.srcSmall);
        dest.writeString(this.srcXBig);
        dest.writeString(this.srcXxBig);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeString(this.text);
        dest.writeString(this.created);
        dest.writeString(this.postId);
    }
}
