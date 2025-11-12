@echo off
setlocal enabledelayedexpansion

if "%1"=="" goto usage
if "%1"=="help" goto usage

if "%1"=="build" goto build
if "%1"=="start" goto start
if "%1"=="stop" goto stop
if "%1"=="restart" goto restart
if "%1"=="logs" goto logs
if "%1"=="status" goto status
if "%1"=="clean" goto clean

goto usage

:usage
echo MSS301 Microservices Management Script
echo.
echo Usage: manage.bat [command]
echo.
echo Commands:
echo   build     - Build all services
echo   start     - Start all services with Docker Compose
echo   stop      - Stop all services
echo   restart   - Restart all services
echo   logs      - Show logs for all services
echo   status    - Show status of all services
echo   clean     - Stop and remove all containers and volumes
echo   help      - Show this help
echo.
echo Examples:
echo   manage.bat build
echo   manage.bat start
echo   manage.bat logs
goto end

:build
echo Building all microservices...
call build-all.bat
goto end

:start
echo Starting all services with Docker Compose...
docker-compose up -d --build
if %errorlevel% equ 0 (
    echo.
    echo Services started successfully!
    echo.
    echo Available endpoints:
    echo - API Gateway: http://localhost:8080
    echo - MS Account: http://localhost:8081/swagger-ui.html
    echo - MS Brand: http://localhost:8082/swagger-ui.html
    echo - MS BlindBox: http://localhost:8083/swagger-ui.html
    echo.
    echo Run 'manage.bat logs' to see logs
    echo Run 'manage.bat status' to check status
) else (
    echo Failed to start services
    exit /b 1
)
goto end

:stop
echo Stopping all services...
docker-compose down
echo Services stopped
goto end

:restart
echo Restarting all services...
docker-compose down
docker-compose up -d --build
echo Services restarted
goto end

:logs
if "%2"=="" (
    echo Showing logs for all services...
    docker-compose logs -f
) else (
    echo Showing logs for %2...
    docker-compose logs -f %2
)
goto end

:status
echo Service Status:
docker-compose ps
goto end

:clean
echo Stopping and cleaning up all services and data...
docker-compose down -v
docker system prune -f
echo Cleanup completed
goto end

:end
endlocal
