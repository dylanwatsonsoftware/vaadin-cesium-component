Vaadin Cesium Component
=======================

Add the [Cesium][1] WebGL Virtual Globe and Map Engine to your [Vaadin 7][2] Application.

[![Build Status](https://travis-ci.org/lotsabackscatter/vaadin-cesium-component.svg?branch=master)](https://travis-ci.org/lotsabackscatter/vaadin-cesium-component)

Download
--------

Download the latest JAR or grab via the Sonatype Maven Repository:
```xml
<repositories>
    <repository>
        <id>oss-sonatype</id>
        <name>oss-sonatype</name>
        <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>

...

<dependency>
  <groupId>com.immersion</groupId>
  <artifactId>vaadin-cesium-component</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

Usage
--------

Simply create a layout and add the Cesium component to it!
```java
VerticalLayout layout = new VerticalLayout();

Cesium cesium = new Cesium();
layout.addComponent(cesium);
```

You can even add billboards to the globe:
```java
Cesium cesium = new Cesium();
cesium.addBillboard(-75.0, 40.0, "banana.jpg");
```

And respond to click events, on the billboards:
```java
Cesium cesium = new Cesium();

BillboardClickListener listener = new BillboardClickListener() {

    @Override
    public void onClick() {
        Notification.show("Bananas", Notification.Type.TRAY_NOTIFICATION);
    }
};

cesium.addBillboard(-75.0, 40.0, "banana.jpg", listener);
```

Possible other examples include:
```java
cesium.addLabel("Perth", -31.9522, 115.8589);
cesium.flyToMyLocation();
cesium.flyToPosition(-31.9522, 115.8589);
```

Developed By
--------

* Dylan Watson - <lotsabackscatter@gmail.com>
   
License
--------

    Copyright 2014 Dylan Watson.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

   
 [1]: http://cesiumjs.org/
 [2]: https://vaadin.com/home 
