package com.immersion.cesium;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * A Cesium billboard.
 *
 * @author watsond
 */
public class Billboard {

    @Nonnull
    private final UUID id = UUID.randomUUID();
    private double latitude;
    private double longitude;
    private double height;
    @CheckForNull
    private String name;
    @Nonnull
    private String imageUrl;

    public Billboard(@CheckForNull String name, double latitude, double longitude, double height, @Nonnull String imageUrl) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.height = height;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Billboard(@CheckForNull String name, double latitude, double longitude, @Nonnull String imageUrl) {
        this(name, latitude, longitude, 0, imageUrl);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @CheckForNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nonnull
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Nonnull
    public UUID getId() {
        return id;
    }
}