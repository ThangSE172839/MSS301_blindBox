-- Initialize Brand Database Schema
USE MSS301Summer25DBBrand;
GO

-- Create brands table
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='brands' AND xtype='U')
BEGIN
    CREATE TABLE brands (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        name NVARCHAR(100) NOT NULL,
        description NVARCHAR(500),
        logo_url NVARCHAR(255),
        website NVARCHAR(255),
        country NVARCHAR(50),
        established_year INT,
        is_active BIT NOT NULL DEFAULT 1,
        created_at DATETIME2 NOT NULL DEFAULT GETDATE(),
        updated_at DATETIME2 NOT NULL DEFAULT GETDATE()
    );
END
GO

-- Create brand_categories table
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='brand_categories' AND xtype='U')
BEGIN
    CREATE TABLE brand_categories (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        name NVARCHAR(100) NOT NULL,
        description NVARCHAR(255)
    );
END
GO

-- Insert sample brand categories
IF NOT EXISTS (SELECT * FROM brand_categories)
BEGIN
    INSERT INTO brand_categories (name, description) VALUES
    ('Anime', 'Anime and manga related brands'),
    ('Gaming', 'Video game related brands'),
    ('Movies', 'Movie and entertainment brands'),
    ('Characters', 'Character merchandise brands'),
    ('Collectibles', 'General collectible brands');
END
GO

-- Insert sample brands
IF NOT EXISTS (SELECT * FROM brands)
BEGIN
    INSERT INTO brands (name, description, country, established_year) VALUES
    ('Funko', 'Pop culture collectibles and toys', 'USA', 1998),
    ('Bandai', 'Japanese toy and entertainment company', 'Japan', 1950),
    ('Good Smile Company', 'Japanese figure manufacturer', 'Japan', 2001),
    ('Kotobukiya', 'Japanese model kit and figure company', 'Japan', 1963),
    ('Hot Toys', 'Hong Kong collectible figure manufacturer', 'Hong Kong', 2000);
END
GO

PRINT 'Brand database initialized successfully!';
