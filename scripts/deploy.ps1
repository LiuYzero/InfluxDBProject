$microservicename="iot-influxdb_api"

cd ..

ssh paas@192.168.1.102 "mkdir -p /home/paas/microservices/$microservicename/"

scp .\target\*.jar paas@192.168.1.102:/home/paas/microservices/$microservicename/
scp .\scripts\* paas@192.168.1.102:/home/paas/microservices/$microservicename/

ssh paas@192.168.1.102 "cd /home/paas/microservices/$microservicename;chmod u+x *.sh;bash ./restart.sh"

cd scripts