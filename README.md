# Maju Mundur API

Maju Mundur API adalah proyek backend berbasis Spring Boot yang menggunakan PostgreSQL sebagai database utama dan menyediakan autentikasi JWT.

## Assessment Aspects

✅ Clean Code

✅ REST request routing

✅ User authentication

✅ Business logic separation

✅ Database structure

## 🚀 Cara Menjalankan Proyek

### 1. Buat Database PostgreSQL
Sebelum menjalankan proyek, pastikan kamu sudah membuat database dengan nama `maju_mundur_db`. Ganti username dan password sesuai dengan konfigurasi user PostgreSQL yang tersedia.

```sql
CREATE DATABASE maju_mundur_db;
```

### 2. Konfigurasi Environment
Edit konfigurasi database di `application.properties` sesuai dengan kredensial yang anda gunakan:


### 3. Jalankan Proyek

#### Menggunakan Maven
```sh
mvn spring-boot:run
```

#### Menggunakan Gradle
```sh
./gradlew bootRun
```

## 📌 API Endpoint
Berikut beberapa endpoint utama yang tersedia:

| Endpoint | Deskripsi |
|----------|------------|
| `POST /api/v1/auth/register/merchant` | Registrasi merchant |
| `POST /api/v1/auth/register/customer` | Registrasi customer |
| `POST /api/v1/auth/login` | Login user |
| `POST /api/v1/auth/logout` | Logout user |
| `GET /api/v1/products/{id}` | Ambil produk berdasarkan ID |
| `POST /api/v1/transactions` | Buat transaksi baru |
| `GET /api/v1/reward` | Dapatkan daftar reward |
| `POST /api/v1/redeem` | Redeem reward |

### 🔹 Untuk Pengujian API
Gunakan **Postman** atau API testing tool lainnya dengan base URL:
```
http://localhost:8080
```

## 📖 Dokumentasi API (Swagger)
Swagger UI tersedia untuk eksplorasi API di:
```
http://localhost:8080/swagger-ui.html
```

---

Selamat mencoba! 🚀

