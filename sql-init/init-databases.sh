#!/bin/bash

# Wait for SQL Server to be ready
echo "Waiting for SQL Server to start..."
sleep 30

echo "Starting database initialization..."

# Run all initialization scripts in order
echo "Creating databases..."
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P $SA_PASSWORD -C -i /docker-entrypoint-initdb.d/01-create-databases.sql

echo "Initializing Account database..."
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P $SA_PASSWORD -C -i /docker-entrypoint-initdb.d/02-init-account-db.sql

echo "Initializing Brand database..."
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P $SA_PASSWORD -C -i /docker-entrypoint-initdb.d/03-init-brand-db.sql

echo "Initializing BlindBox database..."
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P $SA_PASSWORD -C -i /docker-entrypoint-initdb.d/04-init-blindbox-db.sql

echo "Database initialization completed!"
