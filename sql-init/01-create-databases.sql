-- Create databases for MSS301 microservices
USE master;
GO

-- Create Account Database
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'MSS301Summer25DBAccount')
BEGIN
    CREATE DATABASE MSS301Summer25DBAccount;
END
GO

-- Create Brand Database
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'MSS301Summer25DBBrand')
BEGIN
    CREATE DATABASE MSS301Summer25DBBrand;
END
GO

-- Create BlindBox Database
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'MSS301Summer25DBBlindBox')
BEGIN
    CREATE DATABASE MSS301Summer25DBBlindBox;
END
GO

PRINT 'All databases created successfully!';
