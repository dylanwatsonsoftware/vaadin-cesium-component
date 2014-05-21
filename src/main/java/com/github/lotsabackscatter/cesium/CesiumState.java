package com.github.lotsabackscatter.cesium;

import com.vaadin.shared.ui.JavaScriptComponentState;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the state of a Cesium globe.
 *
 * @author watsond
 */
public class CesiumState extends JavaScriptComponentState {
    public Map<String, Billboard> billboards = new HashMap<String, Billboard>();
}
