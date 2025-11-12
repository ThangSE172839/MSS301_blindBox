@echo off
echo ====================================
echo      TẠO DATABASES TỰ ĐỘNG
echo ====================================

echo [1/3] Khởi động SQL Server...
docker-compose up sqlserver -d

echo [2/3] Đợi SQL Server sẵn sàng...
timeout /t 30

echo [3/3] Tạo databases...
docker exec -it mss301-sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P 12345 -Q "
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'MSS301Summer25DBAccount')
CREATE DATABASE MSS301Summer25DBAccount;

IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'MSS301Summer25DBBrand')
CREATE DATABASE MSS301Summer25DBBrand;

IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'MSS301Summer25DBBlindBox')
CREATE DATABASE MSS301Summer25DBBlindBox;

SELECT name FROM sys.databases WHERE name LIKE 'MSS301%%';
"

echo ✅ Databases đã được tạo!
echo Bây giờ có thể khởi động microservices...
pause
