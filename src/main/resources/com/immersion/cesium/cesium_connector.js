 window.com_immersion_cesium_Cesium = function() {
     var SCALE = 0.05;
     var widget = new Cesium.CesiumWidget(this.getElement());
     var scene = widget.scene;
     var ellipsoid = scene.globe.ellipsoid;
     var that = this;

     this.getElement().setAttribute("class", "fullSize");

     this.addBillboard = function(id, name, lat, lon, height, imageSrc) {
             var image = new Image();
             image.onload = function () {
                 var billboards = new Cesium.BillboardCollection();
                 var textureAtlas = scene.createTextureAtlas({
                     image: image
                 });
                 billboards.textureAtlas = textureAtlas;

                 var b = billboards.add({
                     position: ellipsoid.cartographicToCartesian(Cesium.Cartographic.fromDegrees(lon, lat, height)),
                     imageIndex: 0,
                     pixelOffsetScaleByDistance : new Cesium.NearFarScalar(1.5e2, 0.5, 1.5e8, 0.0)
                 });

                 if(name) {
                     var labels = new Cesium.LabelCollection();
                     labels.add({
                         position : ellipsoid.cartographicToCartesian(Cesium.Cartographic.fromDegrees(lon, lat)),
                         text     : name,
                         font : '14px sans-serif',
                         horizontalOrigin : Cesium.HorizontalOrigin.CENTER,
                         pixelOffset : new Cesium.Cartesian2(0.0, image.height),
                         pixelOffsetScaleByDistance : new Cesium.NearFarScalar(1.5e2, 1.0, 1.5e8, 0.0)
                     });
                     scene.primitives.add(labels);
                 }

                 b.id = id;
                 b.name = name;
                 b.imageSrc = imageSrc;

                 scene.primitives.add(billboards);
             };
             image.crossOrigin = '';
             image.src = imageSrc;
         };

     this.flyToPosition = function(lat, lon) {
             var destination = Cesium.Cartographic.fromDegrees(lon, lat, 15000.0);

             var flight = Cesium.CameraFlightPath.createAnimationCartographic(scene, {
                 destination : destination
             });
             scene.animations.add(flight);
         };

     this.flyToMyLocation = function() {
             function fly(position) {
                 var destination = Cesium.Cartographic.fromDegrees(position.coords.longitude, position.coords.latitude, 1000.0);
                 destination = ellipsoid.cartographicToCartesian(destination);

                 var flight = Cesium.CameraFlightPath.createAnimation(scene, {
                     destination : destination
                 });
                 scene.animations.add(flight);
             }

             navigator.geolocation.getCurrentPosition(fly);
         };

     this.addLabel = function (id, name, lat, lon) {
                  var labels = new Cesium.LabelCollection();
                  var label = labels.add({
                      position : ellipsoid.cartographicToCartesian(Cesium.Cartographic.fromDegrees(lon, lat)),
                      text     : name,
                      translucencyByDistance : new Cesium.NearFarScalar(1.5e2, 1.0, 1.5e8, 0.0)
                  });

                  label.id = id;

                  scene.primitives.add(labels);
              };

    /* Add mouse handlers */
     var lastPickedBoard = null;

     handler = new Cesium.ScreenSpaceEventHandler(scene.canvas);
     handler.setInputAction( function (movement) {
         var pickedObject = scene.pick(movement.endPosition);

         if (lastPickedBoard && (!pickedObject || pickedObject.primitive != lastPickedBoard)) {
             lastPickedBoard.scale = SCALE;
         }

         if (pickedObject && Cesium.defined(pickedObject) && (pickedObject.primitive instanceof Cesium.Billboard)) {
             lastPickedBoard = pickedObject.primitive;
             lastPickedBoard.scale = SCALE * 1.4;
         }
     }, Cesium.ScreenSpaceEventType.MOUSE_MOVE);

     handler.setInputAction( function (movement) {
         var pickedObject = scene.pick(movement.position);

         if (pickedObject && Cesium.defined(pickedObject) && (pickedObject.primitive instanceof Cesium.Billboard)) {
         	that.onClick(pickedObject.primitive.id);
         }
     }, Cesium.ScreenSpaceEventType.LEFT_CLICK);
 };