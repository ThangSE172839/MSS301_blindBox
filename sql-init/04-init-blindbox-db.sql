-- Initialize BlindBox Database Schema
USE MSS301Summer25DBBlindBox;
GO

-- Create blind_box_series table
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='blind_box_series' AND xtype='U')
BEGIN
    CREATE TABLE blind_box_series (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        name NVARCHAR(100) NOT NULL,
        description NVARCHAR(500),
        brand_id BIGINT NOT NULL,
        release_date DATE,
        price DECIMAL(10,2) NOT NULL,
        total_items INT NOT NULL,
        image_url NVARCHAR(255),
        is_active BIT NOT NULL DEFAULT 1,
        created_at DATETIME2 NOT NULL DEFAULT GETDATE(),
        updated_at DATETIME2 NOT NULL DEFAULT GETDATE()
    );
END
GO

-- Create blind_box_items table
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='blind_box_items' AND xtype='U')
BEGIN
    CREATE TABLE blind_box_items (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        series_id BIGINT NOT NULL,
        name NVARCHAR(100) NOT NULL,
        description NVARCHAR(500),
        rarity NVARCHAR(20) NOT NULL DEFAULT 'COMMON',
        probability DECIMAL(5,2) NOT NULL,
        image_url NVARCHAR(255),
        is_active BIT NOT NULL DEFAULT 1,
        created_at DATETIME2 NOT NULL DEFAULT GETDATE(),
        FOREIGN KEY (series_id) REFERENCES blind_box_series(id)
    );
END
GO

-- Create user_collections table
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='user_collections' AND xtype='U')
BEGIN
    CREATE TABLE user_collections (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        user_id BIGINT NOT NULL,
        item_id BIGINT NOT NULL,
        obtained_date DATETIME2 NOT NULL DEFAULT GETDATE(),
        quantity INT NOT NULL DEFAULT 1,
        FOREIGN KEY (item_id) REFERENCES blind_box_items(id)
    );
END
GO

-- Create orders table
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='orders' AND xtype='U')
BEGIN
    CREATE TABLE orders (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        user_id BIGINT NOT NULL,
        series_id BIGINT NOT NULL,
        quantity INT NOT NULL,
        total_price DECIMAL(10,2) NOT NULL,
        status NVARCHAR(20) NOT NULL DEFAULT 'PENDING',
        order_date DATETIME2 NOT NULL DEFAULT GETDATE(),
        FOREIGN KEY (series_id) REFERENCES blind_box_series(id)
    );
END
GO

-- Insert sample blind box series
IF NOT EXISTS (SELECT * FROM blind_box_series)
BEGIN
    INSERT INTO blind_box_series (name, description, brand_id, release_date, price, total_items) VALUES
    ('Anime Heroes Series 1', 'Popular anime characters collection', 1, '2024-01-15', 12.99, 12),
    ('Gaming Legends', 'Iconic video game characters', 2, '2024-02-01', 15.99, 10),
    ('Cute Animals Collection', 'Adorable animal figures', 3, '2024-03-01', 9.99, 8);
END
GO

-- Insert sample blind box items
IF NOT EXISTS (SELECT * FROM blind_box_items)
BEGIN
    INSERT INTO blind_box_items (series_id, name, description, rarity, probability) VALUES
    -- Anime Heroes Series 1
    (1, 'Naruto Uzumaki', 'Main protagonist from Naruto', 'COMMON', 15.00),
    (1, 'Sasuke Uchiha', 'Rival character from Naruto', 'COMMON', 15.00),
    (1, 'Luffy', 'Main character from One Piece', 'COMMON', 15.00),
    (1, 'Goku', 'Saiyan warrior from Dragon Ball', 'RARE', 8.00),
    (1, 'Golden Goku', 'Special golden variant', 'ULTRA_RARE', 2.00),

    -- Gaming Legends
    (2, 'Mario', 'Nintendo mascot', 'COMMON', 20.00),
    (2, 'Link', 'Hero of Hyrule', 'COMMON', 20.00),
    (2, 'Pikachu', 'Electric Pokemon', 'RARE', 10.00),
    (2, 'Master Chief', 'Halo protagonist', 'RARE', 10.00),
    (2, 'Solid Snake', 'Metal Gear hero', 'ULTRA_RARE', 5.00),

    -- Cute Animals Collection
    (3, 'Panda', 'Cute panda figure', 'COMMON', 25.00),
    (3, 'Cat', 'Adorable cat figure', 'COMMON', 25.00),
    (3, 'Dog', 'Loyal dog figure', 'COMMON', 25.00),
    (3, 'Unicorn', 'Magical unicorn', 'RARE', 15.00),
    (3, 'Golden Dragon', 'Legendary dragon', 'ULTRA_RARE', 10.00);
END
GO

PRINT 'BlindBox database initialized successfully!';
