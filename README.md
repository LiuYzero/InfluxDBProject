# InfluxDBProject

## directory explain

* InfluxDBDemo  SpringBoot Application with org.influxdb-influxdb-java for Use InfluxDB
  * one schedued example
  * one restcontroller example
* README.md 



## InfluxDB Note

```shell
# ubuntu22.04
# 安装
apt install influxdb influxdb-client

systemctl start influxd

vim /etc/influxdb/influxdb.conf
bind-address="127.0.0.1:8088"
[http]
enabled=true
bind-address=":8086"

systemctl restart influxd

or

 /usr/bin/influxd -config /etc/influxdb/influxdb.conf  # 手动启动，检查启动时输出的内容，一般用在 systectm start/restart influxd启动失败的情况
 

# 客户端使用
influx


# 用户管理
create user "username" with password 'password'
create user "username" with password 'password' with all privileges

grant all privileges on dbname to "username"
grant all privileges to "username"

remove read on dbname from "username"

show usernames

drop user "username"

# GUI工具  influxdb studio
```


