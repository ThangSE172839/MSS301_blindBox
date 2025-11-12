<<<<<<< HEAD
# MSS301 BlindBox Microservices Project

## Tổng quan
Project này bao gồm 4 microservices:
- **API Gateway** (Port 8088) - Điều hướng request
- **MS Account** (Port 8081) - Quản lý tài khoản và authentication
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
- **API Gateway**: http://localhost:8088
- **MS Account**: http://localhost:8081/swagger-ui.html
- **MS Brand**: http://localhost:8082/swagger-ui.html
- **MS BlindBox**: http://localhost:8083/swagger-ui.html

## API Endpoints

### Qua API Gateway (Port 8088)
- **Auth**: `POST /api/auth/login` → MS Account (8081)
- **Brand**: `/api/brands/**` → MS Brand (8082)  
- **BlindBox**: `/api/blind-boxes/**` → MS BlindBox (8083)

### Direct Access
- **MS Account**: http://localhost:8081/api/auth/**
- **MS Brand**: http://localhost:8082/api/brands/**
- **MS BlindBox**: http://localhost:8083/api/blind-boxes/**

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
- **Password**: MyPass12
- **Databases**:
  - MSS301Summer25DBAccount
  - MSS301Summer25DBBrand
  - MSS301Summer25DBBlindBox

## Features Implemented

### 1. MSAccount Service
- JWT Authentication
- User login endpoint
- Data initializer with sample accounts

### 2. MSBrand Service  
- Full CRUD operations for brands
- Filter by country
- Data initializer with POP MART, Funko, Kidrobot

### 3. MSBlindBox Service
- View all blind boxes with category names
- Filter by category and rarity
- Data initializer with sample blind boxes

### 4. API Gateway
- Routes requests to appropriate microservices
- CORS enabled
- Centralized access point

## Development

### Chạy local (không dùng Docker)
1. Start SQL Server locally hoặc dùng Docker:
   ```bash
   docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=MyPass12" -p 1433:1433 mcr.microsoft.com/mssql/server:2019-latest
   ```

2. Chạy từng service với profile default:
   ```bash
   cd MSAccount && .\mvnw.cmd spring-boot:run
   cd MSBrand && .\mvnw.cmd spring-boot:run  
   cd MSBlindBox && .\mvnw.cmd spring-boot:run
   cd APIGateway && .\mvnw.cmd spring-boot:run
   ```

## Troubleshooting

### Service không kết nối được database
1. Kiểm tra SQL Server container đã chạy: `docker-compose ps`
2. Xem logs SQL Server: `docker-compose logs sqlserver`
3. Kiểm tra healthcheck: Đợi SQL Server sẵn sàng

### Port conflicts  
Nếu có conflict ports, có thể thay đổi trong `docker-compose.yml`

### Build errors
1. Đảm bảo Java 11+ đã cài đặt
2. Chạy `.\mvnw.cmd clean` trước khi build
3. Kiểm tra internet connection để download dependencies
