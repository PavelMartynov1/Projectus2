package com.tradinghub.projectus2.model.ItemDetails;

import com.tradinghub.projectus2.model.Item;
import com.tradinghub.projectus2.model.instagram.InstItem;

import javax.persistence.*;

@Entity
@Table(name="itemDetails")
public class ItemDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "url")
    private String url;
    @Column(name = "followers")
    private String followers;
    @Column(name = "header")
    private String header;
    @Column(name = "text")
    private String text;
    @OneToOne(mappedBy = "itemDetails")
    private InstItem item;

    public InstItem getItem() {
        return item;
    }

    public void setItem(InstItem item) {
        this.item = item;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
