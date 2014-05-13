 window.com_immersion_cesium_Cesium = function() {
     var widget = new Cesium.CesiumWidget(this.getElement());
     var scene = widget.scene;
     var self = this;

     this.getElement().setAttribute("class", "fullSize");

     this.addBillboard = function(id, lat, lon, imageSrc) {
         var ellipsoid = scene.globe.ellipsoid;
         var image = new Image();
         image.onload = function () {
             var billboards = new Cesium.BillboardCollection();
             var textureAtlas = scene.createTextureAtlas({
                 image: image
             });
             billboards.textureAtlas = textureAtlas;

             var b = billboards.add({
                 position: ellipsoid.cartographicToCartesian(Cesium.Cartographic.fromDegrees(lat, lon)),
                 imageIndex: 0,
                 //scaleByDistance: new Cesium.NearFarScalar(1.5e2, 2.0, 1.5e7, 0.5)
             });

             b.imageSrc = imageSrc;
             //b.position = ellipsoid.cartographicToCartesian(Cesium.Cartographic.fromDegrees(-75.59777, 40.03883, 30.0));
             b.scale = 0.05;
             b.id = id;
             //b.color = new Cesium.Color(1.0, 1.0, 1.0, 0.25);

             scene.primitives.add(billboards);
         };
         image.crossOrigin = '';
         image.src = imageSrc;
     };

     var lastPickedBoard = null;

     handler = new Cesium.ScreenSpaceEventHandler(scene.canvas);
     handler.setInputAction( function (movement) {
         var pickedObject = scene.pick(movement.endPosition);

         if (lastPickedBoard && (!pickedObject || pickedObject.primitive != lastPickedBoard)) {
             lastPickedBoard.scale = 0.05;
         }

         if (pickedObject && Cesium.defined(pickedObject) && (pickedObject.primitive instanceof Cesium.Billboard)) {
             lastPickedBoard = pickedObject.primitive;
             lastPickedBoard.scale = 0.07;
         }
     }, Cesium.ScreenSpaceEventType.MOUSE_MOVE);

     handler.setInputAction( function (movement) {
         var pickedObject = scene.pick(movement.position);

         if (pickedObject && Cesium.defined(pickedObject) && (pickedObject.primitive instanceof Cesium.Billboard)) {
         	self.onClick(pickedObject.primitive.id);
         }
     }, Cesium.ScreenSpaceEventType.LEFT_CLICK);
 };