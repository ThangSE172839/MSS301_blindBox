@echo off
echo ========================================
echo       KHAC PHUC LOI SQL CONNECTION
echo ========================================
echo.

echo [1/5] Kiem tra Docker Desktop...
docker --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Docker chua duoc cai dat!
    pause
    exit /b 1
)

docker ps >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Docker Desktop chua chay!
    echo 💡 Hay mo Docker Desktop tu Start Menu
    echo 💡 Hoac chay lenh: net start com.docker.service
    pause
    exit /b 1
)
echo ✅ Docker Desktop dang hoat dong

echo.
echo [2/5] Dung tat ca containers cu...
docker-compose down >nul 2>&1
echo ✅ Da dung containers cu

echo.
echo [3/5] Khoi dong SQL Server...
docker-compose up sqlserver -d
if %errorlevel% neq 0 (
    echo ❌ Khong the khoi dong SQL Server!
    pause
    exit /b 1
)

echo ✅ SQL Server dang khoi dong...
echo ⏳ Doi SQL Server san sang (30-60 giay)...

:wait_sql
timeout /t 5 /nobreak >nul
docker exec mss301-sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P 12345 -Q "SELECT 1" >nul 2>&1
if %errorlevel% neq 0 (
    echo ⏳ Van dang cho SQL Server...
    goto wait_sql
)

echo ✅ SQL Server da san sang!

echo.
echo [4/5] Khoi dong tat ca microservices...
docker-compose up -d --build
if %errorlevel% neq 0 (
    echo ❌ Loi khi khoi dong services!
    echo 💡 Kiem tra logs: docker-compose logs
    pause
    exit /b 1
)

echo ✅ Tat ca services da khoi dong

echo.
echo [5/5] Kiem tra trang thai...
timeout /t 10 /nobreak >nul
docker-compose ps

echo.
echo ========================================
echo            HOAN THANH!
echo ========================================
echo.
echo 🌐 API Gateway: http://localhost:8080
echo 📊 MS Account: http://localhost:8081/swagger-ui.html
echo 🏷️  MS Brand: http://localhost:8082/swagger-ui.html
echo 📦 MS BlindBox: http://localhost:8083/swagger-ui.html
echo.
echo 📋 Xem logs: docker-compose logs -f
echo 🛑 Dung services: docker-compose down
echo.
pause
