class Sensor{

    constructor(sensors){
        this.sensors = sensors;
    }

    printout(){
        console.log(this.sensors);
    }

    getValues(sen, title){
        let senb = sen.filter((sensor) => sensor.title === title);
        let senbH = new Array(), senbN = new Array(), senbL = new Array(), senbB = new Array(), senbT = new Array();

        senb.forEach((sensor) => senbH.push(sensor.sensors[0].value));
        senb.forEach((sensor) => senbN.push(sensor.sensors[1].value));
        senb.forEach((sensor) => senbL.push(sensor.sensors[2].value));
        senb.forEach((sensor) => senbB.push(sensor.sensors[3].value));
        senb.forEach((sensor) => senbT.push(sensor.sensors[4].value));

        return [senbH, senbN, senbL, senbB, senbT];
    }

    createChart(sensor, title, sensorname, id){
        console.log('container' + id);
        const chart = Highcharts.chart('container' + id, {
            chart: {
                type: 'line'
            },
            title: {
                text: title + ' ' + sensorname
            },
            xAxis: {
                categories: ['time']
            },
            yAxis: {
                title: {
                    text: 'Value'
                }
            },
            series: [{
                name: title,
                data: sensor.getValues(sen, title)[id]
            }]
        });
    }
}

class Map{
    createMap(){
        let marker = new Array();

        let treeIcon = L.icon({
            iconUrl: 'images/tree.png',

            iconSize:     [60, 95], // size of the icon
            iconAnchor:   [30, 94], // point of the icon which will correspond to marker's location
            popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
        });

        let mymap = L.map('mapid').setView([51.5145, 7.464772], 14);
        L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
            attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
            maxZoom: 18,
            id: 'mapbox/streets-v11',
            tileSize: 512,
            zoomOffset: -1,
            accessToken: 'pk.eyJ1Ijoic2VqaW5zam4iLCJhIjoiY2twNnRrbnYwMTg1NjJwcjJ2cXc1Z2phciJ9.4VY4Kdvdx7Kdy5OAWOM4cg'
        }).addTo(mymap);

        let markerb000 = L.marker([51.510879, 7.464040], {icon: treeIcon}).addTo(mymap).bindPopup('Baum 000');
        let markerb001 = L.marker([51.512875, 7.471388], {icon: treeIcon}).addTo(mymap).bindPopup('Baum 001');
        let markerb002 = L.marker([51.514774, 7.454719], {icon: treeIcon}).addTo(mymap).bindPopup('Baum 002');

        marker.push(markerb000);
        marker.push(markerb001);
        marker.push(markerb002);

        $("#button").click( (e) => {
                    $('html,body').animate({
        scrollTop: $("#mapid").offset().top},
        'fast');});

       //disable default scroll
      mymap.scrollWheelZoom.disable();

      $("#mapid").bind('mousewheel DOMMouseScroll', (event) =>  {
        event.stopPropagation();
         if (event.ctrlKey == true) {
                 event.preventDefault();
             mymap.scrollWheelZoom.enable();
             setTimeout(function(){
                 mymap.scrollWheelZoom.disable();
             }, 1000);
         }
         else{
            //Add code to display "use ctrl to scroll" on map
         }
      });
      return marker;
    }

    createChartsForMarker(sensor, title, marker){
        marker.on('click',() => {
            sensor.createChart(sensor, title, "Helligkeit", 0);
            sensor.createChart(sensor, title, "Niederschlag", 1);
            sensor.createChart(sensor, title, "Luftfeuchtigkeit", 2);
            sensor.createChart(sensor, title, "Bodenfeuchtigkeit", 3);
            sensor.createChart(sensor, title, "Temperatur", 4);
        });
    }
}