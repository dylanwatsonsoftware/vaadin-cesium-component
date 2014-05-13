package com.immersion.cesium;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import org.json.JSONArray;
import org.json.JSONException;

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
     * @param latitude      the latitude of the billboard
     * @param longitude     the longitude of the billboard
     * @param image         the url of the image representing the billboard
     * @param clickListener listener that is fired when the billboard is clicked.
     */
    public void addBillboard(double latitude, double longitude, String image,
                             BillboardClickListener clickListener) {
        UUID id = UUID.randomUUID();
        if (clickListener != null) {
            listeners.put(id, clickListener);
        }

        callFunction("addBillboard", id.toString(), latitude, longitude, image);
    }

    @Override
    protected CesiumState getState() {
        return (CesiumState) super.getState();
    }
}
