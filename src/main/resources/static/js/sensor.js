class Sensor{

    constructor(sensors){
        this.sensors = sensors;
    }

    printout(){
        console.log(this.sensors);
    }

    getValues(sen, title){
        let senb = sen.filter((sensor) => sensor.title === title);

        let sensoritem = senb[0].sensors;
        let senbH = new Array(), senbN = new Array(), senbL = new Array(), senbB = new Array(), senbT = new Array();

        senb.forEach((sensor) => senbH.push(sensor.sensors[0].value));
        senb.forEach((sensor) => senbN.push(sensor.sensors[1].value));
        senb.forEach((sensor) => senbL.push(sensor.sensors[2].value));
        senb.forEach((sensor) => senbB.push(sensor.sensors[3].value));
        senb.forEach((sensor) => senbT.push(sensor.sensors[4].value));

        return [senbH, senbN, senbL, senbB, senbT];
    }
}