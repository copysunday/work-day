# work-day

An application of working time recording based on spring boot.

## What this repo IS

This project is based on java8, spring-boot, mysql8, and mybatis. 
The mybatis file is generated using the mybatis-generator plug-in. 
The api documentation is built using [smart-doc](https://github.com/shalousun/smart-doc), 
and the project release uses [daocloud](https://dashboard.daocloud.io) to build images and automatically publish applications.

# The frontend repo

[The wechat mini app frontend repo](https://github.com/copysunday/work-day-frontend)

# Usage
first, set the env:
```
export MYSQL_DATA_URL=jdbc:mysql://127.0.0.1:3306/work_day
export MYSQL_USERNAME=root
export MYSQL_PASSWORD=12345678
export WX_APPSCERET=***
export WX_APPID=***
```
To build the app run:

    mvn clean package -Dmaven.test.skip=true

To run the mybatis-generator run:

    mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

To build doc see:

    com.sunday.wkday.doc.ApiDocTest#testBuilderControllersApi()

To build Docker image:

    docker build -t wkday-demo . 
    
To start:

    docker run -d -p 8080:8080 wkday-demo

# The demo

<table>
    <tr>
        <td ><center><img src="https://github.com/copysunday/my-image/blob/master/qrcode.jpg"></center></td>
        <td ><center><img src="https://github.com/copysunday/my-image/blob/master/a1.jpg" ></center></td>
        <td ><center><img src="https://github.com/copysunday/my-image/blob/master/a2.jpg"></center></td>
    </tr>
    
    <tr>
        <td ><center><img src="https://github.com/copysunday/my-image/blob/master/a3.jpg"></center></td>
        <td ><center><img src="https://github.com/copysunday/my-image/blob/master/a4.jpg"></center></td>
        <td ><center></center></td>
    </tr>
</table>

# License

This repo is using the MIT LICENSE.

You can find it in the file [LICENSE](LICENSE)


