package app.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageLinks {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String thumbnailUrl;

    @JsonGetter("thumbnail")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @JsonSetter("thumbnail")
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
