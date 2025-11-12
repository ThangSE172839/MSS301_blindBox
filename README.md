# MSS301 Microservices với Docker

## Tổng quan
Project này bao gồm 4 microservices:
- **API Gateway** (Port 8080) - Điều hướng request
- **MS Account** (Port 8081) - Quản lý tài khoản
- **MS Brand** (Port 8082) - Quản lý thương hiệu
- **MS BlindBox** (Port 8083) - Quản lý blind box

## Yêu cầu hệ thống
- Docker Desktop
- Docker Compose
- Maven (để build)
- Java 11+

## Cách chạy với Docker

### Bước 1: Build tất cả services
```bash
# Chạy script build
build-all.bat

# Hoặc build từng service riêng lẻ:
cd MSAccount && .\mvnw.cmd clean package -DskipTests && cd ..
cd MSBrand && .\mvnw.cmd clean package -DskipTests && cd ..
cd MSBlindBox && .\mvnw.cmd clean package -DskipTests && cd ..
cd APIGateway && .\mvnw.cmd clean package -DskipTests && cd ..
```

### Bước 2: Khởi động tất cả services với Docker Compose
```bash
# Khởi động tất cả services
docker-compose up --build

# Chạy ở background
docker-compose up -d --build

# Xem logs
docker-compose logs -f

# Xem logs của service cụ thể
docker-compose logs -f ms-account
```

### Bước 3: Kiểm tra services
- **API Gateway**: http://localhost:8080
- **MS Account**: http://localhost:8081/swagger-ui.html
- **MS Brand**: http://localhost:8082/swagger-ui.html
- **MS BlindBox**: http://localhost:8083/swagger-ui.html

## Quản lý Docker

### Dừng services
```bash
docker-compose down
```

### Dừng và xóa volumes (database data)
```bash
docker-compose down -v
```

### Rebuild một service cụ thể
```bash
# Rebuild ms-account
docker-compose build ms-account
docker-compose up -d ms-account
```

### Xem status
```bash
docker-compose ps
```

## Cấu hình Database
- **Database Engine**: SQL Server 2019 Express
- **Container**: mss301-sqlserver
- **Port**: 1433
- **Username**: sa
- **Password**: 12345
- **Databases**:
  - MSS301Summer25DBAccount
  - MSS301Summer25DBBrand
  - MSS301Summer25DBBlindBox

## Network Architecture
Tất cả services chạy trong cùng Docker network `mss301-network`:
- Services có thể giao tiếp với nhau qua tên container
- API Gateway routing đến các services thông qua container names
- Database accessible qua `sqlserver:1433`

## Troubleshooting

### Service không kết nối được database
1. Kiểm tra SQL Server container đã chạy: `docker-compose ps`
2. Xem logs SQL Server: `docker-compose logs sqlserver`
3. Kiểm tra healthcheck: Đợi SQL Server sẵn sàng

### Port conflicts
Nếu có conflict ports, có thể thay đổi trong `docker-compose.yml`:
```yaml
ports:
  - "8081:8081"  # Thay đổi port đầu tiên nếu cần
```

### Build errors
1. Đảm bảo Java 11+ đã cài đặt
2. Chạy `.\mvnw.cmd clean` trước khi build
3. Kiểm tra internet connection để download dependencies
4. Sử dụng Maven wrapper `.\mvnw.cmd` thay vì `mvn` nếu Maven chưa cài đặt

### Memory issues
Nếu Docker thiếu memory, tăng memory limit trong Docker Desktop settings.

## Development

### Chạy local (không dùng Docker)
1. Start SQL Server locally hoặc dùng Docker:
   ```bash
   docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=12345" -p 1433:1433 mcr.microsoft.com/mssql/server:2019-latest
   ```

2. Chạy từng service với profile default:
   ```bash
   cd MSAccount && .\mvnw.cmd spring-boot:run
   cd MSBrand && .\mvnw.cmd spring-boot:run
   cd MSBlindBox && .\mvnw.cmd spring-boot:run
   cd APIGateway && .\mvnw.cmd spring-boot:run
   ```

### Docker profiles
- **default**: Kết nối localhost:1433
- **docker**: Kết nối sqlserver:1433 (container name)

## API Endpoints

### Qua API Gateway (Port 8080)
- **Account**: `/api/auth/**` → MS Account (8081)
- **Brand**: `/api/brands/**` → MS Brand (8082)
- **BlindBox**: `/api/blindboxes/**` → MS BlindBox (8083)

### Direct access
- **MS Account Swagger**: http://localhost:8081/swagger-ui.html
- **MS Brand Swagger**: http://localhost:8082/swagger-ui.html
- **MS BlindBox Swagger**: http://localhost:8083/swagger-ui.html
