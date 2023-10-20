# API DOCUMENTATION
API documentation of PPDB ONLINE APPLICATION

## Endpoints
### Base url http://localhost:8080/api/v1/

### AUTHENTICATION
- [POST /auth/register](#Registering new data user)
- [POST /auth/login](#Logging in)
- [POST /auth/refresh-token](#Refresh token)
- [GET /auth/findByUsername?username=""](#Find user by username)
---
[POST /auth/register]()

Membuat data baru dari pendaftaran pengguna

**Parameter**
- NO PARAM REQUIRED

**Request**
```json
{
  "username": "AdminTKJ",
  "name": "Admin TKJ Yatindo",
  "email": "AdminTKJ@gmail.com",
  "password": "12345",
  "role": "ADMIN"
}
```
---

[POST /auth/login]()

Masuk ke halaman utama menggunakan otentikasi

**Parameter**
- NO PARAM REQUIRED

**Request**
```json
{
  "username": "admin",
  "password": "1234"
}
```

**Response**
```json
{
  "statusCode": 202,
  "timestamps": "20 October 2023 10:49",
  "payload": {
    "role": "ADMIN",
    "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5Nzc3Mzc1MSwiZXhwIjoxNjk3ODYwMTUxfQ.BEOBlFsGHCBF90_ASLvX0jIZpOpwX-o0-uNDiKjyCkU",
    "refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5Nzc3Mzc1MSwiZXhwIjoxNjk4Mzc4NTUxfQ.U9PrL4lb6PFKmWStvXchw0kw6X7KuVj_fNnlEPCwck4"
  }
}
```
---
[GET /auth/findByUsername?username=""]()

Mengambil data user berdasarnam username untuk keperluan login di Front End

**Parameterr**
- username : String

**Request**
```json
"NO REQUEST REQUIRED"
```

**Response**
```json
{
  "id": 1,
  "username": "admin",
  "email": "Admin@gmail.com",
  "role": "ADMIN",
  "joinAt": "18 October 2023 17:34"
}
```
---
