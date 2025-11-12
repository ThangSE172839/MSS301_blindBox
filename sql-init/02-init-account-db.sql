-- Initialize Account Database Schema
USE MSS301Summer25DBAccount;
GO

-- Create Users table
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='users' AND xtype='U')
BEGIN
    CREATE TABLE users (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        username NVARCHAR(50) NOT NULL UNIQUE,
        email NVARCHAR(100) NOT NULL UNIQUE,
        password NVARCHAR(255) NOT NULL,
        first_name NVARCHAR(50),
        last_name NVARCHAR(50),
        phone NVARCHAR(20),
        address NVARCHAR(255),
        role NVARCHAR(20) NOT NULL DEFAULT 'USER',
        is_active BIT NOT NULL DEFAULT 1,
        created_at DATETIME2 NOT NULL DEFAULT GETDATE(),
        updated_at DATETIME2 NOT NULL DEFAULT GETDATE()
    );
END
GO

-- Create roles table if needed
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='roles' AND xtype='U')
BEGIN
    CREATE TABLE roles (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        name NVARCHAR(50) NOT NULL UNIQUE,
        description NVARCHAR(255)
    );
END
GO

-- Insert default roles
IF NOT EXISTS (SELECT * FROM roles WHERE name = 'ADMIN')
BEGIN
    INSERT INTO roles (name, description) VALUES ('ADMIN', 'Administrator role');
    INSERT INTO roles (name, description) VALUES ('USER', 'Regular user role');
END
GO

-- Create default admin user (password: admin123)
IF NOT EXISTS (SELECT * FROM users WHERE username = 'admin')
BEGIN
    INSERT INTO users (username, email, password, first_name, last_name, role)
    VALUES ('admin', 'admin@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Admin', 'User', 'ADMIN');
END
GO

PRINT 'Account database initialized successfully!';
