@echo off
echo MSS301 Docker Management Script
echo ================================

:menu
echo.
echo Please choose an option:
echo 1. Start all services with database initialization
echo 2. Start services (normal)
echo 3. Stop all services
echo 4. View service logs
echo 5. Rebuild and start all services
echo 6. Check service status
echo 7. Reset and restart everything
echo 8. Exit
echo.

set /p choice="Enter your choice (1-8): "

if "%choice%"=="1" goto start_with_init
if "%choice%"=="2" goto start_normal
if "%choice%"=="3" goto stop_services
if "%choice%"=="4" goto view_logs
if "%choice%"=="5" goto rebuild_services
if "%choice%"=="6" goto check_status
if "%choice%"=="7" goto reset_all
if "%choice%"=="8" goto exit
echo Invalid choice! Please try again.
goto menu

:start_with_init
echo Starting services with database initialization...
docker-compose -f docker-compose-with-init.yml up -d
echo Services started! Check status with option 6.
goto menu

:start_normal
echo Starting services...
docker-compose up -d
echo Services started! Check status with option 6.
goto menu

:stop_services
echo Stopping all services...
docker-compose down
docker-compose -f docker-compose-with-init.yml down 2>nul
echo All services stopped.
goto menu

:view_logs
echo Available services: sqlserver, ms-account, ms-brand, ms-blindbox, api-gateway
set /p service="Enter service name to view logs: "
docker logs %service% --tail 50
goto menu

:rebuild_services
echo Rebuilding and starting all services...
docker-compose down
docker-compose up --build -d
echo Services rebuilt and started!
goto menu

:check_status
echo Checking service status...
echo.
echo Docker containers:
docker ps -a
echo.
echo Docker Compose services:
docker-compose ps 2>nul
echo.
goto menu

:reset_all
echo Resetting everything (WARNING: This will remove all data!)
set /p confirm="Are you sure? (y/N): "
if not "%confirm%"=="y" if not "%confirm%"=="Y" goto menu
docker-compose down -v
docker-compose -f docker-compose-with-init.yml down -v 2>nul
docker system prune -f
echo System reset complete. Use option 1 to start fresh.
goto menu

:exit
echo Goodbye!
exit /b 0
