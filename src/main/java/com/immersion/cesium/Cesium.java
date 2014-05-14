package com.immersion.cesium;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import org.json.JSONArray;
import org.json.JSONException;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A Vaadin Component representing a Cesium WebGL globe.
 *
 * @author watsond
 */
@JavaScript({"http://cesiumjs.org/Cesium/Build/Cesium/Cesium.js", "cesium_connector.js"})
@StyleSheet({"http://cesiumjs.org/Cesium/Build/Cesium/Widgets/CesiumWidget/CesiumWidget.css", "vaadin://addons/cesium/styles.css"})
@StyleSheet({"http://cesiumjs.org/Cesium/Build/Cesium/Widgets/CesiumWidget/CesiumWidget.css", "vaadin://addons/Cesium/styles.css"})
public class Cesium extends AbstractJavaScriptComponent {

    private static final long serialVersionUID = 1L;
    private Map<UUID, BillboardClickListener> listeners = new HashMap<UUID, BillboardClickListener>();

    /**
     * Cesium Constructor
     */
    public Cesium() {
        addFunction("onClick", new JavaScriptFunction() {

            private static final long serialVersionUID = 1L;

            /**
             * {@inheritDoc}
             */
            @Override
            public void call(JSONArray arguments) throws JSONException {
                UUID id = UUID.fromString(arguments.getString(0));

                final BillboardClickListener clickListener = listeners.get(id);
                if (clickListener != null) {
                    clickListener.onClick();
                }
            }
        });
    }

    /**
     * Adds a billboard to the globe.
     *
     * @param name          the name of the billboard
     * @param latitude      the latitude of the billboard
     * @param longitude     the longitude of the billboard
     * @param image         the url of the image representing the billboard
     * @param clickListener the listener that is fired when the billboard is clicked. It may be null.
     * @return the UUID of the added billboard
     */
    public UUID addBillboard(@CheckForNull String name, double latitude, double longitude, @Nonnull String image,
                             @CheckForNull BillboardClickListener clickListener) {
        Billboard billboard = new Billboard(name, latitude, longitude, image);
        return addBillboard(billboard, clickListener);
    }

    /**
     * Adds the billboard to the globe.
     *
     * @param billboard     the billboard
     * @param clickListener the listener that is fired when the billboard is clicked. It may be null.
     * @return the UUID of the added billboard
     */
    public UUID addBillboard(@Nonnull Billboard billboard, @Nonnull BillboardClickListener clickListener) {
        UUID id = billboard.getId();

        if (clickListener != null) {
            listeners.put(id, clickListener);
        }

        callFunction("addBillboard", id.toString(), billboard.getName(), billboard.getLatitude(), billboard.getLongitude(), billboard.getHeight(), billboard.getImageUrl());

        getState().billboards.put(id.toString(), billboard);

        return id;
    }

    /**
     * Adds a label to the globe.
     *
     * @param name      the name of the billboard
     * @param latitude  the latitude of the billboard
     * @param longitude the longitude of the billboard
     * @return the UUID of the added label
     */
    public UUID addLabel(@CheckForNull String name, double latitude, double longitude) {
        UUID id = UUID.randomUUID();

        callFunction("addLabel", id.toString(), name, latitude, longitude);

        return id;
    }

    /**
     * Fly to the current location of the user.
     */
    public void flyToMyLocation() {
        callFunction("flyToMyLocation");
    }

    /**
     * Fly to the given location.
     *
     * @param latitude
     * @param longitude
     */
    public void flyToPosition(double latitude, double longitude) {
        callFunction("flyToPosition", latitude, longitude);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected CesiumState getState() {
        return (CesiumState) super.getState();
    }
}
