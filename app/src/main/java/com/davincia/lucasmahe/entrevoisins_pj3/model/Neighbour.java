package com.davincia.lucasmahe.entrevoisins_pj3.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model object representing a Neighbour
 */
public class Neighbour implements Parcelable {

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    private String address;

    private String phone;

    private String mail;

    private String description;

    private boolean isFavorite;


    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     * @param isFavorite
     */
    public Neighbour(Integer id, String name, String avatarUrl, String address, String phone, String mail, String description, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.description = description;
        this.isFavorite = isFavorite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.avatarUrl);
        dest.writeString(this.address);
        dest.writeString(this.phone);
        dest.writeString(this.mail);
        dest.writeString(this.description);
        dest.writeByte(this.isFavorite ? (byte) 1 : (byte) 0);
    }

    protected Neighbour(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.avatarUrl = in.readString();
        this.address = in.readString();
        this.phone = in.readString();
        this.mail = in.readString();
        this.description = in.readString();
        this.isFavorite = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Neighbour> CREATOR = new Parcelable.Creator<Neighbour>() {
        @Override
        public Neighbour createFromParcel(Parcel source) {
            return new Neighbour(source);
        }

        @Override
        public Neighbour[] newArray(int size) {
            return new Neighbour[size];
        }
    };
}
