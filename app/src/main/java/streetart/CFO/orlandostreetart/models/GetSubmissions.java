package streetart.CFO.orlandostreetart.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eric on 3/14/2019.
 */
public class GetSubmissions {

        @SerializedName("submissions")
        @Expose
        private List<Submission> submissions = null;
        @SerializedName("meta")
        @Expose
        private Meta meta;

    public List<Submission> getSubmissions() {
            return submissions;
        }

        public void setSubmissions(List<Submission> submissions) {
            this.submissions = submissions;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }

    public class Meta {

        @SerializedName("current_page")
        @Expose
        private Integer currentPage;
        @SerializedName("next_page")
        @Expose
        private Object nextPage;
        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("total_pages")
        @Expose
        private Integer totalPages;

        public Integer getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        public Object getNextPage() {
            return nextPage;
        }

        public void setNextPage(Object nextPage) {
            this.nextPage = nextPage;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

    }

    public static class Submission implements Parcelable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("photo_url")
        @Expose
        private String photoUrl;
        @SerializedName("thumb_url")
        @Expose
        private String thumbUrl;
        @SerializedName("tiny_url")
        @Expose
        private String tinyUrl;
        @SerializedName("artist")
        @Expose
        private String artist;
        @SerializedName("location_note")
        @Expose
        private String locationNote;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("favorite")
        @Expose
        private Boolean favorite;
        @SerializedName("latitude")
        @Expose
        private Double latitude;
        @SerializedName("longitude")
        @Expose
        private Double longitude;

        protected Submission(Parcel in) {
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            status = in.readString();
            title = in.readString();
            description = in.readString();
            photoUrl = in.readString();
            thumbUrl = in.readString();
            tinyUrl = in.readString();
            artist = in.readString();
            locationNote = in.readString();
            createdAt = in.readString();
            byte tmpFavorite = in.readByte();
            favorite = tmpFavorite == 0 ? null : tmpFavorite == 1;
            if (in.readByte() == 0) {
                latitude = null;
            } else {
                latitude = in.readDouble();
            }
            if (in.readByte() == 0) {
                longitude = null;
            } else {
                longitude = in.readDouble();
            }
        }

        public static final Creator<Submission> CREATOR = new Creator<Submission>() {
            @Override
            public Submission createFromParcel(Parcel in) {
                return new Submission(in);
            }

            @Override
            public Submission[] newArray(int size) {
                return new Submission[size];
            }
        };

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

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

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }

        public String getTinyUrl() {
            return tinyUrl;
        }

        public void setTinyUrl(String tinyUrl) {
            this.tinyUrl = tinyUrl;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getLocationNote() {
            return locationNote;
        }

        public void setLocationNote(String locationNote) {
            this.locationNote = locationNote;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Boolean getFavorite() {
            return favorite;
        }

        public void setFavorite(Boolean favorite) {
            this.favorite = favorite;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(id);
            }
            dest.writeString(status);
            dest.writeString(title);
            dest.writeString(description);
            dest.writeString(photoUrl);
            dest.writeString(thumbUrl);
            dest.writeString(tinyUrl);
            dest.writeString(artist);
            dest.writeString(locationNote);
            dest.writeString(createdAt);
            dest.writeByte((byte) (favorite == null ? 0 : favorite ? 1 : 2));
            if (latitude == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeDouble(latitude);
            }
            if (longitude == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeDouble(longitude);
            }
        }
    }
}
