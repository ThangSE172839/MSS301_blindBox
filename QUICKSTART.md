# Hướng dẫn nhanh - MSS301 Docker Setup

## TÓM TẮT NHANH

### 🛠️ KHẮC PHỤC LỖI TỰ ĐỘNG (Khuyến nghị)
```bash
.\fix-sql-connection.bat
```
**Script này sẽ tự động:**
- Kiểm tra Docker Desktop
- Dừng containers cũ
- Khởi động SQL Server trước
- Tạo databases tự động với db-init container
- Đợi SQL Server sẵn sàng
- Khởi động tất cả microservices theo đúng thứ tự

### 🚀 KHỞI ĐỘNG NHANH (Đơn giản hơn)
```bash
.\quick-start.bat
```

### 🚀 KHỞI ĐỘNG NHANH (Tất cả cùng lúc)
```bash
.\build-all.bat
docker-compose up --build
```

### 🔧 KHỞI ĐỘNG TỪNG BƯỚC (Tránh lỗi SQL connection)

#### Bước 1: Khởi động Docker Desktop
- Mở Docker Desktop từ Start Menu
- Đợi Docker sẵn sàng (biểu tượng xanh)

#### Bước 2: Build tất cả services
```bash
.\build-all.bat
```

#### Bước 3: Khởi động SQL Server trước
```bash
# Khởi động chỉ SQL Server
docker-compose up sqlserver -d

# Đợi SQL Server sẵn sàng (quan trọng!)
docker-compose logs -f sqlserver
```
**⚠️ Chờ đến khi thấy:** `SQL Server is now ready for client connections`

#### Bước 4: Khởi động các microservices
```bash
# Khởi động tất cả services còn lại
docker-compose up -d --build

# Xem logs để kiểm tra
docker-compose logs -f
```

### 2. Kiểm tra services hoạt động
- **API Gateway**: http://localhost:8080
- **MS Account Swagger**: http://localhost:8081/swagger-ui.html  
- **MS Brand Swagger**: http://localhost:8082/swagger-ui.html
- **MS BlindBox Swagger**: http://localhost:8083/swagger-ui.html

### 4. Xem logs
```bash
docker-compose logs -f
```

### 5. Dừng services
```bash
docker-compose down
```

## SCRIPT QUẢN LÝ
Sử dụng script `manage.bat` để quản lý dễ dàng:

```bash
# Xem hướng dẫn
.\manage.bat help

# Build tất cả
.\manage.bat build

# Khởi động
.\manage.bat start

# Xem status
.\manage.bat status

# Xem logs
.\manage.bat logs

# Dừng
.\manage.bat stop

# Dọn dẹp tất cả
.\manage.bat clean
```

## LỖI THƯỜNG GẶP VÀ CÁCH KHẮC PHỤC

### 🔴 SQL Server Connection Failed (Lỗi TCP/IP connection)

**Triệu chứng:**
```
com.microsoft.sqlserver.jdbc.SQLServerException: The TCP/IP connection to the host localhost, port 1433 has failed
```

**Các bước khắc phục theo thứ tự:**

#### 1. Kiểm tra Docker Desktop đang chạy
```bash
# Kiểm tra Docker hoạt động
docker ps

# Nếu lỗi "pipe/dockerDesktopLinuxEngine", khởi động Docker Desktop từ Start Menu
# Hoặc chạy lệnh (cần quyền admin):
net start com.docker.service
```

#### 2. Khởi động SQL Server trước
```bash
# Khởi động chỉ SQL Server
docker-compose up sqlserver -d

# Đợi SQL Server sẵn sàng (30-60 giây)
docker-compose logs -f sqlserver
```

**Chờ đến khi thấy dòng:** `SQL Server is now ready for client connections`

#### 3. Kiểm tra SQL Server health
```bash
# Kiểm tra container SQL Server
docker-compose ps sqlserver

# Test kết nối SQL
docker exec mss301-sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P MyStrong123Pass -Q "SELECT @@VERSION"
```

#### 4. Khởi động tất cả services
```bash
# Sau khi SQL Server healthy, khởi động tất cả
docker-compose up -d --build

# Xem logs để đảm bảo kết nối thành công
docker-compose logs -f ms-account ms-brand ms-blindbox
```

### 🔴 Port đã được sử dụng
```bash
# Kiểm tra port đang được sử dụng
netstat -ano | findstr :8080
netstat -ano | findstr :1433

# Dừng process đang dùng port (thay <PID>)
taskkill /PID <PID> /F

# Hoặc thay đổi port trong docker-compose.yml
```

### 🔴 Build lỗi
- **Java**: Đảm bảo Java 11+ đã cài đặt (`java -version`)
- **Maven**: Kiểm tra kết nối internet
- **Docker**: Đảm bảo Docker Desktop đang chạy

```bash
# Build lại toàn bộ
.\build-all.bat

# Nếu lỗi dependency, clear cache
docker system prune -f
.\manage.bat clean
.\manage.bat build
```

### 🔴 Container khởi động không thành công
```bash
# Xem chi tiết lỗi
docker-compose logs <service-name>

# Ví dụ:
docker-compose logs ms-account
docker-compose logs sqlserver

# Restart service cụ thể
docker-compose restart <service-name>
```

## ENDPOINTS API

### Qua API Gateway (port 8080):
- **Auth**: `http://localhost:8080/api/auth/**`
- **Brands**: `http://localhost:8080/api/brands/**` 
- **BlindBoxes**: `http://localhost:8080/api/blindboxes/**`

### Direct access:
- **MS Account**: `http://localhost:8081/api/auth/**`
- **MS Brand**: `http://localhost:8082/api/brands/**`
- **MS BlindBox**: `http://localhost:8083/api/blindboxes/**`
