@echo off
echo Building all microservices...

echo Building MSAccount...
cd MSAccount
call .\mvnw.cmd clean package -DskipTests
if %errorlevel% neq 0 (
    echo Failed to build MSAccount
    exit /b 1
)
cd ..

echo Building MSBrand...
cd MSBrand
call .\mvnw.cmd clean package -DskipTests
if %errorlevel% neq 0 (
    echo Failed to build MSBrand
    exit /b 1
)
cd ..

echo Building MSBlindBox...
cd MSBlindBox
call .\mvnw.cmd clean package -DskipTests
if %errorlevel% neq 0 (
    echo Failed to build MSBlindBox
    exit /b 1
)
cd ..

echo Building APIGateway...
cd APIGateway
call .\mvnw.cmd clean package -DskipTests
if %errorlevel% neq 0 (
    echo Failed to build APIGateway
    exit /b 1
)
cd ..

echo All services built successfully!
echo You can now run: docker-compose up --build
