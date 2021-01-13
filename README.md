# Designed

## Flow
![](images/flow.png)

## ER Diagram
![](images/er.png)

## Api Designed
### **API Permission**
|  Flow | URL  | Permistion   |  
|---|---|---|
|  Authentication | http://localhost:9999/Engine/authentication/login    | All   |  
|  Registration | http://localhost:9999/Engine/api/user/register    | All   |  
|  User | http://localhost:9999/Engine/api/user     | Authenticate   |  

### **Authentication**
HTTP Method : Post  
URL : http://localhost:9999/Engine/authentication/login  

Json Request Model
```json
{
  "password": "string",
  "username": "string"
}
```
Json Response
|  Code | Message  | Description   |  
|---|---|---|
| 200  | Generate Access successfully.  | Username or password correct.  |
```json
{
  "message": "Generate Access successfully.",
  "code": "200",
  "data": {
    "access_token": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYxMDU2ODgyNywiZXhwIjoxNjEwNTg2ODI2fQ.ieHIq5F0y2qkD37etaF0fxPdN8R7U9BPDTOoKT8P_54",
    "username": "admin"
  },
  "status": "success"
}
```
| 201  |  Invalid credentials. | Username or password incorrect.  |
```json
{
  "message": "User not found with username: admin2",
  "code": "500",
  "status": "error",
  "info": null
}
```
| 500  | Stack trace error  | Exception error  |

```json
{
  "message": "JSON parse error: Unexpected character ('}' (code 125)): was expecting double-quote to start field name; nested exception is com.fasterxml.jackson.core.JsonParseException: Unexpected character ('}' (code 125)): was expecting double-quote to start field name\n at [Source: (PushbackInputStream); line: 3, column: 2]",
  "code": "500",
  "status": "error",
  "info": null
}
```

### **Registration**
HTTP Method : Post  
URL : http://localhost:9999/Engine/api/user/register
Json Request Model
```json
{
  "address": "string",
  "email": "string",
  "firstName": "string",
  "lastName": "string",
  "password": "string",
  "phoneNumber": "string",
  "salary": 0,
  "username": "string"
}
```

Json Response
|  Code | Message  | Description   |  
|---|---|---|
| 200  | Register success.  | ลงทะเบียนสำเร็จ  |
```json
{
  "message": "Register success.",
  "code": "200",
  "data": {
    "createdBy": null,
    "updatedBy": null,
    "createdDate": "2021-01-13 20:39:22",
    "updatedDate": "2021-01-13 20:39:22",
    "username": "thongchai",
    "firstName": "ธงไชย",
    "lastName": "สิทธิเขตกรณ์",
    "address": "2295/10 ต.ในเมือง อ.เมือง จ.นครราชสีมา",
    "email": "thongchai.sh@gmail.com",
    "phoneNumber": "0980960604",
    "salary": 30000,
    "refCode": "202101140604",
    "memberType": {
      "createdBy": "thongchai",
      "updatedBy": "thongchai",
      "createdDate": "2021-01-13 17:00:00",
      "updatedDate": "2021-01-13 17:00:00",
      "name": "Silver",
      "code": "03"
    }
  },
  "status": "success"
}
```
| 201  |  Salary is lower than required. | จำนวนเงินเดือนน้อยกว่า 15,000.  |
```json
{
  "message": "Salary is lower than required.",
  "code": "201",
  "status": "error",
  "info": "Salary below 15000."
}
```
| 202  |  Information entered is incorrect. | ข้อมูลไม่ถูกต้อง เช่น (Username ถูกใช้แล้ว , เบอร์โทรศัพท์ไม่ถูกต้อง/ถูกใช้แล้ว)  |
```json
{
  "message": "Information entered is incorrect.",
  "code": "202",
  "status": "error",
  "info": "Username has already been used."
}
```
| 500  | Stack trace error  | Exception error  |
```json
{
  "message": "JSON parse error: Unexpected character ('}' (code 125)): was expecting double-quote to start field name; nested exception is com.fasterxml.jackson.core.JsonParseException: Unexpected character ('}' (code 125)): was expecting double-quote to start field name\n at [Source: (PushbackInputStream); line: 9, column: 2]",
  "code": "500",
  "status": "error",
  "info": null
}
```

### **User**
HTTP Method : Get  
URL : http://localhost:9999/Engine/api/user  
HTTP Header Authorization
Ex. Curl
```
curl -X GET "http://localhost:9999/Engine/api/user" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aG9uZ2NoYWkiLCJpYXQiOjE2MTA1NzExODgsImV4cCI6MTYxMDU4OTE4OH0._Z19yoaDfjyc-9wEzegS_L_dl3So94YPt5qUXeAcml8"
```
Json Response
```json
{
  "message": "success.",
  "code": "200",
  "data": {
    "createdBy": null,
    "updatedBy": null,
    "createdDate": "2021-01-13 20:39:22",
    "updatedDate": "2021-01-13 20:39:22",
    "username": "thongchai",
    "firstName": "ธงไชย",
    "lastName": "สิทธิเขตกรณ์",
    "address": "2295/10 ต.ในเมือง อ.เมือง จ.นครราชสีมา",
    "email": "thongchai.sh@gmail.com",
    "phoneNumber": "0980960604",
    "salary": 30000,
    "refCode": "202101140604",
    "memberType": {
      "createdBy": "thongchai",
      "updatedBy": "thongchai",
      "createdDate": "2021-01-13 17:00:00",
      "updatedDate": "2021-01-13 17:00:00",
      "name": "Silver",
      "code": "03"
    }
  },
  "status": "success"
}
```


# Description

# Installation
## Required
- Docker version 19.03.3 or greater
- Docker-compose version 1.25.4 or greater
- Port : 9999 for WebApi
- Port : 5432 for Postgresql database
```
docker-compose --version
docker-compose version 1.25.4, build 8d51620a
```
### Step1 : The run build script
เข้ามาที่ Folder โปรเจคหลังจาก Clone ลงมาแล้ว จากนั้นใช้คำสั่ง
```
./build.sh
```
![](images/docker1.jpg)
### Step2 : Test via browser
ทดสอบเข้า Service โดยผ่าน URL http://localhost:9999/Engine/swagger-ui.html จะพบหน้าจอนี้
![](images/install2.jpg)

# Usage
[คู่มือวิธีการใช้งาน](document/วิธีทดสอบการใช้งาน.pdf)




