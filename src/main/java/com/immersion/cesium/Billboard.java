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
    @CheckForNull
    private String name;
    @Nonnull
    private String imageUrl;

    public Billboard(@CheckForNull String name, double latitude, double longitude, @Nonnull String imageUrl) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.imageUrl = imageUrl;
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

    @Nonnull
    public UUID getId() {
        return id;
    }
}