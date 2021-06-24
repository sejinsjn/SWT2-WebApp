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

    createChart(){

        const chart = Highcharts.stockChart('container', {
            chart: { type: 'line' },
            title: { text: title },
            xAxis: { title: { text: 'Date' }, type: 'datetime', labels: { format: '{value:%e-%b-%y}' } },
            yAxis: [{ labels: { align: 'right', x: -3 }, title: { text: 'Value' }, height: '17%', lineWidth: 2, offset: 0, resize: { enabled: true } },
            { labels: { align: 'right', x: 0 }, title: { text: 'Value' }, top: '20%', height: '17%', lineWidth: 2, offset: 0, resize: { enabled: true } },
            { labels: { align: 'right', x: -3 }, title: { text: 'Value' }, top: '40%', height: '17%', lineWidth: 2, offset: 0, resize: { enabled: true } },
            { labels: { align: 'right', x: -3 }, title: { text: 'Value' }, top: '60%', height: '17%', lineWidth: 2, offset: 0, resize: { enabled: true } },
            { labels: { align: 'right', x: -3 }, title: { text: 'Value' }, top: '80%', height: '17%', lineWidth: 2, offset: 0, resize: { enabled: true } }],
            tooltip: { split: true },
            series: [ {  type: 'line', name: 'Helligkeit', data: this.getValues(baumTitle)[0] },
            { type: 'line', name: 'Niederschlag', data: this.getValues(baumTitle)[1], yAxis: 1 },
            { type: 'line', name: 'Luftfeuchtigkeit', data: this.getValues(baumTitle)[2], yAxis: 2 },
            { type: 'line', name: 'Bodenfeuchtigkeit', data: this.getValues(baumTitle)[3], yAxis: 3 },
            { type: 'line', name: 'Temperatur', data: this.getValues(baumTitle)[4], yAxis: 4 } ]
        });
    }

    updateChart(){
        if($('#container').highcharts() != null){
            $('#container').highcharts().update({
                title: { text: title },
                series: [ {  type: 'line', name: 'Helligkeit', data: this.getValues(baumTitle)[0] },
                { type: 'line', name: 'Niederschlag', data: this.getValues(baumTitle)[1], yAxis: 1 },
                { type: 'line', name: 'Luftfeuchtigkeit', data: this.getValues(baumTitle)[2], yAxis: 2 },
                { type: 'line', name: 'Bodenfeuchtigkeit', data: this.getValues(baumTitle)[3], yAxis: 3 },
                { type: 'line', name: 'Temperatur', data: this.getValues(baumTitle)[4], yAxis: 4 } ]
            });
        }
    }

    getContent() {
        //create url to request fragment
        let activeElement = document.activeElement;
        let url = "";
        if(markerClicked === false && activeElement.value != null){
            url = '/request?interval=' + activeElement.value;
        }else{
            url = '/request?interval=All';
        }


        let senb = this.sensors.filter((sensor) => sensor.title === baumTitle);
        //load fragment and replace content
        $('#scriptCharts').load(url);
        setTimeout(function(){
            if(display){
                document.getElementById("name").innerHTML = "Baum: " + bTitle;

                let date = new Date(senb[senb.length-1].sensors[4].timestamp);
                let months_arr = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
                let day = date.getDate(), month = months_arr[date.getMonth()], year = date.getFullYear(), hours = date.getHours(), minutes = "0" + date.getMinutes(), seconds = "0" + date.getSeconds();
                let formattedTime = day+'-'+month+'-'+year+' '+hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
                document.getElementById("timestamp").innerHTML = "Letzte Aktualisierung: " + formattedTime;

                document.getElementById("status").innerHTML = "Sensorstatus: " + senb[senb.length-1].metadaten.sensorstatus;
                $('#charts').show();
            }else{
                document.getElementById("charts").style.display = "none";
            }
        }, 500);
    }

    getGeoKo(){
        let senb000 = this.sensors.filter((sensor) => sensor.title === 'Baum-000');
        let senb001 = this.sensors.filter((sensor) => sensor.title === 'Baum-001');
        let senb002 = this.sensors.filter((sensor) => sensor.title === 'Baum-002');

       return [senb000[senb000.length - 1].metadaten.geokoordinaten, senb001[senb001.length - 1].metadaten.geokoordinaten, senb002[senb002.length - 1].metadaten.geokoordinaten];
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

        let markerb000 = L.marker(geokoMb000, {icon: treeIcon}).addTo(mymap).bindPopup('Stadtgarten');
        let markerb001 = L.marker(geokoMb001, {icon: treeIcon}).addTo(mymap).bindPopup('Grünanlage "Ostwall"');
        let markerb002 = L.marker(geokoMb002, {icon: treeIcon}).addTo(mymap).bindPopup('Park der Partnerstädte');

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

    setOnClickForMarker(baum, btitle, title, marker){
        marker.on('click',() => {
            baumTitle = btitle;
            bTitle = title;
            markerClicked = true;
            display = true;
            if(highchartsExists){
                $('html,body').animate({scrollTop: $("#charts").offset().top}, 'fast');
                setTimeout(function(){ baum.getContent(); }, 500);
            }else{
                baum.getContent();
                setTimeout(function(){ $('html,body').animate({scrollTop: $("#charts").offset().top}, 'fast'); }, 500);
            }
        });
    }

    setOnClickForAllMarker(baum, marker){
        this.setOnClickForMarker(baum, 'Baum-000', 'Stadtgarten', marker[0]);
        this.setOnClickForMarker(baum, 'Baum-001', 'Grünanlage "Ostwall"', marker[1]);
        this.setOnClickForMarker(baum, 'Baum-002', 'Park der Partnerstädte', marker[2]);
    }
}