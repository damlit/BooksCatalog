package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfo {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String subtitle;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String publisher;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String publishedDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageCount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ImageLinks imageLinks;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String language;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String previewLink;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double averageRating;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] authors;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> categories;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<IndustryIdentifiers> industryIdentifiersList;

    @ConfigurationProperties("volumeInfo.title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ConfigurationProperties("volumeInfo.subtitle")
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @ConfigurationProperties("volumeInfo.publisher")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @ConfigurationProperties("volumeInfo.publishedDate")
    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @ConfigurationProperties("volumeInfo.description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ConfigurationProperties("volumeInfo.pageCount")
    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @ConfigurationProperties("volumeInfo.imageLinks.thumbnail")
    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    @ConfigurationProperties("volumeInfo.language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @ConfigurationProperties("volumeInfo.previewLink")
    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    @ConfigurationProperties("volumeInfo.averageRating")
    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    @ConfigurationProperties("volumeInfo.authors")
    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    @ConfigurationProperties("volumeInfo.categories")
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @ConfigurationProperties("volumeInfo.industryIdentifiers")
    public List<IndustryIdentifiers> getIndustryIdentifiers() {
        return industryIdentifiersList;
    }

    public void setIndustryIdentifiers(List<IndustryIdentifiers> industryIdentifiersList) {
        this.industryIdentifiersList = industryIdentifiersList;
    }

    public List<String> getAuthorsInList() {
        if (authors != null) {
            return Arrays.asList(authors);
        }
        return Collections.emptyList();
    }
}