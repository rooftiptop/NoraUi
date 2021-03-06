/**
 * NoraUi is licensed under the license GNU AFFERO GENERAL PUBLIC LICENSE
 * 
 * @author Nicolas HALLOUIN
 * @author Stéphane GRILLON
 */
package com.github.noraui.application.model.demo;

import com.github.noraui.annotation.Column;
import com.github.noraui.application.model.CommonModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Article extends CommonModel implements Comparable<Article> {

    /**
     * NoraUi id.
     */
    @Expose(serialize = false, deserialize = false)
    private Integer nid;

    @Expose
    @Column(name = "Title")
    private String title;

    @Expose
    @Column(name = "Text")
    private String text;

    @Expose
    @Column(name = "Author")
    private String author;

    @Expose
    @Column(name = "Note")
    private int note;

    // constructor by default for serialize/deserialize
    public Article() {
        this.nid = -1;
        this.title = "";
        this.text = "";
        this.author = "";
        this.note = -1;
    }

    public Article(String nid, String title, String text, String author, String note) {
        this.nid = Integer.parseInt(nid);
        this.title = title;
        this.text = text;
        this.author = author;
        this.note = (int) Double.parseDouble(note);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deserialize(String jsonString) {
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();
        Article w = gson.fromJson(jsonString, Article.class);
        this.nid = w.nid;
        this.title = w.title;
        this.text = w.text;
        this.author = w.author;
        this.note = w.note;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Articles> getModelList() {
        return Articles.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return 31 + (title == null ? 0 : title.hashCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Article other = (Article) obj;
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Article other) {
        return ((Integer) this.note).compareTo(other.note);
    }

    @Override
    public String toString() {
        return "{nid:" + nid + ", title:\"" + title + "\", text:\"" + text + "\", author:\"" + author + "\", note:\"" + note + "\"}";
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

}