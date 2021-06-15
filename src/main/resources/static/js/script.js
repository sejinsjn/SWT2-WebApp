class Baum{

    constructor(sensors){
        this.sensors = sensors;
    }

    printout(){
        console.log(this.sensors);
    }

    getValues(title){
        let senb = this.sensors.filter((sensor) => sensor.title === title);
        let senbH = new Array(), senbN = new Array(), senbL = new Array(), senbB = new Array(), senbT = new Array();

        senb.forEach((sensor) => senbH.push(this.pushVandT(sensor, 0)));
        senb.forEach((sensor) => senbN.push(this.pushVandT(sensor, 1)));
        senb.forEach((sensor) => senbL.push(this.pushVandT(sensor, 2)));
        senb.forEach((sensor) => senbB.push(this.pushVandT(sensor, 3)));
        senb.forEach((sensor) => senbT.push(this.pushVandT(sensor, 4)));

        return [senbH, senbN, senbL, senbB, senbT];
    }

    pushVandT(sensor, id){
        let senb = new Array();

        senb.push(sensor.sensors[id].timestamp);
        senb.push(sensor.sensors[id].value);

        return senb;
    }

    createChart(title, sensorname, id){
        const chart = Highcharts.chart('container' + id, {
            chart: {
                type: 'line'
            },
            title: {
                text: title + ' ' + sensorname
            },
            xAxis: {
                  title: {
                    text: 'Date'
                  },
                  type: 'datetime',
                  labels: {
                      format: '{value:%e-%b-%y}'
                  },
            },
            yAxis: {
                title: {
                    text: 'Value'
                }
            },
            series: [{
                name: title,
                data: this.getValues(title)[id]
            }]
        });
    }

    createChartsForBaum(title){
        this.createChart(title, "Helligkeit", 0);
        this.createChart(title, "Niederschlag", 1);
        this.createChart(title, "Luftfeuchtigkeit", 2);
        this.createChart(title, "Bodenfeuchtigkeit", 3);
        this.createChart(title, "Temperatur", 4);
    }
}

class Map{
    createMap(geokoMb000, geokoMb001, geokoMb002){
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

        let markerb000 = L.marker(geokoMb000, {icon: treeIcon}).addTo(mymap).bindPopup('Baum 000');
        let markerb001 = L.marker(geokoMb001, {icon: treeIcon}).addTo(mymap).bindPopup('Baum 001');
        let markerb002 = L.marker(geokoMb002, {icon: treeIcon}).addTo(mymap).bindPopup('Baum 002');

        marker.push(markerb000);
        marker.push(markerb001);
        marker.push(markerb002);

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
        });
        return marker;
    }

    setOnClickForMarker(baum, title, marker){
        marker.on('click',() => {
            baumTitle = title;
            baum.createChartsForBaum(title);
            $('html,body').animate({scrollTop: $("#graphs").offset().top},'fast');
        });
    }

    setOnClickForAllMarker(baum, marker){
        this.setOnClickForMarker(baum, 'Baum-000', marker[0]);
        this.setOnClickForMarker(baum, 'Baum-001', marker[1]);
        this.setOnClickForMarker(baum, 'Baum-002', marker[2]);
    }
}